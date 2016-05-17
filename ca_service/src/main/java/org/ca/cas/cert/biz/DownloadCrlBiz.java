package org.ca.cas.cert.biz;

import org.ca.cas.cert.domain.BaseCrlEntity;
import org.ca.cas.cert.dto.DownloadCrlRequestDto;
import org.ca.cas.cert.dto.DownloadCrlResponseDto;
import org.ca.cas.cert.enums.CertFailEnum;
import org.ca.cas.cert.service.BaseCrlService;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.facade.annotation.Api;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/5/17.
 */
@Api(name = "下载crl接口")
@Component("downloadCrlBiz")
public class DownloadCrlBiz extends AbstractBiz<DownloadCrlRequestDto, DownloadCrlResponseDto> {
    @Resource
    private BaseCrlService baseCrlService;

    @Override
    public void before() {

    }

    @Override
    public Boolean paramCheck() {
        return null;
    }

    @Override
    public Boolean bizCheck() {
        BaseCrlEntity baseCrlEntity = baseCrlService.findBy("certSerialNumber", requestDto.getCaCertSerialNumber());
        if (baseCrlEntity == null) {
            setFailureResult(CertFailEnum.E_BIZ_21013);
            return false;
        }
        responseDto.setCrl(baseCrlEntity.getCrlBuf());
        setSuccessResult();
        return true;
    }

    @Override
    public Boolean txnPreProcessing() {
        return null;
    }

    @Override
    public Boolean persistence() {
        return null;
    }

    @Override
    public void after() {

    }
}
