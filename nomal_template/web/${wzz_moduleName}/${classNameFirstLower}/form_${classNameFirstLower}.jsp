<#include "/macro.include"/> 
<#include "/custom.include"/> 
<#assign className = table.className>   
<#assign classNameLowerCase = className?lower_case>
<#assign classNameFirstLower = className?uncap_first> 
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="${basepackage}.${classNameLowerCase}.model.${className}" %>

<%@ include file="/WEB-INF/views/common/common_qui.jsp"%>
<body>
	<!-- begin  -->
		<div class="box1" panelWidth="100%" panelTitle=""style="width:100%;height:100%;overflow-y:auto;overflow-x:hidden;">
	  		<form id="modelForm" method="post" class="admin-baseMsg-content-form" action="<@jspEl 'ctx'/>/auth/${wzz_moduleName}/${classNameFirstLower}/save"  failAlert="表单填写不正确，请按要求填写！">

				<#list table.columns as column>
				<#if column.htmlHidden>
					<input type="hidden" value="<@jspEl classNameFirstLower+"."+column.columnNameLower />"  id="${column.columnNameLower}" name="${column.columnNameLower}"   />
					<input type="hidden" value="<@jspEl 'operateType' />"   id="operateType" name="operateType"   />
				</#if>
				</#list>
				
				<table cellpadding="0" cellspacing="0" border="0"  class="tableStyle" formMode="line">
					<tbody>
					<#list table.notPkColumns?chunk(2) as row>
						<tr>
							<#list row as column>
							<#if !column.htmlHidden>
								<td class="" width="20%">
									${column.columnAlias}
									<em>&nbsp;&nbsp;</em>
								</td>
								<td class="">
									<#if column.isDateTimeColumn>
										<input type="text" style="width:80%;"
										value="<@jspEl classNameFirstLower+"."+column.columnNameLower+"String"/>" 
										onclick="WdatePicker({doubleCalendar:false,dateFmt:'<%=${className}.FORMAT_${column.constantName}%>'})" 
										id="${column.columnNameLower}String" name="${column.columnNameLower}String"  
										class=" Wdate" pattern="<%=${className}.FORMAT_${column.constantName}%>" readonly="true"  />
									<#else>
										<input type="text" style="width:80%;" value="<@jspEl classNameFirstLower+"."+column.columnNameLower />"  id="${column.columnNameLower}" name="${column.columnNameLower}"  class=""  />
									</#if>
								</td>
							</#if>
							</#list>
						</tr>
					</#list>	
						
					</tbody>
				</table>
				
				<div align="center" style="height: 50px">
					<button type="button"  onclick="save()"><span class="icon_add">确定</span></button>
					<button type="button"  onclick="closeDialog()"><span class="icon_add">关闭</span></button>
				</div>
	  		</form>
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


	