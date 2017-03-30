# 需要做的工作列表

> 使用springboot和mybatis重构，加深对框架的理解，原本使用的是轻量级的mvc框架Blade。完成
> 模板引擎改用thymeleaf 完成
> 实现docker服务部署mysql,tomcat,redis,mongdb并管理和Nginx反向代理  由于服务器的内存限制，只使用了tomcat 完成
> 使用websocket推送和统计在线人数
> 使用python或者webmagic爬虫，推荐每日好文。
> 接口使用aop记录日志 完成
> 使用swagger2生成restful api，访问地址为http://域名/swagger-ui.html 完成 

以上工作内容只是简单的罗列，详细内容后面补充。

# 工作记录

1. springBoot默认使用的是tomcat-jdbc的数据库连接池，默认使用的是dbcp的
2. 日志使用由'Starter POMs'，使用Commons Logging记录日志
3. 使用的是 [https://github.com/astarring/mybatis-generator-gui/releases]() 生成的DAO层
4. 大致完成原有项目的翻译工作，目前处于测试阶段。

# 存在的问题

1. 无法动态修改数据源的配置，只能重启解决（由于数据库连接参数变化造成的）
2. 错误描述直接使用的是中文，没有使用配置文件的方式，存在硬编码。

# 踩到的坑

1. 开发时运行正常，使用maven打包运行jar，发现找不到thymeleaf模板目录。
    
    解决：由于返回的路径前面有“/”，如 "/comm/error_500",把前面的"/"去掉即可。
   
2. 开发时运行正常，使用maven打包运行jar，无法读取properties文件。
   
   解决：jar方式运行不能使用FileInputStream方式读取文件，而使用xxx.class.getResourceAsStream
   
3. 错误：java.lang.IllegalArgumentException: Invalid character found in the request target. The valid characters are defined in RFC 7230 and RFC 3986

    我遇到的基本上是由于后台代码报错引起的
    
4. 无法实现动态修改运行的jar中的配置文件，因为运行的jar会被加写锁

5. 使用travis ci 运行失败，需要修改mvnw的权限：git add --chmod=+x mvnw 然后commit提交