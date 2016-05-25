package org.ca.cas.cert.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseRequestDto;

/**
 * Created by ligson on 2016/5/25.
 */
public class ImportCaCertRequestDto extends BaseRequestDto {
    @Param(name = "证书base64编码字符串", required = true)
    private String certBuf;
    @Param(name = "管理员Id", required = true)
    private String adminId;
    @Param(name = "证书保护密码", required = true)
    private String certPin;

    public String getCertBuf() {
        return certBuf;
    }

    public void setCertBuf(String certBuf) {
        this.certBuf = certBuf;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getCertPin() {
        return certPin;
    }

    public void setCertPin(String certPin) {
        this.certPin = certPin;
    }

    @Override
    public String toString() {
        return "ImportCaCertRequestDto{" +
                "certBuf='" + certBuf + '\'' +
                ", adminId='" + adminId + '\'' +
                ", certPin='" + certPin + '\'' +
                '}';
    }
}
