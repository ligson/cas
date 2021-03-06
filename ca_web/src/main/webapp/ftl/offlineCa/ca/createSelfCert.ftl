<@override name="title">创建自签名证书</@override>
<@override name="header">
<link type="text/css" rel="stylesheet" href="${assetsPath}js/lib/bootstrap-validator/css/bootstrapValidator.css">
<script type="text/javascript" src="${assetsPath}js/lib/bootstrap-validator/js/bootstrapValidator.js"></script>
<script type="text/javascript" src="${assetsPath}js/cas/offlineca/ca/createSelfCert.js"></script>
</@override>
<@override name="body">
<div class="col-sm-4">
    <form id="enrollForm" class="form-horizontal" method="post" action="${basePath}offlineCa/createSelfCert.do">
        <input type="hidden" name="subjectDn" value=""/>
        <div class="form-group text-center">
            <p>
                <small class="text-danger">${RequestParameters["errorMsg"]}</small>
            </p>
        </div>
        <div class="form-group">
            <label class="col-sm-4">组织(O)</label>
            <div class="col-sm-8">
                <input type="text" class="form-control subjectItem" name="O" value="${RequestParameters['O']}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4">组织单位(OU)</label>
            <div class="col-sm-8">
                <input type="text" class="form-control subjectItem" name="OU" value="${RequestParameters['OU']}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4">姓名(CN)</label>
            <div class="col-sm-8">
                <input type="text" class="form-control subjectItem" name="CN" value="${RequestParameters['CN']}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4">选择密钥对</label>
            <div class="col-sm-8">
                <select name="aliase" class="form-control">
                    <#list keys as key>
                        <option value="${key.aliase}">${(key.keyType==1)?string("RSA","SM2")}-${key.keySize}(${key.aliase})</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="form-group text-center">
            <input type="submit" class="btn btn-info" value="提交"/>
        </div>
    </form>
</div>
<div class="col-sm-4"></div>
</@override>
<@extends name="offlineCa/layout/certMgr.ftl"/>