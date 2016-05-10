package org.ca.cas.cert.biz;

import org.ca.cas.cert.domain.CertEntity;
import org.ca.cas.cert.dto.QueryCertRequestDto;
import org.ca.cas.cert.dto.QueryCertResponseDto;
import org.ca.cas.cert.service.CertService;
import org.ca.cas.cert.vo.Cert;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.entity.Pagination;
import org.ligson.fw.core.facade.annotation.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 2016/5/6.
 */
@Component("queryCertBiz")
@Api(name = "查询证书接口")
public class QueryCertBiz extends AbstractBiz<QueryCertRequestDto, QueryCertResponseDto> {
    @Resource
    private CertService certService;

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
        CertEntity entity = new CertEntity();
        BeanUtils.copyProperties(requestDto, entity);
        entity.setPageAble(requestDto.getPageAble());
        entity.setOffset((requestDto.getPageNum() - 1) * requestDto.getPageSize());
        entity.setMax(requestDto.getPageSize());

        Pagination<CertEntity> pagination = certService.findAllByEqAnd(entity);
        if (requestDto.getPageAble()) {
            List<CertEntity> entities = pagination.getDatas();
            List<Cert> certList = new ArrayList<>();
            for (CertEntity certEntity : entities) {
                Cert cert = new Cert();
                BeanUtils.copyProperties(certEntity, cert);
                certList.add(cert);
            }
            responseDto.setCerts(certList);
        } else {
            List<CertEntity> entities = pagination.getDatas();
            if (!CollectionUtils.isEmpty(entities)) {
                CertEntity certEntity = entities.get(0);
                Cert cert = new Cert();
                BeanUtils.copyProperties(certEntity, cert);
                responseDto.setCert(cert);
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
