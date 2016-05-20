<@override name="title">离线CA管理员注册</@override>
<@override name="header">
<link type="text/css" rel="stylesheet" href="${assetsPath}js/lib/bootstrap-validator/css/bootstrapValidator.css">
<script type="text/javascript" src="${assetsPath}js/lib/bootstrap-validator/js/bootstrapValidator.js"></script>
<script type="text/javascript" src="${assetsPath}js/offlineca/ca/register.js"></script>
</@override>
<@override name="body">
<div class="col-sm-2"></div>
<form id="register_form" class="form-horizontal col-sm-8" action="${basePath}offlineCa/register.do" method="post">
    <div class="form-group text-center">
        <p>
            <small class="text-danger">${RequestParameters["errorMsg"]}</small>
        </p>
    </div>
    <div class="form-group">
        <label class="col-sm-4" for="name">用户名:</label>
        <div class="col-sm-8">
            <input type="text" id="name" name="name" placeholder="用户名" class="form-control" value="${name}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4" for="password">密码:</label>
        <div class="col-sm-8">
            <input type="password" placeholder="密码" class="form-control" id="password" name="password"
                   value="${password}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4" for="password">重复输入密码:</label>
        <div class="col-sm-8">
            <input type="password" placeholder="重复输入密码" class="form-control" id="password2" name="password2"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-12 text-center"><input type="submit" class=" btn btn-large btn-info" value="注册">
            <small>(已经有账号?立即<a href="${basePath}offlineCa/login.html">登陆</a>!)</small>
        </div>
    </div>
</form>
<div class="col-sm-2"></div>
</@override>
<@extends name="offlineCa/layout/index.ftl"/>