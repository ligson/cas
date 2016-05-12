package org.ca.cas.user.biz;

import org.ca.cas.user.domain.UserEntity;
import org.ca.cas.user.dto.ModifyPwdRequestDto;
import org.ca.cas.user.dto.ModifyPwdResponseDto;
import org.ca.cas.user.enums.UserFailCodeEnum;
import org.ca.cas.user.service.UserService;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.facade.annotation.Api;
import org.ligson.fw.core.facade.enums.FailureCodeEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/5/12.
 */
@Component(value = "modifyPwdBiz")
@Api(name = "用户修改密码接口")
public class ModifyPwdBiz extends AbstractBiz<ModifyPwdRequestDto, ModifyPwdResponseDto> {
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
        if (!entity.getPassword().equals(requestDto.getOldPwd())) {
            setFailureResult(FailureCodeEnum.E_BIZ_20004);
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
