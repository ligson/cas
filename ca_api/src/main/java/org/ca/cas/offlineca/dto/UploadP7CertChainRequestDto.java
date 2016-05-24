package org.ca.cas.offlineca.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseRequestDto;

/**
 * Created by ligson on 2016/5/18.
 */
public class UploadP7CertChainRequestDto extends BaseRequestDto {

    @Param(name = "证书链文件", required = true)
    private byte[] p7File;

    public byte[] getP7File() {
        return p7File;
    }

    public void setP7File(byte[] p7File) {
        this.p7File = p7File;
    }
}
