package org.ca.cas.offlineca.facade;

import org.ca.cas.offlineca.api.OfflineCaApi;
import org.ca.cas.offlineca.biz.*;
import org.ca.cas.offlineca.dto.*;
import org.ligson.fw.core.facade.base.result.Result;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/5/18.
 */
@Component("offlineCaApi")
public class OfflineCaApiImpl implements OfflineCaApi {

    @Resource
    private GenCaCsrBiz genCaCsrBiz;
    @Resource
    private GenSelfCACertBiz genSelfCACertBiz;
    @Resource
    private GenSubCaCertBiz genSubCaCertBiz;
    @Resource
    private OfflineCaCertQueryBiz offlineCaCertQueryBiz;
    @Resource
    private UploadP7CertChainBiz uploadP7CertChainBiz;
    @Resource
    private DownloadP12OfflineCertBiz downloadP12OfflineCertBiz;
    @Resource
    private DeleteOfflineCertBiz deleteOfflineCertBiz;
    @Resource
    private ExportCaCertJksBiz exportCaCertJksBiz;
    @Resource
    private CreateOfflineKeyBiz createOfflineKeyBiz;

    @Override
    public Result<GenCaCsrResponseDto> genCaCsr(GenCaCsrRequestDto requestDto) {
        return genCaCsrBiz.operation(requestDto);
    }

    @Override
    public Result<GenSelfCertResponseDto> genSelfCert(GenSelfCertRequestDto requestDto) {
        return genSelfCACertBiz.operation(requestDto);
    }

    @Override
    public Result<GenSubCaCertResponseDto> genSubCaCert(GenSubCaCertRequestDto responseDto) {
        return genSubCaCertBiz.operation(responseDto);
    }

    @Override
    public Result<OfflineCaCertQueryResponseDto> offlineCaCertQuery(OfflineCaCertQueryRequestDto requestDto) {
        return offlineCaCertQueryBiz.operation(requestDto);
    }

    @Override
    public Result<UploadP7CertChainResponseDto> uploadP7CertChain(UploadP7CertChainRequestDto requestDto) {
        return uploadP7CertChainBiz.operation(requestDto);
    }

    @Override
    public Result<DownloadP12CaCertResponseDto> downloadP12CaCert(DownloadP12CaCertRequestDto request) {
        return downloadP12OfflineCertBiz.operation(request);
    }

    @Override
    public Result<DeleteOfflineCertResponseDto> deleteOfflineCert(DeleteOfflineCertRequestDto requestDto) {
        return deleteOfflineCertBiz.operation(requestDto);
    }

    @Override
    public Result<ExportCaCertJksResponseDto> exportCaCertJks(ExportCaCertJksRequestDto requestDto) {
        return exportCaCertJksBiz.operation(requestDto);
    }

    @Override
    public Result<CreateOfflineKeyResponseDto> createOfflineKey(CreateOfflineKeyRequestDto requestDto) {
        return createOfflineKeyBiz.operation(requestDto);
    }
}
