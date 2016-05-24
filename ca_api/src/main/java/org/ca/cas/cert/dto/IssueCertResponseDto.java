package org.ca.cas.cert.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseResponseDto;

/**
 * Created by ligson on 2016/4/25.
 */
public class IssueCertResponseDto extends BaseResponseDto {
    @Param(name = "证书序列号", required = true)
    private String serialNumber;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "IssueCertResponseDto{" +
                "serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
