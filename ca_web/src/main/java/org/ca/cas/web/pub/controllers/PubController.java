package org.ca.cas.web.pub.controllers;

import org.apache.commons.codec.binary.Base64;
import org.ca.cas.cert.api.CertApi;
import org.ca.cas.cert.dto.*;
import org.ca.cas.cert.vo.Cert;
import org.ca.cas.offlineca.api.OfflineCaApi;
import org.ca.cas.user.api.UserApi;
import org.ca.cas.user.dto.*;
import org.ca.common.user.enums.UserRole;
import org.ca.kms.key.api.KeyApi;
import org.ca.kms.key.dto.KeyQueryRequestDto;
import org.ca.kms.key.dto.KeyQueryResponseDto;
import org.ca.kms.key.enums.KeyStatus;
import org.ca.kms.key.vo.Key;
import org.ligson.fw.core.facade.base.result.Result;
import org.ligson.fw.string.encode.HashHelper;
import org.ligson.fw.web.controller.BaseController;
import org.ligson.fw.web.vo.WebResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by ligson on 2016/5/9.
 */
@Controller
public class PubController extends BaseController {
    @Resource
    private UserApi userApi;

    @Resource
    private CertApi certApi;
    @Resource
    private OfflineCaApi offlineCaApi;

    @RequestMapping("/")
    public String index() {
        QueryUserRequestDto requestDto = new QueryUserRequestDto();
        requestDto.setRole(UserRole.SUPER.getCode());
        Result<QueryUserResponseDto> result = userApi.query(requestDto);
        if (result.isSuccess()) {
            if (result.getData().getUserList().size() == 0) {
                return redirect("/init.html");
            } else {
                return redirect("/admin/login.html");
            }
        } else {
            return "500";
        }
    }

    @RequestMapping("/init.html")
    public String toInit() {
        return "pub/init";
    }

    @RequestMapping("/init.do")
    public String init(RegisterRequestDto requestDto) {
        requestDto.setPassword(HashHelper.md5(requestDto.getPassword()));
        Result<RegisterResponseDto> result = userApi.register(requestDto);
        if (result.isSuccess()) {
            String userId = result.getData().getId();
            ModifyUserRequestDto modifyUserRequestDto = new ModifyUserRequestDto();
            modifyUserRequestDto.setRole(UserRole.SUPER.getCode());
            modifyUserRequestDto.setId(userId);
            Result<ModifyUserResponseDto> modifyResult = userApi.modify(modifyUserRequestDto);
            if (modifyResult.isSuccess()) {
                session.setAttribute("initUserId", userId);
                return redirect("/initCert.html");
            }
        }
        return "500";
    }

    @RequestMapping("/initCert.html")
    public String toInitCert() {
        ListKeyStoreRequestDto requestDto = new ListKeyStoreRequestDto();
        Result<ListKeyStoreResponseDto> listResult = certApi.listKeyStore(requestDto);
        if (listResult.isSuccess()) {
            List<String> keys = listResult.getData().getAliases();
            request.setAttribute("keys", keys);
        }
        return "pub/initCert";
    }

    @RequestMapping("/uploadCaCert.html")
    public String toUploadCaCert() {
        return "pub/uploadCaCert";
    }

    @RequestMapping("/uploadCaCert.do")
    public String uploadCaCert(ImportCaCertRequestDto requestDto, @RequestParam("certFile") CommonsMultipartFile certFile) {
        String adminId = session.getAttribute("initUserId").toString();
        requestDto.setAdminId(adminId);
        requestDto.setCertBuf(new String(certFile.getBytes()));
        Result<ImportCaCertResponseDto> importResult = certApi.importCaCert(requestDto);
        if (importResult.isSuccess()) {
            return redirect("/admin/login.html");
        } else {
            model.addAttribute("errorMsg", importResult.getFailureMessage());
            return redirect("/uploadCaCert.html");
        }
    }

    @RequestMapping("/initCert.do")
    public String initCert(String keyId, String o, String ou, String cn, String certPin) {
        EnrollCertRequestDto requestDto = new EnrollCertRequestDto();
        requestDto.setCertPin(certPin);
        requestDto.setKeyId(keyId);
        String userId = (String) session.getAttribute("initUserId");
        requestDto.setUserId(userId);
        String subjectDn = "O=" + o + ",OU=" + ou + ",CN=" + cn;
        requestDto.setSubjectDn(subjectDn);
        requestDto.setIssueDn(subjectDn);
        requestDto.setSubjectDnHashMd5(HashHelper.md5(subjectDn));
        requestDto.setIssueDnHashMd5(requestDto.getSubjectDnHashMd5());
        requestDto.setStartDate(new Date());
        Result<EnrollCertResponseDto> enrollCertResult = certApi.enrollCert(requestDto);
        if (enrollCertResult.isSuccess()) {
            if (enrollCertResult.getData().getSuccess()) {
                return redirect("/download.html?certId=" + enrollCertResult.getData().getCertId());
            }
        }
        model.addAttribute("errorMsg", enrollCertResult.getFailureMessage());
        return redirect("/initCert.html");
    }

    @RequestMapping("/download.html")
    public String toDownload(String certId) {
        request.setAttribute("certId", certId);
        return "pub/download";
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
                byte[] certBuf = Base64.decodeBase64(cert.getSignBuf());
                response.setContentType("application/x-x509-ca-cert");
                response.setHeader("Content-Disposition", "attachment;fileName=" + cert.getSerialNumber() + ".crt");
                response.getWriter().print(cert.getSignBuf());
                return;
                //application/x-x509-ca-cert
            }
        }
        response.setContentType("text/html");
        response.getOutputStream().println("证书下载失败:" + queryResult.getFailureMessage());
    }

    @ResponseBody
    @RequestMapping("/checkLoginName.json")
    public WebResult checkLoginName(QueryUserRequestDto requestDto) {
        Result<QueryUserResponseDto> result = userApi.query(requestDto);
        if (result.isSuccess()) {
            boolean valid = result.getData().getUserList().size() == 0;
            webResult.setSuccess(true);
            webResult.put("valid", valid);
        } else {
            webResult.setSuccess(false);
            webResult.put("valid", false);
        }
        return webResult;
    }

}
