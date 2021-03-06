<@override name="title">初始化根证书</@override>
<@override name="header">
<link type="text/css" rel="stylesheet" href="${assetsPath}js/lib/bootstrap-validator/css/bootstrapValidator.css">
<script type="text/javascript" src="${assetsPath}js/lib/bootstrap-validator/js/bootstrapValidator.js"></script>
<script type="text/javascript" src="${assetsPath}js/cas/pub/cert/enroll.js"></script>
</@override>
<@override name="body">
<!--String keyId, String o, String ou, String cn, String certPin-->
<div class="col-sm-4"></div>
<div class="col-sm-4">
    <form id="enrollForm" class="form-horizontal" method="post" action="${basePath}initCert.do">
        <div class="form-group text-center">
            <p>
                <small class="text-danger">${RequestParameters["errorMsg"]}</small>
            </p>
        </div>
        <div class="form-group">
            <label class="col-sm-4">组织(O)</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" name="o" value="${RequestParameters['o']}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4">组织单位(OU)</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" name="ou" value="${RequestParameters['ou']}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4">姓名(CN)</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" name="cn" value="${RequestParameters['cn']}"/>
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
        <div class="form-group">
            <label class="col-sm-4">选择密钥对</label>
            <div class="col-sm-8">
                <select name="keyId" class="form-control">
                    <#list keys as key>
                        <option value="${key.aliase}">${(key.keyType==1)?string("RSA","SM2")}-${key.keySize}(${key.aliase})</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="form-group text-center">
            <input type="submit" class="btn btn-info" value="提交"/>&nbsp;&nbsp;(您还可以<a href="${basePath}uploadCaCert.html">直接导入</a>)
        </div>
    </form>
</div>
<div class="col-sm-4"></div>
</@override>
<@extends name="pub/layout/index.ftl"/>