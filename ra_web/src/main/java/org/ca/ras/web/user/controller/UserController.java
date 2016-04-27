package org.ca.ras.web.user.controller;

import org.ca.ras.user.api.UserApi;
import org.ca.ras.web.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/4/26.
 * 用户
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Resource
    private UserApi userApi;

    @RequestMapping("/login.html")
    public String toLogin() {
        logger.info("to login.........");
        return "user/login";
    }

    @RequestMapping("/login.do")
    public void login() {
    }

    @RequestMapping("/register.html")
    public String toReg() {
        logger.info("to login.........");
        return "user/register";
    }

    @RequestMapping("/register.do")
    public void register() {
    }

}
