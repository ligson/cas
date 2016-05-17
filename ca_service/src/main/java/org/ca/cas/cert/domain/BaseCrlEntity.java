package org.ca.cas.cert.domain;

import org.hibernate.annotations.GenericGenerator;
import org.ligson.fw.core.entity.BasicEntity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

/**
 * Created by ligson on 2016/5/17.
 */
@Entity
@Table(name = "base_crl")
public class BaseCrlEntity extends BasicEntity{
    private String id;
    private String certSerialNumber;
    private String version;
    private Date lastUpdate;
    private String crlBuf;
    private String revokeIdEnd;
    private Date nextUpdate;
    private String bufSign;

    @Id
    @GeneratedValue(generator = "dr.id")
    @GenericGenerator(name = "dr.id", strategy = "org.ligson.fw.core.common.idgenerator.DateRandomGenerator")
    @Column(length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(length = 32, name = "cert_serial_number")
    public String getCertSerialNumber() {
        return certSerialNumber;
    }

    public void setCertSerialNumber(String certSerialNumber) {
        this.certSerialNumber = certSerialNumber;
    }

    @Column(name = "version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Column(name = "last_update")
    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Lob
    @Basic(fetch = LAZY)
    @Column(name = "crl_buf", length = 1048576)
    public String getCrlBuf() {
        return crlBuf;
    }

    public void setCrlBuf(String crlBuf) {
        this.crlBuf = crlBuf;
    }

    @Column(name = "revoke_id_end")
    public String getRevokeIdEnd() {
        return revokeIdEnd;
    }

    public void setRevokeIdEnd(String revokeIdEnd) {
        this.revokeIdEnd = revokeIdEnd;
    }

    @Column(name = "next_update")
    public Date getNextUpdate() {
        return nextUpdate;
    }

    public void setNextUpdate(Date nextUpdate) {
        this.nextUpdate = nextUpdate;
    }

    @Lob
    @Basic(fetch = LAZY)
    @Column(name = "buf_sign", length = 1048576)
    public String getBufSign() {
        return bufSign;
    }

    public void setBufSign(String bufSign) {
        this.bufSign = bufSign;
    }
}
