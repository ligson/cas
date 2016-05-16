package org.ca.cas.cert.api;

import org.ca.cas.cert.dto.*;
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

    Result<EnrollCertResponseDto> enrollCert(EnrollCertRequestDto requestDto);

    Result<ListKeyStoreResponseDto> listKeyStore(ListKeyStoreRequestDto requestDto);

    Result<ListUserKeyResponseDto> listUserKey(ListUserKeyRequestDto requestDto);

    Result<GenCsrResponseDto> genCsr(GenCsrRequestDto requestDto);


}
