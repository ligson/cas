package org.ca.cas.cert.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseRequestDto;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by ligson on 2016/4/25.
 */
public class ModifyCertRequestDto extends BaseRequestDto {
    /***
     * 证书id
     */
    @Param(name = "证书ID", required = true)
    private String id;
    /***
     * 证书状态
     *
     * @see org.ca.common.cert.enums.CertStatus
     */
    @Param(name = "证书状态")
    private Integer status;
    /***
     * 证书批准日期
     */
    @Param(name = "证书批准日期")
    private Date approveDate;
    /***
     * 证书批准拒绝日期
     */
    @Param(name = "证书批准拒绝日期")
    private Date rejectDate;
    /***
     * 证书签名日期
     */
    @Param(name = "证书签名日期")
    private Date signDate;
    /***
     * 证书签名值
     */
    @Param(name = "证书签名值")
    private String signBuf;
    /***
     * 证书签名值P7格式
     */
    @Param(name = "证书签名值P7格式")
    private String signBufP7;
    /***
     * 证书序列号
     */
    @Param(name = "证书序列号")
    private String serialNumber;
    /***
     * 结束日期
     */
    @Param(name = "结束日期")
    private Date notBefore;
    /***
     * 开始日期
     */
    @Param(name = "开始日期")
    private Date notAfter;
    /***
     * 颁发者issueDn
     */
    @Param(name = "颁发者issueDn")
    private String issuerDn;
    /***
     * 颁发者issueDn哈希
     */
    @Param(name = "颁发者issueDn哈希")
    private String issuerDnHashMd5;
    /***
     * 用户
     */
    @Param(name = "用户")
    private String subjectDn;
    /***
     * 用户哈希
     */
    @Param(name = "用户哈希")
    private String subjectDnHashMd5;
    /***
     * 证书挂起时间
     */
    @Param(name = "证书挂起时间")
    private String suspendDate;
    /***
     * 证书吊销时间
     */
    @Param(name = "证书吊销时间")
    private String revokeDate;
    /***
     * 证书吊销原因
     */
    @Param(name = "证书吊销原因")
    private String revokeReason;
    /***
     * 证书更新日期
     */
    @Param(name = "证书更新日期")
    private Date renewalDate;

    /***
     * 证书更新前一个序列号
     */
    @Param(name = "证书更新前一个序列号")
    private String renewalPrevSerialNumber;
    /***
     * 证书更新后一个序列号
     */
    @Param(name = "证书更新后一个序列号")
    private String renewalNextIdSerialNumber;
    /***
     * 证书请求过期时间
     */
    @Param(name = "证书请求过期时间")
    private Integer reqOverrideValidity;

    /***
     * 证书密码
     */
    @Param(name = "证书密码")
    private String certPin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Date getRejectDate() {
        return rejectDate;
    }

    public void setRejectDate(Date rejectDate) {
        this.rejectDate = rejectDate;
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

    public String getSignBufP7() {
        return signBufP7;
    }

    public void setSignBufP7(String signBufP7) {
        this.signBufP7 = signBufP7;
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

    public String getSuspendDate() {
        return suspendDate;
    }

    public void setSuspendDate(String suspendDate) {
        this.suspendDate = suspendDate;
    }

    public String getRevokeDate() {
        return revokeDate;
    }

    public void setRevokeDate(String revokeDate) {
        this.revokeDate = revokeDate;
    }

    public String getRevokeReason() {
        return revokeReason;
    }

    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }

    public String getRenewalPrevSerialNumber() {
        return renewalPrevSerialNumber;
    }

    public void setRenewalPrevSerialNumber(String renewalPrevSerialNumber) {
        this.renewalPrevSerialNumber = renewalPrevSerialNumber;
    }

    public String getRenewalNextIdSerialNumber() {
        return renewalNextIdSerialNumber;
    }

    public void setRenewalNextIdSerialNumber(String renewalNextIdSerialNumber) {
        this.renewalNextIdSerialNumber = renewalNextIdSerialNumber;
    }

    public Integer getReqOverrideValidity() {
        return reqOverrideValidity;
    }

    public void setReqOverrideValidity(Integer reqOverrideValidity) {
        this.reqOverrideValidity = reqOverrideValidity;
    }

    public String getCertPin() {
        return certPin;
    }

    public void setCertPin(String certPin) {
        this.certPin = certPin;
    }

    @Override
    public String toString() {
        return "ModifyCertRequestDto{" +
                "id=" + id +
                ", status=" + status +
                ", approveDate=" + approveDate +
                ", rejectDate=" + rejectDate +
                ", signDate=" + signDate +
                ", signBuf='" + signBuf + '\'' +
                ", signBufP7='" + signBufP7 + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", notBefore=" + notBefore +
                ", notAfter=" + notAfter +
                ", issuerDn='" + issuerDn + '\'' +
                ", issuerDnHashMd5='" + issuerDnHashMd5 + '\'' +
                ", subjectDn='" + subjectDn + '\'' +
                ", subjectDnHashMd5='" + subjectDnHashMd5 + '\'' +
                ", suspendDate='" + suspendDate + '\'' +
                ", revokeDate='" + revokeDate + '\'' +
                ", revokeReason='" + revokeReason + '\'' +
                ", renewalDate=" + renewalDate +
                ", renewalPrevSerialNumber='" + renewalPrevSerialNumber + '\'' +
                ", renewalNextIdSerialNumber='" + renewalNextIdSerialNumber + '\'' +
                ", reqOverrideValidity=" + reqOverrideValidity +
                ", certPin='" + certPin + '\'' +
                '}';
    }
}
