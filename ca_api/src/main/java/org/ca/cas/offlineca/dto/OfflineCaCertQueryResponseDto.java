package org.ca.cas.offlineca.dto;

import org.ca.cas.offlineca.vo.OfflineCaCert;
import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseQueryPageResponseDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 2016/5/18.
 */
public class OfflineCaCertQueryResponseDto extends BaseQueryPageResponseDto {
    @Param(name = "离线CA证书", description = "非分页时使用")
    private OfflineCaCert offlineCaCert;
    @Param(name = "离线CA证书列表", description = "分页时使用")
    private List<OfflineCaCert> offlineCaCerts = new ArrayList<>();

    public OfflineCaCert getOfflineCaCert() {
        return offlineCaCert;
    }

    public void setOfflineCaCert(OfflineCaCert offlineCaCert) {
        this.offlineCaCert = offlineCaCert;
    }

    public List<OfflineCaCert> getOfflineCaCerts() {
        return offlineCaCerts;
    }

    public void setOfflineCaCerts(List<OfflineCaCert> offlineCaCerts) {
        this.offlineCaCerts = offlineCaCerts;
    }

}
