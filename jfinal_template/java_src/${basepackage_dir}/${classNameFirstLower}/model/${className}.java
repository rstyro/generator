<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 

package ${basepackage}.${classNameLower}.model;

import com.jfinal.ext.kit.ModelExt;
import com.jfinal.ext.plugin.tablebind.TableBind;


/**
 * ${wzz_functionName}Entity
 * @author ${wzz_classAuthor}
 * @version ${wzz_classVersion}
 */


@TableBind(configName="app",pkName="${table.idColumn.sqlName}",tableName="${table.sqlName}")
public class ${className} extends ModelExt<${className}> {
	private static final long serialVersionUID = 1L;
	public static final ${className} me = new ${className}();
}
