package org.ca.cas.cert.biz;

import org.apache.commons.codec.binary.Base64;
import org.ca.cas.cert.biz.core.MakeCrlBiz;
import org.ca.cas.cert.domain.BaseCrlEntity;
import org.ca.cas.cert.domain.CertEntity;
import org.ca.cas.cert.domain.CertRevokeEntity;
import org.ca.cas.cert.dto.GenCrlRequestDto;
import org.ca.cas.cert.dto.GenCrlResponseDto;
import org.ca.cas.cert.enums.CertFailEnum;
import org.ca.cas.cert.service.BaseCrlService;
import org.ca.cas.cert.service.CertRevokeService;
import org.ca.cas.cert.service.CertService;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.entity.Pagination;
import org.ligson.fw.core.facade.annotation.Api;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.security.cert.CRLException;
import java.security.cert.X509CRL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ligson on 2016/5/17.
 */
@Api(name = "生成crl接口")
@Component("genCrlBiz")
public class GenCrlBiz extends AbstractBiz<GenCrlRequestDto, GenCrlResponseDto> {
    @Resource
    private MakeCrlBiz makeCrlBiz;
    @Resource
    private CertRevokeService certRevokeService;
    @Resource
    private BaseCrlService baseCrlService;
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
        CertEntity certEntity = certService.findSignCertByUserId(requestDto.getAdminId());
        context.setAttr("caCert", certEntity);
        BaseCrlEntity baseCrlEntity = new BaseCrlEntity();
        baseCrlEntity.setCertSerialNumber(certEntity.getSerialNumber());
        baseCrlEntity.setOrder("lastUpdate");
        baseCrlEntity.setOrder("asc");
        baseCrlEntity.setPageAble(false);
        Pagination<BaseCrlEntity> pagination = baseCrlService.findAllByEqAnd(baseCrlEntity);
        if (pagination.getDatas().size() > 0) {
            context.setAttr("lastBaseCrlEntity", pagination.getDatas().get(0));
        }
        return true;
    }

    @Override
    public Boolean txnPreProcessing() {
        CertEntity caCert = context.getAttr("caCert", CertEntity.class);
        BaseCrlEntity baseCrlEntity = context.getAttr("lastBaseCrlEntity", BaseCrlEntity.class);
        CertRevokeEntity revokeEntity = new CertRevokeEntity();
        revokeEntity.setAdminId(requestDto.getAdminId());
        revokeEntity.setSort("certRevokeDate");
        revokeEntity.setOrder("asc");
        revokeEntity.setPageAble(false);
        Pagination<CertRevokeEntity> pagination = certRevokeService.findAllByEqAnd(revokeEntity);
        List<CertRevokeEntity> entities = pagination.getDatas();
        List<CertRevokeEntity> revokeEntities = new ArrayList<>();
        if (baseCrlEntity != null) {
            boolean start = false;
            for (CertRevokeEntity certRevokeEntity : entities) {
                if (start) {
                    revokeEntities.add(certRevokeEntity);
                }
                if (certRevokeEntity.getCertSerialNumber().equals(baseCrlEntity.getRevokeIdEnd())) {
                    start = true;
                }
            }
        } else {
            revokeEntities = entities;
        }
        if (revokeEntities.size() == 0) {
            setFailureResult(CertFailEnum.E_BIZ_21012);
            return false;
        }
        Date thisDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(thisDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date nextDate = calendar.getTime();
        X509CRL crl = makeCrlBiz.genCrl(caCert, revokeEntities, thisDate, nextDate);
        if (crl != null) {
            BaseCrlEntity baseCrlEntity1 = new BaseCrlEntity();
            baseCrlEntity1.setCertSerialNumber(caCert.getSerialNumber());
            try {
                baseCrlEntity1.setCrlBuf(Base64.encodeBase64String(crl.getEncoded()));
            } catch (CRLException e) {
                e.printStackTrace();
                setFailureResult(CertFailEnum.E_BIZ_21003);
                return false;
            }
            baseCrlEntity1.setBufSign(Base64.encodeBase64String(crl.getSignature()));
            baseCrlEntity1.setLastUpdate(thisDate);
            baseCrlEntity1.setNextUpdate(nextDate);
            baseCrlEntity1.setVersion("3");
            baseCrlEntity1.setRevokeIdEnd(revokeEntities.get(revokeEntities.size() - 1).getCertSerialNumber());
            baseCrlService.add(baseCrlEntity1);
            setSuccessResult();
        } else {
            setFailureResult(CertFailEnum.E_BIZ_21003);
            return false;
        }
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
