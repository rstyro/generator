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
	</script>
</head>
<body class="easyui-layout">
	<!-- begin  -->
  	<div data-options="region:'center'">
		<div style="width:100%;height:330px;overflow-y:auto;">
	  		<form id="modelForm" method="post" class="admin-baseMsg-content-form" action="<@jspEl 'ctx'/>/${classNameFirstLower}/save" >

				<#list table.columns as column>
				<#if column.htmlHidden>
					<input type="hidden" value="<@jspEl classNameFirstLower+"."+column.columnNameLower />"  id="${column.columnNameLower}" name="${column.columnNameLower}"   />
					<input type="hidden" value="<@jspEl 'operateType' />"   id="operateType" name="operateType"   />
				</#if>
				</#list>
				
				<table cellpadding="0" cellspacing="0" border="0" class="ui-table-form-border ">
					<tbody>
					<#list table.notPkColumns?chunk(2) as row>
						<tr>
							<#list row as column>
							<#if !column.htmlHidden>
								<th class="table_td_title">
									<%=${className}.ALIAS_${column.constantName}%>:
								</th>
								<td class="table_td_body">
									<#if column.isDateTimeColumn>
										<input type="text" 
										value="<@jspEl classNameFirstLower+"."+column.columnNameLower+"String"/>" 
										onclick="WdatePicker({doubleCalendar:false,dateFmt:'<%=${className}.FORMAT_${column.constantName}%>'})" 
										id="${column.columnNameLower}String" name="${column.columnNameLower}String"  
										class="Validform_input Wdate" pattern="<%=${className}.FORMAT_${column.constantName}%>" readonly="true"  />
										<span class="Validform_checktip">请选择日期</span>
									<#else>
										<input type="text" value="<@jspEl classNameFirstLower+"."+column.columnNameLower />"  id="${column.columnNameLower}" name="${column.columnNameLower}"  class="Validform_input" datatype="*" />
										<span class="Validform_checktip">请输入内容</span>
									</#if>
								</td>
							</#if>
							</#list>
						</tr>
					</#list>	
						
					</tbody>
				</table>
				
				<div align="center">
					<a href="#" class="ui-dialog-button ui-dialog-save-button" id="saveBtn">确定</a>
	                <a href="#" class="ui-dialog-button ui-dialog-close-button" id="closeBtn">关闭</a>
				</div>
	  		</form>
  		</div>
  	</div>	
  	<!-- end  -->
</body>
  <script type="text/javascript">
	$(function() {
		/*关闭按钮*/
		$("#closeBtn").live("click",function() {
			art.dialog.close();
		});	
	
		$("#modelForm").Validform({
			tiptype:4,
			btnSubmit:"#saveBtn",
			ajaxPost:true,
			callback:function(data){
				if(data.flag){ 
					var win=art.dialog.open.origin;
					win.refreshPage();
					art.dialog.close();
				}else{
					errorInfo();
					return false;
				}
			}
		});
	});
	</script>
</html>


	