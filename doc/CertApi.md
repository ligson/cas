CERTAPI接口文档
================================

###1.吊销证书接口

####1.1.描述

    无

####1.2.方法名:

```java
    Result revokeCert(RevokeCertRequestDto requestDto);
```

####1.3.请求参数(requestDto)：

    `adminId` : 管理员id,必备,
    `certId` : 证书Id,必备,
    `revokeReason` : 吊销原因,必备,

###1.4.返回参数(responseDto):

    `serialVersionUID` : serialVersionUID
    `resultCode` : resultCode
    `failureCode` : failureCode
    `failureMessage` : failureMessage
    `data` : data

###2.吊销列表查询接口

####2.1.描述

    无

####2.2.方法名:

```java
    Result revokeQuery(RevokeListRequestDto requestDto);
```

####2.3.请求参数(requestDto)：

    `id` : 吊销记录id,可选,
    `certId` : 证书id,可选,
    `certSerialNumber` : 证书序列号,可选,
    `certRevokeDate` : 吊销日期,可选,
    `adminId` : 吊销的管理员id,可选,
    `certRevokeReason` : 
    `certNotAfter` : 
    `certIssuerHashMd5` : 

###2.4.返回参数(responseDto):

    `serialVersionUID` : serialVersionUID
    `resultCode` : resultCode
    `failureCode` : failureCode
    `failureMessage` : failureMessage
    `data` : data

###3.查询可用用户密钥容器接口

####3.1.描述

    无

####3.2.方法名:

```java
    Result listUserKey(ListUserKeyRequestDto requestDto);
```

####3.3.请求参数(requestDto)：

    `adminId` : 管理员ID,必备,

###3.4.返回参数(responseDto):

    `serialVersionUID` : serialVersionUID
    `resultCode` : resultCode
    `failureCode` : failureCode
    `failureMessage` : failureMessage
    `data` : data

###4.查询CA证书密钥容器接口

####4.1.描述

    无

####4.2.方法名:

```java
    Result listKeyStore(ListKeyStoreRequestDto requestDto);
```

####4.3.请求参数(requestDto)：

    `keyType` : 密钥类型,可选,
    `keySize` : 密钥长度,可选,
    `count` : 密钥个数,可选,

###4.4.返回参数(responseDto):

    `serialVersionUID` : serialVersionUID
    `resultCode` : resultCode
    `failureCode` : failureCode
    `failureMessage` : failureMessage
    `data` : data

###5.申请一张证书接口

####5.1.描述

    无

####5.2.方法名:

```java
    Result issueCert(IssueCertRequestDto requestDto);
```

####5.3.请求参数(requestDto)：

    `reqBuf` : csr,必备,
    `reqBufType` : CSR类型,必备,
    `reqComment` : CSR备注,必备,
    `approveDate` : 证书批准日期,必备,
    `notBefore` : notBefore,必备,
    `notAfter` : notAfter,必备,
    `issuerDn` : issuerDn,必备,
    `issuerDnHashMd5` : issuerDnHashMd5,必备,
    `subjectDn` : subjectDn,必备,
    `subjectDnHashMd5` : subjectDnHashMd5,必备,
    `certPin` : certPin,必备,

###5.4.返回参数(responseDto):

    `serialVersionUID` : serialVersionUID
    `resultCode` : resultCode
    `failureCode` : failureCode
    `failureMessage` : failureMessage
    `data` : data

###6.颁发一张证书接口

####6.1.描述

    无

####6.2.方法名:

```java
    Result enrollCert(EnrollCertRequestDto requestDto);
```

####6.3.请求参数(requestDto)：

    `userId` : 用户id,必备,
    `subjectDn` : 颁发给,可选,
    `subjectDnHashMd5` : 颁发给哈希,可选,
    `certPin` : 证书密码,必备,
    `issueDn` : 颁发者,必备,
    `issueDnHashMd5` : 颁发者哈希,必备,
    `startDate` : 开始日期,必备,
    `keyId` : keyId,可选,
    `csr` : csr,可选,

###6.4.返回参数(responseDto):

    `serialVersionUID` : serialVersionUID
    `resultCode` : resultCode
    `failureCode` : failureCode
    `failureMessage` : failureMessage
    `data` : data

###7.生成CRL接口接口

####7.1.描述

    无

####7.2.方法名:

```java
    Result genCrl(GenCrlRequestDto requestDto);
```

####7.3.请求参数(requestDto)：

    `adminId` : 管理员ID,必备,

###7.4.返回参数(responseDto):

    `serialVersionUID` : serialVersionUID
    `resultCode` : resultCode
    `failureCode` : failureCode
    `failureMessage` : failureMessage
    `data` : data

###8.下载CRL接口接口

####8.1.描述

    无

####8.2.方法名:

```java
    Result downloadCrl(DownloadCrlRequestDto requestDto);
```

####8.3.请求参数(requestDto)：

    `caCertSerialNumber` : 证书序号,必备,

###8.4.返回参数(responseDto):

    `serialVersionUID` : serialVersionUID
    `resultCode` : resultCode
    `failureCode` : failureCode
    `failureMessage` : failureMessage
    `data` : data

###9.用户证书CSR生成接口

####9.1.描述

    无

####9.2.方法名:

```java
    Result genCsr(GenCsrRequestDto requestDto);
```

####9.3.请求参数(requestDto)：

    `subjectDn` : 证书主题,必备,
    `aliase` : 密钥对id,必备,
    `adminId` : 管理员id,必备,

###9.4.返回参数(responseDto):

    `serialVersionUID` : serialVersionUID
    `resultCode` : resultCode
    `failureCode` : failureCode
    `failureMessage` : failureMessage
    `data` : data

###10.查询证书接口

####10.1.描述

    无

####10.2.方法名:

```java
    Result queryCert(QueryCertRequestDto requestDto);
```

####10.3.请求参数(requestDto)：

    `id` : 证书id,可选,
    `status` : 证书状态,可选,
    `reqDate` : 申请日期,可选,
    `reqBuf` : CSR,可选,
    `reqBufType` : CSR类型,可选,
    `reqComment` : CSR备注,可选,
    `approveDate` : 证书批准日期,可选,
    `rejectDate` : 证书批准拒绝日期,可选,
    `signDate` : 证书签名日期,可选,
    `signBuf` : 证书签名值,可选,
    `signBufP7` : 证书签名值P7格式,可选,
    `serialNumber` : 证书序列号,可选,
    `notBefore` : 结束日期,可选,
    `notAfter` : 开始日期,可选,
    `issuerDn` : 颁发者issueDn,可选,
    `issuerDnHashMd5` : 颁发者issueDn哈希,可选,
    `subjectDn` : 用户,可选,
    `subjectDnHashMd5` : 用户哈希,可选,
    `suspendDate` : 证书挂起时间,可选,
    `revokeDate` : 证书吊销时间,可选,
    `revokeReason` : 证书吊销原因,可选,
    `renewalDate` : 证书更新日期,可选,
    `renewalPrevSerialNumber` : 证书更新前一个序列号,可选,
    `renewalNextIdSerialNumber` : 证书更新后一个序列号,可选,
    `reqOverrideValidity` : 证书请求过期时间,可选,
    `certPin` : 证书密码,可选,
    `userId` : 用户id,可选,
    `certType` : 证书类型,可选,

###10.4.返回参数(responseDto):

    `serialVersionUID` : serialVersionUID
    `resultCode` : resultCode
    `failureCode` : failureCode
    `failureMessage` : failureMessage
    `data` : data

