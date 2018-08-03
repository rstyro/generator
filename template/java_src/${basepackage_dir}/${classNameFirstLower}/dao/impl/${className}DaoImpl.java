<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 

package ${basepackage}.${classNameLower}.dao.impl;


import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.stereotype.Repository;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.minstone.common.util.Tools;

import ${basepackage}.${classNameLower}.dao.${className}Dao;
import ${basepackage}.${classNameLower}.model.${className};

/**
 * ${wzz_functionName}daoImpl
 * @author ${wzz_classAuthor}
 * @version ${wzz_classVersion}
 */

@Repository
public class ${className}DaoImpl implements ${className}Dao{
	

	@Override
	public boolean save(${className} ${classNameLower}) {
		return ${classNameLower}.save();
	}

	@Override
	public boolean update(${className} ${classNameLower}) {
		return ${classNameLower}.update();
	}

	@Override
	public Record getOne(Map<String, Object> condition) {
		Vector<Object> params = new Vector<Object>();
		StringBuffer sql = new StringBuffer("select * from ${table.sqlName?lower_case} where 1 = 1 ");
		
		if(null != condition){
			Object ${table.idColumn.sqlName?lower_case} = condition.get("${table.idColumn.sqlName?lower_case}");
			if(null != ${table.idColumn.sqlName?lower_case} && Tools.isNotEmpty(${table.idColumn.sqlName?lower_case}.toString())){
				sql.append(" and ${table.idColumn.sqlName?lower_case} = ?");
				params.add(${table.idColumn.sqlName?lower_case});
			}
		}
		
		Record record = Db.findFirst(sql.toString(),params.toArray());
		
		return record;
	}

	@Override
	public List<Record> getList(Map<String, Object> condition) {
		Vector<Object> params = new Vector<Object>();
		StringBuffer sql = new StringBuffer("select * from ${table.sqlName?lower_case} where 1 = 1 ");
		
		if(null != condition){
			<#list table.columns as column>               
			Object ${column.sqlName?lower_case} = condition.get("${column.sqlName?lower_case}");
			if(null != ${column.sqlName?lower_case} && Tools.isNotEmpty(${column.sqlName?lower_case}.toString())){
				sql.append(" and ${column.sqlName?lower_case} = ?");
				params.add(${column.sqlName?lower_case});
			}
			
			</#list>
	       
			
			//排序
			Object orderby = condition.get("orderby");
			if (null != orderby && Tools.isNotEmpty(orderby.toString())) {
				sql.append(" order by " + orderby);
			}
			Object sort = condition.get("sort");
			if (null != sort && Tools.isNotEmpty(sort.toString())) {
				sql.append(" " + sort);
			}
		}
		
		List<Record> list = Db.find(sql.toString(),params.toArray());
		
		return list;
	}

	@Override
	public boolean deleteByIds(String ${table.idColumn.sqlName?lower_case}s)  {
		Vector<Object> params = new Vector<Object>();
		StringBuffer sql = new StringBuffer("delete from ${table.sqlName?lower_case} where ${table.idColumn.sqlName?lower_case} in ( ");
		String[] ${table.idColumn.sqlName?lower_case}_array =  ${table.idColumn.sqlName?lower_case}s.split(",");
		for (int i = 0; i < ${table.idColumn.sqlName?lower_case}_array.length; i++) {
			String ${table.idColumn.sqlName?lower_case} = ${table.idColumn.sqlName?lower_case}_array[i];
			sql.append("?");
			params.add(${table.idColumn.sqlName?lower_case});
			if(i<${table.idColumn.sqlName?lower_case}_array.length - 1){
				sql.append(",");
			}
		}
		sql.append(" )");
		boolean flag = Db.update(sql.toString(),params.toArray()) > 0 ? true : false;
		
		return flag;
	}
	
	
}