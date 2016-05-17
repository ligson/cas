package org.ca.cas.cert.dao.impl;

import org.ca.cas.cert.dao.BaseCrlDao;
import org.ca.cas.cert.domain.BaseCrlEntity;
import org.ligson.fw.core.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by ligson on 2016/5/17.
 */
@Repository("baseCrlDao")
public class BaseCrlDaoImpl extends BaseDaoImpl<BaseCrlEntity> implements BaseCrlDao {
}
