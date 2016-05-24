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
    public static final CertFailEnum E_BIZ_21005 = new CertFailEnum("E_BIZ_21005", "证书还原失败");
    public static final CertFailEnum E_BIZ_21006 = new CertFailEnum("E_BIZ_21006", "证书版本者密钥丢失");
    public static final CertFailEnum E_BIZ_21007 = new CertFailEnum("E_BIZ_21007", "证书签名验证失败");
    public static final CertFailEnum E_BIZ_21008 = new CertFailEnum("E_BIZ_21008", "CSR证书公钥无效");
    public static final CertFailEnum E_BIZ_21009 = new CertFailEnum("E_BIZ_21009", "证书不存在");
    public static final CertFailEnum E_BIZ_21010 = new CertFailEnum("E_BIZ_21010", "证书状态无效");
    public static final CertFailEnum E_BIZ_21011 = new CertFailEnum("E_BIZ_21011", "CRL组装失败无效");
    public static final CertFailEnum E_BIZ_21012 = new CertFailEnum("E_BIZ_21012", "没有吊销的证书");
    public static final CertFailEnum E_BIZ_21013 = new CertFailEnum("E_BIZ_21013", "CRL不存在");
    public static final CertFailEnum E_BIZ_21014 = new CertFailEnum("E_BIZ_21014", "密钥对丢失");
    public static final CertFailEnum E_BIZ_21015 = new CertFailEnum("E_BIZ_21015", "证书链制作失败");
    public static final CertFailEnum E_BIZ_21016 = new CertFailEnum("E_BIZ_21016", "证书主题编码无效");
    public static final CertFailEnum E_BIZ_21017 = new CertFailEnum("E_BIZ_21017", "证书编码失败");
    public static final CertFailEnum E_BIZ_21018 = new CertFailEnum("E_BIZ_21018", "P7证书解码失败");
    public static final CertFailEnum E_BIZ_21019 = new CertFailEnum("E_BIZ_21019", "密钥交换格式文件生成失败");
    public static final CertFailEnum E_BIZ_21020 = new CertFailEnum("E_BIZ_21020", "证书已过期");


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
