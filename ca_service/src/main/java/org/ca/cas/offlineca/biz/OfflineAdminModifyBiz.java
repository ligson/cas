package org.ca.cas.offlineca.biz;

import org.ca.cas.offlineca.domain.OfflineAdminEntity;
import org.ca.cas.offlineca.dto.OfflineAdminModifyRequestDto;
import org.ca.cas.offlineca.dto.OfflineAdminModifyResponseDto;
import org.ca.cas.offlineca.service.OfflineAdminService;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.common.utils.BeanCopy;
import org.ligson.fw.core.facade.enums.FailureCodeEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/5/20.
 */
@Component("offlineAdminModifyBiz")
public class OfflineAdminModifyBiz extends AbstractBiz<OfflineAdminModifyRequestDto, OfflineAdminModifyResponseDto> {
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
        OfflineAdminEntity entity = context.getAttr("entity", OfflineAdminEntity.class);
        BeanCopy.copyPropertiesIgnoreNull(requestDto, entity);
        offlineAdminService.update(entity);
        setSuccessResult();
        return true;
    }

    @Override
    public Boolean persistence() {
        return null;
    }

    @Override
    public void after() {

    }
}
