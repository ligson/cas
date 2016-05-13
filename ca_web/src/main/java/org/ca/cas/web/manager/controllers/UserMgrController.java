package org.ca.cas.web.manager.controllers;

import org.ca.cas.user.api.UserApi;
import org.ca.cas.user.dto.*;
import org.ca.cas.user.vo.User;
import org.ligson.fw.core.facade.base.result.Result;
import org.ligson.fw.string.encode.HashHelper;
import org.ligson.fw.web.controller.BaseController;
import org.ligson.fw.web.vo.WebResult;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ligson on 2016/5/12.
 */
@Controller
@RequestMapping("/admin/userMgr")
public class UserMgrController extends BaseController {
    @Resource
    private UserApi userApi;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/userList.html")
    public String toUserList() {
        return "admin/userMgr/userList";
    }

    @ResponseBody
    @RequestMapping("/userList.json")
    public WebResult userList(QueryUserRequestDto requestDto) {
        String pageString = request.getParameter("page");
        int page = Integer.parseInt(pageString);
        requestDto.setPageNum(page);
        String rowString = request.getParameter("rows");
        int rows = Integer.parseInt(rowString);
        requestDto.setPageSize(rows);
        Result<QueryUserResponseDto> result = userApi.query(requestDto);
        if (result.isSuccess()) {
            webResult.put("total", result.getData().getTotalCount());
            webResult.put("rows", result.getData().getUserList());
            webResult.setSuccess(true);
        } else {
            webResult.setError(result);
        }
        return webResult;
    }

    @RequestMapping("/addUser.json")
    @ResponseBody
    public WebResult addUser(RegisterRequestDto requestDto) {
        User user = (User) session.getAttribute("adminUser");
        int role = Integer.parseInt(request.getParameter("role"));
        //String birth = request.getParameter("birth");
        requestDto.setPassword(HashHelper.md5(requestDto.getPassword()));
        Result<RegisterResponseDto> registerResult = userApi.register(requestDto);
        if (registerResult.isSuccess()) {
            ModifyUserRequestDto modifyUserRequestDto = new ModifyUserRequestDto();
            modifyUserRequestDto.setId(registerResult.getData().getId());
            modifyUserRequestDto.setFatherUserId(user.getId());
            modifyUserRequestDto.setRole(role);
            Result<ModifyUserResponseDto> modifyResult = userApi.modify(modifyUserRequestDto);
            if (modifyResult.isSuccess()) {
                webResult.setSuccess(true);
            } else {
                webResult.setError(registerResult);
            }
        } else {
            webResult.setError(registerResult);
        }
        return webResult;
    }

    @RequestMapping("/view.html")
    public String viewUserInfo(String userId) {
        QueryUserRequestDto requestDto = new QueryUserRequestDto();
        requestDto.setPageAble(false);
        requestDto.setId(userId);
        Result<QueryUserResponseDto> queryResult = userApi.query(requestDto);
        if (queryResult.isSuccess()) {
            User user = queryResult.getData().getUser();
            request.setAttribute("user", user);
            return "/admin/userMgr/view";
        } else {
            response.setStatus(404);
            return "404";
        }
    }

    @RequestMapping("/modify.html")
    public String toModify() {
        return "/admin/userMgr/modify";
    }

    @RequestMapping("/modify.do")
    public String modify(ModifyUserRequestDto requestDto) {
        User user = (User) session.getAttribute("adminUser");
        requestDto.setId(user.getId());
        Result<ModifyUserResponseDto> modifyResult = userApi.modify(requestDto);
        if (modifyResult.isSuccess()) {
            QueryUserRequestDto requestDto1 = new QueryUserRequestDto();
            requestDto1.setId(user.getId());
            requestDto1.setPageAble(false);
            Result<QueryUserResponseDto> queryResult = userApi.query(requestDto1);
            session.setAttribute("adminUser", queryResult.getData().getUser());
            return redirect("/admin/userMgr/view.html?userId=" + user.getId());
        } else {
            model.addAttribute("errorMsg", modifyResult.getFailureMessage());
            return redirect("/admin/userMgr/modify.html");
        }
    }

    @RequestMapping("/modifyPwd.html")
    public String toModifyPwd() {
        return "/admin/userMgr/modifyPwd";
    }

    @RequestMapping("/modifyPwd.do")
    public String modifyPwd(ModifyPwdRequestDto requestDto) {
        User user = (User) session.getAttribute("adminUser");
        requestDto.setUserId(user.getId());
        Result<ModifyPwdResponseDto> modifyResult = userApi.modifyPwd(requestDto);
        if (modifyResult.isSuccess()) {
            return redirect("/admin/userMgr/view.html?userId=" + user.getId());
        } else {
            model.addAttribute("errorMsg", modifyResult.getFailureMessage());
            return redirect("/admin/userMgr/modifyPwd.html");
        }
    }
}
