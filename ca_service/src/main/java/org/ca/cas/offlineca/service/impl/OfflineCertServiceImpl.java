package org.ca.cas.offlineca.service.impl;

import org.ca.cas.offlineca.dao.OfflineCertDao;
import org.ca.cas.offlineca.domain.OfflineCertEntity;
import org.ca.cas.offlineca.service.OfflineCertService;
import org.ligson.fw.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by ligson on 2016/5/18.
 */
@Component("offlineCertService")
public class OfflineCertServiceImpl extends BaseServiceImpl<OfflineCertEntity> implements OfflineCertService {

    @Resource
    private OfflineCertDao offlineCertDao;

    @PostConstruct
    @Override
    public void initBaseDao() {
        baseDao = offlineCertDao;
    }
}
