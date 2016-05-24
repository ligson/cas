package org.ca.cas.user.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseQueryPageRequestDto;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by ligson on 2016/4/25.
 */
public class QueryUserRequestDto extends BaseQueryPageRequestDto {
    /***
     * id
     */
    @Param(name = "用户id")
    private String id;

    /***
     * 生日
     */
    @Param(name = "用户生日")
    private Date birth;

    /***
     * 姓名
     */
    @Param(name = "姓名")
    private String name;

    /***
     * 密码
     */
    @Param(name = "密码")
    private String password;

    /***
     * 性别
     */
    @Param(name = "性别")
    private Boolean sex;

    /***
     * 状态
     *
     * @see org.ca.common.user.enums.UserState
     */
    @Param(name = "状态")
    private Integer status;

    /***
     * 手机号
     */
    @Param(name = "手机号", mobile = true)
    private String mobile;

    /***
     * 邮箱
     */
    @Param(name = "邮箱", email = true)
    private String email;

    /***
     * 注册日期
     */
    @Param(name = "注册日期")
    private Date registerDate;

    /***
     * 用户头像绝对http://
     */
    @Param(name = "用户头像")
    private String photo;

    /***
     * 角色
     *
     * @see org.ca.common.user.enums.UserRole
     */
    @Param(name = "角色")
    private Integer role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
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

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "QueryUserRequestDto{" +
                "id=" + id +
                ", birth=" + birth +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", status=" + status +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", registerDate=" + registerDate +
                ", photo='" + photo + '\'' +
                ", role=" + role +
                '}';
    }
}
