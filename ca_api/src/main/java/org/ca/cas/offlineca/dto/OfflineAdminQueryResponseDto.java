package org.ca.cas.offlineca.dto;

import org.ca.cas.offlineca.vo.OfflineAdmin;
import org.ligson.fw.core.facade.base.dto.BaseQueryPageResponseDto;
import org.ligson.fw.core.facade.base.dto.BaseResponseDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 2016/5/20.
 */
public class OfflineAdminQueryResponseDto extends BaseQueryPageResponseDto {
    private OfflineAdmin offlineAdmin;
    private List<OfflineAdmin> offlineAdminList = new ArrayList<>();

    public OfflineAdmin getOfflineAdmin() {
        return offlineAdmin;
    }

    public void setOfflineAdmin(OfflineAdmin offlineAdmin) {
        this.offlineAdmin = offlineAdmin;
    }

    public List<OfflineAdmin> getOfflineAdminList() {
        return offlineAdminList;
    }

    public void setOfflineAdminList(List<OfflineAdmin> offlineAdminList) {
        this.offlineAdminList = offlineAdminList;
    }

    @Override
    public String toString() {
        return "OfflineAdminQueryResponseDto{" +
                "offlineAdmin=" + offlineAdmin +
                ", offlineAdminList=" + offlineAdminList +
                '}';
    }
}
