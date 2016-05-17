package org.ca.cas.cert.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ligson on 2016/5/17.
 */
public class RevokeCert implements Serializable {
    private String certId;
    private String id;
    private String certSerialNumber;
    private Integer revokeReason;
    private Date revokeDate;
    private String certSubjectDn;
    private String certIssueDn;
    private String adminId;
    private String adminName;

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCertSerialNumber() {
        return certSerialNumber;
    }

    public void setCertSerialNumber(String certSerialNumber) {
        this.certSerialNumber = certSerialNumber;
    }

    public Integer getRevokeReason() {
        return revokeReason;
    }

    public void setRevokeReason(Integer revokeReason) {
        this.revokeReason = revokeReason;
    }

    public Date getRevokeDate() {
        return revokeDate;
    }

    public void setRevokeDate(Date revokeDate) {
        this.revokeDate = revokeDate;
    }

    public String getCertSubjectDn() {
        return certSubjectDn;
    }

    public void setCertSubjectDn(String certSubjectDn) {
        this.certSubjectDn = certSubjectDn;
    }

    public String getCertIssueDn() {
        return certIssueDn;
    }

    public void setCertIssueDn(String certIssueDn) {
        this.certIssueDn = certIssueDn;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    @Override
    public String toString() {
        return "RevokeCert{" +
                "certId='" + certId + '\'' +
                ", id='" + id + '\'' +
                ", certSerialNumber='" + certSerialNumber + '\'' +
                ", revokeReason=" + revokeReason +
                ", revokeDate=" + revokeDate +
                ", certSubjectDn='" + certSubjectDn + '\'' +
                ", certIssueDn='" + certIssueDn + '\'' +
                ", adminId='" + adminId + '\'' +
                ", adminName='" + adminName + '\'' +
                '}';
    }
}
