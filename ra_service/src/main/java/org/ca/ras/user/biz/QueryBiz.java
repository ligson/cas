package org.ca.ras.user.biz;

import org.ca.ras.user.dto.QueryUserRequestDto;
import org.ca.ras.user.dto.QueryUserResponseDto;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.facade.annotation.Api;
import org.springframework.stereotype.Component;

/**
 * Created by ligson on 2016/4/26.
 */
@Api(name = "用户查询接口")
@Component(value = "queryBiz")
public class QueryBiz extends AbstractBiz<QueryUserRequestDto, QueryUserResponseDto> {
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
        return null;
    }

    @Override
    public Boolean persistence() {
        return null;
    }

    @Override
    public void after() {

    }
}
