package org.ca.cas.user.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseRequestDto;

import java.math.BigInteger;

/**
 * Created by ligson on 2016/5/12.
 */
public class ResetPwdRequestDto extends BaseRequestDto {
    @Param(name = "用户id", required = true)
    private BigInteger userId;
    @Param(name = "新密码", required = true)
    private String newPwd;
    @Param(name = "操作者", required = true)
    private BigInteger operatorId;
    @Param(name = "重置原因", required = false)
    private String remark;

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public BigInteger getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(BigInteger operatorId) {
        this.operatorId = operatorId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ResetPwdRequestDto{" +
                "userId=" + userId +
                ", newPwd='" + newPwd + '\'' +
                ", operatorId=" + operatorId +
                ", remark='" + remark + '\'' +
                '}';
    }
}
