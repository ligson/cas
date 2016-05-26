package org.ca.cas.common.biz;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jce.PKCS10CertificationRequest;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.ca.cas.common.model.KeyPairContainer;
import org.ca.ext.security.x509.AlgorithmId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.security.auth.x500.X500Principal;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * Created by ligson on 2016/5/18.
 */
@Component("csrBiz")
public class CsrBiz {
    private static Logger logger = LoggerFactory.getLogger(CsrBiz.class);

    public String genCsr(KeyPairContainer container, X500Name subject) {
        try {
            //生成csr
            X500Principal principal = new X500Principal(subject.getEncoded());
            PKCS10CertificationRequest request = new PKCS10CertificationRequest("SHA1withRSA", principal, container.getPublicKey(), null, container.getPrivateKey());
            String code = "-----BEGIN CERTIFICATE REQUEST-----\n";
            String csr = Base64.encodeBase64String(request.getEncoded());
            code += csr;
            code += "\n-----END CERTIFICATE REQUEST-----\n";
            logger.debug("证书主题{}的CSR:{}", subject, code);
            return csr;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("CSR生成失败:{}", e.getMessage());
            return null;
        }
    }

    public String genCsr(X500Name subject, PublicKey publicKey, PrivateKey privateKey) {


        SubjectPublicKeyInfo publicKeyInfo = null;
        try {
            publicKeyInfo = new SubjectPublicKeyInfo(new AlgorithmIdentifier(AlgorithmId.get(publicKey.getAlgorithm()).getOID().toString()), publicKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PKCS10CertificationRequestBuilder builder = new PKCS10CertificationRequestBuilder(subject, publicKeyInfo);
        ContentSigner contentSigner = new ContentSigner() {
            @Override
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                try {
                    return new AlgorithmIdentifier(AlgorithmId.get(publicKey.getAlgorithm()).getOID().toString());
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public OutputStream getOutputStream() {
                return bos;
            }

            @Override
            public byte[] getSignature() {
                String alg = publicKey.getAlgorithm();
                String signAlg = "SHA1withRSA";
                if (alg.equals("SM2")) {
                    signAlg = "SM3withSM2";
                }
                try {
                    Signature signature = Signature.getInstance(signAlg);
                    signature.initSign(privateKey);
                    signature.update(bos.toByteArray());
                    return signature.sign();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return new byte[0];
            }
        };
        try {
            return Base64.encodeBase64String(builder.build(contentSigner).getEncoded());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public PKCS10CertificationRequest readCsr(String csr) {
        byte[] buffer = Base64.decodeBase64(csr);
        PKCS10CertificationRequest request = new PKCS10CertificationRequest(buffer);
        return request;
    }
}
