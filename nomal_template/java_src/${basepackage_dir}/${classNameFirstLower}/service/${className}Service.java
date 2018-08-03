<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 

package ${basepackage}.${classNameLower}.service;
import ${basepackage}.${classNameLower}.model.${className};

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;


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
     	public int insert(${className} record) throws Exception;

	    /**
	     * 插入一条全新的记录  （带字段选择）
	     * @param record
	     * @return
	     */
     	public int insertSelective(${className} record) throws Exception;

        /**
         * 根据主键查询记录
         * @param ${classNameLower}Id
         * @return
         */
     	public ${className} selectByPrimaryKey(${table.idColumn.javaType} ${classNameLower}Id) throws Exception;

	    /**
	     * 更新记录（带字段）
	     * @param record
	     * @return
	     */
     	public int updateByPrimaryKeySelective(${className} record) throws Exception;

	    /**
	     * 更新记录（全字段）
	    * @param record
	     * @return
	     */
     	public int updateByPrimaryKey(${className} record) throws Exception;
     	
     	
     	/**
     	 * 保存或新增
     	 * @param record
     	 * @param operateType
     	 * @return
     	 * @throws Exception
     	 */
    	public boolean saveOrUpdate(${className} record,String operateType) throws Exception;
    	
    	/**
    	 * 根据主键取对象
    	 * @author wzz
    	 * @param id
    	 * @return
    	 * @throws Exception
    	 */
    	public ${className} getById(${table.idColumn.javaType} id) throws Exception;
    	
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
	    public ${className} getByCondition(HashMap<String,Object> argmap)throws Exception;
	    
	    
	    /**
	     * 根据条件查询 列表集合
	     * @param argmap
	     * @return
	     */
	    public List<${className}> getByCondition4List(HashMap<String,Object> argmap)throws Exception;
	    /**
	     * 根据条件查询集合数量
	     * @param argmap
	     * @return
	     * @throws Exception
	     */
	    public long getByCondition4Count(HashMap<String, Object> argmap) throws Exception;
}
