package org.ca.cas.cert.dao.impl;

import org.ca.cas.cert.dao.CertRevokeDao;
import org.ca.cas.cert.domain.CertRevokeEntity;
import org.ligson.fw.core.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by ligson on 2016/5/16.
 */
@Repository(value = "certRevokeDao")
public class CertRevokeDaoImpl extends BaseDaoImpl<CertRevokeEntity> implements CertRevokeDao {
}
