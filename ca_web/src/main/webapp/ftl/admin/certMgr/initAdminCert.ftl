<@override name="title">${user.name}</@override>
<@override name="header">
</@override>
<@override name="body">
<div class="col-sm-4"></div>
<div class="col-sm-4">
    <form id="enrollForm" class="form-horizontal" method="post" action="${basePath}admin/certMgr/initAdminCert.do">
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
                        <option value="${key}">${key}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="form-group text-center">
            <input type="submit" class="btn-info" value="提交"/>
        </div>
    </form>
</div>
<div class="col-sm-4"></div>
</@override>
<@extends name="admin/layout/userMgr.ftl"/>