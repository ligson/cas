<@override name="title">首页</@override>
<@override name="header">
</@override>
<@override name="body">
<div class="jumbotron">
    <h3>特色一:轻量级</h3>
    <p>使用spring等轻量级库,抛弃EJB的繁琐</p>
</div>
<div class="jumbotron">
    <h3>特色二:灵活部署</h3>
    <p>CA和RA可以多种模式部署</p>
</div>
<div class="jumbotron">
    <h3>特色三:分布式,可伸缩</h3>
    <p>采用dubbo框架可以支持大规模运营</p>
</div>
<div class="jumbotron">
    <h3>特色四:遵循国密标准</h3>
    <p>支持国密算法,双证书,单独密钥管理系统</p>
</div>
<div class="jumbotron">
    <h3>我要试用</h3>
    <a class="btn btn-info" href="${basePath}user/login.html">注册用户</a>
    <a class="btn btn-info">前往注册认证系统(RAS)</a>
    <a class="btn btn-info">前往证书认证系统(CAS)</a>
    <a class="btn btn-info">前往密钥管理系统(KMS)</a>
</div>
</@override>
<@extends name="pub/layout/index.ftl"/>