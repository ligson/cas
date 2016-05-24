package org.ca.cas.cert.dto;


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
    @Param(name = "CSR类型", required = true)
    private Integer reqBufType;
    /***
     * CSR备注
     */
    @Param(name = "CSR备注", required = true)
    private String reqComment;
    /***
     * 证书批准日期
     */
    @Param(name = "证书批准日期", required = true)
    private Date approveDate;
    /***
     * 结束日期
     */
    @Param(name = "notBefore", required = true)
    private Date notBefore;
    /***
     * 开始日期
     */
    @Param(name = "notAfter", required = true)
    private Date notAfter;
    /***
     * 颁发者issueDn
     */
    @Param(name = "issuerDn", required = true)
    private String issuerDn;
    /***
     * 颁发者issueDn哈希
     */
    @Param(name = "issuerDnHashMd5", required = true)
    private String issuerDnHashMd5;
    /***
     * 用户
     */
    @Param(name = "subjectDn", required = true)
    private String subjectDn;
    /***
     * 用户哈希
     */
    @Param(name = "subjectDnHashMd5", required = true)
    private String subjectDnHashMd5;

    /***
     * 证书密码
     */
    @Param(name = "certPin", required = true)
    private String certPin;

    public String getReqBuf() {
        return reqBuf;
    }

    public void setReqBuf(String reqBuf) {
        this.reqBuf = reqBuf;
    }

    public Integer getReqBufType() {
        return reqBufType;
    }

    public void setReqBufType(Integer reqBufType) {
        this.reqBufType = reqBufType;
    }

    public String getReqComment() {
        return reqComment;
    }

    public void setReqComment(String reqComment) {
        this.reqComment = reqComment;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Date getNotBefore() {
        return notBefore;
    }

    public void setNotBefore(Date notBefore) {
        this.notBefore = notBefore;
    }

    public Date getNotAfter() {
        return notAfter;
    }

    public void setNotAfter(Date notAfter) {
        this.notAfter = notAfter;
    }

    public String getIssuerDn() {
        return issuerDn;
    }

    public void setIssuerDn(String issuerDn) {
        this.issuerDn = issuerDn;
    }

    public String getIssuerDnHashMd5() {
        return issuerDnHashMd5;
    }

    public void setIssuerDnHashMd5(String issuerDnHashMd5) {
        this.issuerDnHashMd5 = issuerDnHashMd5;
    }

    public String getSubjectDn() {
        return subjectDn;
    }

    public void setSubjectDn(String subjectDn) {
        this.subjectDn = subjectDn;
    }

    public String getSubjectDnHashMd5() {
        return subjectDnHashMd5;
    }

    public void setSubjectDnHashMd5(String subjectDnHashMd5) {
        this.subjectDnHashMd5 = subjectDnHashMd5;
    }

    public String getCertPin() {
        return certPin;
    }

    public void setCertPin(String certPin) {
        this.certPin = certPin;
    }

    @Override
    public String toString() {
        return "IssueCertRequestDto{" +
                "reqBuf='" + reqBuf + '\'' +
                ", reqBufType=" + reqBufType +
                ", reqComment='" + reqComment + '\'' +
                ", approveDate=" + approveDate +
                ", notBefore=" + notBefore +
                ", notAfter=" + notAfter +
                ", issuerDn='" + issuerDn + '\'' +
                ", issuerDnHashMd5='" + issuerDnHashMd5 + '\'' +
                ", subjectDn='" + subjectDn + '\'' +
                ", subjectDnHashMd5='" + subjectDnHashMd5 + '\'' +
                ", certPin='" + certPin + '\'' +
                '}';
    }
}
