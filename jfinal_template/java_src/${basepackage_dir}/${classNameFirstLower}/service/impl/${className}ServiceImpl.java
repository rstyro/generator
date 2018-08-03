<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 

package ${basepackage}.${classNameLower}.service.impl;

import ${basepackage}.${classNameLower}.model.${className};
import ${basepackage}.${classNameLower}.dao.${className}Dao;
import ${basepackage}.${classNameLower}.service.${className}Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minstone.common.util.Tools;

import com.jfinal.plugin.activerecord.Record;
import com.minstone.common.dbutil.DBManager;


/**
 * ${wzz_functionName}serviceImpl
 * @author ${wzz_classAuthor}
 * @version ${wzz_classVersion}
 */

@Service
public class ${className}ServiceImpl implements ${className}Service{
	
	/**
	 * this loger  can be used by service in anywhere . wzz
	 */
	//private Logger log = LoggerFactory.getLogger(${className}ServiceImpl.class);
	
	@Autowired
	private ${className}Dao ${classNameLower}Dao;
	
	@Override
	public boolean saveOrUpdate(${className} ${classNameLower}) {
		boolean flag = false;

		String ${table.idColumn.sqlName?lower_case} = ${classNameLower}.getStr("${table.idColumn.sqlName?lower_case}");
		if (Tools.isEmpty(${table.idColumn.sqlName?lower_case})) {
			${table.idColumn.sqlName?lower_case} = DBManager.getUUID();
			${classNameLower}.set("${table.idColumn.sqlName?lower_case}", ${table.idColumn.sqlName?lower_case});
			flag = ${classNameLower}Dao.save(${classNameLower});
		} else {
			flag = ${classNameLower}Dao.update(${classNameLower});
		}

		return flag;
	}

	@Override
	public Record getById(String ${table.idColumn.sqlName?lower_case}) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("${table.idColumn.sqlName?lower_case}", ${table.idColumn.sqlName?lower_case});
		return getOne(condition);
	}

	@Override
	public Record getOne(Map<String, Object> condition) {
		return ${classNameLower}Dao.getOne(condition);
	}

	@Override
	public List<Record> getList() {
		return getList(null);
	}

	@Override
	public List<Record> getList(Map<String, Object> condition) {
		return ${classNameLower}Dao.getList(condition);
	}

	@Override
	public boolean deleteByIds(String ${table.idColumn.sqlName?lower_case}s) {
		return ${classNameLower}Dao.deleteByIds(${table.idColumn.sqlName?lower_case}s);
	}
	
}
