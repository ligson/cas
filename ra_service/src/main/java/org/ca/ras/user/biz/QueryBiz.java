package org.ca.ras.user.biz;

import org.ca.ras.user.dto.QueryUserRequestDto;
import org.ca.ras.user.dto.QueryUserResponseDto;
import org.ligson.fw.core.common.biz.AbstractBiz;

/**
 * Created by ligson on 2016/4/26.
 */
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
