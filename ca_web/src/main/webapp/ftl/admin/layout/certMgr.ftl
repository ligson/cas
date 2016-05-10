<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><@block name="title"></@block>-RA管理中心</title>
<#include "admin/layout/adminCommonHead.ftl">
<@block name="header"></@block>
</head>

<body class="easyui-layout">
<#include "admin/layout/adminCommonBody.ftl">
<div id="westPanel" data-options="region:'west',split:false" title="导航菜单" style="width:150px;" about="systemMgr">
    <div class="easyui-accordion" style="width:148px;height:100%;">
        <div title="证书管理" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
            <ul class="easyui-tree">
                <li><a href="${basePath}admin/certMgr/index.html">证书列表</a></li>
                <li><a href="${basePath}admin/certMgr/waitApproveCertList.html">待批准证书列表</a></li>
            </ul>
        </div>
    </div>

</div>


<div data-options="region:'center',title:'主页面',iconCls:'icon-ok'" id="mainDiv">
<@block name="body"></@block>
</div>
</body>
</html>