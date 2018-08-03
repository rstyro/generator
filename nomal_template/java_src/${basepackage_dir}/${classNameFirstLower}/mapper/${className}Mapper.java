<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 

package ${basepackage}.${classNameLower}.mapper;

import java.util.List;
import java.util.HashMap;
import org.springframework.stereotype.Repository;

import ${basepackage}.${classNameLower}.model.${className};

/**
 * ${wzz_functionName}Maper
 * @author ${wzz_classAuthor}
 * @version ${wzz_classVersion}
 */

@Repository
public interface ${className}Mapper {
	
    int deleteByPrimaryKey(Long ${classNameLower}Id);

    int insert(${className} record);

    int insertSelective(${className} record);

    ${className} selectByPrimaryKey(Long ${classNameLower}Id);

    int updateByPrimaryKeySelective(${className} record);

    int updateByPrimaryKey(${className} record);
    
    int deleteByIds(String[] ids);
    
    ${className} getByCondition(HashMap<String,Object> argmap);
    
    List<${className}> getByCondition4List(HashMap<String,Object> argmap);
    
    long getByCondition4Count(HashMap<String,Object> argmap);
}