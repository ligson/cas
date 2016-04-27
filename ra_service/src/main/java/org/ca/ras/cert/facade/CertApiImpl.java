package org.ca.ras.cert.facade;

import org.ca.ras.cert.api.CertApi;
import org.ca.ras.cert.dto.IssueCertRequestDto;
import org.ca.ras.cert.dto.IssueCertResponseDto;
import org.ca.ras.cert.dto.QueryCertRequestDto;
import org.ca.ras.cert.dto.QueryCertResponseDto;
import org.ligson.fw.core.facade.base.result.Result;

import javax.annotation.Resource;

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
