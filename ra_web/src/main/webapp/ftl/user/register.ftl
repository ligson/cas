<@override name="title">注册</@override>
<@override name="header">
</@override>
<@override name="body">
<div class="col-sm-2"></div>
<form class="form-horizontal col-sm-8" action="${basePath}user/register.do" method="post">
    <div class="form-group">
        <label class="col-sm-4">用户名:</label>
        <div class="col-sm-8">
            <input type="text" placeholder="用户名" class="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4">邮箱:</label>
        <div class="col-sm-8">
            <input type="text" placeholder="邮箱" class="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4">手机号:</label>
        <div class="col-sm-8">
            <input type="text" placeholder="手机号" class="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4">密码:</label>
        <div class="col-sm-8">
            <input type="password" placeholder="密码" class="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-12 text-center"><input type="submit" class=" btn btn-large btn-info" value="注册">
            <small>(已经有账号?立即<a href="${basePath}user/login.html">登陆</a>!)</small>
        </div>
    </div>
</form>
<div class="col-sm-2"></div>
</@override>
<@extends name="layout/index.ftl"/>