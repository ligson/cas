USERAPI接口文档
================================

###1.注册接口接口

####1.1.描述

    无

####1.2.方法名:

```java
    Result register(RegisterRequestDto requestDto);
```

####1.3.请求参数(requestDto)：

    `birth` : 生日,可选,
    `name` : 姓名,可选,
    `password` : 密码,可选,
    `sex` : 性别,可选,
    `mobile` : 手机号,可选,
    `email` : 邮箱,可选,

###1.4.返回参数(responseDto):

    `serialVersionUID` : serialVersionUID
    `resultCode` : resultCode
    `failureCode` : failureCode
    `failureMessage` : failureMessage
    `data` : data

###2.用户查询接口接口

####2.1.描述

    无

####2.2.方法名:

```java
    Result query(QueryUserRequestDto requestDto);
```

####2.3.请求参数(requestDto)：

    `id` : 用户id,可选,
    `birth` : 用户生日,可选,
    `name` : 姓名,可选,
    `password` : 密码,可选,
    `sex` : 性别,可选,
    `status` : 状态,可选,
    `mobile` : 手机号,可选,
    `email` : 邮箱,可选,
    `registerDate` : 注册日期,可选,
    `photo` : 用户头像,可选,
    `role` : 角色,可选,

###2.4.返回参数(responseDto):

    `serialVersionUID` : serialVersionUID
    `resultCode` : resultCode
    `failureCode` : failureCode
    `failureMessage` : failureMessage
    `data` : data

###3.用户登录接口接口

####3.1.描述

    无

####3.2.方法名:

```java
    Result login(LoginRequestDto requestDto);
```

####3.3.请求参数(requestDto)：

    `loginName` : 登陆名,可选,
    `loginNameType` : 登陆名类型,必备,
    `password` : 登陆密码,必备,

###3.4.返回参数(responseDto):

    `serialVersionUID` : serialVersionUID
    `resultCode` : resultCode
    `failureCode` : failureCode
    `failureMessage` : failureMessage
    `data` : data

###4.用户信息修改接口接口

####4.1.描述

    无

####4.2.方法名:

```java
    Result modify(ModifyUserRequestDto requestDto);
```

####4.3.请求参数(requestDto)：

    `id` : 用户id,必备,
    `birth` : 生日,可选,
    `name` : 姓名,可选,
    `sex` : 性别,可选,
    `status` : 状态,可选,
    `mobile` : 手机号,可选,
    `email` : 邮箱,可选,
    `photo` : 用户头像,可选,
    `role` : 用户角色,可选,
    `fatherUserId` : 用户父帐号,可选,

###4.4.返回参数(responseDto):

    `serialVersionUID` : serialVersionUID
    `resultCode` : resultCode
    `failureCode` : failureCode
    `failureMessage` : failureMessage
    `data` : data

###5.用户密码修改接口接口

####5.1.描述

    无

####5.2.方法名:

```java
    Result modifyPwd(ModifyPwdRequestDto requestDto);
```

####5.3.请求参数(requestDto)：

    `userId` : 用户id,必备,
    `oldPwd` : 旧密码,必备,
    `newPwd` : 新密码,必备,

###5.4.返回参数(responseDto):

    `serialVersionUID` : serialVersionUID
    `resultCode` : resultCode
    `failureCode` : failureCode
    `failureMessage` : failureMessage
    `data` : data

###6.密码重置接口接口

####6.1.描述

    无

####6.2.方法名:

```java
    Result resetPwd(ResetPwdRequestDto requestDto);
```

####6.3.请求参数(requestDto)：

    `userId` : 用户id,必备,
    `newPwd` : 新密码,必备,
    `operatorId` : 操作者,必备,
    `remark` : 重置原因,可选,

###6.4.返回参数(responseDto):

    `serialVersionUID` : serialVersionUID
    `resultCode` : resultCode
    `failureCode` : failureCode
    `failureMessage` : failureMessage
    `data` : data

