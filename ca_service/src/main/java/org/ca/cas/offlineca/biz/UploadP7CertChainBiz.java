package org.ca.cas.offlineca.biz;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.asn1.x500.X500Name;
import org.ca.cas.common.biz.CsrBiz;
import org.ca.cas.common.biz.MakeCertBiz;
import org.ca.cas.cert.enums.CertFailEnum;
import org.ca.cas.common.biz.KeyContainerBiz;
import org.ca.cas.common.model.KeyPairContainer;
import org.ca.cas.offlineca.domain.OfflineCertEntity;
import org.ca.cas.offlineca.dto.UploadP7CertChainRequestDto;
import org.ca.cas.offlineca.dto.UploadP7CertChainResponseDto;
import org.ca.cas.offlineca.service.OfflineCertService;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.facade.annotation.Api;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * Created by ligson on 2016/5/18.
 */
@Component("uploadP7CertChainBiz")
@Api(name = "上传P7 CA证书链接口")
public class UploadP7CertChainBiz extends AbstractBiz<UploadP7CertChainRequestDto, UploadP7CertChainResponseDto> {

    @Resource
    private OfflineCertService offlineCertService;
    @Resource
    private GenSubCaCertBiz genSubCaCertBiz;
    @Resource
    private KeyContainerBiz keyContainerBiz;
    @Resource
    private CsrBiz csrBiz;
    @Resource
    private MakeCertBiz makeCertBiz;

    @Override
    public void before() {
    }

    @Override
    public Boolean paramCheck() {
        return null;
    }

    @Override
    public Boolean bizCheck() {
        CertPath certPath = makeCertBiz.recoverCertPath(Base64.decodeBase64(requestDto.getP7File()));
        if (certPath == null) {
            setFailureResult(CertFailEnum.E_BIZ_21018);
            return false;
        }
        Map<X509Certificate, KeyPairContainer> certMap = new HashMap<>();
        List<? extends Certificate> certificates = certPath.getCertificates();
        List<java.security.cert.X509Certificate> x509Certificates = new ArrayList<>();
        for (Certificate certificate : certificates) {
            java.security.cert.X509Certificate x509Certificate = (java.security.cert.X509Certificate) certificate;
            String subjectDnHash = DigestUtils.md5Hex(x509Certificate.getSubjectX500Principal().getEncoded());
            OfflineCertEntity caCert = offlineCertService.findBy("subjectDnHashMd5", subjectDnHash);
            if (caCert == null) {
                x509Certificates.add(x509Certificate);
            }
            KeyPairContainer container = keyContainerBiz.getKeyPair(x509Certificate.getPublicKey());
            if (container == null) {
                setFailureResult(CertFailEnum.E_BIZ_21014);
                return false;
            }
            certMap.put(x509Certificate, container);
        }
        if (x509Certificates.size() == 0) {
            setFailureResult(CertFailEnum.E_BIZ_21001);
            return false;
        }

        context.setAttr("certPath", certPath);
        context.setAttr("x509Certificates", x509Certificates);
        context.setAttr("certMap", certMap);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Boolean txnPreProcessing() {
        //CertPath certPath = (CertPath) context.getAttr("certPath");
        List<java.security.cert.X509Certificate> x509Certificates = (List<X509Certificate>) context.getAttr("x509Certificates");
        Map<X509Certificate, KeyPairContainer> certMap = (Map<X509Certificate, KeyPairContainer>) context.getAttr("certMap");
        List<OfflineCertEntity> certEntityList = new ArrayList<>();
        for (java.security.cert.X509Certificate x509Certificate : x509Certificates) {
            X500Name subject = X500Name.getInstance(x509Certificate.getSubjectX500Principal().getEncoded());
            X500Name issuer = X500Name.getInstance(x509Certificate.getIssuerX500Principal().getEncoded());
            String issuerHash = null;
            try {
                issuerHash = DigestUtils.md5Hex(issuer.getEncoded());
            } catch (IOException e) {
                e.printStackTrace();
                setFailureResult(CertFailEnum.E_BIZ_21003);
                return false;
            }
            String subjectHash = null;
            try {
                subjectHash = DigestUtils.md5Hex(subject.getEncoded());
            } catch (IOException e) {
                setFailureResult(CertFailEnum.E_BIZ_21003);
                e.printStackTrace();
                return false;
            }

            OfflineCertEntity certEntity = new OfflineCertEntity();
            certEntity.setSerialNumber(x509Certificate.getSerialNumber().toString());
            certEntity.setSignBuf(Base64.encodeBase64String(x509Certificate.getSignature()));
            try {
                certEntity.setCertBuf(Base64.encodeBase64String(x509Certificate.getEncoded()));
            } catch (CertificateEncodingException e) {
                setFailureResult(CertFailEnum.E_BIZ_21017);
                e.printStackTrace();
                System.out.println(e.getMessage());
                return false;
            }

            byte[] chainBuf = genSubCaCertBiz.getCertChain(issuerHash, x509Certificate);
            if (chainBuf == null) {
                setFailureResult(CertFailEnum.E_BIZ_21015);
                return false;
            }
            certEntity.setCertChainBuf(Base64.encodeBase64String(chainBuf));
            certEntity.setSubjectDn(x509Certificate.getSubjectDN().getName());
            certEntity.setIssuerDn(x509Certificate.getIssuerDN().getName());
            certEntity.setSubjectDnHashMd5(subjectHash);
            certEntity.setIssuerDnHashMd5(issuerHash);
            certEntity.setSignDate(new Date());
            certEntity.setNotAfter(x509Certificate.getNotAfter());
            certEntity.setNotBefore(x509Certificate.getNotBefore());
            KeyPairContainer keyPairContainer = certMap.get(x509Certificate);
            String csr = csrBiz.genCsr(keyPairContainer, subject);
            certEntity.setReqBuf(csr);
            certEntity.setReqBufType(1);
            certEntityList.add(certEntity);
        }
        context.setAttr("certEntityList", certEntityList);
        return true;
    }


    @Override
    public Boolean persistence() {
        List<OfflineCertEntity> certEntityList = (List<OfflineCertEntity>) context.getAttr("certEntityList");
        for (OfflineCertEntity offlineCertEntity : certEntityList) {
            offlineCertService.add(offlineCertEntity);
        }
        setSuccessResult();
        return true;
    }

    @Override
    public void after() {

    }
}
