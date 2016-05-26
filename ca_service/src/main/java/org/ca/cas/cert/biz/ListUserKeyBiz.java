package org.ca.cas.cert.biz;

import org.ca.cas.cert.dto.ListUserKeyRequestDto;
import org.ca.cas.cert.dto.ListUserKeyResponseDto;
import org.ca.cas.cert.vo.KeyPair;
import org.ca.kms.key.api.KeyApi;
import org.ca.kms.key.dto.KeyQueryRequestDto;
import org.ca.kms.key.dto.KeyQueryResponseDto;
import org.ca.kms.key.enums.KeyStatus;
import org.ca.kms.key.enums.KeyType;
import org.ca.kms.key.vo.Key;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.facade.annotation.Api;
import org.ligson.fw.core.facade.base.result.Result;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ligson on 2016/5/16.
 */
@Component("listUserKeyBiz")
@Api(name = "查询用户密钥接口")
public class ListUserKeyBiz extends AbstractBiz<ListUserKeyRequestDto, ListUserKeyResponseDto> {
    @Resource
    private KeyApi keyApi;

    @Override
    public void before() {

    }

    @Override
    public Boolean paramCheck() {
        return null;
    }

    @Override
    public Boolean bizCheck() {
        return null;
    }

    @Override
    public Boolean txnPreProcessing() {
        KeyQueryRequestDto keyQueryRequestDto = new KeyQueryRequestDto();
        keyQueryRequestDto.setKeyStatus(KeyStatus.READY.getCode());
        keyQueryRequestDto.setKeyType(KeyType.SM2.getCode());
        keyQueryRequestDto.setPageNum(requestDto.getPageNum());
        keyQueryRequestDto.setPageSize(requestDto.getPageSize());
        Result<KeyQueryResponseDto> queryKeyResult = keyApi.queryKey(keyQueryRequestDto);
        List<Key> keys = queryKeyResult.getData().getKeyList();
        for (Key key : keys) {
            KeyPair keyPair = new KeyPair();
            keyPair.setAliase(key.getId());
            keyPair.setKeyStatus(key.getKeyStatus());
            keyPair.setCreateTime(key.getCreateTime());
            keyPair.setKeySize(key.getKeySize());
            keyPair.setKeyType(key.getKeyType());
            responseDto.getKeyPairs().add(keyPair);
        }

        responseDto.setSuccess(true);
        return true;
    }

    @Override
    public Boolean persistence() {
        return null;
    }

    @Override
    public void after() {

    }
}
