<@override name="title">修改用户密码</@override>
<@override name="header">
</@override>
<@override name="body">
<form class="form-horizontal  easyui-form" action="${basePath}admin/userMgr/modifyPwd.do" id="modifyUserPwdForm"
      method="post">
    <div class="form-group row text-center">
        <p class="text-danger">${RequestParameters["errorMsg"]}</p>
    </div>
    <div class="form-group row">
        <label class="col-sm-3" for="oldPwd">旧密码</label>
        <div class="col-sm-6">
            <input id="oldPwd" type="password" class="form-control easyui-textbox" name="oldPwd" required="true">
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-3" for="newPwd">新密码</label>
        <div class="col-sm-6">
            <input id="newPwd" type="password" class="form-control easyui-textbox" name="newPwd" required="true">
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-3" for="newPwd2">再次输入新密码</label>
        <div class="col-sm-6">
            <input id="newPwd2" type="password" class="form-control easyui-textbox" name="newPwd2" required="true">
        </div>
    </div>
    <div class="form-group row">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
           onclick="$('#modifyUserPwdForm').form('submit')">修改</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'"
           onclick="history.go(-1)">返回</a>
    </div>
</form>
</@override>
<@extends name="admin/layout/userMgr.ftl"/>