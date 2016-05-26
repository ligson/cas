package org.ca.cas.cert.biz;

import org.ca.cas.cert.dto.ListKeyStoreRequestDto;
import org.ca.cas.cert.dto.ListKeyStoreResponseDto;
import org.ca.cas.cert.vo.KeyPair;
import org.ca.cas.common.biz.KeyContainerBiz;
import org.ca.cas.common.model.KeyPairContainer;
import org.ca.kms.key.enums.KeyType;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.facade.annotation.Api;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ligson on 2016/5/13.
 */
@Component("listKeyStoreBiz")
@Api(name = "查询密钥库接口")
public class ListKeyStoreBiz extends AbstractBiz<ListKeyStoreRequestDto, ListKeyStoreResponseDto> {
    @Resource
    private KeyContainerBiz keyContainerBiz;

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
        List<String> aliases = keyContainerBiz.keyAliases();
        responseDto.setTotalCount(aliases.size());
        int offset = (requestDto.getPageNum() - 1) * requestDto.getPageSize();
        int end = offset + requestDto.getPageSize();
        offset = offset < 0 ? 0 : offset;
        end = end > aliases.size() ? aliases.size() : end;

        for (int i = offset; i < end; i++) {
            String aliase = aliases.get(i);
            KeyPairContainer container = keyContainerBiz.getKeyPair(aliase);
            KeyPair keyPair = new KeyPair();
            keyPair.setAliase(aliase);
            keyPair.setKeySize(container.getKeySize());
            if ("RSA".equals(container.getType())) {
                keyPair.setKeyType(KeyType.RSA.getCode());
            } else {
                keyPair.setKeyType(KeyType.SM2.getCode());
            }
            responseDto.getKeyPairs().add(keyPair);
        }
        setSuccessResult();
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
