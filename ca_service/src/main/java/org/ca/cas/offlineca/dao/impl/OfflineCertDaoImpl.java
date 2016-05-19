package org.ca.cas.offlineca.dao.impl;

import org.ca.cas.offlineca.dao.OfflineCertDao;
import org.ca.cas.offlineca.domain.OfflineCertEntity;
import org.ligson.fw.core.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by ligson on 2016/5/18.
 */
@Repository("offlineCertDao")
public class OfflineCertDaoImpl extends BaseDaoImpl<OfflineCertEntity> implements OfflineCertDao {
}
