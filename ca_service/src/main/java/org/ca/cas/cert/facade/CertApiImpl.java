package org.ca.cas.cert.facade;

import org.ca.cas.cert.ListUserKeyBiz;
import org.ca.cas.cert.api.CertApi;
import org.ca.cas.cert.biz.EnrollCertBiz;
import org.ca.cas.cert.biz.GenCsrBiz;
import org.ca.cas.cert.biz.ListKeyStoreBiz;
import org.ca.cas.cert.biz.QueryCertBiz;
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
}
