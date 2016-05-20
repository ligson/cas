package org.ca.cas.offlineca.dto;

import org.ligson.fw.core.facade.base.dto.BaseRequestDto;


/**
 * Created by ligson on 2016/5/20.
 */
public class OfflineAdminAddRequestDto extends BaseRequestDto {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "OfflineAdminAddRequestDto{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
