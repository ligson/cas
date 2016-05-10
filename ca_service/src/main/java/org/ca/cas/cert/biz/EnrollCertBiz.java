package org.ca.cas.cert.biz;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.ca.cas.cert.biz.core.MakeCertBiz;
import org.ca.common.cert.enums.CertStatus;
import org.ca.common.cert.enums.CertType;
import org.ca.cas.cert.domain.CertEntity;
import org.ca.cas.cert.dto.EnrollCertRequestDto;
import org.ca.cas.cert.dto.EnrollCertResponseDto;
import org.ca.cas.cert.enums.CertFailEnum;
import org.ca.cas.cert.service.CertService;
import org.ca.kms.key.api.KeyApi;
import org.ca.kms.key.dto.QueryKeyPairRequestDto;
import org.ca.kms.key.dto.QueryKeyPairResponseDto;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.facade.annotation.Api;
import org.ligson.fw.core.facade.base.result.Result;
import org.ligson.fw.string.encode.HashHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.*;
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
    private KeyApi keyApi;

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
        if (!requestDto.getSubjectDn().equals(requestDto.getIssueDn())) {
            entity = new CertEntity();
            entity.setIssuerDn(requestDto.getIssueDn());
            n = certService.countByAnd(entity);
            if (n > 0) {
                setFailureResult(CertFailEnum.E_BIZ_21003);
                return false;
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

        QueryKeyPairRequestDto queryKeyPairRequestDto = new QueryKeyPairRequestDto();
        queryKeyPairRequestDto.setKeyId(requestDto.getKeyId());
        Result<QueryKeyPairResponseDto> queryKeyPairResult = keyApi.queryKeyPair(queryKeyPairRequestDto);
        if (queryKeyPairResult.isSuccess()) {
            QueryKeyPairResponseDto queryKeyPairResponseDto = queryKeyPairResult.getData();
            X500Name issueDn = new X500Name(requestDto.getIssueDn());
            X500Name subjectDn = new X500Name(requestDto.getSubjectDn());

            Date startDate = requestDto.getStartDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.YEAR, 1);
            Date endDate = calendar.getTime();

            X509Certificate certificate = makeCertBiz.gen(queryKeyPairResponseDto.getPublicKey(), queryKeyPairResponseDto.getPrivateKey(), issueDn, subjectDn, entity.getId(), startDate, endDate, null);
            byte[] certBuf = null;
            byte[] certChainBuf = null;
            try {
                certBuf = certificate.getEncoded();
                certChainBuf = getCertChain(entity, certBuf);
            } catch (CertificateEncodingException e) {
                e.printStackTrace();
            }
            if (certificate != null && certBuf != null) {
                entity.setStatus(CertStatus.VALID.getCode());
                entity.setNotAfter(endDate);
                entity.setNotBefore(startDate);
                entity.setReqOverrideValidity(365);
                entity.setSerialNumber(entity.getId().toString());
                entity.setCertPin(requestDto.getCertPin());
                entity.setSignBuf(Base64.encodeBase64String(certBuf));
                entity.setSignBufP7(Base64.encodeBase64String(certChainBuf));

            } else {
                setFailureResult(CertFailEnum.E_BIZ_21004);
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
            tmpEntity = certService.findBy("subjectDn", certEntity.getIssuerDn());
            if (tmpEntity.getSubjectDn().equals(tmpEntity.getIssuerDn())) {
                try {
                    x509Certs.add(certificateFactory.generateCertificate(new ByteArrayInputStream(Base64.decodeBase64(tmpEntity.getSignBuf()))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
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
        responseDto.setSuccess(true);
        responseDto.setCertId(entity.getId());
        setSuccessResult();
        return true;
    }

    @Override
    public void after() {

    }
}
