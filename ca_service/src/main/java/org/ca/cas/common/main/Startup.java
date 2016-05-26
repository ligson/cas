package org.ca.cas.common.main;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.ca.ext.security.sm.TopSMProvider;

import java.security.Security;

/**
 * Created by ligson on 2016/4/27.
 */
public class Startup {
    public static final BouncyCastleProvider bouncyCastleProvider = new BouncyCastleProvider();
    public static final TopSMProvider TOP_SM_PROVIDER = new TopSMProvider();


    static {
        Security.addProvider(bouncyCastleProvider);
        Security.addProvider(TOP_SM_PROVIDER);
    }

    public static void main(String[] args) {
        com.alibaba.dubbo.container.Main.main(args);
    }
}
