package org.ca.cas.cert.dto;

import org.ca.cas.cert.vo.RevokeCert;
import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseQueryPageResponseDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 2016/5/17.
 */
public class RevokeListResponseDto extends BaseQueryPageResponseDto {
    @Param(name = "吊销记录")
    private RevokeCert revokeCert;
    @Param(name = "吊销记录列表")
    private List<RevokeCert> revokeCertList = new ArrayList<>();

    public RevokeCert getRevokeCert() {
        return revokeCert;
    }

    public void setRevokeCert(RevokeCert revokeCert) {
        this.revokeCert = revokeCert;
    }

    public List<RevokeCert> getRevokeCertList() {
        return revokeCertList;
    }

    public void setRevokeCertList(List<RevokeCert> revokeCertList) {
        this.revokeCertList = revokeCertList;
    }
}
