<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 

package ${basepackage}.${classNameLower}.service;
import ${basepackage}.${classNameLower}.model.${className};

import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Record;



/**
 * ${wzz_functionName}service
 * @author ${wzz_classAuthor}
 * @version ${wzz_classVersion}
 */

public interface ${className}Service {
	
	/**
	 * 新增或者修改
	 * @param 
	 * @return
	 */
	public boolean saveOrUpdate(${className} ${classNameLower});
	
	/**
	 * 根据ID获取
	 * @param condition
	 * @return
	 */
	public Record getById(String ${table.idColumn.sqlName?lower_case});

	/**
	 * 获取单个
	 * @param condition
	 * @return
	 */
	public Record getOne(Map<String,Object> condition);
	
	/**
	 * 获取所有
	 * @param condition
	 * @return
	 */
	public List<Record> getList();
	
	/**
	 * 获取数组
	 * @param condition
	 * @return
	 */
	public List<Record> getList(Map<String, Object> condition);
	
	/**
	 * 根据ID删除
	 * @param condition
	 * @return
	 */
	public boolean deleteByIds(String ${table.idColumn.sqlName?lower_case}s);
	
}
