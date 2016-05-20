package org.ca.cas.offlineca.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseRequestDto;

/**
 * Created by ligson on 2016/5/20.
 */
public class DeleteOfflineCertRequestDto extends BaseRequestDto {
    @Param(name = "证书id", required = true)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DeleteOfflineCertRequestDto{" +
                "id='" + id + '\'' +
                '}';
    }
}
