<@override name="title">证书列表</@override>
<@override name="header">
<script type="text/javascript" src="${assetsPath}js/cas/offlineca/ca/index.js"></script>
</@override>
<@override name="body">
<table id="tt" class="easyui-datagrid" style="height:500px; width: 100%;"
       data-options="singleSelect:false,fix:true" toolbar="#toolbar" pagination="true" rownumbers="true"
       fitColumns="true"
       url="${basePath}offlineCa/certList.json" iconCls="icon-save" pagination="true">
    <thead>
    </thead>
</table>
<div id="revokeDlgBtns">
    <a class="easyui-linkbutton" onclick="revokeCert()">吊销</a>
    <a class="easyui-linkbutton" onclick="$('#revokeDlg').dialog('close')">取消</a>
</div>
<div class="easyui-dialog" closed="true" title="吊销证书" id="revokeDlg" style="width:400px;height:150px;"
     buttons="#revokeDlgBtns">
    <form class="easyui-form" id="revokeForm" method="post" action="${basePath}admin/certMgr/revokeCert.json">
        <input type="hidden" name="certIds" value=""/>
        <table class="table">
            <tr>
                <td><label>吊销原因</label></td>
                <td><select class="easyui-combobox" editabled="false" name="revokeReason">
                    <option value="1">unused(未知)</option>
                    <option value="2">keyCompromise(密钥遭受损害)</option>
                    <option value="3">caCompromise(CA 遭受损害)</option>
                    <option value="4">affiliationChanged(从属关系变动)</option>
                    <option value="5">superseded(证书被替代)</option>
                    <option value="6">cessationOfOperation(停止使用)</option>
                    <option value="7">certificateHold(证书暂停使用)</option>
                </select></td>
            </tr>
        </table>
    </form>
</div>
<div class="easyui-dialog" style="width:500px;" id="viewCsrDlg" closed="true" title="查看CSR">
    <textarea class="form-control" rows="15" id="csr" disabled="disabled"></textarea>
</div>
<div id="downloadP12Btns">
    <a class="easyui-linkbutton" onclick="$('#downloadP12Form').form('submit')">下载</a>
    <a class="easyui-linkbutton" onclick="$('#downloadP12Dlg').dialog('close')">取消</a>
</div>
<div buttons="#downloadP12Btns" class="easyui-dialog" style="width:400px;" id="downloadP12Dlg" closed="true"
     title="下载交换密钥文件">
    <form style="width:350px;" class="form-horizontal" method="post" id="downloadP12Form"
          action="${basePath}offlineCa/downloadP12.do">
        <input type="hidden" name="certId" value=""/>
        <div class="form-group">
            <label class="col-sm-4">保护密码：</label>
            <div class="col-sm-8">
                <input id="pwd" type="password" required="true" class="form-control easyui-textbox" name="pwd"
                       value=""/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4">再次输入保护密码：</label>
            <div class="col-sm-8">
                <input type="password" validType="equalTo['#pwd']" required="true" class="form-control easyui-textbox"
                       name="retryPwd" value=""/>
            </div>
        </div>
    </form>
</div>
</@override>
<@extends name="offlineCa/layout/certMgr.ftl"/>