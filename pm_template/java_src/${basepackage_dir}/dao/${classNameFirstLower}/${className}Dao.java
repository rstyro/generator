<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 

package ${basepackage}.dao.${classNameLower};

import java.util.List;
import org.springframework.stereotype.Repository;
import com.otcbi.admin.util.ParameterMap;


/**
 * ${wzz_functionName}Maper
 * @author ${wzz_classAuthor}
 * @version ${wzz_classVersion}
 */

@Repository
public interface ${className}Dao {
	
    int deleteByPrimaryKey(Long ${classNameLower}Id);

    int insert(ParameterMap pm);

    int insertSelective(ParameterMap pm);

    ParameterMap selectByPrimaryKey(Long ${classNameLower}Id);

    int updateByPrimaryKeySelective(ParameterMap pm);

    int updateByPrimaryKey(ParameterMap pm);
    
    int deleteByIds(String[] ids);
    
    ParameterMap getByCondition(ParameterMap pm);
    
    List<ParameterMap> getByConditionList(ParameterMap pm);
    
    long getByConditionCount(ParameterMap pm);
}