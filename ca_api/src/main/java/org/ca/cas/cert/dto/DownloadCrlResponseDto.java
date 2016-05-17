package org.ca.cas.cert.dto;

import org.ligson.fw.core.facade.base.dto.BaseResponseDto;

/**
 * Created by ligson on 2016/5/17.
 */
public class DownloadCrlResponseDto extends BaseResponseDto {
    private String crl;

    public String getCrl() {
        return crl;
    }

    public void setCrl(String crl) {
        this.crl = crl;
    }

    @Override
    public String toString() {
        return "DownloadCrlResponseDto{" +
                "crl='" + crl + '\'' +
                '}';
    }
}
