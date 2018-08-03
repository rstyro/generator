<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 

package ${basepackage}.service.${classNameLower};

import java.util.List;
import com.otcbi.admin.util.ParameterMap;

/**
 * ${wzz_functionName}service
 * @author ${wzz_classAuthor}
 * @version ${wzz_classVersion}
 */

public interface ${className}Service {
	
        /**
         * 根据主键删除
         * @param ${classNameLower}Id
         * @return
         */
     	public int deleteByPrimaryKey(${table.idColumn.javaType} ${classNameLower}Id) throws Exception;

	    /**
	     * 插入一条全新的记录
	     * @param record
	     * @return
	     */
     	public int insert(ParameterMap pm) throws Exception;

	    /**
	     * 插入一条全新的记录  （带字段选择）
	     * @param record
	     * @return
	     */
     	public int insertSelective(ParameterMap pm) throws Exception;

        /**
         * 根据主键查询记录
         * @param ${classNameLower}Id
         * @return
         */
     	public ParameterMap selectByPrimaryKey(${table.idColumn.javaType} ${classNameLower}Id) throws Exception;

	    /**
	     * 更新记录（带字段）
	     * @param record
	     * @return
	     */
     	public int updateByPrimaryKeySelective(ParameterMap pm) throws Exception;

	    /**
	     * 更新记录（全字段）
	    * @param record
	     * @return
	     */
     	public int updateByPrimaryKey(ParameterMap pm) throws Exception;
     	
     	
     	/**
     	 * 保存或新增
     	 * @param record
     	 * @param operateType
     	 * @return
     	 * @throws Exception
     	 */
    	public boolean saveOrUpdate(ParameterMap pm,String operateType) throws Exception;
    	
    	/**
    	 * 根据 主键 
    	 * @author wzz
    	 * @param ids
    	 * @return
    	 * @throws Exception
    	 */
    	public boolean delete${className}(String ids) throws Exception;
    	
       	/**
    	 * 根据条件查询 对象
    	 * @param argmap
    	 * @return
    	 */
	    public ParameterMap getByCondition(ParameterMap pm)throws Exception;
	    
	    
	    /**
	     * 根据条件查询 列表集合
	     * @param argmap
	     * @return
	     */
	    public List<ParameterMap> getByConditionList(ParameterMap pm)throws Exception;
	    /**
	     * 根据条件查询集合数量
	     * @param argmap
	     * @return
	     * @throws Exception
	     */
	    public long getByConditionCount(ParameterMap pm) throws Exception;
}
