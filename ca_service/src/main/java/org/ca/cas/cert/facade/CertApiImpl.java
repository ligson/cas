package org.ca.cas.cert.facade;

import org.ca.cas.cert.api.CertApi;
import org.ca.cas.cert.biz.EnrollCertBiz;
import org.ca.cas.cert.dto.*;
import org.ligson.fw.core.facade.base.result.Result;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/4/25.
 */
public class CertApiImpl implements CertApi {
    @Resource
    private EnrollCertBiz enrollCertBiz;

    @Override
    public Result<IssueCertResponseDto> issueCert(IssueCertRequestDto requestDto) {
        return null;
    }

    @Override
    public Result<QueryCertResponseDto> queryCert(QueryCertRequestDto requestDto) {
        return null;
    }

    @Override
    public Result<EnrollCertResponseDto> enrollCert(EnrollCertRequestDto requestDto) {
        return enrollCertBiz.operation(requestDto);
    }
}
