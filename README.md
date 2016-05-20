## cas

CA系统模块

###1.模块说明

####1.1.ca_api：api接口层

####1.2.ca_service：业务服务层

####1.3.ca_web：web处理层

###2.开发配置说明

###2.1.下载gradle2.10

    \\192.168.1.212\share\gradle-2.10-bin.zip

###2.2.配置项目

###2.2.1.ca_service：配置

    1. 配置文件在ca_service/src/main/resources/config.properties

    2. dubbo配置文件在ca_service/src/main/resources/dubbo.properties

###2.2.1.ca_web：配置

    2. dubbo配置文件在ca_web/src/main/resources/X-dubbo.properties

###2.3.启动项目

    1.ca_service:MainClass:org.ca.cas.common.main.Startup

    2.ca_web:配置到tomcat里即可

    3.此项目依赖kms,需要先启动kms

    4.CA管理中心:http://localhost:8089/ca/admin/login.html(超级管理员master,密码password)

    5.离线CA管理中心:http://localhost:8089/ca/offlineCa/login.html(管理员admin1,admin2..admin6,密码都是password)


