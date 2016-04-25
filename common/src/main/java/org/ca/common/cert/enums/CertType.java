package org.ca.common.cert.enums;

/**
 * Created by ligson on 2016/4/25.
 */
public enum CertType {
    SIGN(1, "签名证书"), ENCRYPT(2, "加密证书");
    private int code;
    private String msg;

    CertType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
