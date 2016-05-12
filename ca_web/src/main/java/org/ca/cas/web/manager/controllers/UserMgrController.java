package org.ca.cas.web.manager.controllers;

import org.apache.commons.lang.StringUtils;
import org.ca.cas.user.api.UserApi;
import org.ca.cas.user.dto.*;
import org.ligson.fw.core.facade.base.result.Result;
import org.ligson.fw.string.encode.HashHelper;
import org.ligson.fw.web.controller.BaseController;
import org.ligson.fw.web.vo.WebResult;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
        int role = Integer.parseInt(request.getParameter("role"));
        String birth = request.getParameter("birth");
        requestDto.setPassword(HashHelper.md5(requestDto.getPassword()));
        Result<RegisterResponseDto> registerResult = userApi.register(requestDto);
        if (registerResult.isSuccess()) {
            ModifyUserRequestDto modifyUserRequestDto = new ModifyUserRequestDto();
            modifyUserRequestDto.setId(registerResult.getData().getId());
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
}
