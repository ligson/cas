package org.ca.cas.cert.dto;

import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseResponseDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 2016/5/13.
 */
public class ListKeyStoreResponseDto extends BaseResponseDto {
    @Param(name = "key别名列表")
    private List<String> aliases = new ArrayList<>();

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    @Override
    public String toString() {
        return "ListKeyStoreResponseDto{" +
                "aliases=" + aliases +
                '}';
    }
}
