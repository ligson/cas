package org.ca.cas.common.biz;

import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x509.*;
import org.bouncycastle.jce.provider.X509CRLObject;
import org.ca.cas.cert.domain.CertEntity;
import org.ca.cas.cert.domain.CertRevokeEntity;
import org.ca.cas.common.model.KeyPairContainer;
import org.ca.ext.security.x509.X509Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.*;
import java.util.*;

/**
 * Created by ligson on 2016/5/17.
 */
@Component("makeCrlBiz")
public class MakeCrlBiz {
    @Resource
    private SignBiz signBiz;
    @Resource
    private KeyContainerBiz keyContainerBiz;
    @Resource
    private MakeCertBiz makeCertBiz;
    private static Logger logger = LoggerFactory.getLogger(MakeCrlBiz.class);

    public X509CRL genCrl(CertEntity issuer, List<CertRevokeEntity> revokedCerts, Date thisUpdate, Date nextUpdate) {
        // crl--------------------------------------------------------------

        V2TBSCertListGenerator v2CrlGen = new V2TBSCertListGenerator();
        // 本次更新日期
        v2CrlGen.setThisUpdate(new DERUTCTime(thisUpdate));
        // 下一次更新日期
        v2CrlGen.setNextUpdate(new DERUTCTime(nextUpdate));

        // 颁发者
        v2CrlGen.setIssuer(new X509Name(issuer.getSubjectDn()));
        // 将吊销证书的消息加入到CRL中

        for (CertRevokeEntity tmp : revokedCerts) {
            v2CrlGen.addCRLEntry(new DERInteger(new BigInteger(tmp.getCertSerialNumber())),
                    new org.bouncycastle.asn1.x509.Time(tmp.getCertRevokeDate()),
                    tmp.getCertRevokeReason(), new DERGeneralizedTime(tmp.getCertNotAfter()));
        }
        // 合成证书扩展项
        X509ExtensionsGenerator extensionsGen = new X509ExtensionsGenerator();


        X509Extensions x509extensions = extensionsGen.generate();
        v2CrlGen.setExtensions(x509extensions);
        //println(crlInfo.signAlg);
        AlgorithmIdentifier algSign;

        X509Certificate caCert = makeCertBiz.recoverCert(issuer.getSignBuf());
        if (caCert == null) {
            return null;
        }
        String keyAlgId = caCert.getPublicKey().getAlgorithm();
        //println(caSignAlg);
        String signAlg;
        DERObjectIdentifier oid;
        if (keyAlgId != null) {
            if ("1.2.156.10197.1.301".equalsIgnoreCase(keyAlgId) || "SM2".equalsIgnoreCase(keyAlgId)) {
                oid = new DERObjectIdentifier("1.2.156.10197.1.501");
                signAlg = "SM3withSM2";
            } else {
                oid = X509Utils.getAlgorithmOID("SHA1withRSA");
                signAlg = "SHA1withRSA";
            }

            // println("oid:"+oid);
            algSign = AlgorithmIdentifier.getInstance(oid);
            v2CrlGen.setSignature(algSign);
        } else {
            logger.error("签名算法不能为空");
            throw new IllegalStateException("签名算法不能为空!");
        }

        KeyPairContainer container = keyContainerBiz.getKeyPair(caCert.getPublicKey());
        //调用签名信息
        byte[] signBuffer;
        try {
            signBuffer = signBiz.sign(v2CrlGen.generateTBSCertList().getEncoded(), signAlg, container.getPrivateKey());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        ASN1EncodableVector asn1encodablevector = new ASN1EncodableVector();
        asn1encodablevector.add(v2CrlGen.generateTBSCertList());
        asn1encodablevector.add(algSign);
        //signInfo = caCert.certSignBuf.decodeBase64();
        asn1encodablevector.add(new DERBitString(signBuffer));

        X509CRL crl;
        try {
            crl = new X509CRLObject(new CertificateList(new DERSequence(
                    asn1encodablevector)));
            logger.info("组装CRL成功！");
        } catch (Exception e) {
            logger.error("组装CRL失败，原因:" + e.getMessage());
            return null;
        }
        return crl;
    }
}
