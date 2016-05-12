<@override name="title">下载证书</@override>
<@override name="header">
</@override>
<@override name="body">
<!--String keyId, String o, String ou, String cn, String certPin-->
<div class="col-sm-4"></div>
<div class="col-sm-4 text-center">
    <a class="btn btn-info" href="${basePath}download.do?certId=${RequestParameters["certId"]}">点击下载</a>
</div>
<div class="col-sm-4"></div>
</@override>
<@extends name="pub/layout/index.ftl"/>