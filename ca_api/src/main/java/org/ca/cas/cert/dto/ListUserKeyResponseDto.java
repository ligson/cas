package org.ca.cas.cert.dto;

import org.ca.cas.cert.vo.KeyPair;
import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseQueryPageResponseDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 2016/5/16.
 */
public class ListUserKeyResponseDto extends BaseQueryPageResponseDto {
    @Param(name = "密钥对列表")
    private List<KeyPair> keyPairs = new ArrayList<>();

    public List<KeyPair> getKeyPairs() {
        return keyPairs;
    }

    public void setKeyPairs(List<KeyPair> keyPairs) {
        this.keyPairs = keyPairs;
    }
}
