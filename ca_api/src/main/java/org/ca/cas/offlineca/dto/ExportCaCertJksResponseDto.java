package org.ca.cas.offlineca.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseResponseDto;

import java.util.Arrays;

/**
 * Created by ligson on 2016/5/24.
 */
public class ExportCaCertJksResponseDto extends BaseResponseDto {
    @Param(name = "jks文件")
    private byte[] jksFile;

    public byte[] getJksFile() {
        return jksFile;
    }

    public void setJksFile(byte[] jksFile) {
        this.jksFile = jksFile;
    }

    @Override
    public String toString() {
        return "ExportCaCertJksResponseDto{" +
                "jksFile=" + Arrays.toString(jksFile) +
                '}';
    }
}
