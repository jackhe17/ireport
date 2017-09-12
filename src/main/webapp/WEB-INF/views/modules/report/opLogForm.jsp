<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function addClomn() {
		
			var newTr = tbl1.insertRow();
		    var newTd0 = newTr.insertCell();
		    var newTd1 = newTr.insertCell();
		    var newTd2 = newTr.insertCell();
		    var newTd3 = newTr.insertCell();
		    var newTd4 = newTr.insertCell();
		    var newTd5 = newTr.insertCell();
		    var newTd6 = newTr.insertCell();
		    var newTd7 = newTr.insertCell();
		    var newTd8 = newTr.insertCell();
 			newTd0.innerHTML = '<tr><td><input type="text" id="poolId" name="poolId"  htmlEscape="false" maxlength="50" class="input-mini "/></td>';
		    newTd1.innerHTML = '<td><input type="text" id="waterStartTime" name="waterStartTime"  htmlEscape="false" maxlength="50" class="input-mini "/></td>';
		    newTd2.innerHTML = '<td><input type="text" id="waterEndTime" name="waterEndTime"  htmlEscape="false" maxlength="50" class="input-mini "/></td>';
		    newTd3.innerHTML = '<td><input type="text" id="waterUsage" name="waterUsage"  htmlEscape="false" maxlength="50" class="input-mini "/></td>';
		    newTd4.innerHTML = '<td><input type="text" id="dryHour" name="dryHour" htmlEscape="false" maxlength="50" class="input-mini "/></td>';
		    newTd5.innerHTML = '<td><input type="text" id="dryMin" name="dryMin"  htmlEscape="false" maxlength="50" class="input-mini "/></td>';
		    newTd6.innerHTML = '<td><input type="text" id="logRemarks" name="logRemarks"  htmlEscape="false" maxlength="50" class="input-mini "/></td>';
		    newTd7.innerHTML = '<td><input type="text" id="logRecorder" name="logRecorder"  htmlEscape="false" maxlength="50" class="input-mini "/></td>';
		    newTd8.innerHTML = '<td><a href="javascript:" onclick="deleteClomn(this);" class="btn"><i class="icon-minus"></i></a> </td></tr>';

		};
		function deleteClomn(r){  
		    if(confirm("确定要删除当前行?"))  
		    {  
		        var i=r.parentNode.parentNode.rowIndex;  
		        document.getElementById('tbl1').deleteRow(i);  
		    } 
		}
	</script>
</head>
<body>
<ul class="nav nav-tabs">
		<li ><a href="${ctx}/report/oplog/list">运行日志查询</a></li>
		<shiro:hasPermission name="report:month:edit"><li class="active"><a href="${ctx}/report/oplog/form">运行日志添加</a></li></shiro:hasPermission>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oplog" action="${ctx}/report/oplog/save" method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<form:hidden path="id"/>
		<div class="control-group">
			<label class="lbl">${user.company.name}快渗池运行日志</label><br>
			
		</div>
		<div class="control-group">
			<table  id ="tbl1" class="table table-striped table-bordered table-condensed">
				<thead>
				<tr>
				    <th colspan="4">日 期	<input id="logDate" name="logDate" type="text" readonly="readonly" maxlength="50" class="input-mini Wdate"
								value="<fmt:parseDate value="${oplog.logDate}" pattern="yyyy-MM-dd" var="myDate"/> <fmt:formatDate value="${myDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/></th>
				    <th colspan="4">天 气<form:input path="weather" htmlEscape="false" maxlength="50" class="input-mini "/></th>
				  </tr>
				  <tr>
				    <th rowspan="2">布水池号</th>
				    <th colspan="2">布水时间</th>
				    <th rowspan="2">布水量（m<sup>3</sup>）</th>
				    <th rowspan="2">落干时刻</th>
				    <th rowspan="2">落干时间（min）</th>
				    <th rowspan="2">备注</th>
				    <th rowspan="2">记录人员</th>
				    <th rowspan="2"><a href="javascript:" onclick="addClomn();" class="btn"><i class="icon-plus"></i></a></th>
				  </tr>
				  <tr>
				    <td>开始</td>
				    <td>结束</td>
				  </tr>
				  </thead>
				   <tr>
				    <td><input type="text" id="poolId" name="poolId"  htmlEscape="false" maxlength="50" class="input-mini "/></td>
				    <td><input type="text" id="waterStartTime" name="waterStartTime"  htmlEscape="false" maxlength="50" class="input-mini "/></td>
				    <td><input type="text" id="waterEndTime" name="waterEndTime"  htmlEscape="false" maxlength="50" class="input-mini "/></td>
				    <td><input type="text" id="waterUsage" name="waterUsage"  htmlEscape="false" maxlength="50" class="input-mini "/></td>
				    <td><input type="text" id="dryHour" name="dryHour" htmlEscape="false" maxlength="50" class="input-mini "/></td>
				    <td><input type="text" id="dryMin" name="dryMin"  htmlEscape="false" maxlength="50" class="input-mini "/></td>
				    <td><input type="text" id="logRemarks" name="logRemarks"  htmlEscape="false" maxlength="50" class="input-mini "/></td>
				    <td><input type="text" id="logRecorder" name="logRecorder"  htmlEscape="false" maxlength="50" class="input-mini "/></td>
				    <td><a href="javascript:" onclick="deleteClomn(this);" class="btn"><i class="icon-minus"></i></a> </td>
				  </tr>
			</table>
		</div>	
		<div class="form-actions">
			<shiro:hasPermission name="report:month:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>