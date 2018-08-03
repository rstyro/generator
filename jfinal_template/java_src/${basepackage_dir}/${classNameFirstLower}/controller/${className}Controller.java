<#include "/macro.include"/>
<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameFirstLower = className?uncap_first>   
<#assign classNameLowerCase = className?lower_case>   
<#assign pkJavaType = table.idColumn.javaType>

package ${basepackage}.${classNameFirstLower}.controller;

import java.sql.Timestamp;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.spring.Inject;
import com.jfinal.plugin.spring.IocInterceptor;
import com.minstone.common.model.UserInfo;
import com.minstone.common.util.Tools;
import com.minstone.pms.common.helper.UserHelper;

import ${basepackage}.${classNameFirstLower}.service.${className}Service;
import ${basepackage}.${classNameFirstLower}.model.${className};

/**
 * ${wzz_functionName} controller 
 * @author ${wzz_classAuthor}
 * @version ${wzz_classVersion}
 */

@Before(IocInterceptor.class)
public class ${className}Controller extends Controller{
	
	/**
	 * this loger  can be used by controller in anywhere . wzz
	 */
	//private Logger log = LoggerFactory.getLogger(${className}Controller.class);
	
	@Inject.BY_TYPE
	private ${className}Service ${classNameFirstLower}Service;
	
	
	//---------------------------------
	/**
	 * 获取  ${wzz_functionName}  列表信息
	 * @return
	 */
	public void index() {
		List<Record> ${classNameFirstLower}List =  ${classNameFirstLower}Service.getList();
		
		setAttr("${classNameFirstLower}List", ${classNameFirstLower}List);
		
		render("list_${table.sqlNameRemovePrefixesLower}.jsp");
	}
	
	
	public void toForm(){
		String ${table.idColumn.sqlName?lower_case} = getPara("${table.idColumn.sqlName?lower_case}");
		if(Tools.isNotEmpty(${table.idColumn.sqlName?lower_case})){
			Record ${classNameFirstLower} = ${classNameFirstLower}Service.getById(${table.idColumn.sqlName?lower_case});
			setAttr("${classNameFirstLower}", ${classNameFirstLower});
		}
		
		render("form_${table.sqlNameRemovePrefixesLower}.jsp");
	}
	
	@Before(Tx.class)
	public void save() throws Exception{
		${className} ${classNameFirstLower} = getModel(${className}.class);
		
		UserInfo userInfo = UserHelper.getCurrentUser(getRequest());
		
		if (Tools.isEmpty(${classNameFirstLower}.get("${table.idColumn.sqlName?lower_case}"))) {//新增
			${classNameFirstLower}.set("create_time", new Timestamp(System.currentTimeMillis()));
			${classNameFirstLower}.set("creater_code", userInfo.getUserCode());
			${classNameFirstLower}.set("creater_name", userInfo.getUserName());
		} else {//修改
			${classNameFirstLower}.set("update_time", new Timestamp(System.currentTimeMillis()));
			${classNameFirstLower}.set("updater_code", userInfo.getUserCode());
			${classNameFirstLower}.set("updater_name", userInfo.getUserName());
		}
		
		boolean flag = ${classNameFirstLower}Service.saveOrUpdate(${classNameFirstLower});
		
		renderJson(flag);
	}
	
	@Before(Tx.class)
	public void delete(){
		String ${table.idColumn.sqlName?lower_case} = getPara("${table.idColumn.sqlName?lower_case}");
		
		boolean flag = ${classNameFirstLower}Service.deleteByIds(${table.idColumn.sqlName?lower_case});
		
		renderJson(flag);
	}
	
}
