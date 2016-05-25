package org.ca.cas.cert.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseResponseDto;

/**
 * Created by ligson on 2016/5/25.
 */
public class ImportCaCertResponseDto extends BaseResponseDto {
    @Param(name = "证书id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ImportCaCertResponseDto{" +
                "id='" + id + '\'' +
                '}';
    }
}
