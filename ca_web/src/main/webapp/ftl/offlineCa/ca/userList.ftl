<@override name="title">管理员列表</@override>
<@override name="header">
<script type="text/javascript" src="${assetsPath}js/cas/offlineca/ca/userList.js"></script>
</@override>
<@override name="body">
<table id="tt" class="easyui-datagrid" style="height:500px; width: 100%;"
       data-options="singleSelect:false,fix:true" toolbar="#toolbar" pagination="true" rownumbers="true"
       fitColumns="true"
       url="${basePath}offlineCa/userList.json" iconCls="icon-save" pagination="true">
    <thead>
    </thead>
</table>

<div id="modifyPwdBtns">
    <a class="easyui-linkbutton" onclick="modifyPwd()">修改</a>
    <a class="easyui-linkbutton" onclick="$('#modifyPwdForm').dialog('close')">取消</a>
</div>
<div class="easyui-dialog" closed="true" title="修改密码" id="modifyPwdDlg" style="width:400px;height:250px;"
     buttons="#modifyPwdBtns">
    <form class="easyui-form" id="modifyPwdForm" method="post" action="${basePath}offlineCa/modifyPwd.json">
        <input type="hidden" name="id" value=""/>
        <table class="table">
            <tr>
                <td><label>旧密码</label></td>
                <td><input required="true" type="password" class="easyui-textbox" name="oldPwd" value=""/></td>
            </tr>
            <tr>
                <td><label>新密码</label></td>
                <td><input id="newPwd" required="true" type="password" class="easyui-textbox" name="newPwd" value=""/>
                </td>
            </tr>
            <tr>
                <td><label>再次输入密码</label></td>
                <td><input required="true" validType="equalTo['#newPwd']" type="password" class="easyui-textbox"
                           name="retryNewPwd" value=""/></td>
            </tr>
        </table>
    </form>
</div>
</@override>
<@extends name="offlineCa/layout/certMgr.ftl"/>