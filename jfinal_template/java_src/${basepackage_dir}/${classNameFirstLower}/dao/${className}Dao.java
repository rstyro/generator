<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 

package ${basepackage}.${classNameLower}.dao;

import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Record;

import ${basepackage}.${classNameLower}.model.${className};

/**
 * ${wzz_functionName}dao
 * @author ${wzz_classAuthor}
 * @version ${wzz_classVersion}
 */


public interface ${className}Dao {
	
	public boolean save(${className} ${classNameLower});
	
	public boolean update(${className} ${classNameLower});

	public Record getOne(Map<String,Object> condition);
	
	public List<Record> getList(Map<String, Object> condition);
	
	public boolean deleteByIds(String ${table.idColumn.sqlName?lower_case}s);
}