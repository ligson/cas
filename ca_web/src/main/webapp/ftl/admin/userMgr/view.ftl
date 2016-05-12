<@override name="title">${user.name}</@override>
<@override name="header">
</@override>
<@override name="body">
<table class="table">
    <tr>
        <td><label>登录名:</label></td>
        <td>${user.name}</td>
    </tr>
    <tr>
        <td><label>状态:</label></td>
        <td>${(user.status==1)?string("有效","禁用")}</td>
    </tr>
    <tr>
        <td><label>性别:</label></td>
        <td>
            <#if user.sex>
            ${user.sex?string("男","女")}
            <#else>
                未设置
            </#if>
        </td>
    </tr>
    <tr>
        <td><label>角色:</label></td>
        <td>
            <#if user.role==100>
                超级管理员
            <#elseif user.role==3>
                CA管理员
            <#elseif user.role==2>
                RA管理员
            <#elseif user.role==1>
                普通用户
            <#else>
                未设置
            </#if>
        </td>
    </tr>
    <tr>
        <td><label>手机号:</label></td>
        <td>${user.mobile}</td>
    </tr>
    <tr>
        <td><label>邮箱:</label></td>
        <td>${user.email}</td>
    </tr>
    <tr>
        <td><label>注册日期:</label></td>
        <td>
            <#if user.registerDate??>
        ${user.registerDate?string("yyyy-MM-dd HH:mm:ss")}
            </#if>
        </td>
    </tr>
    <tr>
        <td><label>出生日期:</label></td>
        <td>
            <#if user.birth>
                ${user.birth?string("yyyy-MM-dd")}
            </#if>
        </td>
    </tr>
    <#if user.id==adminUser.id>
        <tr>
            <td colspan="2">
                <a href="${basePath}admin/userMgr/modify.html" class="btn btn-info">修改信息</a>&nbsp;&nbsp;<a
                    href="${basePath}admin/userMgr/modifyPwd.html" class="btn btn-info">修改密码</a>
            </td>
        </tr>
    </#if>
</table>
</@override>
<@extends name="admin/layout/userMgr.ftl"/>