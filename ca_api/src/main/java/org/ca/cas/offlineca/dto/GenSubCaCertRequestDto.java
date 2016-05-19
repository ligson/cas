package org.ca.cas.offlineca.dto;

import org.ligson.fw.core.facade.base.dto.BaseRequestDto;

/**
 * Created by ligson on 2016/5/18.
 */
public class GenSubCaCertRequestDto extends BaseRequestDto {
    private String csr;
    private String issuerCertId;

    public String getCsr() {
        return csr;
    }

    public void setCsr(String csr) {
        this.csr = csr;
    }

    public String getIssuerCertId() {
        return issuerCertId;
    }

    public void setIssuerCertId(String issuerCertId) {
        this.issuerCertId = issuerCertId;
    }

    @Override
    public String toString() {
        return "GenSubCaCertRequestDto{" +
                "csr='" + csr + '\'' +
                ", issuerCertId='" + issuerCertId + '\'' +
                '}';
    }
}
