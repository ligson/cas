package org.ca.cas.cert.api;

import org.ca.cas.cert.dto.*;
import org.ligson.fw.core.facade.annotation.Api;
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
    @Api(name = "申请一张证书")
    Result<IssueCertResponseDto> issueCert(IssueCertRequestDto requestDto);

    /***
     * 查询证书
     *
     * @param requestDto
     * @return
     */
    @Api(name = "查询证书")
    Result<QueryCertResponseDto> queryCert(QueryCertRequestDto requestDto);

    /***
     * 颁发一张证书
     *
     * @param requestDto
     * @return
     */
    @Api(name = "颁发一张证书")
    Result<EnrollCertResponseDto> enrollCert(EnrollCertRequestDto requestDto);

    /***
     * 查询CA证书密钥容器
     *
     * @param requestDto
     * @return
     */
    @Api(name = "查询CA证书密钥容器")
    Result<ListKeyStoreResponseDto> listKeyStore(ListKeyStoreRequestDto requestDto);

    /***
     * 查询可用用户密钥容器
     *
     * @param requestDto
     * @return
     */
    @Api(name = "查询可用用户密钥容器")
    Result<ListUserKeyResponseDto> listUserKey(ListUserKeyRequestDto requestDto);

    /***
     * 用户证书CSR生成
     *
     * @param requestDto
     * @return
     */
    @Api(name = "用户证书CSR生成")
    Result<GenCsrResponseDto> genCsr(GenCsrRequestDto requestDto);

    /***
     * 吊销证书
     *
     * @param revokeCertRequestDto
     * @return
     */
    @Api(name = "吊销证书")
    Result<RevokeCertResponseDto> revokeCert(RevokeCertRequestDto revokeCertRequestDto);

    /***
     * 吊销列表查询
     *
     * @param revokeListRequestDto
     * @return
     */
    @Api(name = "吊销列表查询")
    Result<RevokeListResponseDto> revokeQuery(RevokeListRequestDto revokeListRequestDto);

    /***
     * 生成CRL接口
     *
     * @param requestDto
     * @return
     */
    @Api(name = "生成CRL接口")
    Result<GenCrlResponseDto> genCrl(GenCrlRequestDto requestDto);

    /***
     * 下载CRL接口
     *
     * @param requestDto
     * @return
     */
    @Api(name = "下载CRL接口")
    Result<DownloadCrlResponseDto> downloadCrl(DownloadCrlRequestDto requestDto);

    /***
     * 导入管理员CA证书
     *
     * @param requestDto
     * @return
     */
    @Api(name = "导入管理员CA证书")
    Result<ImportCaCertResponseDto> importCaCert(ImportCaCertRequestDto requestDto);

}
