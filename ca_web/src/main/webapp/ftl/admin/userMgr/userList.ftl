<@override name="title">用户列表</@override>
<@override name="header">
<script type="text/javascript" src="${assetsPath}js/cas/admin/userMgr/userList.js"></script>
</@override>
<@override name="body">
<table id="tt" class="easyui-datagrid" style="height:500px; width: 100%;"
       data-options="singleSelect:false,fix:true" toolbar="#toolbar" pagination="true" rownumbers="true"
       fitColumns="true"
       url="${basePath}admin/userMgr/userList.json" iconCls="icon-save" pagination="true">
    <thead>
    </thead>
</table>
<div id="addUserDlg" title="创建用户" class="easyui-dialog col-sm-4" closed="true" style="padding:30px;">
    <div class="container-fluid">
        <form class="form-horizontal  easyui-form" action="${basePath}admin/userMgr/addUser.json" id="addUserForm" method="post">
            <div class="form-group row">
                <label class="col-sm-3" for="name1">登陆名</label>
                <div class="col-sm-6">
                    <input id="name1" type="text" class="form-control easyui-textbox" name="name" required="true">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3" for="role1">角色</label>
                <div class="col-sm-6">
                    <select class="easyui-combobox form-control" name="role" editable="false" required="true"
                            id="role1">
                        <#if adminUser.role==100>
                            <option value="3">CA管理员</option>
                        <#else>
                            <option value="2">RA管理员</option>
                        </#if>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3" for="sex1">性别</label>
                <div class="col-sm-6">
                    <select class="easyui-combobox form-control" name="sex" editable="false" required="true" id="sex1">
                        <option value="false">女</option>
                        <option value="true">男</option>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3" for="mobile1">手机号</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control easyui-textbox" name="mobile" validType="mobile"
                           required="true" id="mobile1">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3" for="email1">邮箱</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control easyui-textbox" name="email" validType="email"
                           required="true" id="email1">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3" for="password1">密码</label>
                <div class="col-sm-6">
                    <input type="password" class="form-control easyui-textbox" name="password"
                           required="true" id="password1">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3" for="birth1">出生日期</label>
                <div class="col-sm-6">
                    <input class="form-control easyui-datebox" name="birth"
                           required="false" id="birth1">
                </div>
            </div>
            <div class="form-group row">
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addUser()">创建</a>
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'"
                   onclick="$('#addUserDlg').dialog('close')">取消</a>
            </div>
        </form>
    </div>
</div>
</@override>
<@extends name="admin/layout/userMgr.ftl"/>