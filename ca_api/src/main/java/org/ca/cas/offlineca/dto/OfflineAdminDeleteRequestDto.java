package org.ca.cas.offlineca.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseQueryPageRequestDto;

/**
 * Created by ligson on 2016/5/20.
 */
public class OfflineAdminDeleteRequestDto extends BaseQueryPageRequestDto {
    @Param(name = "管理员id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OfflineAdminDeleteRequestDto{" +
                "id='" + id + '\'' +
                '}';
    }
}
