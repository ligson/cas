package org.ca.cas.common.biz;

import org.ca.cas.common.main.Startup;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.Provider;
import java.security.Signature;

/**
 * Created by ligson on 2016/5/9.
 */
@Component("signBiz")
public class SignBiz {
    public byte[] sign(byte[] plainText, String signAlg, PrivateKey privateKey) {
        try {
            Provider provider = Startup.bouncyCastleProvider;
            if (signAlg.equals("SM3withSM2")) {
                provider = Startup.TOP_SM_PROVIDER;
            }
            Signature sign = Signature.getInstance(signAlg, provider);
            sign.initSign(privateKey);
            sign.update(plainText);
            return sign.sign();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
