<@override name="title">登陆</@override>
<@override name="header">
</@override>
<@override name="body">
<div class="col-sm-4"></div>
<form class="form-horizontal col-sm-4" action="${basePath}offlineCa/login.do" method="post">
    <div class="form-group text-center">
        <p>
            <small class="text-danger">${RequestParameters["errorMsg"]}</small>
        </p>
    </div>
    <div class="form-group">
        <label class="col-sm-4">用户名:</label>
        <div class="col-sm-8">
            <input type="text" placeholder="用户名" class="form-control" name="name" value="${name}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4">密码:</label>
        <div class="col-sm-8">
            <input type="password" placeholder="密码" class="form-control" name="password"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-12 text-center"><input type="submit" class=" btn btn-large btn-info" value="登陆">
            <small>(没有账号?立即<a href="${basePath}offlineCa/register.html">注册</a>一个!)</small>
        </div>
    </div>
</form>
<div class="col-sm-4"></div>
</@override>
<@extends name="offlineCa/layout/index.ftl"/>