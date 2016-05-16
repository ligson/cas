package org.ca.cas.cert.dto;

import org.ligson.fw.core.facade.base.dto.BaseResponseDto;

/**
 * Created by ligson on 2016/5/16.
 */
public class EnrollUserCertResponseDto extends BaseResponseDto{
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
