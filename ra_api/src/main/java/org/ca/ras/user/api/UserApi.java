package org.ca.ras.user.api;

import org.ca.ras.cert.dto.LoginRequestDto;
import org.ca.ras.cert.dto.LoginResponseDto;
import org.ca.ras.user.dto.QueryUserRequestDto;
import org.ca.ras.user.dto.QueryUserResponseDto;
import org.ca.ras.user.dto.RegisterRequestDto;
import org.ca.ras.user.dto.RegisterResponseDto;
import org.ligson.fw.core.facade.base.result.Result;

/**
 * Created by ligson on 2016/4/25.
 */
public interface UserApi {

    Result<RegisterResponseDto> register(RegisterRequestDto requestDto);

    Result<QueryUserResponseDto> query(QueryUserRequestDto requestDto);

    Result<LoginResponseDto> login(LoginRequestDto requestDto);
}
