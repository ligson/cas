package org.ca.cas.cert.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseRequestDto;

/**
 * Created by ligson on 2016/5/16.
 */
public class GenCsrRequestDto extends BaseRequestDto {
    @Param(name = "subjectDn", required = true)
    private String subjectDn;
    @Param(name = "aliase", required = true)
    private String aliase;
    @Param(name = "adminId", required = true)
    private String adminId;


    public String getSubjectDn() {
        return subjectDn;
    }

    public void setSubjectDn(String subjectDn) {
        this.subjectDn = subjectDn;
    }

    public String getAliase() {
        return aliase;
    }

    public void setAliase(String aliase) {
        this.aliase = aliase;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "GenCsrRequestDto{" +
                "subjectDn='" + subjectDn + '\'' +
                ", aliase='" + aliase + '\'' +
                ", adminId='" + adminId + '\'' +
                '}';
    }
}
