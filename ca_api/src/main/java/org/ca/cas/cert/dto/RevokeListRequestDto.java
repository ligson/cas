package org.ca.cas.cert.dto;

import org.ca.common.cert.enums.CertRevokeReason;
import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseQueryPageRequestDto;

import java.util.Date;

/**
 * Created by ligson on 2016/5/17.
 */
public class RevokeListRequestDto extends BaseQueryPageRequestDto {
    @Param(name = "吊销记录id")
    private String id;
    @Param(name = "证书id")
    private String certId;
    @Param(name = "证书序列号")
    private String certSerialNumber;
    @Param(name = "吊销日期")
    private Date certRevokeDate;
    @Param(name = "吊销的管理员id")
    private String adminId;
    /**
     * 吊销原因
     *
     * @see CertRevokeReason#getCode()
     */
    private Integer certRevokeReason;
    private Date certNotAfter;
    private String certIssuerHashMd5;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getCertSerialNumber() {
        return certSerialNumber;
    }

    public void setCertSerialNumber(String certSerialNumber) {
        this.certSerialNumber = certSerialNumber;
    }

    public Date getCertRevokeDate() {
        return certRevokeDate;
    }

    public void setCertRevokeDate(Date certRevokeDate) {
        this.certRevokeDate = certRevokeDate;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Integer getCertRevokeReason() {
        return certRevokeReason;
    }

    public void setCertRevokeReason(Integer certRevokeReason) {
        this.certRevokeReason = certRevokeReason;
    }

    public Date getCertNotAfter() {
        return certNotAfter;
    }

    public void setCertNotAfter(Date certNotAfter) {
        this.certNotAfter = certNotAfter;
    }

    public String getCertIssuerHashMd5() {
        return certIssuerHashMd5;
    }

    public void setCertIssuerHashMd5(String certIssuerHashMd5) {
        this.certIssuerHashMd5 = certIssuerHashMd5;
    }

    @Override
    public String toString() {
        return "RevokeListRequestDto{" +
                "id='" + id + '\'' +
                ", certId='" + certId + '\'' +
                ", certSerialNumber='" + certSerialNumber + '\'' +
                ", certRevokeDate=" + certRevokeDate +
                ", adminId='" + adminId + '\'' +
                ", certRevokeReason=" + certRevokeReason +
                ", certNotAfter=" + certNotAfter +
                ", certIssuerHashMd5='" + certIssuerHashMd5 + '\'' +
                '}';
    }
}
