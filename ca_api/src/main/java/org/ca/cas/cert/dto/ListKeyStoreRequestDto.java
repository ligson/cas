package org.ca.cas.cert.dto;

import org.ligson.fw.core.facade.base.dto.BaseRequestDto;

/**
 * Created by ligson on 2016/5/13.
 */
public class ListKeyStoreRequestDto extends BaseRequestDto {
    /**
     * RSA(1, "RSA"), SM2(2, "SM2");
     */
    private int keyType;
    private int keySize;
    private int count;

    public int getKeyType() {
        return keyType;
    }

    public void setKeyType(int keyType) {
        this.keyType = keyType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getKeySize() {
        return keySize;
    }

    public void setKeySize(int keySize) {
        this.keySize = keySize;
    }

    @Override
    public String toString() {
        return "ListKeyStoreRequestDto{" +
                "keyType=" + keyType +
                ", keySize=" + keySize +
                ", count=" + count +
                '}';
    }
}