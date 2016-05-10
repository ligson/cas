package org.ca.cas.user.dto;

import org.ca.cas.user.vo.User;
import org.ligson.fw.core.facade.base.dto.BaseResponseDto;

/**
 * Created by ligson on 2016/4/26.
 */
public class LoginResponseDto extends BaseResponseDto {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LoginResponseDto{" +
                "user=" + user +
                '}';
    }
}
