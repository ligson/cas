package org.ca.cas.offlineca.domain;

import org.hibernate.annotations.GenericGenerator;
import org.ligson.fw.core.entity.BasicEntity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

/**
 * Created by ligson on 2016/5/18.
 */
@Table(name = "offline_cert")
@Entity
public class OfflineCertEntity extends BasicEntity {
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

    @Id
    @GeneratedValue(generator = "dr.id")
    @GenericGenerator(name = "dr.id", strategy = "org.ligson.fw.core.common.idgenerator.DateRandomGenerator")
    @Column(length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Lob
    @Basic(fetch = LAZY)
    @Column(name = "req_buf", length = 1048576)
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
    @Column(name = "cert_chain_buf", length = 1048576)
    public String getCertChainBuf() {
        return certChainBuf;
    }

    public void setCertChainBuf(String certChainBuf) {
        this.certChainBuf = certChainBuf;
    }

    @Lob
    @Basic(fetch = LAZY)
    @Column(name = "cert_buf", length = 1048576)
    public String getCertBuf() {
        return certBuf;
    }

    public void setCertBuf(String certBuf) {
        this.certBuf = certBuf;
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
}
