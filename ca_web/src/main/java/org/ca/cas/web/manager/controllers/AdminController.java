package org.ca.cas.web.manager.controllers;

import org.ca.cas.user.api.UserApi;
import org.ca.cas.user.dto.LoginRequestDto;
import org.ca.cas.user.dto.LoginResponseDto;
import org.ca.common.user.enums.UserRole;
import org.ligson.fw.core.facade.base.result.Result;
import org.ligson.fw.string.encode.HashHelper;
import org.ligson.fw.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/5/11.
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
    @Resource
    private UserApi userApi;

    @RequestMapping("/login.html")
    public String toLogin() {
        logger.info("to login.........");
        String errorMsg = request.getParameter("errorMsg");
        if (errorMsg != null) {
            request.setAttribute("errorMsg", errorMsg);
        }
        return "admin/login";
    }

    @RequestMapping("/login.do")
    public String login(LoginRequestDto requestDto) {
        String loginName = requestDto.getLoginName();
        requestDto.setLoginNameType(LoginRequestDto.getByLoginName(loginName));
        requestDto.setPassword(HashHelper.md5(requestDto.getPassword()));

        if (!requestDto.validate()) {
            String errorMsg = "";
            for (String e : requestDto.getErrorFieldMap().values()) {
                errorMsg += e + "<br/>";
            }
            model.addAttribute("loginName", loginName);
            model.addAttribute("errorMsg", errorMsg);
            return redirect("/admin/login.html");
        }

        Result<LoginResponseDto> result = userApi.login(requestDto);
        if (result.isSuccess()) {
            Integer role = result.getData().getUser().getRole();
            if (!role.equals(UserRole.SUPER.getCode()) && !role.equals(UserRole.CA_ADMIN.getCode())) {
                model.addAttribute("loginName", loginName);
                model.addAttribute("errorMsg", "没有权限");
                return redirect("/admin/login.html");
            } else {
                session.setAttribute("adminUser", result.getData().getUser());
            }
        } else {
            model.addAttribute("errorMsg", result.getFailureMessage());
            model.addAttribute("loginName", loginName);
            return redirect("/admin/login.html");
        }
        return redirect("/admin/certMgr/index.html");
    }

    @RequestMapping("/logout.do")
    public String logout() {
        session.invalidate();
        return redirect("/admin/login.html");
    }
}
