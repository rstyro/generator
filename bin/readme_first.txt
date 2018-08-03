使用说明
1. 各置项目请参考"generator.xml"与 "生成器特性简要说明.txt"
2. 打开GeneratorMain.java 以application方式运行， 该程序将根据template文件夹下的模块生成相应代码
     代码生成位置在generator.xml中的outRoot节点中配置。
3. 使用GeneratorMain.java前 请先配置好 generator.xml
    主要参数说明 
    wzz_moduleName   模块名称 ，如： znnj  智能农机 ， 在模板的controller中 有些代码 可以指定跳到特定的模块。
    wzz_classAuthor   作者名称 ， 生成类时会自动 带上
    wzz_moduleName   功能说明 ， 生成jsp页面时会在 titile中显示
    wzz_classVersion  类的版本号，可有可无
    basepackage   类包根路径
    tableRemovePrefixes 要移除的表名前缀,如有些表叫 NJ_TEST ，如果不配置该项会生成NjTest.java，如果配置NJ_，则生成Test.java
    jdbc.username
    jdbc.password
     注意数据库的链接
     
     
模板说明 :
this template is created by wzz for minstone co.ltd   
log
template version 1.0  基础的curd操作            --2015/03/31  wzz  onthewing#qq.com   
     