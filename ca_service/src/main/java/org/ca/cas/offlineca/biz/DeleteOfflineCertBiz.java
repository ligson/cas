package org.ca.cas.offlineca.biz;

import org.ca.cas.cert.enums.CertFailEnum;
import org.ca.cas.offlineca.domain.OfflineCertEntity;
import org.ca.cas.offlineca.dto.DeleteOfflineCertRequestDto;
import org.ca.cas.offlineca.dto.DeleteOfflineCertResponseDto;
import org.ca.cas.offlineca.service.OfflineCertService;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.facade.annotation.Api;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/5/20.
 */
@Component("deleteOfflineCertBiz")
@Api(name = "删除离线证书接口")
public class DeleteOfflineCertBiz extends AbstractBiz<DeleteOfflineCertRequestDto, DeleteOfflineCertResponseDto> {

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
        OfflineCertEntity offlineCertEntity = offlineCertService.get(requestDto.getId());
        if (offlineCertEntity == null) {
            setFailureResult(CertFailEnum.E_BIZ_21009);
            return false;
        } else {
            context.setAttr("entity", offlineCertEntity);
        }
        return true;
    }

    @Override
    public Boolean txnPreProcessing() {
        return null;
    }

    @Override
    public Boolean persistence() {
        OfflineCertEntity entity = context.getAttr("entity", OfflineCertEntity.class);
        offlineCertService.delete(entity);
        setSuccessResult();
        return true;
    }

    @Override
    public void after() {

    }
}
