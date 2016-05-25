package org.ca.cas.common.biz;

import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.Signature;

/**
 * Created by ligson on 2016/5/9.
 */
@Component("signBiz")
public class SignBiz {
    public byte[] sign(byte[] plainText, String signAlg, PrivateKey privateKey) {
        try {
            Signature sign = Signature.getInstance(signAlg);
            sign.initSign(privateKey);
            sign.update(plainText);
            return sign.sign();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
