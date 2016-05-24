OFFLINECAAPI接口文档
================================

**目录**

[1.上传p7证书链接口接口](#t1)

[2.下载P12交换密钥文件接口接口](#t2)

[3.删除离线证书接口接口](#t3)

[4.离线CA证书查询接口接口](#t4)

[5.生成CA证书CSR接口接口](#t5)

[6.生成自签名证书接口接口](#t6)

[7.生成下级CA证书接口接口](#t7)


###<a name="t1">1.上传p7证书链接口接口</a>

####1.1.描述

        无

####1.2.方法名:

```java
    Result uploadP7CertChain(UploadP7CertChainRequestDto requestDto);
```

####1.3.请求参数(requestDto)：

`p7File` : 证书链文件,必备,


###1.4.返回参数(responseDto):

`serialVersionUID` : serialVersionUID

`resultCode` : resultCode

`failureCode` : failureCode

`failureMessage` : failureMessage

`data` : data



###<a name="t2">2.下载P12交换密钥文件接口接口</a>

####2.1.描述

        无

####2.2.方法名:

```java
    Result downloadP12CaCert(DownloadP12CaCertRequestDto requestDto);
```

####2.3.请求参数(requestDto)：

`certId` : 证书id,必备,

`pwd` : 导出密码,必备,


###2.4.返回参数(responseDto):

`serialVersionUID` : serialVersionUID

`resultCode` : resultCode

`failureCode` : failureCode

`failureMessage` : failureMessage

`data` : data



###<a name="t3">3.删除离线证书接口接口</a>

####3.1.描述

        无

####3.2.方法名:

```java
    Result deleteOfflineCert(DeleteOfflineCertRequestDto requestDto);
```

####3.3.请求参数(requestDto)：

`id` : 证书id,必备,


###3.4.返回参数(responseDto):

`serialVersionUID` : serialVersionUID

`resultCode` : resultCode

`failureCode` : failureCode

`failureMessage` : failureMessage

`data` : data



###<a name="t4">4.离线CA证书查询接口接口</a>

####4.1.描述

        无

####4.2.方法名:

```java
    Result offlineCaCertQuery(OfflineCaCertQueryRequestDto requestDto);
```

####4.3.请求参数(requestDto)：

`id` : 证书id,可选,

`reqBuf` : 证书CSR,可选,

`reqBufType` : 证书CSR类型,可选,

`signDate` : 证书签名日期,可选,

`signBuf` : 证书签名值,可选,

`certBuf` : 证书buf,可选,

`certChainBuf` : 证书链buf,可选,

`serialNumber` : 证书序列号,可选,

`notBefore` : 结束日期,可选,

`notAfter` : 开始日期,可选,

`issuerDn` : 颁发者issueDn,可选,

`issuerDnHashMd5` : 颁发者issueDn哈希,可选,

`subjectDn` : 用户,可选,

`subjectDnHashMd5` : 用户哈希,可选,


###4.4.返回参数(responseDto):

`serialVersionUID` : serialVersionUID

`resultCode` : resultCode

`failureCode` : failureCode

`failureMessage` : failureMessage

`data` : data



###<a name="t5">5.生成CA证书CSR接口接口</a>

####5.1.描述

        无

####5.2.方法名:

```java
    Result genCaCsr(GenCaCsrRequestDto requestDto);
```

####5.3.请求参数(requestDto)：

`subjectDn` : 主题,必备,

`aliase` : 密钥id,必备,


###5.4.返回参数(responseDto):

`serialVersionUID` : serialVersionUID

`resultCode` : resultCode

`failureCode` : failureCode

`failureMessage` : failureMessage

`data` : data



###<a name="t6">6.生成自签名证书接口接口</a>

####6.1.描述

        无

####6.2.方法名:

```java
    Result genSelfCert(GenSelfCertRequestDto requestDto);
```

####6.3.请求参数(requestDto)：

`subjectDn` : 主题,必备,

`aliase` : 密钥对Id,必备,


###6.4.返回参数(responseDto):

`serialVersionUID` : serialVersionUID

`resultCode` : resultCode

`failureCode` : failureCode

`failureMessage` : failureMessage

`data` : data



###<a name="t7">7.生成下级CA证书接口接口</a>

####7.1.描述

        无

####7.2.方法名:

```java
    Result genSubCaCert(GenSubCaCertRequestDto requestDto);
```

####7.3.请求参数(requestDto)：

`csr` : CA证书CSR,必备,

`issuerCertId` : 颁发者证书id,必备,


###7.4.返回参数(responseDto):

`serialVersionUID` : serialVersionUID

`resultCode` : resultCode

`failureCode` : failureCode

`failureMessage` : failureMessage

`data` : data


