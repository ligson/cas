package org.ca.cas.user.api;

import org.ca.cas.user.dto.*;
import org.ligson.fw.core.facade.annotation.Api;
import org.ligson.fw.core.facade.base.result.Result;

/**
 * Created by ligson on 2016/4/25.
 * 用户接口
 */
public interface UserApi {

    /***
     * 注册接口
     *
     * @param requestDto
     * @return
     */
    @Api(name = "注册接口")
    Result<RegisterResponseDto> register(RegisterRequestDto requestDto);

    /***
     * 用户查询接口
     *
     * @param requestDto
     * @return
     */
    @Api(name = "用户查询接口")
    Result<QueryUserResponseDto> query(QueryUserRequestDto requestDto);

    /***
     * 用户登录接口
     *
     * @param requestDto
     * @return
     */
    @Api(name = "用户登录接口")
    Result<LoginResponseDto> login(LoginRequestDto requestDto);

    /****
     * 用户信息修改接口
     *
     * @param requestDto
     * @return
     */
    @Api(name = "用户信息修改接口")
    Result<ModifyUserResponseDto> modify(ModifyUserRequestDto requestDto);

    /****
     * 用户密码修改接口
     *
     * @param requestDto
     * @return
     */
    @Api(name = "用户密码修改接口")
    Result<ModifyPwdResponseDto> modifyPwd(ModifyPwdRequestDto requestDto);

    /***
     * 密码重置接口
     *
     * @param requestDto
     * @return
     */
    @Api(name = "密码重置接口")
    Result<ResetPwdResponseDto> resetPwd(ResetPwdRequestDto requestDto);
}
