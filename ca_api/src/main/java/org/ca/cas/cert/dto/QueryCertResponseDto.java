package org.ca.cas.cert.dto;

import org.ca.cas.cert.vo.Cert;
import org.ligson.fw.core.facade.annotation.Param;
import org.ligson.fw.core.facade.base.dto.BaseQueryPageResponseDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligson on 2016/4/25.
 */
public class QueryCertResponseDto extends BaseQueryPageResponseDto {
    @Param(name = "证书列表", description = "分页查询时返回结果")
    private List<Cert> certs = new ArrayList<>();
    @Param(name = "证书", description = "非分页查询时返回结果")
    private Cert cert;

    public List<Cert> getCerts() {
        return certs;
    }

    public void setCerts(List<Cert> certs) {
        this.certs = certs;
    }

    public Cert getCert() {
        return cert;
    }

    public void setCert(Cert cert) {
        this.cert = cert;
    }

    @Override
    public String toString() {
        return "QueryCertResponseDto{" +
                "certs=" + certs +
                ", cert=" + cert +
                '}';
    }
}
