package org.ca.cas.offlineca.dto;

import org.ca.common.user.enums.UserRole;
import org.ca.common.user.enums.UserState;
import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseQueryPageRequestDto;

import java.util.Date;

/**
 * Created by ligson on 2016/5/20.
 */
public class OfflineAdminQueryRequestDto extends BaseQueryPageRequestDto {
    @Param(name = "管理员id")
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
    /***
     * @see UserRole#getCode()
     */
    @Param(name = "用户角色")
    private Integer role;

    @Param(name = "创建日期")
    private Date createDate;
    @Param(name = "最后修改日期")
    private Date lastModifyDate;
    @Param(name = "最后登陆日期")
    private Date lastLoginDate;
    @Param(name = "最后登陆IP")
    private String lastLoginIp;

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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    @Override
    public String toString() {
        return "OfflineAdmin{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", role=" + role +
                ", createDate=" + createDate +
                ", lastModifyDate=" + lastModifyDate +
                ", lastLoginDate=" + lastLoginDate +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                '}';
    }
}
