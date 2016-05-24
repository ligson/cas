package org.ca.cas.cert.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseRequestDto;

/**
 * Created by ligson on 2016/5/17.
 */
public class GenCrlRequestDto extends BaseRequestDto {
    @Param(name = "管理员ID", required = true)
    private String adminId;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "GenCrlRequestDto{" +
                "adminId='" + adminId + '\'' +
                '}';
    }
}
