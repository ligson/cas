package org.ca.cas.cert.biz;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.asn1.x500.X500Name;
import org.ca.cas.cert.domain.CertEntity;
import org.ca.cas.cert.enums.CertFailEnum;
import org.ca.cas.cert.service.CertService;
import org.ca.cas.common.biz.CsrBiz;
import org.ca.cas.common.biz.KeyContainerBiz;
import org.ca.cas.common.biz.MakeCertBiz;
import org.ca.cas.cert.dto.ImportCaCertRequestDto;
import org.ca.cas.cert.dto.ImportCaCertResponseDto;
import org.ca.cas.common.model.KeyPairContainer;
import org.ca.cas.user.domain.UserEntity;
import org.ca.cas.user.service.UserService;
import org.ca.cas.user.vo.User;
import org.ca.common.cert.enums.CertStatus;
import org.ca.common.cert.enums.CertType;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.facade.annotation.Api;
import org.ligson.fw.core.facade.enums.FailureCodeEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ligson on 2016/5/25.
 */
@Api(name = "导入管理员CA证书")
@Component("importCaCertBiz")
public class ImportCaCertBiz extends AbstractBiz<ImportCaCertRequestDto, ImportCaCertResponseDto> {
    @Resource
    private MakeCertBiz makeCertBiz;
    @Resource
    private CertService certService;
    @Resource
    private KeyContainerBiz keyContainerBiz;
    @Resource
    private CsrBiz csrBiz;
    @Resource
    private UserService userService;
    @Resource
    private EnrollCertBiz enrollCertBiz;

    @Override
    public void before() {

    }

    @Override
    public Boolean paramCheck() {
        X509Certificate cert = makeCertBiz.recoverCert(requestDto.getCertBuf());
        if (cert == null) {
            setFailureResult(CertFailEnum.E_BIZ_21005);
            return false;
        }
        KeyPairContainer keyPairContainer = keyContainerBiz.getKeyPair(cert.getPublicKey());
        if (keyPairContainer == null) {
            setFailureResult(CertFailEnum.E_BIZ_21014);
            return false;
        }
        context.setAttr("cert", cert);
        context.setAttr("certKeyPair", keyPairContainer);
        return true;
    }

    @Override
    public Boolean bizCheck() {
        X509Certificate cert = (X509Certificate) context.getAttr("cert");
        String subjectDnHashMd5 = DigestUtils.md5Hex(cert.getSubjectX500Principal().getEncoded());
        CertEntity certEntity = certService.findBy("subjectDnHashMd5", subjectDnHashMd5);
        if (certEntity != null) {
            setFailureResult(CertFailEnum.E_BIZ_21001);
            return false;
        }
        UserEntity user = userService.get(requestDto.getAdminId());
        if (user == null) {
            setFailureResult(FailureCodeEnum.E_BIZ_20003);
            return false;
        }
        return true;
    }

    /****
     * x509证书转换未certentity,其中userid和证书链未赋值
     *
     * @param cert      x509证书
     * @param container 密钥对容器
     * @return certEntity
     */
    public CertEntity addCertToEntity(X509Certificate cert, KeyPairContainer container) {
        CertEntity certEntity = new CertEntity();
        certEntity.setSerialNumber(cert.getSerialNumber().toString());
        try {
            certEntity.setSignBuf(Base64.encodeBase64String(cert.getEncoded()));
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
            return null;
        }
        certEntity.setStatus(CertStatus.VALID.getCode());
        X500Name subject = X500Name.getInstance(cert.getSubjectX500Principal().getEncoded());
        X500Name issue = X500Name.getInstance(cert.getIssuerX500Principal().getEncoded());
        certEntity.setSubjectDn(cert.getSubjectDN().getName());
        certEntity.setIssuerDn(cert.getIssuerDN().getName());
        try {
            certEntity.setSubjectDnHashMd5(DigestUtils.md5Hex(subject.getEncoded()));
            certEntity.setIssuerDnHashMd5(DigestUtils.md5Hex(issue.getEncoded()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        certEntity.setNotAfter(cert.getNotAfter());
        certEntity.setNotBefore(cert.getNotBefore());
        DateTime notAfter = new DateTime(cert.getNotAfter());
        DateTime notBefore = new DateTime(cert.getNotBefore());
        //计算区间天数
        Period p = new Period(notAfter, notBefore, PeriodType.days());
        int days = p.getDays();
        certEntity.setReqOverrideValidity(days);
        certEntity.setApproveDate(new Date());
        certEntity.setCertPin(requestDto.getCertPin());
        certEntity.setCertType(CertType.SIGN.getCode());
        String csr = csrBiz.genCsr(container, subject);
        certEntity.setReqBuf(csr);
        certEntity.setReqBufType(1);
        certEntity.setSignDate(new Date());
        return certEntity;
    }

    @Override
    public Boolean txnPreProcessing() {
        X509Certificate cert = (X509Certificate) context.getAttr("cert");
        KeyPairContainer container = context.getAttr("certKeyPair", KeyPairContainer.class);
        CertEntity certEntity = addCertToEntity(cert, container);
        if (certEntity == null) {
            setFailureResult(CertFailEnum.E_BIZ_21005);
            return false;
        }
        certEntity.setUserId(requestDto.getAdminId());
        byte[] certBuf;
        try {
            certBuf = cert.getEncoded();
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
            setFailureResult(CertFailEnum.E_BIZ_21017);
            return false;
        }
        try {
            byte[] certChainBuf = enrollCertBiz.getCertChain(certEntity, certBuf);
            certEntity.setSignBufP7(Base64.encodeBase64String(certChainBuf));
        } catch (Exception e) {
            e.printStackTrace();
            setFailureResult(CertFailEnum.E_BIZ_21015);
            return false;
        }
        context.setAttr("certEntity", certEntity);
        return true;
    }

    @Override
    public Boolean persistence() {
        CertEntity certEntity = context.getAttr("certEntity", CertEntity.class);
        certService.add(certEntity);
        responseDto.setId(certEntity.getId());
        setSuccessResult();
        return true;
    }

    @Override
    public void after() {

    }
}
