<@override name="title">初始化根证书</@override>
<@override name="header">
<link type="text/css" rel="stylesheet" href="${assetsPath}js/lib/bootstrap-validator/css/bootstrapValidator.css">
<script type="text/javascript" src="${assetsPath}js/lib/bootstrap-validator/js/bootstrapValidator.js"></script>
<script type="text/javascript" src="${assetsPath}js/cas/pub/cert/uploadCaCert.js"></script>
</@override>
<@override name="body">
<!--String keyId, String o, String ou, String cn, String certPin-->
<div class="col-sm-4"></div>
<div class="col-sm-4">
    <form id="enrollForm" class="form-horizontal" method="post" action="${basePath}uploadCaCert.do"
          enctype="multipart/form-data">
        <div class="form-group text-center">
            <p>
                <small class="text-danger">${RequestParameters["errorMsg"]}</small>
            </p>
        </div>
        <div class="form-group">
            <h3>导入证书</h3>
            <p>1.证书Base64编码格式</p>
            <p>2.导入前请正确配置密钥库(JKS或者硬件加密机)</p>
        </div>
        <div class="form-group">
            <div class="col-sm-12">
                <input type="file" name="certFile" class="form-control" value=""/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4">证书密码</label>
            <div class="col-sm-8">
                <input type="password" class="form-control" name="certPin"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4">再次输入证书密码</label>
            <div class="col-sm-8">
                <input type="password" class="form-control" name="certPinRetry"/>
            </div>
        </div>
        <div class="form-group text-center">
            <input type="submit" class="btn btn-info" value="提交"/>
            <a class="btn btn-info" href="${basePath}initCert.html">返回</a>
        </div>
    </form>
</div>
<div class="col-sm-4"></div>
</@override>
<@extends name="pub/layout/index.ftl"/>