package org.ca.cas.user.biz;

import org.ca.cas.user.domain.UserEntity;
import org.ca.cas.user.dto.ResetPwdRequestDto;
import org.ca.cas.user.dto.ResetPwdResponseDto;
import org.ca.cas.user.enums.UserFailCodeEnum;
import org.ca.cas.user.service.UserService;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.facade.annotation.Api;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/5/12.
 */
@Component(value = "resetPwdBiz")
@Api(name = "重置密码接口")
public class ResetPwdBiz extends AbstractBiz<ResetPwdRequestDto, ResetPwdResponseDto> {
    @Resource
    private UserService userService;

    @Override
    public void before() {

    }

    @Override
    public Boolean paramCheck() {
        return null;
    }

    @Override
    public Boolean bizCheck() {
        UserEntity entity = userService.get(requestDto.getUserId());
        if (entity == null) {
            setFailureResult(UserFailCodeEnum.E_BIZ_20003);
            return false;
        }
        UserEntity operator = userService.get(requestDto.getOperatorId());
        if (operator == null) {
            setFailureResult(UserFailCodeEnum.E_BIZ_20003);
            return false;
        }
        context.setAttr("entity", entity);
        return true;
    }

    @Override
    public Boolean txnPreProcessing() {
        UserEntity entity = (UserEntity) context.getAttr("entity");
        entity.setPassword(requestDto.getNewPwd());
        return true;
    }

    @Override
    public Boolean persistence() {
        UserEntity entity = (UserEntity) context.getAttr("entity");
        userService.update(entity);
        setSuccessResult();
        return true;
    }

    @Override
    public void after() {

    }
}
