package org.ca.cas.cert.facade;

import org.ca.cas.cert.biz.ListUserKeyBiz;
import org.ca.cas.cert.api.CertApi;
import org.ca.cas.cert.biz.*;
import org.ca.cas.cert.dto.*;
import org.ligson.fw.core.facade.base.result.Result;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/4/25.
 */
public class CertApiImpl implements CertApi {
    @Resource
    private EnrollCertBiz enrollCertBiz;

    @Resource
    private QueryCertBiz queryCertBiz;

    @Resource
    private ListKeyStoreBiz listKeyStoreBiz;
    @Resource
    private GenCsrBiz genCsrBiz;

    @Resource
    private ListUserKeyBiz listUserKeyBiz;

    @Resource
    private RevokeCertBiz revokeCertBiz;

    @Resource
    private RevokeListBiz revokeListBiz;

    @Resource
    private GenCrlBiz genCrlBiz;

    @Resource
    private DownloadCrlBiz downloadCrlBiz;

    @Override
    public Result<IssueCertResponseDto> issueCert(IssueCertRequestDto requestDto) {
        return null;
    }

    @Override
    public Result<QueryCertResponseDto> queryCert(QueryCertRequestDto requestDto) {
        return queryCertBiz.operation(requestDto);
    }

    @Override
    public Result<EnrollCertResponseDto> enrollCert(EnrollCertRequestDto requestDto) {
        return enrollCertBiz.operation(requestDto);
    }

    @Override
    public Result<ListKeyStoreResponseDto> listKeyStore(ListKeyStoreRequestDto requestDto) {
        return listKeyStoreBiz.operation(requestDto);
    }

    @Override
    public Result<ListUserKeyResponseDto> listUserKey(ListUserKeyRequestDto requestDto) {
        return listUserKeyBiz.operation(requestDto);
    }

    @Override
    public Result<GenCsrResponseDto> genCsr(GenCsrRequestDto requestDto) {
        return genCsrBiz.operation(requestDto);
    }

    @Override
    public Result<RevokeCertResponseDto> revokeCert(RevokeCertRequestDto revokeCertRequestDto) {
        return revokeCertBiz.operation(revokeCertRequestDto);
    }

    @Override
    public Result<RevokeListResponseDto> revokeQuery(RevokeListRequestDto revokeListRequestDto) {
        return revokeListBiz.operation(revokeListRequestDto);
    }

    @Override
    public Result<GenCrlResponseDto> genCrl(GenCrlRequestDto requestDto) {
        return genCrlBiz.operation(requestDto);
    }

    @Override
    public Result<DownloadCrlResponseDto> downloadCrl(DownloadCrlRequestDto requestDto) {
        return downloadCrlBiz.operation(requestDto);
    }
}
