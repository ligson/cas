package org.ca.cas.web.offlineca.controllers;

import org.apache.commons.codec.binary.Base64;
import org.ca.cas.cert.api.CertApi;
import org.ca.cas.cert.dto.ListKeyStoreRequestDto;
import org.ca.cas.cert.dto.ListKeyStoreResponseDto;
import org.ca.cas.offlineca.api.OfflineAdminApi;
import org.ca.cas.offlineca.api.OfflineCaApi;
import org.ca.cas.offlineca.dto.*;
import org.ca.cas.offlineca.vo.OfflineAdmin;
import org.ca.cas.offlineca.vo.OfflineCaCert;
import org.ca.common.user.enums.UserState;
import org.ligson.fw.core.facade.base.result.Result;
import org.ligson.fw.string.encode.HashHelper;
import org.ligson.fw.web.controller.BaseController;
import org.ligson.fw.web.vo.WebResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 2016/5/19.
 */
@Controller
@RequestMapping("/offlineCa")
public class OfflineCaController extends BaseController {
    @Resource
    private OfflineCaApi offlineCaApi;
    @Resource
    private CertApi certApi;
    @Resource
    private OfflineAdminApi offlineAdminApi;

    @Value("#{caConfig['minLoginAdminCount']}")
    private int MIN_LOGIN_ADMIN_COUNT;

    @RequestMapping("/index.html")
    public String index() {
        logger.info("funck..............{}", offlineCaApi);
        return "offlineCa/ca/index";
    }

    @ResponseBody
    @RequestMapping("/certList.json")
    public WebResult certList(OfflineCaCertQueryRequestDto requestDto) {
        String pageString = request.getParameter("page");
        int page = Integer.parseInt(pageString);
        requestDto.setPageNum(page);
        String rowString = request.getParameter("rows");
        int rows = Integer.parseInt(rowString);
        requestDto.setPageSize(rows);
        Result<OfflineCaCertQueryResponseDto> result = offlineCaApi.offlineCaCertQuery(requestDto);
        if (result.isSuccess()) {
            webResult.put("total", result.getData().getTotalCount());
            webResult.put("rows", result.getData().getOfflineCaCerts());
            webResult.setSuccess(true);
        } else {
            webResult.setError(result);
        }
        return webResult;
    }

    @RequestMapping("/keyList.html")
    public String toKeyList() {
        return "offlineCa/ca/keyList";
    }

    @ResponseBody
    @RequestMapping("/keyList.json")
    public WebResult keyList(ListKeyStoreRequestDto requestDto) {
        String pageString = request.getParameter("page");
        int page = Integer.parseInt(pageString);
        requestDto.setPageNum(page);
        String rowString = request.getParameter("rows");
        int rows = Integer.parseInt(rowString);
        requestDto.setPageSize(rows);

        Result<ListKeyStoreResponseDto> listResult = certApi.listKeyStore(requestDto);
        if (listResult.isSuccess()) {
            webResult.setSuccess(true);
            webResult.put("rows", listResult.getData().getKeyPairs());
            webResult.put("total", listResult.getData().getTotalCount());
        } else {
            webResult.setError(listResult);
        }
        return webResult;
    }

    @ResponseBody
    @RequestMapping("/createOfflineKey.json")
    public WebResult createOfflineKey(CreateOfflineKeyRequestDto requestDto) {
        Result<CreateOfflineKeyResponseDto> createResult = offlineCaApi.createOfflineKey(requestDto);
        if (createResult.isSuccess()) {
            webResult.setSuccess(true);
        } else {
            webResult.setError(createResult);
        }
        return webResult;
    }

    @RequestMapping("/createSelfCert.html")
    public String toCreateSelfCert() {
        ListKeyStoreRequestDto requestDto = new ListKeyStoreRequestDto();
        Result<ListKeyStoreResponseDto> listResult = certApi.listKeyStore(requestDto);
        if (listResult.isSuccess()) {
            request.setAttribute("keys", listResult.getData().getKeyPairs());
            return "offlineCa/ca/createSelfCert";
        } else {
            logger.error("list key store error:{}", listResult);
            request.setAttribute("errorMsg", listResult.getFailureMessage());
            return "offlineCa/ca/message";
        }
    }

    @RequestMapping("/createSelfCert.do")
    public String createSelfCert(GenSelfCertRequestDto requestDto) {
        Result<GenSelfCertResponseDto> genResult = offlineCaApi.genSelfCert(requestDto);
        if (genResult.isSuccess()) {
            return redirect("/offlineCa/index.html");
        } else {
            model.addAttribute("errorMsg", genResult.getFailureMessage());
            return redirect("/offlineCa/createSelfCert.html");
        }
    }

    @RequestMapping("/createSubCert.html")
    public String toCreateSubCert(String issuerCertId) {
        return "offlineCa/ca/createSubCert";
    }

    @RequestMapping("/createSubCert.do")
    public String createSubCert(GenSubCaCertRequestDto requestDto) {
        Result<GenSubCaCertResponseDto> genResult = offlineCaApi.genSubCaCert(requestDto);
        if (genResult.isSuccess()) {
            return redirect("/offlineCa/index.html");
        } else {
            model.addAttribute("errorMsg", genResult.getFailureMessage());
            model.addAttribute("issuerCertId", requestDto.getIssuerCertId());
            return redirect("/offlineCa/createSubCert.html");
        }
    }

    @RequestMapping("/genCaCsr.html")
    public String toGenCaCsr() {
        ListKeyStoreRequestDto requestDto = new ListKeyStoreRequestDto();
        Result<ListKeyStoreResponseDto> listResult = certApi.listKeyStore(requestDto);
        if (listResult.isSuccess()) {
            request.setAttribute("keys", listResult.getData().getKeyPairs());
            return "offlineCa/ca/genCaCsr";
        } else {
            logger.error("list key store error:{}", listResult);
            request.setAttribute("errorMsg", listResult.getFailureMessage());
            return "offlineCa/ca/message";
        }
    }

    @ResponseBody
    @RequestMapping("/genCaCsr.json")
    public WebResult genCaCsr(GenCaCsrRequestDto requestDto) {
        Result<GenCaCsrResponseDto> genResult = offlineCaApi.genCaCsr(requestDto);
        if (genResult.isSuccess()) {
            webResult.put("csr", genResult.getData().getCsr());
            webResult.setSuccess(true);
        } else {
            webResult.setError(genResult);
        }
        return webResult;
    }

    @RequestMapping("/uploadP7.html")
    public String toUploadP7() {
        return "offlineCa/ca/uploadP7";
    }

    @RequestMapping("/uploadP7.do")
    public String uploadP7(@RequestParam CommonsMultipartFile p7File) {
        UploadP7CertChainRequestDto requestDto = new UploadP7CertChainRequestDto();
        requestDto.setP7File(p7File.getFileItem().get());
        Result<UploadP7CertChainResponseDto> uploadResult = offlineCaApi.uploadP7CertChain(requestDto);
        if (uploadResult.isSuccess()) {
            return redirect("/offlineCa/index.html");
        } else {
            model.addAttribute("errorMsg", uploadResult.getFailureMessage());
            return redirect("/offlineCa/uploadP7.html");
        }
    }

    @RequestMapping("/download.do")
    public void download(String certId) throws IOException {
        OfflineCaCertQueryRequestDto requestDto = new OfflineCaCertQueryRequestDto();
        requestDto.setPageAble(false);
        requestDto.setId(certId);
        Result<OfflineCaCertQueryResponseDto> queryResult = offlineCaApi.offlineCaCertQuery(requestDto);
        if (queryResult.isSuccess()) {
            if (queryResult.getData().getSuccess()) {
                OfflineCaCert cert = queryResult.getData().getOfflineCaCert();
                response.setContentType("application/x-x509-ca-cert");
                response.setHeader("Content-Disposition", "attachment;fileName=" + cert.getSerialNumber() + ".crt");
                response.getWriter().print(cert.getCertBuf());
                return;
                //application/x-x509-ca-cert
            }
        }
        response.setContentType("text/html");
        response.getWriter().println("证书下载失败:" + queryResult.getFailureMessage());
    }

    @RequestMapping("/downloadChain.do")
    public void downloadChain(String certId) throws IOException {
        OfflineCaCertQueryRequestDto requestDto = new OfflineCaCertQueryRequestDto();
        requestDto.setPageAble(false);
        requestDto.setId(certId);
        Result<OfflineCaCertQueryResponseDto> queryResult = offlineCaApi.offlineCaCertQuery(requestDto);
        if (queryResult.isSuccess()) {
            if (queryResult.getData().getSuccess()) {
                OfflineCaCert cert = queryResult.getData().getOfflineCaCert();
                response.setContentType("application/x-pkcs7-certificates");
                response.setHeader("Content-Disposition", "attachment;fileName=" + cert.getSerialNumber() + ".p7b");
                response.getWriter().print(cert.getCertChainBuf());
                return;
                //application/x-x509-ca-cert
            }
        }
        response.setContentType("text/html");
        response.getWriter().println("证书下载失败:" + queryResult.getFailureMessage());
    }

    @RequestMapping("/downloadP12.do")
    public void downloadP12(DownloadP12CaCertRequestDto requestDto) throws IOException {
        Result<DownloadP12CaCertResponseDto> downloadResult = offlineCaApi.downloadP12CaCert(requestDto);
        if (downloadResult.isSuccess()) {
            if (downloadResult.getData().getSuccess()) {
                String p12 = downloadResult.getData().getP12();
                response.setContentType("application/x-pkcs12");
                response.setHeader("Content-Disposition", "attachment;fileName=" + requestDto.getCertId() + ".pfx");
                response.getOutputStream().write(Base64.decodeBase64(p12));
                return;
                //application/x-x509-ca-cert
            }
        }
        response.setContentType("text/html");
        response.getWriter().println("证书下载失败:" + downloadResult.getFailureMessage());
    }

    @ResponseBody
    @RequestMapping("/deleteOfflineCert.json")
    public WebResult deleteOfflineCert(DeleteOfflineCertRequestDto requestDto) {
        Result<DeleteOfflineCertResponseDto> deleteResult = offlineCaApi.deleteOfflineCert(requestDto);
        if (deleteResult.isSuccess()) {
            webResult.setSuccess(true);
        } else {
            webResult.setError(deleteResult);
        }
        return webResult;
    }

    @RequestMapping("/login.html")
    public String toLogin() {
        return "offlineCa/ca/login";
    }

    @RequestMapping("/login.do")
    public String login(String name, String password) {
        OfflineAdminQueryRequestDto requestDto = new OfflineAdminQueryRequestDto();
        requestDto.setName(name);
        requestDto.setPassword(HashHelper.md5(password));
        requestDto.setPageAble(false);
        Result<OfflineAdminQueryResponseDto> queryResult = offlineAdminApi.queryAdmin(requestDto);
        if (queryResult.isSuccess() && queryResult.getData().getOfflineAdmin() != null) {
            OfflineAdmin offlineAdmin = queryResult.getData().getOfflineAdmin();
            if (offlineAdmin.getStatus() == UserState.DISABLED.getCode()) {
                model.addAttribute("errorMsg", "管理员[" + offlineAdmin.getName() + "]状态无效");
                return redirect("/offlineCa/login.html");
            }
            Object adminListObj = session.getAttribute("offlineAdminList");
            if (adminListObj != null) {
                List<OfflineAdmin> offlineAdminList = (List<OfflineAdmin>) adminListObj;
                if (offlineAdminList.contains(offlineAdmin)) {
                    model.addAttribute("errorMsg", "管理员[" + offlineAdmin.getName() + "]已经登陆");
                    return redirect("/offlineCa/login.html");
                } else {
                    offlineAdminList.add(offlineAdmin);
                    session.setAttribute("offlineAdminList", offlineAdminList);
                    if (offlineAdminList.size() == MIN_LOGIN_ADMIN_COUNT) {
                        return redirect("/offlineCa/index.html");
                    } else {
                        model.addAttribute("errorMsg", "请登录第" + (offlineAdminList.size() + 1) + "位管理员");
                        return redirect("/offlineCa/login.html");
                    }
                }
            } else {
                List<OfflineAdmin> offlineAdminList = new ArrayList<>();
                offlineAdminList.add(offlineAdmin);
                session.setAttribute("offlineAdminList", offlineAdminList);
                model.addAttribute("errorMsg", "请登录第" + (offlineAdminList.size() + 1) + "位管理员");
                return redirect("/offlineCa/login.html");
            }
        } else {
            model.addAttribute("errorMsg", "用户不存在或者密码错误");
            return redirect("/offlineCa/login.html");
        }
    }

    @RequestMapping("/register.html")
    public String toRegister() {
        return "offlineCa/ca/register";
    }

    @RequestMapping("/register.do")
    public String register(OfflineAdminAddRequestDto requestDto) {
        requestDto.setPassword(HashHelper.md5(requestDto.getPassword()));
        Result<OfflineAdminAddResponseDto> addAdminResult = offlineAdminApi.addAdmin(requestDto);
        if (addAdminResult.isSuccess()) {
            Result<OfflineAdminQueryResponseDto> queryResult = offlineAdminApi.queryAdmin(new OfflineAdminQueryRequestDto());
            if (queryResult.isSuccess()) {
                int total = queryResult.getData().getTotalCount();
                if (total < 6) {
                    model.addAttribute("errorMsg", "请注册第" + (total + 1) + "位管理员");
                    return redirect("/offlineCa/register.html");
                } else {
                    model.addAttribute("errorMsg", "请登录");
                    return redirect("/offlineCa/login.html");
                }
            } else {
                model.addAttribute("errorMsg", addAdminResult.getFailureMessage());
                return redirect("/offlineCa/register.html");
            }
        } else {
            model.addAttribute("errorMsg", addAdminResult.getFailureMessage());
            return redirect("/offlineCa/register.html");
        }
    }

    @RequestMapping("/userList.html")
    public String toUserList() {
        return "offlineCa/ca/userList";
    }

    @ResponseBody
    @RequestMapping("/userList.json")
    public WebResult userList(OfflineAdminQueryRequestDto requestDto) {
        String pageString = request.getParameter("page");
        int page = Integer.parseInt(pageString);
        requestDto.setPageNum(page);
        String rowString = request.getParameter("rows");
        int rows = Integer.parseInt(rowString);
        requestDto.setPageSize(rows);
        Result<OfflineAdminQueryResponseDto> result = offlineAdminApi.queryAdmin(requestDto);
        if (result.isSuccess()) {
            webResult.put("total", result.getData().getTotalCount());
            webResult.put("rows", result.getData().getOfflineAdminList());
            webResult.setSuccess(true);
        } else {
            webResult.setError(result);
        }
        return webResult;
    }

    @RequestMapping("/modifyStatus.json")
    @ResponseBody
    public WebResult modifyStatus(String id, int status) {
        OfflineAdminModifyRequestDto requestDto = new OfflineAdminModifyRequestDto();
        requestDto.setId(id);
        requestDto.setStatus(status);
        Result<OfflineAdminModifyResponseDto> modifyResult = offlineAdminApi.modifyAdmin(requestDto);
        if (modifyResult.isSuccess()) {
            webResult.setSuccess(true);
        } else {
            webResult.setError(modifyResult);
        }
        return webResult;
    }

    @RequestMapping("/modifyPwd.html")
    public String toModifyPwd() {
        return "offlineCa/ca/modifyPwd";
    }

    @ResponseBody
    @RequestMapping("/modifyPwd.json")
    public WebResult modifyPwd(String id, String oldPwd, String newPwd) {
        OfflineAdminQueryRequestDto queryRequestDto = new OfflineAdminQueryRequestDto();
        queryRequestDto.setPageAble(false);
        queryRequestDto.setId(id);
        Result<OfflineAdminQueryResponseDto> queryResult = offlineAdminApi.queryAdmin(queryRequestDto);
        if (queryResult.isSuccess()) {
            OfflineAdmin offlineAdmin = queryResult.getData().getOfflineAdmin();
            if (offlineAdmin.getPassword().equals(HashHelper.md5(oldPwd))) {
                OfflineAdminModifyRequestDto modifyRequestDto = new OfflineAdminModifyRequestDto();
                modifyRequestDto.setId(id);
                modifyRequestDto.setPassword(HashHelper.md5(newPwd));
                Result<OfflineAdminModifyResponseDto> modifyResult = offlineAdminApi.modifyAdmin(modifyRequestDto);
                if (modifyResult.isSuccess()) {
                    webResult.setSuccess(true);
                } else {
                    webResult.setError(modifyResult);
                }
            } else {
                webResult.setSuccess(false);
                webResult.setErrorMsg("旧密码不一致");
            }
        } else {
            webResult.setError(queryResult);
        }
        return webResult;
    }


    @RequestMapping("/logout.do")
    public String logout() {
        session.invalidate();
        return redirect("/offlineCa/login.html");
    }

    @RequestMapping("/exportJks.do")
    public void exportJks(@RequestParam("certIds") String certIds, @RequestParam("password") String password) {
        String[] certIdArr = certIds.split(",");
        List<OfflineAdmin> adminList = (List<OfflineAdmin>) session.getAttribute("offlineAdminList");
        ExportCaCertJksRequestDto requestDto = new ExportCaCertJksRequestDto();
        requestDto.setAdminId(adminList.get(0).getId());
        List<String> certIdList = new ArrayList<>();
        for (String certId : certIdArr) {
            certIdList.add(certId);
        }
        requestDto.setCertIds(certIdList);
        requestDto.setJksPassword(password);
        Result<ExportCaCertJksResponseDto> exportResult = offlineCaApi.exportCaCertJks(requestDto);
        if (exportResult.isSuccess()) {
            byte[] buffer = exportResult.getData().getJksFile();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=keystore.jks");
            try {
                response.getOutputStream().write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            response.setContentType("text/html");
            try {
                response.getWriter().println(exportResult.getFailureMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
