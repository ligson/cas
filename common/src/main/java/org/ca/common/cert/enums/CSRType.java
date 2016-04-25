package org.ca.common.cert.enums;

/**
 * Created by ligson on 2016/4/25.
 *
 * @author ligson
 *         csr类型
 */
public enum CSRType {
    PKCS10(1, "PKCS10");
    private int code;
    private String msg;

    CSRType(int code, String msg) {
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
