package org.ca.cas.cert.service.impl;

import org.ca.cas.cert.dao.BaseCrlDao;
import org.ca.cas.cert.domain.BaseCrlEntity;
import org.ca.cas.cert.service.BaseCrlService;
import org.ligson.fw.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by ligson on 2016/5/17.
 */
@Component("baseCrlService")
public class BaseCrlServiceImpl extends BaseServiceImpl<BaseCrlEntity> implements BaseCrlService {
    @Resource
    private BaseCrlDao baseCrlDao;

    @PostConstruct
    @Override
    public void initBaseDao() {
        baseDao = baseCrlDao;
    }
}
