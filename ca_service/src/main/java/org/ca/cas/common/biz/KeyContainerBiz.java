package org.ca.cas.common.biz;

import org.ca.cas.common.utils.KeyStoreUtil;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

/**
 * Created by ligson on 2016/5/12.
 */
public class KeyContainerBiz implements InitializingBean {
    private static KeyStore keyStore;
    private static String type;
    private static File jks;
    private static String password;

    @Override
    public void afterPropertiesSet() throws Exception {
        KeyStoreUtil keyStoreUtil = new KeyStoreUtil();
        //keyStoreUtil.j
    }
}
