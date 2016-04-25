package org.ca.common.user.enums;

/**
 * Created by ligson on 2016/4/25.
 * 用户状态
 */
public enum UserState {
    VALID(1, "有效"), DISABLED(2, "禁用");
    private int code;
    private String msg;

    UserState(int code, String msg) {
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
