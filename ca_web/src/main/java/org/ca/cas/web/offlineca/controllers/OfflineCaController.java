package org.ca.cas.web.offlineca.controllers;

import org.ca.cas.cert.api.CertApi;
import org.ca.cas.cert.dto.ListKeyStoreRequestDto;
import org.ca.cas.cert.dto.ListKeyStoreResponseDto;
import org.ca.cas.cert.dto.QueryCertRequestDto;
import org.ca.cas.cert.dto.QueryCertResponseDto;
import org.ca.cas.cert.vo.Cert;
import org.ca.cas.offlineca.api.OfflineCaApi;
import org.ca.cas.offlineca.dto.*;
import org.ca.cas.offlineca.vo.OfflineCaCert;
import org.ligson.fw.core.facade.base.result.Result;
import org.ligson.fw.web.controller.BaseController;
import org.ligson.fw.web.vo.WebResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

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

    @RequestMapping("/createSelfCert.html")
    public String toCreateSelfCert() {
        ListKeyStoreRequestDto requestDto = new ListKeyStoreRequestDto();
        Result<ListKeyStoreResponseDto> listResult = certApi.listKeyStore(requestDto);
        if (listResult.isSuccess()) {
            request.setAttribute("keys", listResult.getData().getAliases());
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
            request.setAttribute("keys", listResult.getData().getAliases());
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
    public String uploadP7(CommonsMultipartFile p7File) {
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
}
