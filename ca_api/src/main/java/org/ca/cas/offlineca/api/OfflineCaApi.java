package org.ca.cas.offlineca.api;

import org.ca.cas.offlineca.dto.*;
import org.ligson.fw.core.facade.annotation.Api;
import org.ligson.fw.core.facade.base.result.Result;

/**
 * Created by ligson on 2016/5/18.
 * 离线CA接口
 */
public interface OfflineCaApi {
    /***
     * 生成CA证书CSR接口
     *
     * @param requestDto
     * @return
     */
    @Api(name = "生成CA证书CSR接口")
    Result<GenCaCsrResponseDto> genCaCsr(GenCaCsrRequestDto requestDto);

    /***
     * 生成自签名证书接口
     *
     * @param requestDto
     * @return
     */
    @Api(name = "生成自签名证书接口")
    Result<GenSelfCertResponseDto> genSelfCert(GenSelfCertRequestDto requestDto);

    /***
     * 生成下级CA证书接口
     *
     * @param requestDto
     * @return
     */
    @Api(name = "生成下级CA证书接口")
    Result<GenSubCaCertResponseDto> genSubCaCert(GenSubCaCertRequestDto requestDto);

    /****
     * 离线CA证书查询接口
     *
     * @param requestDto
     * @return
     */
    @Api(name = "离线CA证书查询接口")
    Result<OfflineCaCertQueryResponseDto> offlineCaCertQuery(OfflineCaCertQueryRequestDto requestDto);

    /***
     * 上传p7证书链接口
     *
     * @param requestDto
     * @return
     */
    @Api(name = "上传p7证书链接口")
    Result<UploadP7CertChainResponseDto> uploadP7CertChain(UploadP7CertChainRequestDto requestDto);

    /***
     * 下载P12交换密钥文件接口
     *
     * @param request
     * @return
     */
    @Api(name = "下载P12交换密钥文件接口")
    Result<DownloadP12CaCertResponseDto> downloadP12CaCert(DownloadP12CaCertRequestDto request);

    /***
     * 删除离线证书接口
     *
     * @param requestDto
     * @return
     */
    @Api(name = "删除离线证书接口")
    Result<DeleteOfflineCertResponseDto> deleteOfflineCert(DeleteOfflineCertRequestDto requestDto);

    @Api(name = "导出离线CA证书到jks文件接口")
    Result<ExportCaCertJksResponseDto> exportCaCertJks(ExportCaCertJksRequestDto requestDto);

    @Api(name = "创建离线密钥接口")
    Result<CreateOfflineKeyResponseDto> createOfflineKey(CreateOfflineKeyRequestDto requestDto);
}
