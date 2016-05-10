package org.ca.cas.user.service.impl;

import org.ca.cas.user.dao.UserDao;
import org.ca.cas.user.domain.UserEntity;
import org.ca.cas.user.service.UserService;
import org.ligson.fw.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by ligson on 2016/4/26.
 */
@Repository(value = "userService")
public class UserServiceImpl extends BaseServiceImpl<UserEntity> implements UserService {

    @Resource
    private UserDao userDao;

    @PostConstruct
    public void initBaseDao() {
        baseDao = userDao;
    }
}
