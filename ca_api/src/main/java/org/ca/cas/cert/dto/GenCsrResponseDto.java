package org.ca.cas.cert.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseResponseDto;

/**
 * Created by ligson on 2016/5/16.
 */
public class GenCsrResponseDto extends BaseResponseDto {
    @Param(name = "csr", required = true)
    private String csr;

    public String getCsr() {
        return csr;
    }

    public void setCsr(String csr) {
        this.csr = csr;
    }

    @Override
    public String toString() {
        return "GenCsrResponseDto{" +
                "csr='" + csr + '\'' +
                '}';
    }
}
