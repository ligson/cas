package org.ca.cas.offlineca.biz;

import org.ca.cas.offlineca.domain.OfflineCertEntity;
import org.ca.cas.offlineca.dto.OfflineCaCertQueryRequestDto;
import org.ca.cas.offlineca.dto.OfflineCaCertQueryResponseDto;
import org.ca.cas.offlineca.service.OfflineCertService;
import org.ca.cas.offlineca.vo.OfflineCaCert;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.common.utils.BeanCopy;
import org.ligson.fw.core.entity.Pagination;
import org.ligson.fw.core.facade.annotation.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 2016/5/18.
 */
@Component("offlineCaCertQueryBiz")
@Api(name = "离线CA证书查询接口")
public class OfflineCaCertQueryBiz extends AbstractBiz<OfflineCaCertQueryRequestDto, OfflineCaCertQueryResponseDto> {
    @Resource
    private OfflineCertService offlineCertService;

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
        OfflineCertEntity entity = new OfflineCertEntity();
        BeanCopy.copyPropertiesIgnoreNull(requestDto, entity);
        entity.setPageAble(requestDto.getPageAble());
        entity.setOffset((requestDto.getPageNum() - 1) * requestDto.getPageSize());
        entity.setMax(requestDto.getPageSize());
        Pagination<OfflineCertEntity> pagination = offlineCertService.findAllByEqAnd(entity);
        if (requestDto.getPageAble()) {
            List<OfflineCertEntity> entities = pagination.getDatas();
            List<OfflineCaCert> certList = new ArrayList<>();
            for (OfflineCertEntity certEntity : entities) {
                OfflineCaCert cert = new OfflineCaCert();
                BeanUtils.copyProperties(certEntity, cert);
                certList.add(cert);
            }
            responseDto.setOfflineCaCerts(certList);
        } else {
            List<OfflineCertEntity> entities = pagination.getDatas();
            if (!CollectionUtils.isEmpty(entities)) {
                OfflineCertEntity certEntity = entities.get(0);
                OfflineCaCert cert = new OfflineCaCert();
                BeanUtils.copyProperties(certEntity, cert);
                responseDto.setOfflineCaCert(cert);
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
