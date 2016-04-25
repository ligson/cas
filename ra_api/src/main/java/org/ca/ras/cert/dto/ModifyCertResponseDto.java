package org.ca.ras.cert.dto;

import org.ligson.fw.core.facade.base.dto.BaseResponseDto;

/**
 * Created by ligson on 2016/4/25.
 */
public class ModifyCertResponseDto extends BaseResponseDto {
    private Boolean success;

    @Override
    public Boolean getSuccess() {
        return success;
    }

    @Override
    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
