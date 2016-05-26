package org.ca.cas.offlineca.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseRequestDto;

/**
 * Created by ligson on 2016/5/26.
 */
public class CreateOfflineKeyRequestDto extends BaseRequestDto {
    @Param(name = "创建的个数", required = true)
    private int count;
    @Param(name = "密钥类型", required = true)
    private int keyType;
    @Param(name = "密钥长度", required = true)
    private int keySize;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getKeyType() {
        return keyType;
    }

    public void setKeyType(int keyType) {
        this.keyType = keyType;
    }

    public int getKeySize() {
        return keySize;
    }

    public void setKeySize(int keySize) {
        this.keySize = keySize;
    }

    @Override
    public String toString() {
        return "CreateOfflineKeyRequestDto{" +
                "count=" + count +
                ", keyType=" + keyType +
                ", keySize=" + keySize +
                '}';
    }
}
