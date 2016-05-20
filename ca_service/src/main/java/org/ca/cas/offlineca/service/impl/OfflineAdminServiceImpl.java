package org.ca.cas.offlineca.service.impl;

import org.ca.cas.offlineca.dao.OfflineAdminDao;
import org.ca.cas.offlineca.domain.OfflineAdminEntity;
import org.ca.cas.offlineca.service.OfflineAdminService;
import org.ligson.fw.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by ligson on 2016/5/20.
 */
@Component("offlineAdminService")
public class OfflineAdminServiceImpl extends BaseServiceImpl<OfflineAdminEntity> implements OfflineAdminService {
    @Resource
    private OfflineAdminDao offlineAdminDao;

    @PostConstruct
    @Override
    public void initBaseDao() {
        baseDao = offlineAdminDao;
    }
}
