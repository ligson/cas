package org.ca.ras.user.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.ligson.fw.core.common.idgenerator.DateRandomGenerator;
import org.ligson.fw.core.entity.BasicEntity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by ligson on 2016/4/25.
 */
@Entity
@Table(name = "ra_user")
public class UserEntity extends BasicEntity {
    /***
     * id
     */
    private BigInteger id;

    /***
     * 生日
     */
    private Date birth;

    /***
     * 姓名
     */
    private String name;

    /***
     * 密码
     */
    private String password;

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

    @Id
    @GeneratedValue(generator = "dr.id")
    @GenericGenerator(name = "dr.id", strategy = "org.ligson.fw.core.common.idgenerator.DateRandomGenerator")
    @Column(length = 32, precision = 32, scale = 0)
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Column(columnDefinition = "datetime comment '出生日期'")
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Column(columnDefinition = "varchar(32) comment '登陆名'")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(columnDefinition = "varchar(255) comment '密码'")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(columnDefinition = "tinyint comment '性别'")
    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    @Column(columnDefinition = "int comment '状态'")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(columnDefinition = "varchar(32) comment '手机号'")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(columnDefinition = "varchar(128) comment '邮箱'")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "register_date", columnDefinition = "datetime comment '注册日期'")
    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Column(columnDefinition = "varchar(255) comment '头像'")
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
