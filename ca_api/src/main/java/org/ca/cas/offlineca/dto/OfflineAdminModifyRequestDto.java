package org.ca.cas.offlineca.dto;

import org.ca.common.user.enums.UserState;
import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseQueryPageRequestDto;


/**
 * Created by ligson on 2016/5/20.
 */
public class OfflineAdminModifyRequestDto extends BaseQueryPageRequestDto {
    @Param(name = "管理员id", required = true)
    private String id;
    @Param(name = "用户名")
    private String name;
    @Param(name = "密码")
    private String password;
    /***
     * @see UserState#getCode()
     */
    @Param(name = "用户状态")
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OfflineAdminModifyRequestDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
}
