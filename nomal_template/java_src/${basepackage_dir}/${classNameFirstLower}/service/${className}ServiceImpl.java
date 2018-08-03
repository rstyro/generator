<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 

package ${basepackage}.${classNameLower}.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;


import ${basepackage}.${classNameLower}.model.${className};
import ${basepackage}.${classNameLower}.mapper.${className}Mapper;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.otcbi.common.service.impl.BaseService;

/**
 * ${wzz_functionName}service
 * @author ${wzz_classAuthor}
 * @version ${wzz_classVersion}
 */

@Service
@Transactional(readOnly = true)
public class ${className}ServiceImpl extends BaseService implements ${className}Service {
	
	/**
	 * this loger  can be used by service in anywhere . wzz
	 */
	@Autowired
	private ${className}Mapper ${classNameLower}Mapper;
	
	
	@Transactional(readOnly = false)
	 public  int deleteByPrimaryKey(${table.idColumn.javaType} ${classNameLower}Id){
		 return  ${classNameLower}Mapper.deleteByPrimaryKey(${classNameLower}Id);
	 }
	
	@Transactional(readOnly = false)
	 public   int insert(${className} record){
		 return ${classNameLower}Mapper.insert(record);
	 }
	
	@Transactional(readOnly = false)
	 public  int insertSelective(${className} record){
		 return ${classNameLower}Mapper.insertSelective(record);
	 }

	 public   ${className} selectByPrimaryKey(${table.idColumn.javaType} ${classNameLower}Id){
		 return ${classNameLower}Mapper.selectByPrimaryKey(${classNameLower}Id);
	 }
	 
	@Transactional(readOnly = false)
	 public  int updateByPrimaryKeySelective(${className} record){
		 return  ${classNameLower}Mapper.updateByPrimaryKeySelective(record);
	 }

	@Transactional(readOnly = false)
	 public  int updateByPrimaryKey(${className} record){
		 return  ${classNameLower}Mapper.updateByPrimaryKey(record);
	 }
	 
	@Transactional(rollbackFor=Exception.class)  
	public boolean saveOrUpdate(${className} record,String operateType) throws Exception{
		boolean flag = false;
		
		if("add".equals(operateType)){
			flag = this.${classNameLower}Mapper.insert(record) > 0 ? true : false;
		}
		
		if("edit".equals(operateType)){
			flag = this.${classNameLower}Mapper.updateByPrimaryKey(record) > 0 ? true : false;
		}
		
		return flag;
	}

	
	public ${className} getById(${table.idColumn.javaType} id){
		${className} record = null;
		record = this.${classNameLower}Mapper.selectByPrimaryKey(id);
		return record;
	}

	@Transactional(rollbackFor=Exception.class)
	public boolean delete${className}(String ids) throws Exception {
		boolean flag = false;
		if(ids != null && !"".equals(ids.trim())){
			String[] id = ids.split(",");
			flag = this.${classNameLower}Mapper.deleteByIds(id)> 0 ? true : false;
		}
		return flag;
	}
	
	public ${className} getByCondition(HashMap<String, Object> argmap) throws Exception {
		return this.${classNameLower}Mapper.getByCondition(argmap);
	}

	
	public List<${className}> getByCondition4List(HashMap<String, Object> argmap) throws Exception {
		return this.${classNameLower}Mapper.getByCondition4List(argmap);
	}
	
	public long getByCondition4Count(HashMap<String, Object> argmap) throws Exception {
		return this.${classNameLower}Mapper.getByCondition4Count(argmap);
	}
	
}
