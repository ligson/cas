package org.ca.cas.cert.service.impl;

import org.ca.cas.cert.dao.CertRevokeDao;
import org.ca.cas.cert.domain.CertRevokeEntity;
import org.ca.cas.cert.service.CertRevokeService;
import org.ligson.fw.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by ligson on 2016/5/16.
 */
@Repository(value = "certRevokeService")
public class CertRevokeServiceImpl extends BaseServiceImpl<CertRevokeEntity> implements CertRevokeService {

    @Resource
    private CertRevokeDao certRevokeDao;

    @PostConstruct
    @Override
    public void initBaseDao() {
        baseDao = certRevokeDao;
    }
}
