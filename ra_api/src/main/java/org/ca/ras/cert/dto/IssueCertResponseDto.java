package org.ca.ras.cert.dto;

import org.ligson.fw.core.facade.base.dto.BaseResponseDto;

/**
 * Created by ligson on 2016/4/25.
 */
public class IssueCertResponseDto extends BaseResponseDto {
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
