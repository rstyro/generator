<#include "/macro.include"/> 
<#include "/custom.include"/> 
<#assign className = table.className>   
<#assign classNameLowerCase = className?lower_case>
<#assign classNameFirstLower = className?uncap_first> 
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="${basepackage}.${classNameLowerCase}.model.${className}" %>

	<%@ include file="/WEB-INF/views/common/common_qui.jsp"%>
	<body class="">
		<!-- begin -->
			<div class="box2" paneltitle="${wzz_functionName}管理">
				<form id="searchForm">
					<table id="queryTable" style="width: 100%;border: 0" class="ui-table-layout ">
						<#list table.notPkColumns?chunk(3) as row>
							<tr>
								<#list row as column>
								<#if !column.htmlHidden>	
									<td style="text-align: right;"> ${column.columnAlias}：
									</td>
									<td>
										<#if column.isDateTimeColumn>
										
											<input type="text" style="width:100px;" value="" class="Validform_input Wdate"
											onclick="WdatePicker({doubleCalendar:false,dateFmt:'<%=${className}.FORMAT_${column.constantName}%>'})" 
											id="${column.columnNameLower}Begin" name="${column.columnNameLower}Begin"  
											onkeypress="onKeyPressSearch(this);" readonly="true"  />
											
											<input type="text" style="width:100px;" value="" class="Validform_input Wdate"
											onclick="WdatePicker({doubleCalendar:false,dateFmt:'<%=${className}.FORMAT_${column.constantName}%>'})" 
											id="${column.columnNameLower}End" 
											name="${column.columnNameLower}End"      
											onkeypress="onKeyPressSearch(this);" readonly="true"  />
											
										<#else>
										    <input type="text" style="width:150px;" value="" id="${column.columnNameLower}" name="${column.columnNameLower}" onkeypress="onKeyPressSearch(this);" />
										</#if>
									</td>
								</#if>
								</#list>
							</tr>
						</#list>					
						    <tr>
						    	<td colspan="3">
									<button type="button" onclick="Search()"><span class="icon_find">查询</span></button>
									<button type="button" onclick="$('#searchForm')[0].reset()"><span class="icon_reload">重置</span></button> 
								</td>
						    </tr>
					</table>
				</form>
				
				<div class="ui-table-head">
					<ul class="ui-table-tablebar" style="background: none;">
						<li>
							<a href="javascript:addItem()" class="easyui-linkbutton" data-options="iconCls:'icon16-plus',plain:true">增加</a>
						</li>
						<li>
							<a href="javascript:editItem()" class="easyui-linkbutton" data-options="iconCls:'icon16-pencil',plain:true">修改</a>
						</li>
						<li>
							<a href="javascript:deleteItem()" class="easyui-linkbutton" data-options="iconCls:'icon16-cross-script',plain:true">删除</a>
						</li>
					</ul>
				</div>
					
				<!-- 通用列表标签   属性showTools=‘true’为显示工具栏-->
				<mstn:list id='${classNameLowerCase}' pageSize='10' showTools='true' />
			</div>
		<!-- end -->
		
			<script type="text/javascript">
			$(document).ready(function() {
				
			});
			
			//条件查询
			function Search() {
				//获取条件输入框中的值
				 <#list table.columns as column>
		           <#if !column.pk>
		           		<#if column.isDateTimeColumn>
			        		var ${column.columnNameLower}Begin = $("input[name='${column.columnNameLower}Begin']").val();
			        		var ${column.columnNameLower}End = $("input[name='${column.columnNameLower}End']").val();
						<#else>
			        		var ${column.columnNameLower} = $("input[name='${column.columnNameLower}']").val();
						</#if>
		           </#if>
				</#list>
				//拼接条件sql  , 
				var whereSql = " ";
				var wherehashmap = [] ;
				wherehashmap.push(" 1=1 ");
				var sqlPara = new SQLPara();	
				 <#list table.columns as column>
		           <#if !column.pk>
		           		<#if column.isDateTimeColumn>
						   if(${column.columnNameLower} && ${column.columnNameLower}Begin!= ""){
							   wherehashmap.push(" ${column.sqlName} >= ? ");
							   sqlPara.add(${column.columnNameLower}Begin,3);
						   }
						   if(${column.columnNameLower} && ${column.columnNameLower}End!= ""){
							   wherehashmap.push(" ${column.sqlName} <= ? ");
							   sqlPara.add(${column.columnNameLower}End,3);
						   }
						<#else>
						   if(${column.columnNameLower} && ${column.columnNameLower}!= ""){
							   wherehashmap.push(" ${column.sqlName} like ? ");
							   sqlPara.add("%"+${column.columnNameLower}+"%",2);
						   }
						</#if>
		           </#if>
				</#list>			
				whereSql += wherehashmap.join(" and ");
				searchListByPara(whereSql,sqlPara);
			}
		
			//绑定enter事件
			function onKeyPressSearch(inputObject){
				if (inputObject.value == "")
				  return;
		       if (event.keyCode!=13){
		           return;
		       }
		
			   Search();
			}
	
			//打开dialog	
			function openDialog(url){
				art.dialog.open(
		           url,
		            {
		               title: '${wzz_functionName}信息',
		               width: '1100px',          //todo ,programer set it self
		               height:'500px',
		               background:'#000',
		               left:'35%',
		               lock:true,
		               opacity:0.2
		            }
		         );
			}
			
			//新增
			function addItem(){
				var url = '<@jspEl 'ctx'/>/auth/${wzz_moduleName}/${classNameFirstLower}/add';
				openDialog(url);
			}
			
			//修改
			function editItem(){
				var id = getMultiSelected('${table.idColumn.sqlName}');
				
			   	if(id == null){
			   		warnInfo("请选择需要修改的记录！");
			   		return;
			   	}
			   	
			   	if(id.split(",").length > 1){
			   		warnInfo("不能同时对多条记录进行修改！");
			   		return;
			   	}
			   	
			   	var url = '<@jspEl 'ctx'/>/auth/${wzz_moduleName}/${classNameFirstLower}/edit/'+id;
				openDialog(url);
			}
			
			//删除
			function deleteItem() {
				var ids = getMultiSelected('${table.idColumn.sqlName}');
				
			   	if(ids == null){
			   		warnInfo("请选择需要删除的记录！");
			   		return;
			   	}
			   	
			   	var sResult = confirm("确定删除？");
			   	if(sResult){
				   	var url = '<@jspEl 'ctx'/>/auth/${wzz_moduleName}/${classNameFirstLower}/delete/'+ids;
	
					$.ajax({
						url : url,
						type : "POST",
						success : function(data) {
							if (data.flag) {
								refreshPage();
							} else {
								errorInfo("删除失败！");
							}
						},
						error : function() {
							errorInfo("删除失败！");
						}
					});
			   	}
			}		
		</script>
		
	</body>
</html>




