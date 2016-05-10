package org.ca.cas.cert.enums;

import org.ligson.fw.core.facade.enums.FailureCodeEnum;

/**
 * Created by ligson on 2016/5/6.
 */
public class CertFailEnum extends FailureCodeEnum {

    public static final CertFailEnum E_PARAM_11001 = new CertFailEnum("E_PARAM_11001", "证书主题被篡改");

    public static final CertFailEnum E_BIZ_21001 = new CertFailEnum("E_BIZ_21001", "证书主题已存在");
    public static final CertFailEnum E_BIZ_21002 = new CertFailEnum("E_BIZ_21002", "用户签名证书已存在");
    public static final CertFailEnum E_BIZ_21003 = new CertFailEnum("E_BIZ_21003", "证书颁发者不存在");
    public static final CertFailEnum E_BIZ_21004 = new CertFailEnum("E_BIZ_21004", "证书制作失败");


    /**
     * 默认构造
     *
     * @param code 错误代码
     * @param msg  错误信息
     */
    protected CertFailEnum(String code, String msg) {
        super(code, msg);
    }
}
