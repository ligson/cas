package org.ca.cas.user.api;

import org.ca.cas.user.dto.*;
import org.ligson.fw.core.facade.base.result.Result;

/**
 * Created by ligson on 2016/4/25.
 */
public interface UserApi {

    Result<RegisterResponseDto> register(RegisterRequestDto requestDto);

    Result<QueryUserResponseDto> query(QueryUserRequestDto requestDto);

    Result<LoginResponseDto> login(LoginRequestDto requestDto);

    Result<ModifyUserResponseDto> modify(ModifyUserRequestDto requestDto);

    Result<ModifyPwdResponseDto> modifyPwd(ModifyPwdRequestDto requestDto);

    Result<ResetPwdResponseDto> resetPwd(ResetPwdRequestDto requestDto);
}
