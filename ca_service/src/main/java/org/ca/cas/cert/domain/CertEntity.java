package org.ca.cas.cert.domain;

import org.ca.common.cert.enums.CertType;
import org.hibernate.annotations.GenericGenerator;
import org.ligson.fw.core.entity.BasicEntity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

/**
 * Created by ligson on 2016/4/18.
 * CA证书
 */
@Entity
@Table(name = "cert")
public class CertEntity extends BasicEntity {
    /***
     * 证书id
     */
    private BigInteger id;
    /***
     * 证书状态
     *
     * @see org.ca.common.cert.enums.CertStatus
     */
    private Integer status;
    /***
     * 申请日期
     */
    private Date reqDate;
    /***
     * CSR
     */
    private String reqBuf;
    /***
     * CSR类型
     */
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
     * 证书批准拒绝日期
     */
    private Date rejectDate;
    /***
     * 证书签名日期
     */
    private Date signDate;
    /***
     * 证书签名值
     */
    private String signBuf;
    /***
     * 证书签名值P7格式
     */
    private String signBufP7;
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
    /***
     * 证书挂起时间
     */
    private String suspendDate;
    /***
     * 证书吊销时间
     */
    private String revokeDate;
    /***
     * 证书吊销原因
     */
    private String revokeReason;
    /***
     * 证书更新日期
     */
    private Date renewalDate;

    /***
     * 证书更新前一个序列号
     */
    private String renewalPrevSerialNumber;
    /***
     * 证书更新后一个序列号
     */
    private String renewalNextIdSerialNumber;
    /***
     * 证书请求过期时间
     */
    private Integer reqOverrideValidity;

    /***
     * 证书密码
     */
    private String certPin;

    /***
     * 证书类型
     */
    private Integer certType = CertType.SIGN.getCode();
    /***
     * 用户id
     */
    private BigInteger userId;

    @Id
    @GeneratedValue(generator = "dr.id")
    @GenericGenerator(name = "dr.id", strategy = "org.ligson.fw.core.common.idgenerator.DateRandomGenerator")
    @Column(length = 32, precision = 32, scale = 0)
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "req_date")
    public Date getReqDate() {
        return reqDate;
    }

    public void setReqDate(Date reqDate) {
        this.reqDate = reqDate;
    }

    @Column(name = "req_buf", length = 2048)
    public String getReqBuf() {
        return reqBuf;
    }

    public void setReqBuf(String reqBuf) {
        this.reqBuf = reqBuf;
    }

    @Column(name = "req_buf_type")
    public Integer getReqBufType() {
        return reqBufType;
    }

    public void setReqBufType(Integer reqBufType) {
        this.reqBufType = reqBufType;
    }

    @Column(name = "req_comment")
    public String getReqComment() {
        return reqComment;
    }

    public void setReqComment(String reqComment) {
        this.reqComment = reqComment;
    }

    @Column(name = "approve_date")
    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    @Column(name = "reject_after")
    public Date getRejectDate() {
        return rejectDate;
    }

    public void setRejectDate(Date rejectDate) {
        this.rejectDate = rejectDate;
    }

    @Column(name = "sign_date")
    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    @Lob
    @Basic(fetch = LAZY)
    @Column(name = "sign_buf", length = 1048576)
    public String getSignBuf() {
        return signBuf;
    }

    public void setSignBuf(String signBuf) {
        this.signBuf = signBuf;
    }

    @Lob
    @Basic(fetch = LAZY)
    @Column(name = "sign_buf_p7", length = 1048576)
    public String getSignBufP7() {
        return signBufP7;
    }

    public void setSignBufP7(String signBufP7) {
        this.signBufP7 = signBufP7;
    }

    @Column(name = "serial_number")
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Column(name = "not_before")
    public Date getNotBefore() {
        return notBefore;
    }

    public void setNotBefore(Date notBefore) {
        this.notBefore = notBefore;
    }

    @Column(name = "not_after")
    public Date getNotAfter() {
        return notAfter;
    }

    public void setNotAfter(Date notAfter) {
        this.notAfter = notAfter;
    }

    @Column(name = "issuer_dn")
    public String getIssuerDn() {
        return issuerDn;
    }

    public void setIssuerDn(String issuerDn) {
        this.issuerDn = issuerDn;
    }

    @Column(name = "issuer_dn_hash_md5")
    public String getIssuerDnHashMd5() {
        return issuerDnHashMd5;
    }

    public void setIssuerDnHashMd5(String issuerDnHashMd5) {
        this.issuerDnHashMd5 = issuerDnHashMd5;
    }

    @Column(name = "subject_dn")
    public String getSubjectDn() {
        return subjectDn;
    }

    public void setSubjectDn(String subjectDn) {
        this.subjectDn = subjectDn;
    }

    @Column(name = "subject_dn_hash_md5")
    public String getSubjectDnHashMd5() {
        return subjectDnHashMd5;
    }

    public void setSubjectDnHashMd5(String subjectDnHashMd5) {
        this.subjectDnHashMd5 = subjectDnHashMd5;
    }

    @Column(name = "suspend_date")
    public String getSuspendDate() {
        return suspendDate;
    }

    public void setSuspendDate(String suspendDate) {
        this.suspendDate = suspendDate;
    }

    @Column(name = "revoke_date")
    public String getRevokeDate() {
        return revokeDate;
    }

    public void setRevokeDate(String revokeDate) {
        this.revokeDate = revokeDate;
    }

    @Column(name = "revoke_reason")
    public String getRevokeReason() {
        return revokeReason;
    }

    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    @Column(name = "renewal_date")
    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }

    @Column(name = "renewal_prev_serial_number")
    public String getRenewalPrevSerialNumber() {
        return renewalPrevSerialNumber;
    }

    public void setRenewalPrevSerialNumber(String renewalPrevSerialNumber) {
        this.renewalPrevSerialNumber = renewalPrevSerialNumber;
    }

    @Column(name = "renewal_next_serial_number")
    public String getRenewalNextIdSerialNumber() {
        return renewalNextIdSerialNumber;
    }

    public void setRenewalNextIdSerialNumber(String renewalNextIdSerialNumber) {
        this.renewalNextIdSerialNumber = renewalNextIdSerialNumber;
    }

    @Column(name = "req_override_validity")
    public Integer getReqOverrideValidity() {
        return reqOverrideValidity;
    }

    public void setReqOverrideValidity(Integer reqOverrideValidity) {
        this.reqOverrideValidity = reqOverrideValidity;
    }

    @Column(name = "cert_pin")
    public String getCertPin() {
        return certPin;
    }

    public void setCertPin(String certPin) {
        this.certPin = certPin;
    }

    @Column(name = "cert_type")
    public Integer getCertType() {
        return certType;
    }

    public void setCertType(Integer certType) {
        this.certType = certType;
    }

    @Column(length = 32, precision = 32, scale = 0, name = "user_id")
    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }
}
