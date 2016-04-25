package org.ca.common.cert.enums;

/**
 * Created by ligson on 2016/4/25.
 *
 * @author ligson
 *         证书状态
 */
public enum CertStatus {
    VALID(1, "有效"), REVOKE(2, "吊销"), SUSPEND(3, "挂起");
    private int code;
    private String msg;

    CertStatus(int code, String msg) {
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
