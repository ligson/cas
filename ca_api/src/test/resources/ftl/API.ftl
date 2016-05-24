${collection.clazz.simpleName?upper_case}接口文档
================================

**目录**

<#list collection.apiModelList as api>
[${api_index+1}.${api.api.name()}接口](#t${api_index+1})

</#list>
<#list collection.apiModelList as apiModel>

###<a name="t${apiModel_index+1}">${apiModel_index+1}.${apiModel.api.name()}接口</a>

####${apiModel_index+1}.1.描述

    <#if apiModel.api.description()!="">
    ${"    "}${apiModel.api.description()}
    <#else>
    ${"    "}无
    </#if>

####${apiModel_index+1}.2.方法名:

```java
${"    "}${apiModel.method.returnType.simpleName} ${apiModel.method.name}(${apiModel.method.parameterTypes[0].simpleName} requestDto);
```

####${apiModel_index+1}.3.请求参数(requestDto)：

    <#list apiModel.requestDto.paramModels as param>
`${param.field.name}` : <#if param.param??>${param.param.name()},${param.param.required()?string("必备","可选")},${param.param.description()}</#if>

    </#list>

###${apiModel_index+1}.4.返回参数(responseDto):

    <#list apiModel.responseDto.paramModels as param2>
`${param2.field.name}` : <#if param2.param??>${param2.param.name()},${param2.param.description()}<#else>${param2.field.name}</#if>

    </#list>

</#list>
