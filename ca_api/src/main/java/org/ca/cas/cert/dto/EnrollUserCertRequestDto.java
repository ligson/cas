package org.ca.cas.cert.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseRequestDto;

import java.util.Date;

/**
 * Created by ligson on 2016/5/16.
 */
public class EnrollUserCertRequestDto extends BaseRequestDto {
    @Param(name = "用户id", required = true)
    private String userId;
    @Param(name = "证书密码", required = true)
    private String certPin;
    @Param(name = "颁发者", required = true)
    private String issueDn;
    @Param(name = "颁发者哈希", required = true)
    private String issueDnHashMd5;
    @Param(name = "开始日期", required = true)
    private Date startDate;
    @Param(name = "csr", required = false)
    private String csr;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getCsr() {
        return csr;
    }

    public void setCsr(String csr) {
        this.csr = csr;
    }
}
