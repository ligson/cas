package org.ca.ras.cert.api;

import org.ca.ras.cert.dto.IssueCertRequestDto;
import org.ca.ras.cert.dto.IssueCertResponseDto;
import org.ca.ras.cert.dto.QueryCertRequestDto;
import org.ca.ras.cert.dto.QueryCertResponseDto;
import org.ligson.fw.core.facade.base.result.Result;

/**
 * Created by ligson on 2016/4/25.
 * CA证书api
 */
public interface CertApi {

    /***
     * 申请一张证书
     *
     * @param requestDto
     * @return
     */
    Result<IssueCertResponseDto> issueCert(IssueCertRequestDto requestDto);

    /***
     * 查询证书
     *
     * @param requestDto
     * @return
     */
    Result<QueryCertResponseDto> queryCert(QueryCertRequestDto requestDto);


}
