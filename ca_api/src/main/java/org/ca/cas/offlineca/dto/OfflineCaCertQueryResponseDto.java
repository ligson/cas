package org.ca.cas.offlineca.dto;

import org.ca.cas.offlineca.vo.OfflineCaCert;
import org.ligson.fw.core.facade.base.dto.BaseQueryPageResponseDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 2016/5/18.
 */
public class OfflineCaCertQueryResponseDto extends BaseQueryPageResponseDto {
    private OfflineCaCert offlineCaCert;
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
