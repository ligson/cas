package org.ca.cas.offlineca.biz;

import org.ca.cas.offlineca.domain.OfflineAdminEntity;
import org.ca.cas.offlineca.dto.OfflineAdminDeleteRequestDto;
import org.ca.cas.offlineca.dto.OfflineAdminDeleteResponseDto;
import org.ca.cas.offlineca.service.OfflineAdminService;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.facade.annotation.Api;
import org.ligson.fw.core.facade.enums.FailureCodeEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/5/20.
 */
@Component("offlineAdminDeleteBiz")
@Api(name = "离线管理员删除接口")
public class OfflineAdminDeleteBiz extends AbstractBiz<OfflineAdminDeleteRequestDto, OfflineAdminDeleteResponseDto> {
    @Resource
    private OfflineAdminService offlineAdminService;

    @Override
    public void before() {

    }

    @Override
    public Boolean paramCheck() {
        return null;
    }

    @Override
    public Boolean bizCheck() {
        OfflineAdminEntity adminEntity = offlineAdminService.get(requestDto.getId());
        if (adminEntity == null) {
            setFailureResult(FailureCodeEnum.E_BIZ_20003);
            return false;
        } else {
            context.setAttr("entity", adminEntity);
        }
        return true;
    }

    @Override
    public Boolean txnPreProcessing() {
        return null;
    }

    @Override
    public Boolean persistence() {
        OfflineAdminEntity entity = context.getAttr("entity", OfflineAdminEntity.class);
        offlineAdminService.delete(entity);
        setSuccessResult();
        return true;
    }

    @Override
    public void after() {

    }
}
