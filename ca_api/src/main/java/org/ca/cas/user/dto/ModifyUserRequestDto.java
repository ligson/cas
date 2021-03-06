package org.ca.cas.user.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseRequestDto;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by ligson on 2016/4/25.
 */
public class ModifyUserRequestDto extends BaseRequestDto {
    /***
     * id
     */
    @Param(name = "用户id", required = true)
    private String id;

    /***
     * 生日
     */
    @Param(name = "生日")
    private Date birth;

    /***
     * 姓名
     */
    @Param(name = "姓名")
    private String name;

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
    @Param(name = "手机号", required = false, mobile = true)
    private String mobile;

    /***
     * 邮箱
     */
    @Param(name = "邮箱", required = false, email = true)
    private String email;

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
    @Param(name = "用户角色")
    private Integer role;

    /***
     * 父帐号
     */
    @Param(name = "用户父帐号")
    private String fatherUserId;

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

    public String getFatherUserId() {
        return fatherUserId;
    }

    public void setFatherUserId(String fatherUserId) {
        this.fatherUserId = fatherUserId;
    }

    @Override
    public String toString() {
        return "ModifyUserRequestDto{" +
                "id='" + id + '\'' +
                ", birth=" + birth +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", status=" + status +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", role=" + role +
                ", fatherUserId='" + fatherUserId + '\'' +
                '}';
    }
}
