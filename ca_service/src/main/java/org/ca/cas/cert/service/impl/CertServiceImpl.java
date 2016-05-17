package org.ca.cas.cert.service.impl;

import org.ca.cas.cert.dao.CertDao;
import org.ca.cas.cert.domain.CertEntity;
import org.ca.cas.cert.service.CertService;
import org.ca.common.cert.enums.CertType;
import org.ligson.fw.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by ligson on 2016/4/26.
 */
@Repository(value = "certService")
public class CertServiceImpl extends BaseServiceImpl<CertEntity> implements CertService {
    @Resource
    private CertDao certDao;

    @PostConstruct
    @Override
    public void initBaseDao() {
        baseDao = certDao;
    }

    @Override
    public CertEntity findSignCertByUserId(String adminId) {
        CertEntity entity = new CertEntity();
        entity.setCertType(CertType.SIGN.getCode());
        entity.setUserId(adminId);
        return certDao.findByAnd(entity);
    }
}
