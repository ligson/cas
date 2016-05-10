package org.ca.cas.cert.dto;

import org.ligson.fw.core.facade.base.dto.BaseResponseDto;

import java.math.BigInteger;

/**
 * Created by ligson on 2016/5/6.
 */
public class EnrollCertResponseDto extends BaseResponseDto {
    private BigInteger certId;

    public BigInteger getCertId() {
        return certId;
    }

    public void setCertId(BigInteger certId) {
        this.certId = certId;
    }

    @Override
    public String toString() {
        return "EnrollCertResponseDto{" +
                "certId=" + certId +
                '}';
    }
}
