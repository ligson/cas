package org.ca.cas.offlineca.domain;

import org.ca.common.user.enums.UserRole;
import org.ca.common.user.enums.UserState;
import org.hibernate.annotations.GenericGenerator;
import org.ligson.fw.core.entity.BasicEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ligson on 2016/5/20.
 */
@Table(name = "offline_admin")
@Entity
public class OfflineAdminEntity extends BasicEntity {

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

    @Id
    @GeneratedValue(generator = "dr.id")
    @GenericGenerator(name = "dr.id", strategy = "org.ligson.fw.core.common.idgenerator.DateRandomGenerator")
    @Column(length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "last_modify_date")
    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    @Column(name = "last_login_date")
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Column(name = "last_login_ip")
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    @Override
    public String toString() {
        return "OfflineAdminEntity{" +
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
