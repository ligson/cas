package org.ca.cas.cert.biz;

import org.ca.cas.cert.domain.CertEntity;
import org.ca.cas.cert.domain.CertRevokeEntity;
import org.ca.cas.cert.dto.RevokeCertRequestDto;
import org.ca.cas.cert.dto.RevokeCertResponseDto;
import org.ca.cas.cert.dto.RevokeListRequestDto;
import org.ca.cas.cert.dto.RevokeListResponseDto;
import org.ca.cas.cert.service.CertRevokeService;
import org.ca.cas.cert.service.CertService;
import org.ca.cas.cert.vo.RevokeCert;
import org.ca.cas.user.domain.UserEntity;
import org.ca.cas.user.service.UserService;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.common.utils.BeanCopy;
import org.ligson.fw.core.entity.Pagination;
import org.ligson.fw.core.facade.annotation.Api;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 2016/5/17.
 */
@Api(name = "吊销证书查询接口")
@Component("revokeListBiz")
public class RevokeListBiz extends AbstractBiz<RevokeListRequestDto, RevokeListResponseDto> {
    @Resource
    private CertRevokeService certRevokeService;
    @Resource
    private CertService certService;
    @Resource
    private UserService userService;

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
        CertRevokeEntity entity = new CertRevokeEntity();
        BeanCopy.copyPropertiesIgnoreNull(requestDto, entity);
        entity.setPageAble(requestDto.getPageAble());
        entity.setOffset((requestDto.getPageNum() - 1) * requestDto.getPageSize());
        entity.setMax(requestDto.getPageSize());
        Pagination<CertRevokeEntity> pagination = certRevokeService.findAllByEqAnd(entity);
        List<CertRevokeEntity> datas = pagination.getDatas();
        List<RevokeCert> revokeCerts = new ArrayList<>();
        for (CertRevokeEntity entity1 : datas) {
            RevokeCert revokeCert = new RevokeCert();
            revokeCert.setCertId(entity1.getCertId());
            revokeCert.setAdminId(entity1.getAdminId());
            revokeCert.setRevokeReason(entity1.getCertRevokeReason());
            revokeCert.setRevokeDate(entity1.getCertRevokeDate());
            revokeCert.setCertSerialNumber(entity1.getCertSerialNumber());
            revokeCert.setId(entity1.getId());
            CertEntity certEntity = certService.get(entity1.getCertId());
            revokeCert.setCertSubjectDn(certEntity.getSubjectDn());
            revokeCert.setCertIssueDn(certEntity.getIssuerDn());
            if (entity.getAdminId() != null) {
                UserEntity userEntity = userService.get(entity.getAdminId());
                revokeCert.setAdminName(userEntity.getName());
            }
            revokeCerts.add(revokeCert);
        }
        if (requestDto.getPageAble()) {
            responseDto.setRevokeCertList(revokeCerts);
        } else {
            if (revokeCerts.size() > 0) {
                responseDto.setRevokeCert(revokeCerts.get(0));
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
