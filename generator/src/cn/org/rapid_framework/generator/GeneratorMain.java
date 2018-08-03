package cn.org.rapid_framework.generator;


public class GeneratorMain {
	/**
	 * 请直接修改以下代码调用不同的方法以执行相关生成任务.
	 */
	public static void main(String[] args) throws Exception {
		GeneratorFacade g = new GeneratorFacade();
//		g.printAllTableNames();				//打印数据库中的表名称
		
		g.deleteOutRootDir();							//删除生成器的输出目录
		//第一个参数：是数据库表名，第二个参数是：你选择哪个模板生成代码
		g.generateByTable("table_name","pm_template");	//通过数据库表生成文件,template为模板的根目录,pm_template 这个模板是符合我项目生成的模板，通用的模板为：nomal_template
//		g.generateByAllTable("template");	//自动搜索数据库中的所有表并生成文件,template为模板的根目录
//		g.generateByClass(Blog.class,"template_clazz");
		 
//		g.deleteByTable("table_name", "template"); //删除生成的文件
		//打开文件夹
		Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot"));
	}
}
