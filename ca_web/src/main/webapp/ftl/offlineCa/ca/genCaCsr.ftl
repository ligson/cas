<@override name="title">创建CSR</@override>
<@override name="header">
<script type="text/javascript" src="${assetsPath}js/cas/offlineca/ca/createCsr.js"></script>
</@override>
<@override name="body">
<div class="col-sm-4"></div>
<div class="col-sm-4">
    <form id="enrollForm" class="form-horizontal" method="post" action="${basePath}offlineCa/genCaCsr.json">
        <input type="hidden" name="subjectDn" value=""/>
        <input type="hidden" name="issueCertId" value="${RequestParameters["issueCertId"]}"/>
        <div class="form-group text-center">
            <p>
                <small class="text-danger">${RequestParameters["errorMsg"]}</small>
            </p>
        </div>
        <div class="form-group">
            <label class="col-sm-4">组织(O)</label>
            <div class="col-sm-8">
                <input type="text" required="true" class="form-control subjectItem easyui-textbox" name="o"
                       value="${RequestParameters['o']}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4">组织单位(OU)</label>
            <div class="col-sm-8">
                <input type="text" required="true" class="form-control subjectItem  easyui-textbox" name="ou"
                       value="${RequestParameters['ou']}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4">姓名(CN)</label>
            <div class="col-sm-8">
                <input type="text" required="true" class="form-control subjectItem  easyui-textbox" name="cn"
                       value="${RequestParameters['cn']}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4">选择密钥对</label>
            <div class="col-sm-8">
                <select name="aliase" class="form-control easyui-combobox" editable="false">
                    <#list keys as key>
                        <option value="${key}">${key}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="form-group text-center">
            <input type="button" onclick="genCsr()" class="btn btn-info" value="生成"/>
        </div>
        <div class="form-group">
            <textarea rows="15" disabled id="csr" class="form-control"></textarea>
        </div>
    </form>
</div>
<div class="col-sm-4"></div>
</@override>
<@extends name="offlineCa/layout/certMgr.ftl"/>