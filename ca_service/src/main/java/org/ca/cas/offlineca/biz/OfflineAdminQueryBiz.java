package org.ca.cas.offlineca.biz;

import org.ca.cas.offlineca.domain.OfflineAdminEntity;
import org.ca.cas.offlineca.domain.OfflineCertEntity;
import org.ca.cas.offlineca.dto.OfflineAdminQueryRequestDto;
import org.ca.cas.offlineca.dto.OfflineAdminQueryResponseDto;
import org.ca.cas.offlineca.service.OfflineAdminService;
import org.ca.cas.offlineca.vo.OfflineAdmin;
import org.ca.cas.offlineca.vo.OfflineCaCert;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.common.utils.BeanCopy;
import org.ligson.fw.core.entity.Pagination;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 2016/5/20.
 */
@Component("offlineAdminQueryBiz")
public class OfflineAdminQueryBiz extends AbstractBiz<OfflineAdminQueryRequestDto, OfflineAdminQueryResponseDto> {
    @Resource
    private OfflineAdminService offlineAdminService;

    @Override
    public void before() {

    }

    @Override
    public Boolean paramCheck() {
        return null;
    }

    @Override
    public Boolean bizCheck() {
        return null;
    }

    @Override
    public Boolean txnPreProcessing() {
        OfflineAdminEntity entity = new OfflineAdminEntity();
        BeanCopy.copyPropertiesIgnoreNull(requestDto, entity);
        entity.setPageAble(requestDto.getPageAble());
        entity.setOffset((requestDto.getPageNum() - 1) * requestDto.getPageSize());
        entity.setMax(requestDto.getPageSize());
        Pagination<OfflineAdminEntity> pagination = offlineAdminService.findAllByEqAnd(entity);
        if (requestDto.getPageAble()) {
            List<OfflineAdminEntity> entities = pagination.getDatas();
            List<OfflineAdmin> adminList = new ArrayList<>();
            for (OfflineAdminEntity adminEntity : entities) {
                OfflineAdmin admin = new OfflineAdmin();
                BeanUtils.copyProperties(adminEntity, admin);
                adminList.add(admin);
            }
            responseDto.setOfflineAdminList(adminList);
        } else {
            List<OfflineAdminEntity> entities = pagination.getDatas();
            if (!CollectionUtils.isEmpty(entities)) {
                OfflineAdminEntity adminEntity = entities.get(0);
                OfflineAdmin admin = new OfflineAdmin();
                BeanUtils.copyProperties(adminEntity, admin);
                responseDto.setOfflineAdmin(admin);
            }
        }
        responseDto.setPageNum(requestDto.getPageNum());
        responseDto.setPageSize(requestDto.getPageSize());
        responseDto.setTotalCount(pagination.getTotalCount());
        responseDto.setSuccess(true);
        setSuccessResult();
        return true;
    }

    @Override
    public Boolean persistence() {
        return null;
    }

    @Override
    public void after() {

    }
}
