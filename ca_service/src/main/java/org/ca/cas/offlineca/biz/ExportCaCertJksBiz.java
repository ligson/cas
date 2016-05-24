package org.ca.cas.offlineca.biz;

import org.ca.cas.cert.biz.core.MakeCertBiz;
import org.ca.cas.cert.enums.CertFailEnum;
import org.ca.cas.common.biz.KeyContainerBiz;
import org.ca.cas.common.model.KeyPairContainer;
import org.ca.cas.offlineca.domain.OfflineCertEntity;
import org.ca.cas.offlineca.dto.ExportCaCertJksRequestDto;
import org.ca.cas.offlineca.dto.ExportCaCertJksResponseDto;
import org.ca.cas.offlineca.service.OfflineCertService;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.facade.annotation.Api;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 2016/5/24.
 */
@Api(name = "导出离线CA证书到jks文件接口")
@Component("exportCaCertJksBiz")
public class ExportCaCertJksBiz extends AbstractBiz<ExportCaCertJksRequestDto, ExportCaCertJksResponseDto> {
    @Resource
    private OfflineCertService offlineCertService;
    @Resource
    private MakeCertBiz makeCertBiz;
    @Resource
    KeyContainerBiz keyContainerBiz;

    @Override
    public void before() {

    }

    @Override
    public Boolean paramCheck() {
        if (CollectionUtils.isEmpty(requestDto.getCertIds())) {
            setFailureResult(CertFailEnum.E_BIZ_21021);
            return false;
        }
        return true;
    }

    @Override
    public Boolean bizCheck() {
        List<OfflineCertEntity> entities = new ArrayList<>();
        for (String id : requestDto.getCertIds()) {
            OfflineCertEntity entity = offlineCertService.get(id);
            if (entity == null) {
                setFailureResult(CertFailEnum.E_BIZ_21009);
                return false;
            }
            entities.add(entity);
        }
        context.setAttr("entities", entities);
        return true;
    }

    @Override
    public Boolean txnPreProcessing() {
        List<OfflineCertEntity> entities = (List<OfflineCertEntity>) context.getAttr("entities");
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(null, null);
            for (OfflineCertEntity entity : entities) {
                X509Certificate cert = makeCertBiz.recoverCert(entity.getCertBuf());
                if (cert == null) {
                    setFailureResult(CertFailEnum.E_BIZ_21005);
                    return false;
                }
                KeyPairContainer keyPair = keyContainerBiz.getKeyPair(cert.getPublicKey());
                if (keyPair == null) {
                    setFailureResult(CertFailEnum.E_BIZ_21014);
                    return false;
                }
                keyStore.setKeyEntry(cert.getSerialNumber().toString(), keyPair.getPrivateKey(), requestDto.getJksPassword().toCharArray(), new Certificate[]{cert});
            }
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            keyStore.store(bos, requestDto.getJksPassword().toCharArray());
            byte[] bytes = bos.toByteArray();
            responseDto.setJksFile(bytes);
            setSuccessResult();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            setFailureResult(CertFailEnum.E_BIZ_21022);
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
