<@override name="title">导入P7证书</@override>
<@override name="header">
</@override>
<@override name="body">
<div class="col-sm-4"></div>
<div class="col-sm-4">
    <form id="enrollForm" class="form-horizontal" method="post" action="${basePath}offlineCa/uploadP7.do"
          enctype="multipart/form-data">
        <div class="form-group text-center">
            <p>
                <small class="text-danger">${RequestParameters["errorMsg"]}</small>
            </p>
        </div>
        <div class="form-group">
            <label class="col-sm-4">P7证书链:</label>
            <div class="col-sm-8">
                <input type="file" buttonText="选择文件" style="width:300px"
                       accept="application/x-pkcs7-certificates" name="p7File"/>
            </div>
        </div>
        <div class="form-group">
            <div class="alert alert-danger" role="alert">
                <h4>
                    <small>上传注意:</small>
                </h4>
                <ol style="margin-left:30px;">
                    <li>必须是PKCS7格式文件</li>
                    <li>必须是完整证书链</li>
                    <li>上传前请先正确配置JKS文件</li>
                    <li>证书系统中不存在</li>
                </ol>
            </div>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-info">上传</button>
        </div>
    </form>
</div>
<div class="col-sm-4"></div>
</@override>
<@extends name="offlineCa/layout/certMgr.ftl"/>