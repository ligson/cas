package org.ca.cas.cert.biz;

import org.ca.cas.cert.domain.BaseCrlEntity;
import org.ca.cas.cert.dto.DownloadCrlRequestDto;
import org.ca.cas.cert.dto.DownloadCrlResponseDto;
import org.ca.cas.cert.enums.CertFailEnum;
import org.ca.cas.cert.service.BaseCrlService;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.entity.Pagination;
import org.ligson.fw.core.facade.annotation.Api;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

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
        BaseCrlEntity crlEntity = new BaseCrlEntity();
        crlEntity.setCertSerialNumber(requestDto.getCaCertSerialNumber());
        crlEntity.setSort("lastUpdate");
        crlEntity.setOrder("desc");
        Pagination<BaseCrlEntity> page = baseCrlService.findAllByEqAnd(crlEntity);
        List<BaseCrlEntity> crlEntities = page.getDatas();
        if (CollectionUtils.isEmpty(crlEntities)) {
            setFailureResult(CertFailEnum.E_BIZ_21013);
            return false;
        }
        BaseCrlEntity baseCrlEntity = crlEntities.get(0);
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
