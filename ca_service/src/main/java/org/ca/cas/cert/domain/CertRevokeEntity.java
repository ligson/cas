package org.ca.cas.cert.domain;

import org.ca.common.cert.enums.CertRevokeReason;
import org.hibernate.annotations.GenericGenerator;
import org.ligson.fw.core.entity.BasicEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ligson on 2016/5/16.
 */
@Table(name = "cert_revoke")
@Entity
public class CertRevokeEntity extends BasicEntity {
    private String id;
    private String certId;
    private String certSerialNumber;
    private Date certRevokeDate;
    private String adminId;
    /**
     * 吊销原因
     *
     * @see CertRevokeReason#getCode()
     */
    private Integer certRevokeReason;
    private Date certNotAfter;
    private String certIssuerHashMd5;

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

    @Column(length = 32, name = "cert_id")
    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    @Column(length = 32, name = "cert_serialnumber")
    public String getCertSerialNumber() {
        return certSerialNumber;
    }

    public void setCertSerialNumber(String certSerialNumber) {
        this.certSerialNumber = certSerialNumber;
    }

    @Column(name = "cert_revoke_date")
    public Date getCertRevokeDate() {
        return certRevokeDate;
    }

    public void setCertRevokeDate(Date certRevokeDate) {
        this.certRevokeDate = certRevokeDate;
    }

    @Column(name = "cert_revoke_reason")
    public Integer getCertRevokeReason() {
        return certRevokeReason;
    }

    public void setCertRevokeReason(Integer certRevokeReason) {
        this.certRevokeReason = certRevokeReason;
    }

    @Column(name = "cert_not_after")
    public Date getCertNotAfter() {
        return certNotAfter;
    }

    public void setCertNotAfter(Date certNotAfter) {
        this.certNotAfter = certNotAfter;
    }

    @Column(name = "cert_issuer_hash_md5")
    public String getCertIssuerHashMd5() {
        return certIssuerHashMd5;
    }

    public void setCertIssuerHashMd5(String certIssuerHashMd5) {
        this.certIssuerHashMd5 = certIssuerHashMd5;
    }

    @Column(name = "admin_id")
    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
