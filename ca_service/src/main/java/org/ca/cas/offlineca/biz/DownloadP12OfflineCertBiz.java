package org.ca.cas.offlineca.biz;

import org.ca.cas.cert.biz.core.MakeCertBiz;
import org.ca.cas.cert.enums.CertFailEnum;
import org.ca.cas.offlineca.domain.OfflineCertEntity;
import org.ca.cas.offlineca.dto.DownloadP12CaCertRequestDto;
import org.ca.cas.offlineca.dto.DownloadP12CaCertResponseDto;
import org.ca.cas.offlineca.service.OfflineCertService;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.facade.annotation.Api;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.security.cert.X509Certificate;

/**
 * Created by ligson on 2016/5/19.
 */
@Component("downloadP12OfflineCertBiz")
@Api(name = "下载CA证书交换密钥接口")
public class DownloadP12OfflineCertBiz extends AbstractBiz<DownloadP12CaCertRequestDto, DownloadP12CaCertResponseDto> {
    @Resource
    private MakeCertBiz makeCertBiz;
    @Resource
    private OfflineCertService offlineCertService;

    @Override
    public void before() {

    }

    @Override
    public Boolean paramCheck() {
        return null;
    }

    @Override
    public Boolean bizCheck() {
        OfflineCertEntity offlineCaCert = offlineCertService.get(requestDto.getCertId());
        if (offlineCaCert == null) {
            setFailureResult(CertFailEnum.E_BIZ_21003);
            return false;
        }
        X509Certificate x509Certificate = makeCertBiz.recoverCert(offlineCaCert.getCertBuf());
        if (x509Certificate == null) {
            setFailureResult(CertFailEnum.E_BIZ_21005);
            return false;
        }
        context.setAttr("cert", x509Certificate);
        return true;
    }

    @Override
    public Boolean txnPreProcessing() {
        X509Certificate x509Certificate = context.getAttr("cert", X509Certificate.class);
        String pkcs12 = makeCertBiz.genPkcs12(x509Certificate, requestDto.getPwd());
        if (pkcs12 == null) {
            setFailureResult(CertFailEnum.E_BIZ_21019);
            return false;
        } else {
            responseDto.setP12(pkcs12);
            setSuccessResult();
            return true;
        }
    }

    @Override
    public Boolean persistence() {
        return null;
    }

    @Override
    public void after() {

    }
}
