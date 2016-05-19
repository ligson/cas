<@override name="title">创建下级证书</@override>
<@override name="header">
<script type="text/javascript" src="${assetsPath}js/cas/offlineca/ca/createSubCert.js"></script>
</@override>
<@override name="body">
<div class="col-sm-4"></div>
<div class="col-sm-4">
    <form id="enrollForm" class="form-horizontal" method="post" action="${basePath}offlineCa/createSubCert.do">
        <input type="hidden" name="issuerCertId" value="${RequestParameters["issueCertId"]}"/>
        <div class="form-group text-center">
            <p>
                <small class="text-danger">${RequestParameters["errorMsg"]}</small>
            </p>
        </div>
        <div class="form-group">
            <label>证书请求信息(CSR)</label>
        </div>
        <div class="form-group">
            <div class="col-sm-12">
                <textarea required="true" multiline="true" style="height:300px;" name="csr" rows="15"
                          class="easyui-textbox form-control"></textarea>
            </div>
        </div>
        <div class="form-group text-center">
            <input type="submit" onclick="submitCert()" class="btn btn-info" value="提交"/>
        </div>
    </form>
</div>
<div class="col-sm-4"></div>
</@override>
<@extends name="offlineCa/layout/certMgr.ftl"/>