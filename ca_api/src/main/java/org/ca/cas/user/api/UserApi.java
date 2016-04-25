package org.ca.cas.user.api;

import org.ca.cas.user.dto.QueryUserRequestDto;
import org.ca.cas.user.dto.QueryUserResponseDto;
import org.ca.cas.user.dto.RegisterRequestDto;
import org.ca.cas.user.dto.RegisterResponseDto;
import org.ligson.fw.core.facade.base.result.Result;

/**
 * Created by ligson on 2016/4/25.
 */
public interface UserApi {

    Result<RegisterResponseDto> register(RegisterRequestDto requestDto);

    Result<QueryUserResponseDto> query(QueryUserRequestDto requestDto);
}
