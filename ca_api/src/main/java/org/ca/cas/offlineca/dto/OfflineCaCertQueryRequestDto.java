package org.ca.cas.offlineca.dto;

import org.ligson.fw.core.facade.base.dto.BaseQueryPageRequestDto;

import java.util.Date;

/**
 * Created by ligson on 2016/5/18.
 */
public class OfflineCaCertQueryRequestDto extends BaseQueryPageRequestDto {
    /***
     * 证书id
     */
    private String id;
    /***
     * CSR
     */
    private String reqBuf;
    /***
     * CSR类型
     */
    private Integer reqBufType;
    /***
     * 证书签名日期
     */
    private Date signDate;
    /***
     * 证书签名值
     */
    private String signBuf;
    /***
     * 证书buf
     */
    private String certBuf;
    /***
     * 证书链buf
     */
    private String certChainBuf;

    /***
     * 证书序列号
     */
    private String serialNumber;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public String getSignBuf() {
        return signBuf;
    }

    public void setSignBuf(String signBuf) {
        this.signBuf = signBuf;
    }

    public String getCertBuf() {
        return certBuf;
    }

    public void setCertBuf(String certBuf) {
        this.certBuf = certBuf;
    }

    public String getCertChainBuf() {
        return certChainBuf;
    }

    public void setCertChainBuf(String certChainBuf) {
        this.certChainBuf = certChainBuf;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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
}
