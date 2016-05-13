package org.ca.cas.cert.biz;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.*;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.ca.cas.cert.biz.core.MakeCertBiz;
import org.ca.cas.cert.biz.core.model.*;
import org.ca.cas.cert.biz.core.model.Extension;
import org.ca.cas.common.biz.KeyContainerBiz;
import org.ca.cas.common.model.KeyPairContainer;
import org.ca.cas.user.domain.UserEntity;
import org.ca.cas.user.enums.UserFailCodeEnum;
import org.ca.cas.user.service.UserService;
import org.ca.cas.user.vo.User;
import org.ca.common.cert.enums.CertStatus;
import org.ca.common.cert.enums.CertType;
import org.ca.cas.cert.domain.CertEntity;
import org.ca.cas.cert.dto.EnrollCertRequestDto;
import org.ca.cas.cert.dto.EnrollCertResponseDto;
import org.ca.cas.cert.enums.CertFailEnum;
import org.ca.cas.cert.service.CertService;
import org.ca.common.user.enums.UserRole;
import org.ca.common.utils.KeyPairUtils;
import org.ca.common.utils.X500NameUtils;
import org.ca.ext.security.util.CertUtil;
import org.ca.kms.key.api.KeyApi;
import org.ca.kms.key.dto.*;
import org.ca.kms.key.enums.KeyFailEnum;
import org.ca.kms.key.enums.KeyStatus;
import org.ca.kms.key.vo.Key;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.facade.annotation.Api;
import org.ligson.fw.core.facade.base.result.Result;
import org.ligson.fw.core.facade.enums.FailureCodeEnum;
import org.ligson.fw.string.encode.HashHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ligson on 2016/5/6.
 */
@Component("enrollCertBiz")
@Api(name = "申请证书接口")
public class EnrollCertBiz extends AbstractBiz<EnrollCertRequestDto, EnrollCertResponseDto> {
    @Resource
    private CertService certService;

    @Resource
    private MakeCertBiz makeCertBiz;

    @Resource
    private KeyContainerBiz keyContainerBiz;

    @Resource
    private UserService userService;

    private static CertificateFactory certificateFactory;

    @Override
    public void before() {
        try {
            certificateFactory = CertificateFactory.getInstance("x509");
        } catch (CertificateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean paramCheck() {
        if (!HashHelper.md5(requestDto.getSubjectDn()).equals(requestDto.getSubjectDnHashMd5())) {
            setFailureResult(CertFailEnum.E_PARAM_11001);
            return false;
        }
        return true;
    }

    @Override
    public Boolean bizCheck() {
        CertEntity entity = new CertEntity();
        entity.setSubjectDn(requestDto.getSubjectDn());
        entity.setStatus(CertStatus.VALID.getCode());
        entity.setCertType(CertType.SIGN.getCode());
        long n = certService.countByAnd(entity);
        if (n > 0) {
            setFailureResult(CertFailEnum.E_BIZ_21001);
            return false;
        }
        entity.setSubjectDn(null);
        entity.setStatus(null);
        entity.setUserId(requestDto.getUserId());
        n = certService.countByAnd(entity);
        if (n > 0) {
            setFailureResult(CertFailEnum.E_BIZ_21002);
            return false;
        }
        UserEntity user = userService.get(requestDto.getUserId());
        if (user == null) {
            setFailureResult(FailureCodeEnum.E_BIZ_20003);
            return false;
        } else {
            context.setAttr("user", user);
        }
        if (!requestDto.getSubjectDn().equals(requestDto.getIssueDn())) {
            entity = certService.findBy("subjectDn", requestDto.getIssueDn());
            if (entity == null) {
                setFailureResult(CertFailEnum.E_BIZ_21003);
                return false;
            } else {
                context.setAttr("issueCert", entity);
            }
        }
        return true;
    }

    @Override
    public Boolean txnPreProcessing() {
        CertEntity entity = new CertEntity();
        entity.setStatus(CertStatus.ENROLL.getCode());
        entity.setSubjectDn(requestDto.getSubjectDn());
        entity.setSubjectDnHashMd5(requestDto.getSubjectDnHashMd5());
        entity.setUserId(requestDto.getUserId());
        entity.setReqDate(new Date());
        entity.setCertPin(requestDto.getCertPin());
        entity.setCertType(CertType.SIGN.getCode());
        entity.setIssuerDn(requestDto.getIssueDn());
        entity.setIssuerDnHashMd5(requestDto.getIssueDnHashMd5());
        certService.add(entity);

        KeyQueryRequestDto keyQueryRequestDto = new KeyQueryRequestDto();
        keyQueryRequestDto.setId(requestDto.getKeyId());
        keyQueryRequestDto.setPageAble(false);
        KeyPairContainer keyPairContainer = keyContainerBiz.getKeyPair(requestDto.getKeyId());
        if (keyPairContainer != null) {
            PublicKey publicKey = keyPairContainer.getPublicKey();
            //签名私钥
            PrivateKey signPrivateKey = null;
            //颁发者公钥
            PublicKey signPublicKey = publicKey;
            if (!requestDto.getSubjectDn().equals(requestDto.getIssueDn())) {
                CertEntity issueCert = (CertEntity) context.getAttr("issueCert");
                try {
                    Certificate certificate = certificateFactory.generateCertificate(new ByteArrayInputStream(Base64.decodeBase64(issueCert.getSignBuf())));
                    KeyPairContainer issueContainer = keyContainerBiz.getKeyPair(certificate.getPublicKey());
                    if (issueContainer != null) {
                        signPrivateKey = issueContainer.getPrivateKey();
                        signPublicKey = issueContainer.getPublicKey();
                    } else {
                        setFailureResult(CertFailEnum.E_BIZ_21006);
                        certService.delete(entity);
                        return false;
                    }
                } catch (CertificateException e) {
                    e.printStackTrace();
                    setFailureResult(CertFailEnum.E_BIZ_21005);
                    certService.delete(entity);
                    return false;
                }
            } else {
                signPrivateKey = keyPairContainer.getPrivateKey();
            }

            X500Name issueDn = X500NameUtils.subjectToX500Name(requestDto.getIssueDn());
            X500Name subjectDn = X500NameUtils.subjectToX500Name(requestDto.getSubjectDn());

            Date startDate = requestDto.getStartDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.YEAR, 1);
            Date endDate = calendar.getTime();
            List<org.ca.cas.cert.biz.core.model.Extension> extensions = new ArrayList<>();
            UserEntity userEntity = (UserEntity) context.getAttr("user");
            if (userEntity.getRole() == UserRole.CA_ADMIN.getCode()) {
                Extension extension = new Extension(org.bouncycastle.asn1.x509.X509Extension.basicConstraints, true, new BasicConstraints(2));
                extensions.add(extension);
            } else if (userEntity.getRole() == UserRole.RA_ADMIN.getCode()) {
                Extension extension = new Extension(org.bouncycastle.asn1.x509.X509Extension.basicConstraints, true, new BasicConstraints(1));
                extensions.add(extension);
            }
            X509Certificate certificate = makeCertBiz.gen(publicKey, signPrivateKey, issueDn, subjectDn, new BigInteger(entity.getId()), startDate, endDate, extensions);
            try {
                certificate.verify(signPublicKey);
            } catch (Exception e) {
                e.printStackTrace();
                setFailureResult(CertFailEnum.E_BIZ_21007);
                certService.delete(entity);
                return false;
            }
            byte[] certBuf = null;
            byte[] certChainBuf = null;
            try {
                certBuf = certificate.getEncoded();
                entity.setSignBuf(Base64.encodeBase64String(certBuf));
                certChainBuf = getCertChain(entity, certBuf);
            } catch (CertificateEncodingException e) {
                e.printStackTrace();
            }
            if (certificate != null && certBuf != null) {
                entity.setStatus(CertStatus.VALID.getCode());
                entity.setNotAfter(endDate);
                entity.setNotBefore(startDate);
                entity.setReqOverrideValidity(365);
                entity.setSerialNumber(entity.getId());
                entity.setCertPin(requestDto.getCertPin());

                entity.setSignBufP7(Base64.encodeBase64String(certChainBuf));

            } else {
                setFailureResult(CertFailEnum.E_BIZ_21004);
                certService.delete(entity);
                return false;
            }
        }


        context.setAttr("entity", entity);
        return true;
    }

    private byte[] getCertChain(CertEntity certEntity, byte[] certBuf) {
        List<Certificate> x509Certs = new ArrayList<>();
        try {
            x509Certs.add(certificateFactory.generateCertificate(new ByteArrayInputStream(certBuf)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        CertEntity tmpEntity = certService.findBy("subjectDn", certEntity.getIssuerDn());
        while (tmpEntity != null) {
            try {
                x509Certs.add(certificateFactory.generateCertificate(new ByteArrayInputStream(Base64.decodeBase64(tmpEntity.getSignBuf()))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (tmpEntity.getSubjectDn().equals(tmpEntity.getIssuerDn())) {
                break;
            }
            tmpEntity = certService.findBy("subjectDn", tmpEntity.getIssuerDn());
        }
        CMSProcessableByteArray cmsProc = new CMSProcessableByteArray("hi".getBytes());

        CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
        try {
            gen.addCertificates(new JcaCertStore(x509Certs));
            return gen.generate(cmsProc, "BC").getEncoded();
        } catch (CMSException e) {
            e.printStackTrace();
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Boolean persistence() {
        CertEntity entity = (CertEntity) context.getAttr("entity");
        certService.update(entity);
        responseDto.setCertId(entity.getId());
        setSuccessResult();
        return true;
    }

    @Override
    public void after() {

    }
}
