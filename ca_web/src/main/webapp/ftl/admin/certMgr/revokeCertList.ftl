<@override name="title">吊销证书列表</@override>
<@override name="header">
<script type="text/javascript" src="${assetsPath}js/cas/admin/certMgr/revokeCertList.js"></script>
</@override>
<@override name="body">
<table id="tt" class="easyui-datagrid" style="height:500px; width: 100%;"
       data-options="singleSelect:false,fix:true" toolbar="#toolbar" pagination="true" rownumbers="true"
       fitColumns="true"
       url="${basePath}admin/certMgr/revokeCertList.json" iconCls="icon-save" pagination="true">
    <thead>
    </thead>
</table>
</@override>
<@extends name="admin/layout/certMgr.ftl"/>