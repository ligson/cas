package org.ca.ras.cert.dto;


import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseRequestDto;

import java.util.Date;

/**
 * Created by ligson on 2016/4/25.
 */
public class IssueCertRequestDto extends BaseRequestDto {
    /***
     * CSR
     */
    @Param(name = "csr", required = true)
    private String reqBuf;
    /***
     * CSR类型
     */
    @Param(name = "csr类型", required = true)
    private Integer reqBufType;
    /***
     * CSR备注
     */
    private String reqComment;
    /***
     * 证书批准日期
     */
    private Date approveDate;
    /***
     * 结束日期
     */
    private Date notBefore;
    /***
     * 开始日期
     */
    private Date notAfter;
    /***
     * 颁发者issueDn
     */
    private String issuerDn;
    /***
     * 颁发者issueDn哈希
     */
    private String issuerDnHashMd5;
    /***
     * 用户
     */
    private String subjectDn;
    /***
     * 用户哈希
     */
    private String subjectDnHashMd5;

    /***
     * 证书密码
     */
    private String certPin;

}
