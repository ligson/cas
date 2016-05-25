package org.ca.cas.cert.biz.core;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v1CertificateBuilder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.ca.cas.cert.biz.core.model.Extension;
import org.ca.cas.common.biz.KeyContainerBiz;
import org.ca.cas.common.model.KeyPairContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.*;
import java.util.Date;
import java.util.List;

/**
 * Created by ligson on 2016/5/9.
 */
@Component("makeCertBiz")
public class MakeCertBiz {

    @Resource
    private SignBiz signBiz;
    @Resource
    private KeyContainerBiz keyContainerBiz;

    private static CertificateFactory certificateFactory;

    private static Logger logger = LoggerFactory.getLogger(MakeCertBiz.class);

    @PostConstruct
    public void init() {
        try {
            certificateFactory = CertificateFactory.getInstance("X509");
        } catch (CertificateException e) {
            e.printStackTrace();
            logger.info("x509证书初始化失败!");
        }
    }

    private X509Certificate gen(String version, SubjectPublicKeyInfo
            subjectPublicKeyInfo, PrivateKey privateKey, X500Name issuer, X500Name subject, BigInteger serial, Date notBefore, Date notAfter, List<Extension> extensionList) {
        AlgorithmIdentifier signAlg = new AlgorithmIdentifier(PKCSObjectIdentifiers.sha1WithRSAEncryption, DERNull.INSTANCE);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ContentSigner signer = new ContentSigner() {
            @Override
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return signAlg;
            }

            @Override
            public OutputStream getOutputStream() {
                return bos;
            }

            @Override
            public byte[] getSignature() {
                return signBiz.sign(bos.toByteArray(), "SHA1withRSA", privateKey);
            }
        };
        X509CertificateHolder holder;
        if ("v1".equalsIgnoreCase(version)) {
            X509v1CertificateBuilder v1Builder = new X509v1CertificateBuilder(issuer, serial, notBefore, notAfter, subject, subjectPublicKeyInfo);
            holder = v1Builder.build(signer);
        } else {
            X509v3CertificateBuilder v3Builder = new X509v3CertificateBuilder(issuer, serial, notBefore, notAfter, subject, subjectPublicKeyInfo);
            if (extensionList != null && extensionList.size() > 0) {
                for (Extension extension : extensionList) {
                    v3Builder.addExtension(extension.getOid(), extension.isCritical(), extension.getValue());
                }
            }
            holder = v3Builder.build(signer);
        }
        try {
            X509Certificate certificate = (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(holder.getEncoded()));
            return certificate;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public X509Certificate genV3(SubjectPublicKeyInfo
                                         subjectPublicKeyInfo, PrivateKey privateKey, X500Name issuer, X500Name subject, BigInteger serial, Date notBefore, Date notAfter, List<Extension> extensionList) {
        return gen("v3", subjectPublicKeyInfo, privateKey, issuer, subject, serial,
                notBefore, notAfter, extensionList);
    }

    public X509Certificate gen(PublicKey publicKey, PrivateKey privateKey, X500Name issuer, X500Name subject, BigInteger serial, Date notBefore, Date notAfter, List<Extension> extensionList) {
        SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(publicKey.getEncoded());
        return genV3(subjectPublicKeyInfo, privateKey, issuer, subject,
                serial, notBefore, notAfter, extensionList);
    }

    public String genPkcs12(Certificate certificate, String pwd) {
        KeyPairContainer container = keyContainerBiz.getKeyPair(certificate.getPublicKey());
        if (container == null) {
            return null;
        }
        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(null, null);
            keyStore.setKeyEntry("user", container.getPrivateKey(), pwd.toCharArray(), new Certificate[]{certificate});
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            keyStore.store(byteArrayOutputStream, pwd.toCharArray());
            return Base64.encodeBase64String(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * 从base64字符串还原证书
     *
     * @param certBuf base64证书字符串
     * @return
     */
    public X509Certificate recoverCert(String certBuf) {
        return recoverCert(Base64.decodeBase64(certBuf));
    }

    /***
     * 从证书的encode还原正式
     *
     * @param certBuf 证书的二进制
     * @return
     */
    public X509Certificate recoverCert(byte[] certBuf) {
        try {
            Certificate certificate = certificateFactory.generateCertificate(new ByteArrayInputStream(certBuf));
            return (X509Certificate) certificate;
        } catch (CertificateException e) {
            e.printStackTrace();
            logger.error("证书还原失败!{}", e.getMessage());
            return null;
        }
    }

    public CertPath recoverCertPath(String certPathBuf) {
        return recoverCertPath(Base64.decodeBase64(certPathBuf));
    }

    public CertPath recoverCertPath(byte[] certPathBuf) {
        try {
            return certificateFactory.generateCertPath(new ByteArrayInputStream(certPathBuf), "PKCS7");
        } catch (CertificateException e) {
            e.printStackTrace();
            logger.error("证书链还原失败!{}", e.getMessage());
            return null;
        }
    }
}
