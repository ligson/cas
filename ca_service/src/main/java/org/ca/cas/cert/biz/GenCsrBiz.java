package org.ca.cas.cert.biz;

import org.ca.cas.cert.dto.EnrollCertRequestDto;
import org.ca.cas.cert.dto.EnrollCertResponseDto;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.facade.annotation.Api;
import org.springframework.stereotype.Component;

/**
 * Created by ligson on 2016/5/6.
 */
@Component("GenCsrBiz")
@Api(name = "生成csr接口")
public class GenCsrBiz extends AbstractBiz<EnrollCertRequestDto, EnrollCertResponseDto> {
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
