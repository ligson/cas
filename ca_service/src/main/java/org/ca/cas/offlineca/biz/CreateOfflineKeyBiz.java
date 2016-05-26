package org.ca.cas.offlineca.biz;

import org.apache.commons.codec.binary.Base64;
import org.ca.cas.common.biz.KeyContainerBiz;
import org.ca.cas.common.main.Startup;
import org.ca.cas.common.model.KeyPairContainer;
import org.ca.cas.offlineca.dto.CreateOfflineKeyRequestDto;
import org.ca.cas.offlineca.dto.CreateOfflineKeyResponseDto;
import org.ca.kms.key.enums.KeyFailEnum;
import org.ca.kms.key.enums.KeyType;
import org.ligson.fw.core.common.biz.AbstractBiz;
import org.ligson.fw.core.common.utils.IdUtils;
import org.ligson.fw.core.facade.annotation.Api;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 2016/5/26.
 */
@Api(name = "创建离线Key接口")
@Component("createOfflineKeyBiz")
public class CreateOfflineKeyBiz extends AbstractBiz<CreateOfflineKeyRequestDto, CreateOfflineKeyResponseDto> {

    @Resource
    private KeyContainerBiz keyContainerBiz;

    @Override
    public void before() {

    }

    @Override
    public Boolean paramCheck() {
        if (requestDto.getKeyType() == KeyType.RSA.getCode()) {
            if (requestDto.getKeySize() != 512 && requestDto.getKeySize() != 1024 && requestDto.getKeySize() != 2048) {
                setFailureResult(KeyFailEnum.E_PARAM_11002);
                return false;
            }
        } else if (requestDto.getKeyType() == KeyType.SM2.getCode()) {
            if (requestDto.getKeySize() != 256) {
                setFailureResult(KeyFailEnum.E_PARAM_11002);
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean bizCheck() {
        KeyType keyType = KeyType.getByCode(requestDto.getKeyType());
        KeyPairGenerator generator;
        try {
            Provider provider = Startup.bouncyCastleProvider;
            if (keyType == KeyType.SM2) {
                provider = Startup.TOP_SM_PROVIDER;
            }
            generator = KeyPairGenerator.getInstance(keyType.name(), provider);
            generator.initialize(requestDto.getKeySize());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            setFailureResult(KeyFailEnum.E_PARAM_11001);
            return false;
        }

        context.setAttr("generator", generator);
        return true;
    }

    @Override
    public Boolean txnPreProcessing() {
        KeyPairGenerator generator = (KeyPairGenerator) context.getAttr("generator");
        List<KeyPair> containers = new ArrayList<>();
        for (int i = 0; i < requestDto.getCount(); i++) {
            KeyPair keyPair = generator.generateKeyPair();
            containers.add(keyPair);
        }
        context.setAttr("containers", containers);
        return true;
    }

    @Override
    public Boolean persistence() {
        List<KeyPair> containers = (List<KeyPair>) context.getAttr("containers");
        for (int i = 0; i < containers.size(); i++) {
            KeyPair container = containers.get(i);
            try {
                keyContainerBiz.storeKey(IdUtils.randomIntString(), container.getPublic(), container.getPrivate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        responseDto.setSuccess(true);
        setSuccessResult();
        return true;
    }

    @Override
    public void after() {

    }
}
