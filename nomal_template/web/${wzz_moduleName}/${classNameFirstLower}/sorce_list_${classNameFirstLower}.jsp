<#include "/macro.include"/> 
<#include "/custom.include"/> 
<#assign className = table.className>   
<#assign classNameLowerCase = className?lower_case>
<#assign classNameFirstLower = className?uncap_first> 
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="${basepackage}.${classNameLowerCase}.model.${className}" %>

<!DOCTYPE HTML>
<html>

<head>
	<title>${wzz_functionName}管理</title>
	<%@ include file="/WEB-INF/views/common/common.jsp"%>
	
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
			 <#list table.columns as column>
	           <#if !column.pk>
	           		<#if column.isDateTimeColumn>
					   if(${column.columnNameLower}Begin!= ""){
						   wherehashmap.push(" ${column.sqlName} >= '"+${column.columnNameLower}Begin+"' ");
					   }
					   if(${column.columnNameLower}End!= ""){
						   wherehashmap.push(" ${column.sqlName} <= '"+${column.columnNameLower}End+"' ");
					   }
					<#else>
					   if(${column.columnNameLower}!= ""){
						   wherehashmap.push(" ${column.sqlName} like '%"+${column.columnNameLower}+"%' ");
					   }
					</#if>
	           </#if>
			</#list>			
			whereSql += wherehashmap.join(" and ");
			alert(whereSql);
			//调用PublicQuery.js中的function searchList(whereSql,id)
			//searchList(whereSql,"base_right_new"); //当页面出现多列表时，需引入列表标识，用于辨别
			searchList(whereSql);
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
	               width: '600px',          //todo ,programer set it self
	               height:'400px',
	               background:'#000',
	               left:'35%',
	               lock:true,
	               opacity:0.2
	            }
	         );
		}
		
		//新增
		function addItem(){
			var url = '<@jspEl 'ctx'/>/${classNameFirstLower}/add';
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
		   	
		   	var url = '<@jspEl 'ctx'/>/${classNameFirstLower}/edit/'+id;
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
			   	var url = '<@jspEl 'ctx'/>/${classNameFirstLower}/delete/'+ids;

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
</head>
<body class="easyui-layout">
	<!-- begin -->
		<div data-options="region:'center',iconCls:'icon16-balloon-pencil'" title="${wzz_functionName}管理">
			<form id="searchForm">
				<table id="queryTable" style="width: 100%;border: 0" class="ui-table-layout ">
					<#list table.notPkColumns?chunk(4) as row>
						<tr>
							<#list row as column>
							<#if !column.htmlHidden>	
								<td style="text-align: right;">  <%=${className}.ALIAS_${column.constantName}%>：
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
					    	<td rowspan="4">
								<a href="javascript:Search();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查 询</a> 
								<a href="javascript:$('#searchForm')[0].reset();" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
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
</body>
</html>




