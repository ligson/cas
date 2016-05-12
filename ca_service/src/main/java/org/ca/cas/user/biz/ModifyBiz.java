package org.ca.cas.user.biz;

import org.ca.cas.user.domain.UserEntity;
import org.ca.cas.user.dto.ModifyUserRequestDto;
import org.ca.cas.user.dto.ModifyUserResponseDto;
import org.ca.cas.user.service.UserService;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.common.utils.BeanCopy;
import org.ligson.fw.core.entity.Pagination;
import org.ligson.fw.core.facade.annotation.Api;
import org.ligson.fw.core.facade.enums.FailureCodeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/5/9.
 */
@Api(name = "用户修改接口")
@Component(value = "modifyBiz")
public class ModifyBiz extends AbstractBiz<ModifyUserRequestDto, ModifyUserResponseDto> {
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
        if (!StringUtils.isEmpty(requestDto.getName())) {
            UserEntity entity1 = new UserEntity();
            entity1.setName(requestDto.getName());
            UserEntity entity2 = new UserEntity();
            entity2.setId(requestDto.getId());
            Pagination<UserEntity> pagination = userService.findAllByEqAndNe(entity1, entity2);
            if (pagination.getDatas().size() > 0) {
                setFailureResult(FailureCodeEnum.E_BIZ_20001);
                return false;
            }
        }
        if (!StringUtils.isEmpty(requestDto.getEmail())) {
            UserEntity entity1 = new UserEntity();
            entity1.setEmail(requestDto.getEmail());
            UserEntity entity2 = new UserEntity();
            entity2.setId(requestDto.getId());
            Pagination<UserEntity> pagination = userService.findAllByEqAndNe(entity1, entity2);
            if (pagination.getDatas().size() > 0) {
                setFailureResult(FailureCodeEnum.E_BIZ_20005);
                return false;
            }
        }
        if (!StringUtils.isEmpty(requestDto.getMobile())) {
            UserEntity entity1 = new UserEntity();
            entity1.setMobile(requestDto.getMobile());
            UserEntity entity2 = new UserEntity();
            entity2.setId(requestDto.getId());
            Pagination<UserEntity> pagination = userService.findAllByEqAndNe(entity1, entity2);
            if (pagination.getDatas().size() > 0) {
                setFailureResult(FailureCodeEnum.E_BIZ_20006);
                return false;
            }
        }
        UserEntity entity = userService.findBy("id", requestDto.getId());
        if (entity == null) {
            setFailureResult(FailureCodeEnum.E_BIZ_20003);
            return false;
        }
        context.setAttr("entity", entity);
        return true;
    }

    @Override
    public Boolean txnPreProcessing() {
        UserEntity entity = (UserEntity) context.getAttr("entity");
        BeanCopy.copyPropertiesIgnoreNull(requestDto, entity);
        return true;
    }

    @Override
    public Boolean persistence() {
        UserEntity entity = (UserEntity) context.getAttr("entity");
        userService.update(entity);
        responseDto.setSuccess(true);
        setSuccessResult();
        return true;
    }

    @Override
    public void after() {

    }
}
