<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><@block name="title"></@block>-CA管理中心</title>
<#include "offlineCa/layout/adminCommonHead.ftl">
<@block name="header"></@block>
</head>

<body class="easyui-layout">
<#include "offlineCa/layout/adminCommonBody.ftl">
<div id="westPanel" data-options="region:'west',split:false" title="导航菜单" style="width:150px;" about="systemMgr">
    <div class="easyui-accordion" style="width:148px;height:100%;">
        <div title="证书管理" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
            <ul class="easyui-tree">
                <li><a href="${basePath}offlineCa/index.html">CA证书列表</a></li>
                <li><a href="${basePath}offlineCa/createSelfCert.html">创建自签名证书</a></li>
                <li><a href="${basePath}offlineCa/genCaCsr.html">创建CSR</a></li>
                <li><a href="${basePath}offlineCa/uploadP7.html">导入P7证书</a></li>
            </ul>
        </div>
    </div>

</div>


<div data-options="region:'center',title:'主页面',iconCls:'icon-ok'" id="mainDiv">
<@block name="body"></@block>
</div>
</body>
</html>