<#include "/macro.include"/> 
<#include "/custom.include"/> 
<#assign className = table.className>   
<#assign classNameLowerCase = className?lower_case>
<#assign classNameFirstLower = className?uncap_first> 
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/common_qui.jsp"%>
<%@ include file="/WEB-INF/views/common/common_attach.jsp"%>

<body>
	<!-- begin  -->
	<div class="box1" panelWidth="100%" whiteBg="true">
  		<form id="modelForm" method="post" action="<@jspEl 'PATH'/>/${classNameFirstLower}/save.do" >

			<#list table.columns as column>
			<#if column.htmlHidden>
			<input type="hidden" name="${classNameFirstLower}.${column.sqlName?lower_case}" value="<@jspEl classNameFirstLower+"."+column.sqlName?lower_case />" />
			</#if>
			</#list>
			
			<table class="tableStyle" formMode="line" footer="normal">
				<tbody>
				<#list table.notPkColumns?chunk(2) as row>
					<tr>
						<#list row as column>
						<#if !column.htmlHidden>
						<td>
							${column.columnAlias}
							<span class="star">*</span>
						</td>
						<td>
							<#if column.isDateTimeColumn>
							<input type="text" id="${column.sqlName?lower_case}" name="${classNameFirstLower}.${column.sqlName?lower_case}" value="<fmt:formatDate value="<@jspEl classNameFirstLower+'.'+column.sqlName?lower_case />" pattern="yyyy-MM-dd"/>"    
							style="width: 70%;" class="dateIcon" readonly="readonly" 
							onclick="WdatePicker({skin:themeColor,maxDate:'%y-%M-%d'})" />						
							<#else>
							<input type="text" class="" style="width:80%;" id="${column.sqlName?lower_case}" name="${classNameFirstLower}.${column.sqlName?lower_case}" value="<@jspEl classNameFirstLower+"."+column.sqlName?lower_case />" />
							</#if>
						</td>
						</#if>
						</#list>
					</tr>
				</#list>	
					
				</tbody>
			</table>
			
			<div align="center" style="height: 40px;padding-top: 20px;">
				<input type="submit" value="确定"/>
			</div>
  		</form>
 	</div>
 	
 	<script type="text/javascript">
		$(document).ready(function(){
		});
		
		//重置
		function closeWin(){
			//刷新数据
			listPage.refreshPage();
			//关闭窗口
			frmDialog.close();
		}
	
	
		function initComplete(){
		    //表单提交
		    $('#modelForm').submit(function(){ 
			    //判断表单的客户端验证是否通过
				var valid = $('#modelForm').validationEngine({returnIsValid: true});
				if(valid){
				   $(this).ajaxSubmit({
				        //表单提交成功后的回调
				        success: function(responseText, statusText, xhr, $form){
				        	frmDialog.alert("操作成功！",function(){
				            	closeWin();
				            });
				        }
				    }); 
				 }
			    //阻止表单默认提交事件
			    return false;
			});
		}
	</script>
  	<!-- end  -->
</body>
</html>