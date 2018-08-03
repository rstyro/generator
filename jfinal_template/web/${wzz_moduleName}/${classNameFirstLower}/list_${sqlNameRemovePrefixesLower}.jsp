<#include "/macro.include"/> 
<#include "/custom.include"/> 
<#assign className = table.className>   
<#assign classNameLowerCase = className?lower_case>
<#assign classNameFirstLower = className?uncap_first> 
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ include file="/WEB-INF/views/common/common_qui.jsp"%>
<body class="">
	<!-- begin -->
	<div class="box2" paneltitle="${wzz_functionName}管理">
		<form id="searchForm">
			<table id="queryTable" class="tableStyle" formMode="transparent" footer="normal">
				<#list table.notPkColumns?chunk(3) as row>
				<tr>
					<#list row as column>
					<#if !column.htmlHidden>	
					<td> ${column.columnAlias}：
					</td>
					<td>
						<#if column.isDateTimeColumn>
						<input type="text" id="${column.sqlName?lower_case}_begin" name="${column.sqlName?lower_case}_begin" class="date" style="width:100px;" dateFmt="yyyy-MM-dd" value=""/>
						-
						<input type="text" id="${column.sqlName?lower_case}_end" name="${column.sqlName?lower_case}_end" class="date" style="width:100px;" dateFmt="yyyy-MM-dd" value=""/>
						<#else>
						<input type="text" style="width:150px;" value="" id="${column.sqlName?lower_case}" name="${column.sqlName?lower_case}" onkeypress="onKeyPressSearch(this);" />
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
	</div>
	<div class="box_tool_min padding_top2 padding_bottom2 padding_right5">
		<div class="center">
			<div class="left">
				<div class="right">
					<div class="padding_top5 padding_left10">
						<common:btnauth btnname="add">
						    <a href="javascript:;" onclick="addItem()">
						    	<span class="icon_add">增加</span>
						    </a>
							<div class="box_tool_line"></div>
						</common:btnauth>
						<common:btnauth btnname="edit">
						    <a href="javascript:;" onclick="editItem()">
						    	<span class="icon_edit">修改</span>
						    </a>
							<div class="box_tool_line"></div>
						</common:btnauth>
						<common:btnauth btnname="del">
							<a href="javascript:;" onclick="deleteItem()">
								<span class="icon_delete">删除</span>
							</a>
							<div class="box_tool_line"></div>
						</common:btnauth>
						<common:btnauth btnname="import">
							<a href="javascript:;" onclick="inputItem()">
								<span class="icon_import">导入</span>
							</a>
							<div class="box_tool_line"></div>
						</common:btnauth>
						<common:btnauth btnname="export">
							<a href="javascript:;" onclick="outputItem()">
								<span class="icon_export">导出</span>
							</a>
						</common:btnauth>
						<div class="clear"></div>
					</div>
				</div>		
			</div>	
		</div>
		<div class="clear"></div>
	</div>
	<!-- 通用列表标签 -->
	<mstn:list id='${table.sqlNameRemovePrefixes}' pageSize='10' showTools='false' dataGrid = "true"/>
			
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
			var ${column.sqlName?lower_case}_begin = $("input[name='${column.sqlName?lower_case}_begin']").val();
			var ${column.sqlName?lower_case}_end = $("input[name='${column.sqlName?lower_case}_end']").val();
			<#else>
			var ${column.sqlName?lower_case} = $("input[name='${column.sqlName?lower_case}']").val();
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
	        if(${column.sqlName?lower_case}_begin!= ""){
	        	wherehashmap.push(" ${column.sqlName} >= '" + ${column.sqlName?lower_case}_begin + "' ");
			}
			if(${column.sqlName?lower_case}_end!= ""){
				wherehashmap.push(" ${column.sqlName} <= '" + ${column.sqlName?lower_case}_end + "' ");
			}
			<#else>
			if(${column.sqlName?lower_case}!= ""){
				wherehashmap.push(" ${column.sqlName} like '%" + ${column.sqlName?lower_case} + "%' ");
			}
			</#if>
	        </#if>
			</#list>			
			whereSql += wherehashmap.join(" and ");
			searchList(whereSql);
		}
	
		//绑定enter事件
		function onKeyPressSearch(inputObject){
			if (inputObject.value == ""){
				return;
			}
			if (event.keyCode!=13){
				return;
			}
			Search();
		}

		
		//新增
		function addItem(){
			var url = '<@jspEl 'ctx'/>/${classNameFirstLower}/toForm.do';
			frmDialog.open({URL:url,Title:"${wzz_functionName}",Width:950,Height:450,ShowMaxButton:true});
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
		   	
		   	var url = '<@jspEl 'ctx'/>/${classNameFirstLower}/toForm.do?${table.idColumn.sqlName?lower_case}='+id;
		   	frmDialog.open({URL:url,Title:"${wzz_functionName}",Width:950,Height:450,ShowMaxButton:true});
		}
		
		//删除
		function deleteItem() {
			var ids = getMultiSelected('${table.idColumn.sqlName}');
			
		   	if(ids == null){
		   		warnInfo("请选中要删除的记录!");
		   		return;
		   	}
		   	
		   	
		   	frmDialog.confirm("确定要删除该记录吗？",function(){
		   		$("body").mask("正在加载中...", null, true);
		   		var url = '<@jspEl 'ctx'/>/${classNameFirstLower}/delete.do?${table.idColumn.sqlName?lower_case}='+ids;

				$.ajax({
					url : url,
					type : "POST",
					success : function(data) {
						if (data) {
							frmDialog.alert("删除成功！",function(){
								refreshPage();
				            });
						} else {
							warnInfo("删除失败!");
						}
						$("body").unmask();
					},
					error : function() {
						warnInfo("删除失败!");
						$("body").unmask();
					}
				});
		   	});
		}
	</script>
</body>
</html>