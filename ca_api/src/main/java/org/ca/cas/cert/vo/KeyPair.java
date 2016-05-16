package org.ca.cas.cert.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ligson on 2016/5/16.
 */
public class KeyPair implements Serializable {
    private int keyType;
    private int keySize;
    private String aliase;
    private Date createTime;
    private Integer keyStatus;

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

    public String getAliase() {
        return aliase;
    }

    public void setAliase(String aliase) {
        this.aliase = aliase;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getKeyStatus() {
        return keyStatus;
    }

    public void setKeyStatus(Integer keyStatus) {
        this.keyStatus = keyStatus;
    }

    @Override
    public String toString() {
        return "KeyPair{" +
                "keyType=" + keyType +
                ", keySize=" + keySize +
                ", aliase='" + aliase + '\'' +
                ", createTime=" + createTime +
                ", keyStatus=" + keyStatus +
                '}';
    }
}
