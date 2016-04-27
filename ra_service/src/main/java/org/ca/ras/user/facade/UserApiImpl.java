package org.ca.ras.user.facade;

import org.ca.ras.cert.dto.LoginRequestDto;
import org.ca.ras.cert.dto.LoginResponseDto;
import org.ca.ras.user.api.UserApi;
import org.ca.ras.user.biz.LoginBiz;
import org.ca.ras.user.biz.QueryBiz;
import org.ca.ras.user.biz.RegisterBiz;
import org.ca.ras.user.dto.QueryUserRequestDto;
import org.ca.ras.user.dto.QueryUserResponseDto;
import org.ca.ras.user.dto.RegisterRequestDto;
import org.ca.ras.user.dto.RegisterResponseDto;
import org.ligson.fw.core.facade.base.result.Result;
import org.springframework.beans.factory.annotation.Autowired;

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
    private QueryBiz queryBiz;

    @Override
    public Result<RegisterResponseDto> register(RegisterRequestDto requestDto) {
        return registerBiz.operation(requestDto);
    }

    @Override
    public Result<QueryUserResponseDto> query(QueryUserRequestDto requestDto) {
        return queryBiz.operation(requestDto);
    }

    @Override
    public Result<LoginResponseDto> login(LoginRequestDto requestDto) {
        return loginBiz.operation(requestDto);
    }
}
