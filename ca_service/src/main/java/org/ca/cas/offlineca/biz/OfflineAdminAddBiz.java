package org.ca.cas.offlineca.biz;

import org.ca.cas.offlineca.domain.OfflineAdminEntity;
import org.ca.cas.offlineca.dto.OfflineAdminAddRequestDto;
import org.ca.cas.offlineca.dto.OfflineAdminAddResponseDto;
import org.ca.cas.offlineca.service.OfflineAdminService;
import org.ca.common.user.enums.UserRole;
import org.ca.common.user.enums.UserState;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.facade.annotation.Api;
import org.ligson.fw.core.facade.enums.FailureCodeEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by ligson on 2016/5/20.
 */
@Component("offlineAdminAddBiz")
@Api(name = "离线CA管理员增加接口")
public class OfflineAdminAddBiz extends AbstractBiz<OfflineAdminAddRequestDto, OfflineAdminAddResponseDto> {
    @Resource
    private OfflineAdminService offlineAdminService;

    @Override
    public void before() {

    }

    @Override
    public Boolean paramCheck() {
        OfflineAdminEntity adminEntity = offlineAdminService.findBy("name", requestDto.getName());
        if (adminEntity != null) {
            setFailureResult(FailureCodeEnum.E_BIZ_20001);
            return false;
        }
        return true;
    }

    @Override
    public Boolean bizCheck() {
        OfflineAdminEntity offlineAdminEntity = new OfflineAdminEntity();
        offlineAdminEntity.setName(requestDto.getName());
        offlineAdminEntity.setPassword(requestDto.getPassword());
        offlineAdminEntity.setStatus(UserState.VALID.getCode());
        offlineAdminEntity.setCreateDate(new Date());
        offlineAdminEntity.setRole(UserRole.OFFLINE_ADMIN.getCode());
        context.setAttr("entity", offlineAdminEntity);
        return true;
    }

    @Override
    public Boolean txnPreProcessing() {
        OfflineAdminEntity offlineAdminEntity = context.getAttr("entity", OfflineAdminEntity.class);
        offlineAdminService.add(offlineAdminEntity);
        responseDto.setId(offlineAdminEntity.getId());
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
