<@override name="title">管理员列表</@override>
<@override name="header">
<script type="text/javascript" src="${assetsPath}js/cas/offlineca/ca/keyList.js"></script>
</@override>
<@override name="body">
<table id="tt" class="easyui-datagrid" style="height:500px; width: 100%;"
       data-options="singleSelect:false,fix:true" toolbar="#toolbar" rownumbers="true"
       fitColumns="true"
       url="${basePath}offlineCa/keyList.json" iconCls="icon-save" pagination="true">
    <thead>
    </thead>
</table>
<div id="showGenKeyDlg" title="创建密钥对" class="easyui-dialog col-sm-4" closed="true" style="padding:30px;">
    <div class="container-fluid">
        <form class="form-horizontal  easyui-form" action="${basePath}offlineCa/createOfflineKey.json" id="genKeyForm">
            <div class="form-group row">
                <label class="col-sm-3">密钥类型</label>
                <div class="col-sm-6">
                    <select class="easyui-combobox form-control" name="keyType" editable="false" required="true">
                        <option value="1">RSA</option>
                        <option value="2">SM2</option>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3">密钥长度</label>
                <div class="col-sm-6">
                    <select class="easyui-combobox form-control" name="keySize" editable="false" required="true">
                        <option value="256">256</option>
                        <option value="512">512</option>
                        <option value="1024">1024</option>
                        <option value="2048">2048</option>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3">生成个数</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control easyui-textbox" name="count" validType="number"
                           required="true">
                </div>
            </div>
            <div class="form-group row">
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="genKey()">生成</a>
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'"
                   onclick="$('#showGenKeyDlg').dialog('close')">取消</a>
            </div>
        </form>
    </div>
</div>
</@override>
<@extends name="offlineCa/layout/certMgr.ftl"/>