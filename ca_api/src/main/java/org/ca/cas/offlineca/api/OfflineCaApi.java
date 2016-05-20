package org.ca.cas.offlineca.api;

import org.ca.cas.offlineca.dto.*;
import org.ligson.fw.core.facade.base.result.Result;

/**
 * Created by ligson on 2016/5/18.
 */
public interface OfflineCaApi {
    Result<GenCaCsrResponseDto> genCaCsr(GenCaCsrRequestDto requestDto);

    Result<GenSelfCertResponseDto> genSelfCert(GenSelfCertRequestDto requestDto);

    Result<GenSubCaCertResponseDto> genSubCaCert(GenSubCaCertRequestDto requestDto);

    Result<OfflineCaCertQueryResponseDto> offlineCaCertQuery(OfflineCaCertQueryRequestDto requestDto);

    Result<UploadP7CertChainResponseDto> uploadP7CertChain(UploadP7CertChainRequestDto requestDto);

    Result<DownloadP12CaCertResponseDto> downloadP12CaCert(DownloadP12CaCertRequestDto request);

    Result<DeleteOfflineCertResponseDto> deleteOfflineCert(DeleteOfflineCertRequestDto requestDto);
}
