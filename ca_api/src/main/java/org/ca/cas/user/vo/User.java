package org.ca.cas.user.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by ligson on 2016/4/25.
 */
public class User implements Serializable {
    /***
     * id
     */
    private String id;

    /***
     * 生日
     */
    private Date birth;

    /***
     * 姓名
     */
    private String name;

    /***
     * 性别
     */
    private Boolean sex;

    /***
     * 状态
     *
     * @see org.ca.common.user.enums.UserState
     */
    private Integer status;

    /***
     * 手机号
     */
    private String mobile;

    /***
     * 邮箱
     */
    private String email;

    /***
     * 注册日期
     */
    private Date registerDate;

    /***
     * 用户头像绝对http://
     */
    private String photo;

    /***
     * @see org.ca.common.user.enums.UserRole
     */
    private Integer role;

    /***
     * 父帐号
     */
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

    public String getFatherUserId() {
        return fatherUserId;
    }

    public void setFatherUserId(String fatherUserId) {
        this.fatherUserId = fatherUserId;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", birth=" + birth +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", status=" + status +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", registerDate=" + registerDate +
                ", photo='" + photo + '\'' +
                ", role=" + role +
                ", fatherUserId='" + fatherUserId + '\'' +
                '}';
    }
}
