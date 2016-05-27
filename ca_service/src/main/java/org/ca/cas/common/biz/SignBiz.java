package org.ca.cas.common.biz;

import org.ca.cas.common.main.Startup;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * Created by ligson on 2016/5/9.
 */
@Component("signBiz")
public class SignBiz {
    public byte[] sign(byte[] plainText, String signAlg, PrivateKey privateKey) {
        try {
            Signature sign;
            if (signAlg.equals("SM3withSM2")) {
                sign = Signature.getInstance(signAlg, Startup.TOP_SM_PROVIDER);
            } else {
                sign = Signature.getInstance(signAlg);
            }
            sign.initSign(privateKey);
            sign.update(plainText);
            return sign.sign();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean verify(byte[] signBuffer, byte[] plainText, String signAlg, PublicKey publicKey) {
        try {
            Signature sign;
            if (signAlg.equals("SM3withSM2")) {
                sign = Signature.getInstance(signAlg, Startup.TOP_SM_PROVIDER);
            } else {
                sign = Signature.getInstance(signAlg);
            }
            sign.initVerify(publicKey);
            sign.update(plainText);
            return sign.verify(signBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
