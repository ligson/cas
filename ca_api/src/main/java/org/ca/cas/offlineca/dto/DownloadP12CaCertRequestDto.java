package org.ca.cas.offlineca.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseRequestDto;

/**
 * Created by ligson on 2016/5/19.
 */
public class DownloadP12CaCertRequestDto extends BaseRequestDto {
    @Param(name = "证书id", required = true)
    private String certId;
    @Param(name = "导出密码", required = true)
    private String pwd;

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "DownloadP12CaCertRequestDto{" +
                "certId='" + certId + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
