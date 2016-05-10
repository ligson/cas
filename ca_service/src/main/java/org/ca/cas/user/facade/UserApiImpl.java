package org.ca.cas.user.facade;

import org.ca.cas.user.biz.ModifyBiz;
import org.ca.cas.user.dto.*;
import org.ca.cas.user.api.UserApi;
import org.ca.cas.user.biz.LoginBiz;
import org.ca.cas.user.biz.QueryUserBiz;
import org.ca.cas.user.biz.RegisterBiz;
import org.ligson.fw.core.facade.base.result.Result;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/4/26.
 */
public class UserApiImpl implements UserApi {

    @Resource
    private LoginBiz loginBiz;
    @Resource
    private RegisterBiz registerBiz;
    @Resource
    private QueryUserBiz queryUserBiz;
    @Resource
    private ModifyBiz modifyBiz;

    @Override
    public Result<RegisterResponseDto> register(RegisterRequestDto requestDto) {
        return registerBiz.operation(requestDto);
    }

    @Override
    public Result<QueryUserResponseDto> query(QueryUserRequestDto requestDto) {
        return queryUserBiz.operation(requestDto);
    }

    @Override
    public Result<LoginResponseDto> login(LoginRequestDto requestDto) {
        return loginBiz.operation(requestDto);
    }

    @Override
    public Result<ModifyUserResponseDto> modify(ModifyUserRequestDto requestDto) {
        return modifyBiz.operation(requestDto);
    }
}
