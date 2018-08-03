<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 

package ${basepackage}.service.${classNameLower}.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import com.otcbi.admin.util.ParameterMap;

import ${basepackage}.dao.${classNameLower}.${className}Dao;
import ${basepackage}.service.${classNameLower}.${className}Service;

/**
 * ${wzz_functionName}service
 * @author ${wzz_classAuthor}
 * @version ${wzz_classVersion}
 */

@Service
@Transactional(readOnly = true)
public class ${className}ServiceImpl implements ${className}Service {
	
	/**
	 * this loger  can be used by service in anywhere . wzz
	 */
	@Autowired
	private ${className}Dao ${classNameLower}Mapper;
	
	
	@Transactional(readOnly = false)
	 public  int deleteByPrimaryKey(${table.idColumn.javaType} ${classNameLower}Id){
		 return  ${classNameLower}Mapper.deleteByPrimaryKey(${classNameLower}Id);
	 }
	
	@Transactional(readOnly = false)
	 public   int insert(ParameterMap pm){
		 return ${classNameLower}Mapper.insert(pm);
	 }
	
	@Transactional(readOnly = false)
	 public  int insertSelective(ParameterMap pm){
		 return ${classNameLower}Mapper.insertSelective(pm);
	 }

	 public   ParameterMap selectByPrimaryKey(${table.idColumn.javaType} ${classNameLower}Id){
		 return ${classNameLower}Mapper.selectByPrimaryKey(${classNameLower}Id);
	 }
	 
	@Transactional(readOnly = false)
	 public  int updateByPrimaryKeySelective(ParameterMap pm){
		 return  ${classNameLower}Mapper.updateByPrimaryKeySelective(pm);
	 }

	@Transactional(readOnly = false)
	 public  int updateByPrimaryKey(ParameterMap pm){
		 return  ${classNameLower}Mapper.updateByPrimaryKey(pm);
	 }
	 
	@Transactional(rollbackFor=Exception.class)  
	public boolean saveOrUpdate(ParameterMap pm,String operateType) throws Exception{
		boolean flag = false;
		
		if("add".equals(operateType)){
			flag = this.${classNameLower}Mapper.insert(pm) > 0 ? true : false;
		}
		
		if("edit".equals(operateType)){
			flag = this.${classNameLower}Mapper.updateByPrimaryKey(pm) > 0 ? true : false;
		}
		
		return flag;
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
	
	public ParameterMap getByCondition(ParameterMap pm) throws Exception {
		return this.${classNameLower}Mapper.getByCondition(pm);
	}

	
	public List<ParameterMap> getByConditionList(ParameterMap argmap) throws Exception {
		return this.${classNameLower}Mapper.getByConditionList(argmap);
	}
	
	public long getByConditionCount(ParameterMap pm) throws Exception {
		return this.${classNameLower}Mapper.getByConditionCount(pm);
	}
	
}
