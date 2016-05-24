package org.ca.cas.offlineca.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseRequestDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 2016/5/24.
 */
public class ExportCaCertJksRequestDto extends BaseRequestDto {
    @Param(name = "CA证书Id列表")
    private List<String> certIds = new ArrayList<>();
    @Param(name = "JKS文件密码", required = true)
    private String jksPassword;
    @Param(name = "管理员", required = true)
    private String adminId;

    public List<String> getCertIds() {
        return certIds;
    }

    public void setCertIds(List<String> certIds) {
        this.certIds = certIds;
    }

    public String getJksPassword() {
        return jksPassword;
    }

    public void setJksPassword(String jksPassword) {
        this.jksPassword = jksPassword;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "ExportCaCertJksRequestDto{" +
                "certIds=" + certIds +
                ", jksPassword='" + jksPassword + '\'' +
                ", adminId='" + adminId + '\'' +
                '}';
    }
}
