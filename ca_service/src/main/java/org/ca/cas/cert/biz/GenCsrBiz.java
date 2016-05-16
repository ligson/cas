package org.ca.cas.cert.biz;

import org.ca.cas.cert.domain.CertEntity;
import org.ca.cas.cert.dto.GenCsrRequestDto;
import org.ca.cas.cert.dto.GenCsrResponseDto;
import org.ca.cas.cert.enums.CertFailEnum;
import org.ca.cas.cert.service.CertService;
import org.ca.kms.key.api.KeyApi;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.facade.base.result.Result;
import org.ligson.fw.core.facade.enums.FailureCodeEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/5/16.
 */
@Component("genCsrBiz")
public class GenCsrBiz extends AbstractBiz<GenCsrRequestDto, GenCsrResponseDto> {
    @Resource
    private KeyApi keyApi;
    @Resource
    private CertService certService;

    @Override
    public void before() {

    }

    @Override
    public Boolean paramCheck() {
        return null;
    }

    @Override
    public Boolean bizCheck() {

        return true;
    }

    @Override
    public Boolean txnPreProcessing() {
        org.ca.kms.key.dto.GenCsrRequestDto genCsrRequestDto = new org.ca.kms.key.dto.GenCsrRequestDto();
        genCsrRequestDto.setKeyId(requestDto.getAliase());
        String subjectDn = requestDto.getSubjectDn();
        genCsrRequestDto.setSubjectDn(subjectDn);
        Result<org.ca.kms.key.dto.GenCsrResponseDto> genCsrResult = keyApi.genCsr(genCsrRequestDto);
        if (genCsrResult.isSuccess()) {
            responseDto.setCsr(genCsrResult.getData().getCsr());
            setSuccessResult();
            return true;
        } else {
            setFailureResult(FailureCodeEnum.getByCode(genCsrResult.getFailureCode()));
            return false;
        }
    }

    @Override
    public Boolean persistence() {
        return null;
    }

    @Override
    public void after() {

    }
}
