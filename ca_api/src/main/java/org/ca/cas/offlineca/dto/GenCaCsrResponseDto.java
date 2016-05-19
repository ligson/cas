package org.ca.cas.offlineca.dto;

import org.ligson.fw.core.facade.base.dto.BaseResponseDto;

/**
 * Created by ligson on 2016/5/18.
 */
public class GenCaCsrResponseDto extends BaseResponseDto {
    private String csr;

    public String getCsr() {
        return csr;
    }

    public void setCsr(String csr) {
        this.csr = csr;
    }
}
