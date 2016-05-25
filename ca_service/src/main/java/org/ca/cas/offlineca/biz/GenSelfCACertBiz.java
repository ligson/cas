package org.ca.cas.offlineca.biz;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.jce.PKCS10CertificationRequest;
import org.ca.cas.cert.biz.EnrollCertBiz;
import org.ca.cas.common.biz.MakeCertBiz;
import org.ca.cas.common.biz.model.Extension;
import org.ca.cas.cert.enums.CertFailEnum;
import org.ca.cas.common.biz.KeyContainerBiz;
import org.ca.cas.common.model.KeyPairContainer;
import org.ca.cas.offlineca.domain.OfflineCertEntity;
import org.ca.cas.offlineca.dto.GenSelfCertRequestDto;
import org.ca.cas.offlineca.dto.GenSelfCertResponseDto;
import org.ca.cas.offlineca.service.OfflineCertService;
import org.ca.common.utils.X500NameUtils;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.common.utils.IdUtils;
import org.ligson.fw.core.facade.annotation.Api;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ligson on 2016/5/18.
 */
@Component("genSelfCACertBiz")
@Api(name = "创建自签名证书接口")
public class GenSelfCACertBiz extends AbstractBiz<GenSelfCertRequestDto, GenSelfCertResponseDto> {
    @Resource
    private KeyContainerBiz keyContainerBiz;
    @Resource
    private OfflineCertService offlineCertService;
    @Resource
    private MakeCertBiz makeCertBiz;

    private static CertificateFactory certificateFactory;

    @Override
    public void before() {
        try {
            certificateFactory = CertificateFactory.getInstance("X509");
        } catch (CertificateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean paramCheck() {
        return null;
    }

    @Override
    public Boolean bizCheck() {
        OfflineCertEntity caCert = offlineCertService.findBy("subjectDn", requestDto.getSubjectDn());
        if (caCert != null) {
            setFailureResult(CertFailEnum.E_BIZ_21001);
            return false;
        }
        KeyPairContainer keyContainer = keyContainerBiz.getKeyPair(requestDto.getAliase());
        if (keyContainer == null) {
            setFailureResult(CertFailEnum.E_BIZ_21014);
            return false;
        } else {
            context.setAttr("keyContainer", keyContainer);
        }
        return true;
    }

    @Override
    public Boolean txnPreProcessing() {
        KeyPairContainer container = context.getAttr("keyContainer", KeyPairContainer.class);
        X500Name subject = X500NameUtils.subjectToX500Name(requestDto.getSubjectDn());
        String serialNum = IdUtils.randomIntString();
        Date notBefore= new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(notBefore);
        calendar.add(Calendar.YEAR, 1);
        Date notAfter = calendar.getTime();
        List<Extension> extensions = new ArrayList<>();
        Extension extension = new Extension(org.bouncycastle.asn1.x509.X509Extension.basicConstraints, false, new BasicConstraints(4));
        extensions.add(extension);
        X509Certificate certificate = makeCertBiz.gen(container.getPublicKey(), container.getPrivateKey(), subject, subject, new BigInteger(serialNum), notBefore, notAfter, extensions);
        if (certificate == null) {
            setFailureResult(CertFailEnum.E_BIZ_21004);
            return false;
        }
        try {
            certificate.verify(container.getPublicKey());
        } catch (Exception e) {
            e.printStackTrace();
            setFailureResult(CertFailEnum.E_BIZ_21007);
            return false;
        }
        PKCS10CertificationRequest csr = EnrollCertBiz.genCsr(container, subject);
        if (csr == null) {
            setFailureResult(CertFailEnum.E_BIZ_21004);
            return false;
        }

        byte[] chainBuf;
        try {
            chainBuf = getCertChain(certificate.getEncoded());
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
            setFailureResult(CertFailEnum.E_BIZ_21015);
            return false;
        }
        if (chainBuf == null) {
            setFailureResult(CertFailEnum.E_BIZ_21015);
            return false;
        }

        OfflineCertEntity offlineCertEntity = new OfflineCertEntity();
        offlineCertEntity.setReqBufType(1);
        offlineCertEntity.setReqBuf(Base64.encodeBase64String(csr.getEncoded()));
        offlineCertEntity.setIssuerDn(requestDto.getSubjectDn());
        offlineCertEntity.setSubjectDn(requestDto.getSubjectDn());
        try {
            offlineCertEntity.setIssuerDnHashMd5(DigestUtils.md5Hex(subject.getEncoded()));
        } catch (IOException e) {
            e.printStackTrace();
            setFailureResult(CertFailEnum.E_BIZ_21004);
            return false;
        }
        offlineCertEntity.setSubjectDnHashMd5(offlineCertEntity.getIssuerDnHashMd5());
        offlineCertEntity.setNotAfter(notAfter);
        offlineCertEntity.setNotBefore(notBefore);
        offlineCertEntity.setSerialNumber(serialNum);
        offlineCertEntity.setSignBuf(Base64.encodeBase64String(certificate.getSignature()));
        offlineCertEntity.setCertChainBuf(Base64.encodeBase64String(chainBuf));
        try {
            offlineCertEntity.setCertBuf(Base64.encodeBase64String(certificate.getEncoded()));
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
            setFailureResult(CertFailEnum.E_BIZ_21017);
            return false;
        }
        offlineCertEntity.setSignDate(new Date());
        context.setAttr("entity", offlineCertEntity);
        return true;
    }

    private byte[] getCertChain(byte[] certBuf) {
        List<Certificate> x509Certs = new ArrayList<>();
        try {
            x509Certs.add(certificateFactory.generateCertificate(new ByteArrayInputStream(certBuf)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        CMSProcessableByteArray cmsProc = new CMSProcessableByteArray("hi".getBytes());
        CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
        try {
            gen.addCertificates(new JcaCertStore(x509Certs));
            return gen.generate(cmsProc, "BC").getEncoded();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Boolean persistence() {
        OfflineCertEntity offlineCertEntity = context.getAttr("entity", OfflineCertEntity.class);
        offlineCertService.add(offlineCertEntity);
        setSuccessResult();
        return true;
    }

    @Override
    public void after() {

    }
}
