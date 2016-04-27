package org.ca.ras.user.dao.impl;

import org.ca.ras.user.dao.UserDao;
import org.ca.ras.user.domain.UserEntity;
import org.ligson.fw.core.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/4/26.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<UserEntity> implements UserDao {
}
