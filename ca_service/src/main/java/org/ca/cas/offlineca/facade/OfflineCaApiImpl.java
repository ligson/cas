package org.ca.cas.offlineca.facade;

import org.ca.cas.offlineca.api.OfflineCaApi;
import org.ca.cas.offlineca.biz.*;
import org.ca.cas.offlineca.dto.*;
import org.ligson.fw.core.facade.base.result.Result;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/5/18.
 */
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
}
