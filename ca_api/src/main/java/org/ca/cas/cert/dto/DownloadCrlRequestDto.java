package org.ca.cas.cert.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseRequestDto;

/**
 * Created by ligson on 2016/5/17.
 */
public class DownloadCrlRequestDto extends BaseRequestDto {
    @Param(name = "证书序号", required = true)
    private String caCertSerialNumber;

    public String getCaCertSerialNumber() {
        return caCertSerialNumber;
    }

    public void setCaCertSerialNumber(String caCertSerialNumber) {
        this.caCertSerialNumber = caCertSerialNumber;
    }

    @Override
    public String toString() {
        return "DownloadCrlRequestDto{" +
                "caCertSerialNumber='" + caCertSerialNumber + '\'' +
                '}';
    }
}
