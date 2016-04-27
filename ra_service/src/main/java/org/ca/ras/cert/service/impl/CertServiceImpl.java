package org.ca.ras.cert.service.impl;

import org.ca.ras.cert.dao.CertDao;
import org.ca.ras.cert.domain.CertEntity;
import org.ca.ras.cert.service.CertService;
import org.ligson.fw.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/4/26.
 */
@Repository(value = "certService")
public class CertServiceImpl extends BaseServiceImpl<CertEntity> implements CertService {
    @Resource
    private CertDao certDao;

    @Override
    public void initBaseDao() {
        baseDao = certDao;
    }
}
