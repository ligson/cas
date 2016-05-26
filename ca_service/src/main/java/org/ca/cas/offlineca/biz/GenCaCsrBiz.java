package org.ca.cas.offlineca.biz;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.jce.PKCS10CertificationRequest;
import org.ca.cas.cert.biz.EnrollCertBiz;
import org.ca.cas.cert.biz.GenCsrBiz;
import org.ca.cas.cert.enums.CertFailEnum;
import org.ca.cas.common.biz.CsrBiz;
import org.ca.cas.common.biz.KeyContainerBiz;
import org.ca.cas.common.model.KeyPairContainer;
import org.ca.cas.offlineca.dto.GenCaCsrRequestDto;
import org.ca.cas.offlineca.dto.GenCaCsrResponseDto;
import org.ca.common.utils.X500NameUtils;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.facade.annotation.Api;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/5/18.
 */
@Component("genCaCsrBiz")
@Api(name = "生成CA证书的CSR接口")
public class GenCaCsrBiz extends AbstractBiz<GenCaCsrRequestDto, GenCaCsrResponseDto> {
    @Resource
    private KeyContainerBiz keyContainerBiz;
    @Resource
    private CsrBiz csrBiz;

    @Override
    public void before() {

    }

    @Override
    public Boolean paramCheck() {
        return null;
    }

    @Override
    public Boolean bizCheck() {
        KeyPairContainer keyContainer = keyContainerBiz.getKeyPair(requestDto.getAliase());
        if (keyContainer == null) {
            setFailureResult(CertFailEnum.E_BIZ_21014);
            return false;
        } else {
            context.setAttr("keyContainer", keyContainer);
        }
        return true;
    }

    @Override
    public Boolean txnPreProcessing() {
        KeyPairContainer container = context.getAttr("keyContainer", KeyPairContainer.class);
        X500Name subject = X500NameUtils.subjectToX500Name(requestDto.getSubjectDn());
        String csr = csrBiz.genCsr(subject, container.getPublicKey(), container.getPrivateKey());
        if (csr == null) {
            setFailureResult(CertFailEnum.E_BIZ_21004);
            return false;
        }
        responseDto.setCsr(csr);
        setSuccessResult();
        return true;
    }

    @Override
    public Boolean persistence() {
        return null;
    }

    @Override
    public void after() {

    }
}
