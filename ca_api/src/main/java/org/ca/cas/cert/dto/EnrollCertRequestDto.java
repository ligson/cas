package org.ca.cas.cert.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseRequestDto;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by ligson on 2016/5/4.
 * 提交证书申请
 */
public class EnrollCertRequestDto extends BaseRequestDto {
    @Param(name = "用户id", required = true)
    private String userId;
    @Param(name = "颁发给", required = true)
    private String subjectDn;
    @Param(name = "颁发给哈希", required = true)
    private String subjectDnHashMd5;
    @Param(name = "证书密码", required = true)
    private String certPin;
    @Param(name = "颁发者", required = true)
    private String issueDn;
    @Param(name = "颁发者哈希", required = true)
    private String issueDnHashMd5;
    @Param(name = "开始日期", required = true)
    private Date startDate;
    @Param(name = "keyId", required = true)
    private String keyId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubjectDn() {
        return subjectDn;
    }

    public void setSubjectDn(String subjectDn) {
        this.subjectDn = subjectDn;
    }

    public String getSubjectDnHashMd5() {
        return subjectDnHashMd5;
    }

    public void setSubjectDnHashMd5(String subjectDnHashMd5) {
        this.subjectDnHashMd5 = subjectDnHashMd5;
    }

    public String getCertPin() {
        return certPin;
    }

    public void setCertPin(String certPin) {
        this.certPin = certPin;
    }

    public String getIssueDn() {
        return issueDn;
    }

    public void setIssueDn(String issueDn) {
        this.issueDn = issueDn;
    }

    public String getIssueDnHashMd5() {
        return issueDnHashMd5;
    }

    public void setIssueDnHashMd5(String issueDnHashMd5) {
        this.issueDnHashMd5 = issueDnHashMd5;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    @Override
    public String toString() {
        return "EnrollCertRequestDto{" +
                "userId=" + userId +
                ", subjectDn='" + subjectDn + '\'' +
                ", subjectDnHashMd5='" + subjectDnHashMd5 + '\'' +
                ", certPin='" + certPin + '\'' +
                ", issueDn='" + issueDn + '\'' +
                ", issueDnHashMd5='" + issueDnHashMd5 + '\'' +
                ", startDate=" + startDate +
                ", keyId=" + keyId +
                '}';
    }
}
