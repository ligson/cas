package org.ca.cas.common.main;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

/**
 * Created by ligson on 2016/4/27.
 */
public class Startup {
    public static BouncyCastleProvider bouncyCastleProvider = new BouncyCastleProvider();

    static {
        Security.addProvider(bouncyCastleProvider);
    }

    public static void main(String[] args) {
        com.alibaba.dubbo.container.Main.main(args);
    }
}
