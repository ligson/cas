package org.ca.cas.offlineca.dto;

import org.ca.common.user.enums.UserRole;
import org.ca.common.user.enums.UserState;
import org.ligson.fw.core.facade.base.dto.BaseQueryPageRequestDto;

import java.util.Date;

/**
 * Created by ligson on 2016/5/20.
 */
public class OfflineAdminQueryRequestDto extends BaseQueryPageRequestDto {
    private String id;
    private String name;
    private String password;
    /***
     * @see UserState#getCode()
     */
    private Integer status;
    /***
     * @see UserRole#getCode()
     */
    private Integer role;

    private Date createDate;
    private Date lastModifyDate;
    private Date lastLoginDate;
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
