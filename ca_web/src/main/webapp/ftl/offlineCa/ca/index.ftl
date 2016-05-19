<@override name="title">证书列表</@override>
<@override name="header">
<script type="text/javascript" src="${assetsPath}js/cas/admin/certMgr/index.js"></script>
</@override>
<@override name="body">
<table id="tt" class="easyui-datagrid" style="height:500px; width: 100%;"
       data-options="singleSelect:false,fix:true" toolbar="#toolbar" pagination="true" rownumbers="true"
       fitColumns="true"
       url="${basePath}admin/certMgr/certList.json" iconCls="icon-save" pagination="true">
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
</@override>
<@extends name="offlineCa/layout/certMgr.ftl"/>