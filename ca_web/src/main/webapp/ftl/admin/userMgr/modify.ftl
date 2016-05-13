<@override name="title">修改用户信息</@override>
<@override name="header">
<script type="text/javascript" src="${assetsPath}js/cas/admin/userMgr/modify.js"></script>
</@override>
<@override name="body">
<form style="margin-left:50px;" class="form-horizontal  easyui-form" action="${basePath}admin/userMgr/modify.do"
      id="modifyUserForm"
      method="post">
    <div class="form-group row text-center">
        <p class="text-danger">${RequestParameters["errorMsg"]}</p>
    </div>
    <div class="form-group row">
        <label class="col-sm-3" for="name1">登陆名</label>
        <div class="col-sm-6">
            <input id="name1" type="text" class="form-control easyui-textbox" name="name" required="true"
                   value="${adminUser.name}">
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-3" for="sex1">性别</label>
        <div class="col-sm-6">
            <select class="easyui-combobox form-control" name="sex" editable="false" required="true" id="sex1">
                <#if adminUser.sex??>
                    <option value="false" ${adminUser.sex?string("","selected='selected'")}>女</option>
                    <option value="true" ${adminUser.sex?string("selected='selected'","")}>男</option>
                <#else>
                    <option value="false">女</option>
                    <option value="true">男</option>
                </#if>

            </select>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-3" for="mobile1">手机号</label>
        <div class="col-sm-6">
            <input type="text" class="form-control easyui-textbox" name="mobile" validType="mobile"
                   required="true" id="mobile1" value="${adminUser.mobile}">
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-3" for="email1">邮箱</label>
        <div class="col-sm-6">
            <input type="text" class="form-control easyui-textbox" name="email" validType="email"
                   required="true" id="email1" value="${adminUser.email}">
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-3" for="birth1">出生日期</label>
        <div class="col-sm-6">
            <input class="form-control easyui-datebox" name="birth" id="birth1" value="${adminUser.birth??}"/>
        </div>
    </div>
    <div class="form-group row">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="modifyUser()">修改</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'"
           onclick="resetUser()">重置</a>
    </div>
</form>
</@override>
<@extends name="admin/layout/userMgr.ftl"/>