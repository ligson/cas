package org.ca.cas.offlineca.biz;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.jce.PKCS10CertificationRequest;
import org.ca.cas.cert.biz.core.MakeCertBiz;
import org.ca.cas.cert.biz.core.model.Extension;
import org.ca.cas.cert.enums.CertFailEnum;
import org.ca.cas.common.biz.KeyContainerBiz;
import org.ca.cas.common.model.KeyPairContainer;
import org.ca.cas.offlineca.domain.OfflineCertEntity;
import org.ca.cas.offlineca.dto.GenSubCaCertRequestDto;
import org.ca.cas.offlineca.dto.GenSubCaCertResponseDto;
import org.ca.cas.offlineca.service.OfflineCertService;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.common.utils.IdUtils;
import org.ligson.fw.core.facade.annotation.Api;
import org.ligson.fw.string.encode.HashHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.PublicKey;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ligson on 2016/5/18.
 */
@Component("genSubCaCertBiz")
@Api(name = "颁发下级CA证书接口")
public class GenSubCaCertBiz extends AbstractBiz<GenSubCaCertRequestDto, GenSubCaCertResponseDto> {

    @Resource
    private OfflineCertService offlineCertService;
    @Resource
    private KeyContainerBiz keyContainerBiz;
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
        byte[] csrBuffer = Base64.decodeBase64(requestDto.getCsr());
        PKCS10CertificationRequest request = new PKCS10CertificationRequest(csrBuffer);
        try {
            PublicKey publicKey = request.getPublicKey();
            context.setAttr("publicKey", publicKey);
            context.setAttr("csr", request);
        } catch (Exception e) {
            e.printStackTrace();
            setFailureResult(CertFailEnum.E_BIZ_21008);
            return false;
        }
        X509Name subject = request.getCertificationRequestInfo().getSubject();
        try {
            String subjectHash = DigestUtils.md5Hex(subject.getEncoded());
            if (offlineCertService.findBy("subjectDnHashMd5", subjectHash) != null) {
                setFailureResult(CertFailEnum.E_BIZ_21001);
                return false;
            } else {
                context.setAttr("subjectHash", subjectHash);
            }
        } catch (IOException e) {
            e.printStackTrace();
            setFailureResult(CertFailEnum.E_BIZ_21016);
            return false;
        }
        try {
            X500Name x500Name = X500Name.getInstance(subject.getEncoded());
            context.setAttr("subject", x500Name);
        } catch (IOException e) {
            e.printStackTrace();
            setFailureResult(CertFailEnum.E_BIZ_21016);
        }


        OfflineCertEntity issueCert = offlineCertService.get(requestDto.getIssuerCertId());
        if (issueCert == null) {
            setFailureResult(CertFailEnum.E_BIZ_21003);
            return false;
        } else {
            context.setAttr("issueCert", issueCert);
        }
        return true;
    }

    @Override
    public Boolean txnPreProcessing() {
        X500Name subject = context.getAttr("subject", X500Name.class);
        String subjectHash = context.getAttr("subjectHash", String.class);
        OfflineCertEntity issuerCert = context.getAttr("issueCert", OfflineCertEntity.class);
        PKCS10CertificationRequest certificationRequest = context.getAttr("csr", PKCS10CertificationRequest.class);
        X509Certificate issuerCertObj;
        try {
            issuerCertObj = (X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(Base64.decodeBase64(issuerCert.getCertBuf())));
        } catch (CertificateException e) {
            e.printStackTrace();
            setFailureResult(CertFailEnum.E_BIZ_21005);
            return false;
        }
        X500Name issuer = X500Name.getInstance(issuerCertObj.getSubjectX500Principal().getEncoded());
        KeyPairContainer issuerContainer = keyContainerBiz.getKeyPair(issuerCertObj.getPublicKey());

        String serialNum = IdUtils.randomIntString();
        Date notAfter = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(notAfter);
        calendar.add(Calendar.YEAR, 1);
        Date notBefore = calendar.getTime();
        List<Extension> extensions = new ArrayList<>();
        Extension extension = new Extension(org.bouncycastle.asn1.x509.X509Extension.basicConstraints, false, new BasicConstraints(4));
        extensions.add(extension);
        X509Certificate certificate = makeCertBiz.genV3(certificationRequest.getCertificationRequestInfo().getSubjectPublicKeyInfo(), issuerContainer.getPrivateKey(), issuer, subject, new BigInteger(serialNum), notBefore, notAfter, extensions);
        if (certificate == null) {
            setFailureResult(CertFailEnum.E_BIZ_21004);
            return false;
        }
        try {
            certificate.verify(issuerContainer.getPublicKey());
        } catch (Exception e) {
            setFailureResult(CertFailEnum.E_BIZ_21007);
            e.printStackTrace();
            return false;
        }

        byte[] chainBuf = getCertChain(issuerCert.getSubjectDnHashMd5(), certificate);
        if (chainBuf == null) {
            setFailureResult(CertFailEnum.E_BIZ_21015);
            return false;
        }

        OfflineCertEntity offlineCertEntity = new OfflineCertEntity();
        offlineCertEntity.setReqBufType(1);
        offlineCertEntity.setReqBuf(Base64.encodeBase64String(certificationRequest.getEncoded()));
        offlineCertEntity.setIssuerDn(issuerCert.getSubjectDn());
        offlineCertEntity.setSubjectDn(certificationRequest.getCertificationRequestInfo().getSubject().toString());
        offlineCertEntity.setIssuerDnHashMd5(issuerCert.getSubjectDnHashMd5());
        offlineCertEntity.setSubjectDnHashMd5(subjectHash);
        offlineCertEntity.setNotAfter(notAfter);
        offlineCertEntity.setNotBefore(notBefore);
        offlineCertEntity.setSerialNumber(serialNum);
        offlineCertEntity.setSignBuf(Base64.encodeBase64String(certificate.getSignature()));
        offlineCertEntity.setCertChainBuf(Base64.encodeBase64String(chainBuf));
        try {
            offlineCertEntity.setCertBuf(Base64.encodeBase64String(certificate.getEncoded()));
        } catch (CertificateEncodingException e) {
            setFailureResult(CertFailEnum.E_BIZ_21017);
            e.printStackTrace();
            return false;
        }
        offlineCertEntity.setSignDate(new Date());
        context.setAttr("entity", offlineCertEntity);
        return true;
    }

    public byte[] getCertChain(String issuerHashMd5, X509Certificate cert) {
        List<Certificate> x509Certs = new ArrayList<>();
        x509Certs.add(cert);
        OfflineCertEntity tmpEntity = offlineCertService.findBy("subjectDnHashMd5", issuerHashMd5);
        while (tmpEntity != null) {
            try {
                x509Certs.add(certificateFactory.generateCertificate(new ByteArrayInputStream(Base64.decodeBase64(tmpEntity.getCertBuf()))));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            if (tmpEntity.getSubjectDnHashMd5().equals(tmpEntity.getIssuerDnHashMd5())) {
                break;
            }
            tmpEntity = offlineCertService.findBy("subjectDnHashMd5", tmpEntity.getIssuerDnHashMd5());
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
