OFFLINEADMINAPI接口文档
================================

**目录**

[1.管理员查询接口接口](#t1)

[2.增加管理员接口接口](#t2)

[3.修改管理员接口接口](#t3)

[4.删除管理员接口接口](#t4)


###<a name="t1">1.管理员查询接口接口</a>

####1.1.描述

        无

####1.2.方法名:

```java
    Result queryAdmin(OfflineAdminQueryRequestDto requestDto);
```

####1.3.请求参数(requestDto)：

`id` : 管理员id,可选,

`name` : 用户名,可选,

`password` : 密码,可选,

`status` : 用户状态,可选,

`role` : 用户角色,可选,

`createDate` : 创建日期,可选,

`lastModifyDate` : 最后修改日期,可选,

`lastLoginDate` : 最后登陆日期,可选,

`lastLoginIp` : 最后登陆IP,可选,


###1.4.返回参数(responseDto):

`serialVersionUID` : serialVersionUID

`resultCode` : resultCode

`failureCode` : failureCode

`failureMessage` : failureMessage

`data` : data



###<a name="t2">2.增加管理员接口接口</a>

####2.1.描述

        无

####2.2.方法名:

```java
    Result addAdmin(OfflineAdminAddRequestDto requestDto);
```

####2.3.请求参数(requestDto)：

`name` : 用户名,必备,

`password` : 密码,必备,


###2.4.返回参数(responseDto):

`serialVersionUID` : serialVersionUID

`resultCode` : resultCode

`failureCode` : failureCode

`failureMessage` : failureMessage

`data` : data



###<a name="t3">3.修改管理员接口接口</a>

####3.1.描述

        无

####3.2.方法名:

```java
    Result modifyAdmin(OfflineAdminModifyRequestDto requestDto);
```

####3.3.请求参数(requestDto)：

`id` : 管理员id,必备,

`name` : 用户名,可选,

`password` : 密码,可选,

`status` : 用户状态,可选,


###3.4.返回参数(responseDto):

`serialVersionUID` : serialVersionUID

`resultCode` : resultCode

`failureCode` : failureCode

`failureMessage` : failureMessage

`data` : data



###<a name="t4">4.删除管理员接口接口</a>

####4.1.描述

        无

####4.2.方法名:

```java
    Result deleteAdmin(OfflineAdminDeleteRequestDto requestDto);
```

####4.3.请求参数(requestDto)：

`id` : 管理员id,可选,


###4.4.返回参数(responseDto):

`serialVersionUID` : serialVersionUID

`resultCode` : resultCode

`failureCode` : failureCode

`failureMessage` : failureMessage

`data` : data


