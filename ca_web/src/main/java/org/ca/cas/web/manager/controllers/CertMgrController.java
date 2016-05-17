package org.ca.cas.web.manager.controllers;

import org.ca.cas.cert.dto.*;
import org.ca.cas.cert.vo.Cert;
import org.ca.cas.user.api.UserApi;
import org.ca.cas.user.dto.QueryUserRequestDto;
import org.ca.cas.user.dto.QueryUserResponseDto;
import org.ca.cas.user.vo.User;
import org.ca.common.cert.enums.CertStatus;
import org.ca.cas.cert.api.CertApi;
import org.ca.common.user.enums.UserRole;
import org.ca.kms.key.api.KeyApi;
import org.ligson.fw.core.facade.base.result.Result;
import org.ligson.fw.string.encode.HashHelper;
import org.ligson.fw.web.controller.BaseController;
import org.ligson.fw.web.vo.WebResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by ligson on 2016/5/6.
 */
@Controller
@RequestMapping("/admin/certMgr")
public class CertMgrController extends BaseController {
    @Resource
    private CertApi certApi;

    @Resource
    private UserApi userApi;

    @Resource
    private KeyApi keyApi;

    @RequestMapping("/index.html")
    public String index() {
        User user = (User) session.getAttribute("adminUser");
        QueryCertRequestDto requestDto = new QueryCertRequestDto();
        requestDto.setUserId(user.getId());
        requestDto.setPageAble(false);
        Result<QueryCertResponseDto> queryResult = certApi.queryCert(requestDto);
        if (queryResult.isSuccess()) {
            if (queryResult.getData().getCert() == null) {
                return redirect("/admin/certMgr/initAdminCert.html");
            }
        }
        return "admin/certMgr/index";
    }


    @RequestMapping("/initAdminCert.html")
    public String toInitAdminCert() {
        ListKeyStoreRequestDto requestDto = new ListKeyStoreRequestDto();
        Result<ListKeyStoreResponseDto> listResult = certApi.listKeyStore(requestDto);
        if (listResult.isSuccess()) {
            List<String> keys = listResult.getData().getAliases();
            request.setAttribute("keys", keys);
        }
        return "admin/certMgr/initAdminCert";
    }

    @RequestMapping("/initAdminCert.do")
    public String initAdminCert(String keyId, String o, String ou, String cn, String certPin) {
        User user = (User) session.getAttribute("adminUser");

        QueryUserRequestDto queryUserRequestDto = new QueryUserRequestDto();
        queryUserRequestDto.setRole(UserRole.SUPER.getCode());
        queryUserRequestDto.setPageAble(false);
        Result<QueryUserResponseDto> queryUserResult = userApi.query(queryUserRequestDto);
        User rootUser = queryUserResult.getData().getUser();
        QueryCertRequestDto queryCertRequestDto = new QueryCertRequestDto();
        queryCertRequestDto.setUserId(rootUser.getId());
        queryCertRequestDto.setPageAble(false);
        Result<QueryCertResponseDto> queryCertResult = certApi.queryCert(queryCertRequestDto);
        Cert rootCert = queryCertResult.getData().getCert();

        EnrollCertRequestDto requestDto = new EnrollCertRequestDto();
        requestDto.setCertPin(certPin);
        requestDto.setKeyId(keyId);
        requestDto.setUserId(user.getId());
        String subjectDn = "o=" + o + ",ou=" + ou + ",cn=" + cn;
        requestDto.setSubjectDn(subjectDn);
        requestDto.setIssueDn(rootCert.getSubjectDn());
        requestDto.setSubjectDnHashMd5(HashHelper.md5(subjectDn));
        requestDto.setIssueDnHashMd5(HashHelper.md5(rootCert.getSubjectDn()));
        requestDto.setStartDate(new Date());
        Result<EnrollCertResponseDto> enrollCertResult = certApi.enrollCert(requestDto);
        if (enrollCertResult.isSuccess()) {
            if (enrollCertResult.getData().getSuccess()) {
                return redirect("/admin/certMgr/index.html");
            }
        }
        model.addAttribute("errorMsg", enrollCertResult.getFailureMessage());
        return redirect("/admin/certMgr/initAdminCert.html");
    }

    @RequestMapping("/download.do")
    public void download(String certId) throws IOException {
        QueryCertRequestDto requestDto = new QueryCertRequestDto();
        requestDto.setPageAble(false);
        requestDto.setId(certId);
        Result<QueryCertResponseDto> queryResult = certApi.queryCert(requestDto);
        if (queryResult.isSuccess()) {
            if (queryResult.getData().getSuccess()) {
                Cert cert = queryResult.getData().getCert();
                response.setContentType("application/x-x509-ca-cert");
                response.setHeader("Content-Disposition", "attachment;fileName=" + cert.getSerialNumber() + ".crt");
                response.getWriter().print(cert.getSignBuf());
                return;
                //application/x-x509-ca-cert
            }
        }
        response.setContentType("text/html");
        response.getWriter().println("证书下载失败:" + queryResult.getFailureMessage());
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

    @RequestMapping("/revokeCertList.html")
    public String toRevokeCertList() {
        return "admin/certMgr/revokeCertList";
    }

    @ResponseBody
    @RequestMapping("/revokeCertList.json")
    public WebResult revokeCertList(RevokeListRequestDto requestDto) {
        String pageString = request.getParameter("page");
        int page = Integer.parseInt(pageString);
        requestDto.setPageNum(page);
        String rowString = request.getParameter("rows");
        int rows = Integer.parseInt(rowString);
        requestDto.setPageSize(rows);
        Result<RevokeListResponseDto> result = certApi.revokeQuery(requestDto);
        if (result.isSuccess()) {
            webResult.put("total", result.getData().getTotalCount());
            webResult.put("rows", result.getData().getRevokeCertList());
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

    @ResponseBody
    @RequestMapping("/revokeCert.json")
    public WebResult revokeCert(String[] certIds, int revokeReason) {
        User user = (User) session.getAttribute("adminUser");
        for (String certId : certIds) {
            RevokeCertRequestDto revokeCertRequestDto = new RevokeCertRequestDto();
            revokeCertRequestDto.setAdminId(user.getId());
            revokeCertRequestDto.setCertId(certId);
            revokeCertRequestDto.setRevokeReason(revokeReason);
            Result<RevokeCertResponseDto> revokeResult = certApi.revokeCert(revokeCertRequestDto);
            if (!revokeResult.isSuccess()) {
                webResult.setError(revokeResult);
                return webResult;
            }
        }
        webResult.setSuccess(true);
        return webResult;
    }
}
