package org.ca.ras.user.service.impl;

import org.ca.ras.user.dao.UserDao;
import org.ca.ras.user.domain.UserEntity;
import org.ca.ras.user.service.UserService;
import org.ligson.fw.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by ligson on 2016/4/26.
 */
@Repository(value = "userService")
public class UserServiceImpl extends BaseServiceImpl<UserEntity> implements UserService{
    private UserDao userDao;

    @Override
    public void initBaseDao() {
        baseDao = userDao;
    }
}
