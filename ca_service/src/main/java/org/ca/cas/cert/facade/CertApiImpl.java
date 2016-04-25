package org.ca.cas.cert.facade;

import org.ca.cas.cert.api.CertApi;
import org.ca.cas.cert.dto.IssueCertRequestDto;
import org.ca.cas.cert.dto.IssueCertResponseDto;
import org.ca.cas.cert.dto.QueryCertRequestDto;
import org.ca.cas.cert.dto.QueryCertResponseDto;
import org.ligson.fw.core.facade.base.result.Result;

/**
 * Created by ligson on 2016/4/25.
 */
public class CertApiImpl implements CertApi {
    @Override
    public Result<IssueCertResponseDto> issueCert(IssueCertRequestDto requestDto) {
        return null;
    }

    @Override
    public Result<QueryCertResponseDto> queryCert(QueryCertRequestDto requestDto) {
        return null;
    }
}
