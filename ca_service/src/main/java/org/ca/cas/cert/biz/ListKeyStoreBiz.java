package org.ca.cas.cert.biz;

import org.ca.cas.cert.dto.ListKeyStoreRequestDto;
import org.ca.cas.cert.dto.ListKeyStoreResponseDto;
import org.ca.cas.common.biz.KeyContainerBiz;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.facade.annotation.Api;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
        responseDto.setAliases(keyContainerBiz.keyAliases());
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
