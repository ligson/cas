package org.ca.cas.cert.biz.core;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.jce.PKCS10CertificationRequest;
import org.ca.cas.common.model.KeyPairContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.security.auth.x500.X500Principal;

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
}
