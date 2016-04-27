package org.ca.ras.cert.dao.impl;

import org.ca.ras.cert.dao.CertDao;
import org.ca.ras.cert.domain.CertEntity;
import org.ligson.fw.core.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by ligson on 2016/4/26.
 */
@Repository(value = "certDao")
public class CertDaoImpl extends BaseDaoImpl<CertEntity> implements CertDao {
}
