package org.ca.cas.cert.biz;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.*;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.jce.PKCS10CertificationRequest;
import org.ca.cas.cert.vo.Cert;
import org.ca.cas.common.biz.MakeCertBiz;
import org.ca.cas.common.biz.model.Extension;
import org.ca.cas.common.biz.KeyContainerBiz;
import org.ca.cas.common.main.Startup;
import org.ca.cas.common.model.KeyPairContainer;
import org.ca.cas.user.domain.UserEntity;
import org.ca.cas.user.service.UserService;
import org.ca.common.cert.enums.CertStatus;
import org.ca.common.cert.enums.CertType;
import org.ca.cas.cert.domain.CertEntity;
import org.ca.cas.cert.dto.EnrollCertRequestDto;
import org.ca.cas.cert.dto.EnrollCertResponseDto;
import org.ca.cas.cert.enums.CertFailEnum;
import org.ca.cas.cert.service.CertService;
import org.ca.common.user.enums.UserRole;
import org.ca.common.utils.X500NameUtils;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.common.utils.IdUtils;
import org.ligson.fw.core.facade.annotation.Api;
import org.ligson.fw.core.facade.enums.FailureCodeEnum;
import org.ligson.fw.string.encode.HashHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.security.auth.x500.X500Principal;
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

    @Resource
    private ImportCaCertBiz importCaCertBiz;


    @Override
    public void before() {
    }

    @Override
    public Boolean paramCheck() {
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
        } else {
            context.setAttr("isRootCert", true);
        }
        return true;
    }

    private CertEntity certPreAdd() {
        CertEntity entity = new CertEntity();
        entity.setStatus(CertStatus.ENROLL.getCode());
        entity.setSubjectDn(requestDto.getSubjectDn());
        X500Name subject = X500NameUtils.subjectToX500Name(requestDto.getSubjectDn());
        try {
            entity.setSubjectDnHashMd5(DigestUtils.md5Hex(subject.getEncoded()));
        } catch (IOException e) {
            e.printStackTrace();
            setFailureResult(CertFailEnum.E_BIZ_21016);
            return null;
        }
        entity.setUserId(requestDto.getUserId());
        entity.setReqDate(new Date());
        entity.setCertPin(requestDto.getCertPin());
        entity.setCertType(CertType.SIGN.getCode());
        entity.setIssuerDn(requestDto.getIssueDn());
        X500Name issue = X500NameUtils.subjectToX500Name(requestDto.getIssueDn());
        try {
            entity.setIssuerDnHashMd5(DigestUtils.md5Hex(issue.getEncoded()));
        } catch (IOException e) {
            e.printStackTrace();
            setFailureResult(CertFailEnum.E_BIZ_21016);
            return null;
        }
        if (requestDto.getCsr() != null) {
            entity.setReqBuf(requestDto.getCsr());
            entity.setReqBufType(1);
        }
        certService.add(entity);
        return entity;
    }

    @Override
    public Boolean txnPreProcessing() {
        //CertEntity entity = certPreAdd();
        //if (entity == null) {
        //  return false;
        //}
        //CA证书密钥对
        PublicKey issuePublicKey;
        PrivateKey issuePrivateKey;
        KeyPairContainer issueCaContainer;
        KeyPairContainer subCaContainer = null;
        X500Name issueDn;

        //用户公钥或者CSR
        PublicKey userPublicKey = null;
        PKCS10CertificationRequest request = null;
        X500Name subjectDn;


        if (context.getAttr("isRootCert") != null) {
            //根证书，需要选择密钥对
            issueCaContainer = keyContainerBiz.getKeyPair(requestDto.getKeyId());
            issuePublicKey = issueCaContainer.getPublicKey();
            issuePrivateKey = issueCaContainer.getPrivateKey();
            userPublicKey = issueCaContainer.getPublicKey();
            subjectDn = X500NameUtils.subjectToX500Name(requestDto.getSubjectDn());
            issueDn = subjectDn;
            subCaContainer = issueCaContainer;
        } else {
            //下级证书，可能是二级CA证书，也能能是用户证书，二级CA证书有keyid
            CertEntity issueCertEntity = (CertEntity) context.getAttr("issueCert");
            X509Certificate issueCert = makeCertBiz.recoverCert(issueCertEntity.getSignBuf());
            if (issueCert == null) {
                setFailureResult(CertFailEnum.E_BIZ_21006);
                return false;
            }
            issueCaContainer = keyContainerBiz.getKeyPair(issueCert.getPublicKey());
            issuePublicKey = issueCaContainer.getPublicKey();
            issuePrivateKey = issueCaContainer.getPrivateKey();
            //下级CA证书
            if (requestDto.getKeyId() != null) {
                KeyPairContainer userkeyPairContainer = keyContainerBiz.getKeyPair(requestDto.getKeyId());
                userPublicKey = userkeyPairContainer.getPublicKey();
                subjectDn = X500NameUtils.subjectToX500Name(requestDto.getSubjectDn());
                subCaContainer = userkeyPairContainer;
            } else {
                //用户证书CSR不能为空
                byte[] buffer = Base64.decodeBase64(requestDto.getCsr());
                request = new PKCS10CertificationRequest(buffer);
                try {
                    try {
                        userPublicKey = request.getPublicKey();
                    } catch (Exception e) {
                        userPublicKey = request.getPublicKey(Startup.TOP_SM_PROVIDER.getName());
                    }
                    subjectDn = X500NameUtils.subjectToX500Name(request.getCertificationRequestInfo().getSubject().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    setFailureResult(CertFailEnum.E_BIZ_21008);
                    return false;
                }
            }
            issueDn = X500Name.getInstance(issueCert.getSubjectX500Principal().getEncoded());
        }

        Date startDate = requestDto.getStartDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.YEAR, 1);
        Date endDate = calendar.getTime();
        List<org.ca.cas.common.biz.model.Extension> extensions = new ArrayList<>();
        UserEntity userEntity = (UserEntity) context.getAttr("user");
        if (userEntity.getRole() == UserRole.CA_ADMIN.getCode()) {
            Extension extension = new Extension(org.bouncycastle.asn1.x509.X509Extension.basicConstraints, true, new BasicConstraints(2));
            extensions.add(extension);
        } else if (userEntity.getRole() == UserRole.RA_ADMIN.getCode()) {
            Extension extension = new Extension(org.bouncycastle.asn1.x509.X509Extension.basicConstraints, true, new BasicConstraints(1));
            extensions.add(extension);
        }

        BigInteger serialNum = IdUtils.randomBigInt();
        X509Certificate userCertObj = null;
        if (request != null) {
            //用户证书直接用csr
            userCertObj = makeCertBiz.genV3(request.getCertificationRequestInfo().getSubjectPublicKeyInfo(), issuePrivateKey, issueDn, subjectDn, serialNum, startDate, endDate, extensions);
        } else {
            //用户证书直接用csr
            userCertObj = makeCertBiz.gen(userPublicKey, issuePrivateKey, issueDn, subjectDn, serialNum, startDate, endDate, extensions);
        }
        if (userCertObj == null) {
            setFailureResult(CertFailEnum.E_BIZ_21004);
            return false;
        }

        //验证签名
        try {
            userCertObj.verify(issuePublicKey);
        } catch (Exception e) {
            e.printStackTrace();
            setFailureResult(CertFailEnum.E_BIZ_21007);
            return false;
        }


        CertEntity entity;
        if (subCaContainer != null) {
            entity = importCaCertBiz.addCertToEntity(userCertObj, subCaContainer, requestDto.getCertPin());
        } else {
            entity = importCaCertBiz.addCertToEntity(userCertObj, requestDto.getCsr(), requestDto.getCertPin());
        }
        if (entity == null) {
            setFailureResult(CertFailEnum.E_BIZ_21004);
            return false;
        }


        //组装证书链
        byte[] certBuf = null;
        byte[] certChainBuf = null;
        try {
            certBuf = userCertObj.getEncoded();
            entity.setSignBuf(Base64.encodeBase64String(certBuf));
            certChainBuf = getCertChain(entity, certBuf);
            entity.setSignBufP7(Base64.encodeBase64String(certChainBuf));
        } catch (Exception e) {
            e.printStackTrace();
            setFailureResult(CertFailEnum.E_BIZ_21004);
            certService.delete(entity);
            return false;
        }

        entity.setUserId(requestDto.getUserId());

        context.setAttr("entity", entity);
        return true;
    }


    public byte[] getCertChain(CertEntity certEntity, byte[] certBuf) throws Exception {
        List<Certificate> x509Certs = new ArrayList<>();
        x509Certs.add(makeCertBiz.recoverCert(certBuf));
        CertEntity tmpEntity = certService.findBy("subjectDn", certEntity.getIssuerDn());
        while (tmpEntity != null) {
            x509Certs.add(makeCertBiz.recoverCert(tmpEntity.getSignBuf()));
            if (tmpEntity.getSubjectDn().equals(tmpEntity.getIssuerDn())) {
                break;
            }
            tmpEntity = certService.findBy("subjectDn", tmpEntity.getIssuerDn());
        }
        CMSProcessableByteArray cmsProc = new CMSProcessableByteArray("hi".getBytes());

        CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
        gen.addCertificates(new JcaCertStore(x509Certs));
        return gen.generate(cmsProc, "BC").getEncoded();

    }

    @Override
    public Boolean persistence() {
        CertEntity entity = (CertEntity) context.getAttr("entity");
        certService.add(entity);
        responseDto.setCertId(entity.getId());
        setSuccessResult();
        return true;
    }

    @Override
    public void after() {

    }
}
