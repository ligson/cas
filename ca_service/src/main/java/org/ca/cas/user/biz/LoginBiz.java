package org.ca.cas.user.biz;

import org.ca.cas.cert.biz.core.MakeCertBiz;
import org.ca.cas.cert.domain.CertEntity;
import org.ca.cas.cert.enums.CertFailEnum;
import org.ca.cas.cert.service.CertService;
import org.ca.common.cert.enums.CertStatus;
import org.ca.common.user.enums.LoginNameType;
import org.ca.common.user.enums.UserState;
import org.ca.cas.user.dto.LoginRequestDto;
import org.ca.cas.user.dto.LoginResponseDto;
import org.ca.cas.user.domain.UserEntity;
import org.ca.cas.user.enums.UserFailCodeEnum;
import org.ca.cas.user.service.UserService;
import org.ca.cas.user.vo.User;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.common.paramcheck.CommonParamCheck;
import org.ligson.fw.core.facade.annotation.Api;
import org.ligson.fw.core.facade.enums.FailureCodeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.security.cert.X509Certificate;
import java.util.Date;

/**
 * Created by ligson on 2016/4/26.
 */
@Api(name = "用户登录接口")
@Component(value = "loginBiz")
public class LoginBiz extends AbstractBiz<LoginRequestDto, LoginResponseDto> {

    @Resource
    private UserService userService;
    @Resource
    private MakeCertBiz makeCertBiz;
    @Resource
    private CertService certService;

    @Override
    public void before() {

    }

    @Override
    public Boolean paramCheck() {
        if (requestDto.getLoginNameType().equals(LoginNameType.EMAIL)) {
            if (!CommonParamCheck.isValidEmail(requestDto.getLoginName(), context, responseDto.getClass())) {
                return false;
            }
        }
        if (requestDto.getLoginNameType().equals(LoginNameType.MOBILE)) {
            if (!CommonParamCheck.isValidMobile(requestDto.getLoginName(), context, responseDto.getClass())) {
                return false;
            }
        }
        if (requestDto.getLoginNameType().equals(LoginNameType.NAME)) {
            if (!CommonParamCheck.isValidName(requestDto.getLoginName(), context, responseDto.getClass())) {
                return false;
            }
        }
        if (requestDto.getLoginNameType().equals(LoginNameType.CERT)) {
            X509Certificate cert = makeCertBiz.recoverCert(requestDto.getPassword());
            if (cert == null) {
                setFailureResult(CertFailEnum.E_BIZ_21005);
                return false;
            } else {
                context.setAttr("cert", cert);
            }
        }
        return true;
    }

    @Override
    public Boolean bizCheck() {
        if (requestDto.getLoginNameType().equals(LoginNameType.CERT)) {
            X509Certificate cert = (X509Certificate) context.getAttr("cert");
            CertEntity certEntity = certService.findBy("serialNumber", cert.getSerialNumber().toString());
            if (certEntity == null) {
                setFailureResult(CertFailEnum.E_BIZ_21009);
                return false;
            } else {
                if (!certEntity.getStatus().equals(CertStatus.VALID.getCode())) {
                    setFailureResult(CertFailEnum.E_BIZ_21010);
                    return false;
                }
                Date nowDate = new Date();
                if (certEntity.getNotBefore().getTime() - nowDate.getTime() < 0) {
                    setFailureResult(CertFailEnum.E_BIZ_21020);
                    return false;
                }

                UserEntity userEntity = userService.get(certEntity.getUserId());
                if (userEntity == null) {
                    setFailureResult(FailureCodeEnum.E_BIZ_20003);
                    return false;
                }
                context.setAttr("certEntity", certEntity);
                context.setAttr("entity", userEntity);
            }
        } else {
            UserEntity entity = new UserEntity();
            if (requestDto.getLoginNameType().equals(LoginNameType.EMAIL)) {
                entity.setEmail(requestDto.getLoginName());
            }
            if (requestDto.getLoginNameType().equals(LoginNameType.MOBILE)) {
                entity.setMobile(requestDto.getLoginName());
            }
            if (requestDto.getLoginNameType().equals(LoginNameType.NAME)) {
                entity.setName(requestDto.getLoginName());
            }
            entity = userService.findByAnd(entity);
            if (entity == null) {
                setFailureResult(FailureCodeEnum.E_BIZ_20003);
                return false;
            }
            if (!requestDto.getPassword().equals(entity.getPassword())) {
                setFailureResult(FailureCodeEnum.E_BIZ_20004);
                return false;
            }
            if (UserState.DISABLED.equals(entity.getStatus())) {
                setFailureResult(UserFailCodeEnum.E_BIZ_20002);
                return false;
            }
            context.setAttr("entity", entity);
        }
        return true;
    }

    @Override
    public Boolean txnPreProcessing() {
        UserEntity entity = (UserEntity) context.getAttr("entity");
        User user = new User();
        BeanUtils.copyProperties(entity, user);
        responseDto.setUser(user);
        responseDto.setSuccess(true);
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
