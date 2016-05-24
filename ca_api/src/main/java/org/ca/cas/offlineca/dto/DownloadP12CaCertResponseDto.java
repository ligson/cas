package org.ca.cas.offlineca.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseResponseDto;

/**
 * Created by ligson on 2016/5/19.
 */
public class DownloadP12CaCertResponseDto extends BaseResponseDto {
    @Param(name = "密钥交换文件", description = "base64编码")
    private String p12;

    public String getP12() {
        return p12;
    }

    public void setP12(String p12) {
        this.p12 = p12;
    }

    @Override
    public String toString() {
        return "DownloadP12CaCertResponseDto{" +
                "p12='" + p12 + '\'' +
                '}';
    }
}
