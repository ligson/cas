package org.ca.cas.cert.dto;

import org.ligson.fw.core.facade.base.dto.BaseRequestDto;

/**
 * Created by ligson on 2016/5/16.
 */
public class RevokeCertRequestDto extends BaseRequestDto {
    private String adminId;
    private String certId;
    private Integer revokeReason;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public Integer getRevokeReason() {
        return revokeReason;
    }

    public void setRevokeReason(Integer revokeReason) {
        this.revokeReason = revokeReason;
    }

    @Override
    public String toString() {
        return "RevokeCertRequestDto{" +
                "adminId='" + adminId + '\'' +
                ", certId='" + certId + '\'' +
                ", revokeReason=" + revokeReason +
                '}';
    }
}
