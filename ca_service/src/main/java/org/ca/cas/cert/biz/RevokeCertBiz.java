package org.ca.cas.cert.biz;

import org.ca.cas.cert.domain.CertEntity;
import org.ca.cas.cert.domain.CertRevokeEntity;
import org.ca.cas.cert.dto.RevokeCertRequestDto;
import org.ca.cas.cert.dto.RevokeCertResponseDto;
import org.ca.cas.cert.enums.CertFailEnum;
import org.ca.cas.cert.service.CertRevokeService;
import org.ca.cas.cert.service.CertService;
import org.ca.common.cert.enums.CertStatus;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.facade.annotation.Api;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by ligson on 2016/5/16.
 */
@Component("revokeCertBiz")
@Api(name = "吊销证书接口")
public class RevokeCertBiz extends AbstractBiz<RevokeCertRequestDto, RevokeCertResponseDto> {

    @Resource
    private CertRevokeService certRevokeService;
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
        CertEntity certEntity = certService.get(requestDto.getCertId());
        if (certEntity == null) {
            setFailureResult(CertFailEnum.E_BIZ_21009);
            return false;
        }
        context.setAttr("entity", certEntity);
        if (certEntity.getStatus() != CertStatus.VALID.getCode()) {
            setFailureResult(CertFailEnum.E_BIZ_21010);
            return false;
        }
        return true;
    }

    @Override
    public Boolean txnPreProcessing() {
        CertEntity entity = (CertEntity) context.getAttr("entity");
        CertRevokeEntity revokeEntity = new CertRevokeEntity();
        revokeEntity.setCertId(requestDto.getCertId());
        revokeEntity.setAdminId(requestDto.getAdminId());
        revokeEntity.setCertIssuerHashMd5(entity.getIssuerDnHashMd5());
        revokeEntity.setCertRevokeDate(new Date());
        revokeEntity.setCertNotAfter(entity.getNotAfter());
        revokeEntity.setCertRevokeReason(requestDto.getRevokeReason());
        revokeEntity.setCertSerialNumber(entity.getSerialNumber());
        context.setAttr("revokeEntity", revokeEntity);
        return true;
    }

    @Override
    public Boolean persistence() {
        CertEntity entity = context.getAttr("entity", CertEntity.class);
        CertRevokeEntity revokeEntity = context.getAttr("revokeEntity", CertRevokeEntity.class);
        certRevokeService.add(revokeEntity);
        entity.setStatus(CertStatus.REVOKE.getCode());
        certService.update(entity);
        responseDto.setSuccess(true);
        setSuccessResult();
        return true;
    }

    @Override
    public void after() {

    }
}
