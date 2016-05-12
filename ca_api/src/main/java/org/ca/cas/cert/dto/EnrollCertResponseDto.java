package org.ca.cas.cert.dto;

import org.ligson.fw.core.facade.base.dto.BaseResponseDto;

import java.math.BigInteger;

/**
 * Created by ligson on 2016/5/6.
 */
public class EnrollCertResponseDto extends BaseResponseDto {
    private String certId;

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    @Override
    public String toString() {
        return "EnrollCertResponseDto{" +
                "certId=" + certId +
                '}';
    }
}
