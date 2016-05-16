package org.ca.cas.cert.dto;

import org.ligson.fw.core.facade.base.dto.BaseQueryPageRequestDto;
import org.ligson.fw.core.facade.base.dto.BaseRequestDto;

/**
 * Created by ligson on 2016/5/16.
 */
public class ListUserKeyRequestDto extends BaseQueryPageRequestDto{
    private String adminId;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
