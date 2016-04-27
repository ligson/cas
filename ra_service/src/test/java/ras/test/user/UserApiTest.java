package ras.test.user;

import org.ca.ras.user.dto.RegisterRequestDto;
import org.ca.ras.user.dto.RegisterResponseDto;
import org.junit.Test;
import org.ligson.fw.core.facade.base.result.Result;
import org.ligson.fw.string.encode.HashHelper;
import ras.test.base.DubboBaseTest;

import java.util.Date;

/**
 * Created by ligson on 2016/4/27.
 */
public class UserApiTest extends DubboBaseTest {
    @Test
    public void register() {
        RegisterRequestDto requestDto = new RegisterRequestDto();
        requestDto.setName("ligson");
        requestDto.setPassword(HashHelper.md5("password"));
        requestDto.setBirth(new Date());
        requestDto.setMobile("18210344122");
        requestDto.setEmail("lijinsheng@lecxe.com");
        requestDto.setSex(true);
        Result<RegisterResponseDto> response = userApi.register(requestDto);
        testResult(response);
        println(response.getData().getId());
    }
}
