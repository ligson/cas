package org.ca.cas.web.manager.controllers;

import org.ca.common.cert.enums.CertStatus;
import org.ca.cas.cert.api.CertApi;
import org.ca.cas.cert.dto.QueryCertRequestDto;
import org.ca.cas.cert.dto.QueryCertResponseDto;
import org.ligson.fw.core.facade.base.result.Result;
import org.ligson.fw.web.controller.BaseController;
import org.ligson.fw.web.vo.WebResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/5/6.
 */
@Controller
@RequestMapping("/admin/certMgr")
public class CertMgrController extends BaseController {
    @Resource
    private CertApi certApi;

    @RequestMapping("/index.html")
    public String index() {
        return "admin/certMgr/index";
    }

    @ResponseBody
    @RequestMapping("/certList.json")
    public WebResult certList(QueryCertRequestDto requestDto) {
        String pageString = request.getParameter("page");
        int page = Integer.parseInt(pageString);
        requestDto.setPageNum(page);
        String rowString = request.getParameter("rows");
        int rows = Integer.parseInt(rowString);
        requestDto.setPageSize(rows);
        Result<QueryCertResponseDto> result = certApi.queryCert(requestDto);
        if (result.isSuccess()) {
            webResult.put("total", result.getData().getTotalCount());
            webResult.put("rows", result.getData().getCerts());
            webResult.setSuccess(true);
        } else {
            webResult.setError(result);
        }
        return webResult;
    }

    @RequestMapping("/waitApproveCertList.html")
    public String toWaitApproveCertList() {
        return "admin/certMgr/waitApproveCertList";
    }

    @ResponseBody
    @RequestMapping("/waitApproveCertList.json")
    public WebResult waitApproveCertList(QueryCertRequestDto requestDto) {
        String pageString = request.getParameter("page");
        int page = Integer.parseInt(pageString);
        requestDto.setPageNum(page);
        String rowString = request.getParameter("rows");
        int rows = Integer.parseInt(rowString);
        requestDto.setPageSize(rows);
        requestDto.setStatus(CertStatus.ENROLL.getCode());
        Result<QueryCertResponseDto> result = certApi.queryCert(requestDto);
        if (result.isSuccess()) {
            webResult.put("rows", result.getData().getCerts());
            webResult.put("total", result.getData().getTotalCount());
            webResult.setSuccess(true);
        } else {
            webResult.setError(result);
        }
        return webResult;
    }
}
