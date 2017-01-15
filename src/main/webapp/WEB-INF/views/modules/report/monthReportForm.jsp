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
		function addClomnIn() {
			
			var newTr = tblIn.insertRow();
		    var newTd0 = newTr.insertCell();
		    var newTd1 = newTr.insertCell();
		    var newTd2 = newTr.insertCell();
		    var newTd3 = newTr.insertCell();
		    var newTd4 = newTr.insertCell();
		    var newTd5 = newTr.insertCell();
		    var newTd6 = newTr.insertCell();
		    var newTd7 = newTr.insertCell();
		    newTd0.innerHTML = '<tr><td><input type="text" id="inDate" name ="inDate" htmlEscape="false" maxlength="20" class="input-mini "/></td>';
		    newTd1.innerHTML = '<td><input type="text" id="inCOD" name ="inCOD" htmlEscape="false" maxlength="20" class="input-mini "/></td>';
		    newTd2.innerHTML = '<td><input type="text" id="inNh3h" name ="inNh3h" htmlEscape="false" maxlength="20" class="input-mini "/></td>';
		    newTd3.innerHTML = '<td><input type="text" id="inTp" name ="inTp" htmlEscape="false" maxlength="20" class="input-mini "/></td>';
		    newTd4.innerHTML = '<td><input type="text" id="inTn" name ="inTn" htmlEscape="false" maxlength="20" class="input-mini "/></td>';
		    newTd5.innerHTML = '<td><input type="text" id="inSs" name ="inSs" htmlEscape="false" maxlength="20" class="input-mini "/></td>';
		    newTd6.innerHTML = '<td><input type="text" id="inPh" name ="inPh" htmlEscape="false" maxlength="20" class="input-mini "/></td>';
			newTd7.innerHTML = '<td><a href="javascript:" onclick="deleteClomn(this);" class="btn"><i class="icon-minus"></i></a> </td></tr>';

		};
		function deleteClomnIn(r){  
		    if(confirm("确定要删除当前行?"))  
		    {  
		        var i=r.parentNode.parentNode.rowIndex;  
		        document.getElementById('tblIn').deleteRow(i);  
		    } 
		}
		function addClomnOut() {
			
			var newTr = tblOut.insertRow();
		    var newTd0 = newTr.insertCell();
		    var newTd1 = newTr.insertCell();
		    var newTd2 = newTr.insertCell();
		    var newTd3 = newTr.insertCell();
		    var newTd4 = newTr.insertCell();
		    var newTd5 = newTr.insertCell();
		    var newTd6 = newTr.insertCell();
		    var newTd7 = newTr.insertCell();
		    newTd0.innerHTML = '<tr><td><input type="text" id="outDate" name ="outDate" htmlEscape="false" maxlength="20" class="input-mini "/></td>';
		    newTd1.innerHTML = '<td><input type="text" id="outCOD" name ="outCOD" htmlEscape="false" maxlength="20" class="input-mini "/></td>';
		    newTd2.innerHTML = '<td><input type="text" id="outNh3h" name ="outNh3h" htmlEscape="false" maxlength="20" class="input-mini "/></td>';
		    newTd3.innerHTML = '<td><input type="text" id="outTp" name ="outTp" htmlEscape="false" maxlength="20" class="input-mini "/></td>';
		    newTd4.innerHTML = '<td><input type="text" id="outTn" name ="outTn" htmlEscape="false" maxlength="20" class="input-mini "/></td>';
		    newTd5.innerHTML = '<td><input type="text" id="outSs" name ="outSs" htmlEscape="false" maxlength="20" class="input-mini "/></td>';
		    newTd6.innerHTML = '<td><input type="text" id="outPh" name ="outPh" htmlEscape="false" maxlength="20" class="input-mini "/></td>';
			newTd7.innerHTML = '<td><a href="javascript:" onclick="deleteClomn(this);" class="btn"><i class="icon-minus"></i></a> </td></tr>';

		};
		function deleteClomnOut(r){  
		    if(confirm("确定要删除当前行?"))  
		    {  
		        var i=r.parentNode.parentNode.rowIndex;  
		        document.getElementById('tblOut').deleteRow(i);  
		    } 
		}
	</script>
</head>
<body>
<ul class="nav nav-tabs">
		<li ><a href="${ctx}/report/month/list/">月报表列表</a></li>
		<li class="active"><a href="${ctx}/report/month/form">月报表添加</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="report" action="${ctx}/report/month/save" method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<form:hidden path="id"/>
		<div class="control-group">
			<label class="lbl">${user.company.name}生产月报表</label><br>
			<label class="input-label" >汇报时间</label>
			<input id="reportDate" name="reportDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
								value="<fmt:parseDate value="${report.reportDate}" pattern="yyyy-MM-dd" var="myDate"/> <fmt:formatDate value="${myDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			<label class="input-label" >汇报月度</label>
			<input id="reportMonth" name="reportMonth" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
								value="<fmt:parseDate value="${report.reportMonth}" pattern="yyyy-MM" var="myDate"/> <fmt:formatDate value="${myDate}" pattern="yyyy-MM"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true});"/>
		</div>
		<div class="control-group">
			<label class="lbl">一,厂区基本运营情况</label><br>
			<label class="lbl">（1）处理水量及成本</label><br>
			<table class="table table-striped table-bordered table-condensed">
			  <tr>
			    <td colspan="2">处理水量（m3）</td>
			    <td>月度总计成本（元）</td>
			  </tr>
			  <tr>
			    <td><form:input path="waterTreatment1" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="waterTreatment2" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="monthCost" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			  </tr>
			  <tr>
			    <td>应收水费（元）</td>
			    <td>实收水费（元）</td>
			    <td>历史欠款（元）</td>
			  </tr>
			  <tr>
			    <td><form:input path="receivableWaterRent" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="netWaterRent" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="arrears" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			  </tr>
			</table>
			<label class="lbl">进出水水质</label><br>
			<table class="table table-striped table-bordered table-condensed">
			  <tr>
			    <td>自测月均值</td>
			    <td>COD</td>
			    <td>NH3-H</td>
			    <td>TP</td>
			    <td>TN</td>
			    <td>SS</td>
			    <td>PH</td>
			  </tr>
			  <tr>
			    <td>进水浓度（mg/L）</td>
			    <td><form:input path="codSelfAverageIn" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="nhhSelfAverageIn" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="tpSelfAverageIn" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="tnSelfAverageIn" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="ssSelfAverageIn" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="phSelfAverageIn" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			  </tr>
			  <tr>
			    <td>出水浓度（mg/L）</td>
			    <td><form:input path="codSelfAverageOut" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="nhhSelfAverageOut" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="tpSelfAverageOut" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="tnSelfAverageOut" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="ssSelfAverageOut" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="phSelfAverageOut" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			  </tr>
			</table>
			<table class="table table-striped table-bordered table-condensed">
			  <tr>
			    <td>在线监测月均值</td>
			    <td>COD</td>
			    <td>NH3-H</td>
			    <td>TP</td>
			    <td>TN</td>
			    <td>SS</td>
			    <td>PH</td>
			  </tr>
			  <tr>
			    <td>进水浓度（mg/L）</td>
			    <td><form:input path="codOnLineAverageIn" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="nhhOnLineAverageIn" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="tpOnLineAverageIn" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="tnOnLineAverageIn" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="ssOnLineAverageIn" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="phOnLineAverageIn" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			  </tr>
			  <tr>
			    <td>出水浓度（mg/L）</td>
			    <td><form:input path="codOnLineAverageOut" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="nhhOnLineAverageOut" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="tpOnLineAverageOut" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="tnOnLineAverageOut" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="ssOnLineAverageOut" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="phOnLineAverageOut" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			  </tr>
			</table>
			<label class="lbl">超标情况</label><br>
			<table  id ="tblIn" class="table table-striped table-bordered table-condensed">
			  <tr>
			    <td colspan="8">进水</td>
			  </tr>
			  <tr>
			    <td>日期</td>
			    <td>COD</td>
			    <td>NH3-H</td>
			    <td>TP</td>
			    <td>TN</td>
			    <td>SS</td>
			    <td>PH</td>
			    <th><a href="javascript:" onclick="addClomnIn();" class="btn"><i class="icon-plus"></i></a></th>
			  </tr>
			  <tr>
			    <td><input type="text" id="inDate" name ="inDate" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><input type="text" id="inCOD" name ="inCOD" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><input type="text" id="inNh3h" name ="inNh3h" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><input type="text" id="inTp" name ="inTp" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><input type="text" id="inTn" name ="inTn" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><input type="text" id="inSs" name ="inSs" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><input type="text" id="inPh" name ="inPh" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><a href="javascript:" onclick="deleteClomn(this);" class="btn"><i class="icon-minus"></i></a> </td>
			  </tr>
			</table>
			<table  id ="tblOut" class="table table-striped table-bordered table-condensed">
			  <tr>
			    <td colspan="8">出水</td>
			  </tr>
			  <tr>
			    <td>日期</td>
			    <td>COD</td>
			    <td>NH3-H</td>
			    <td>TP</td>
			    <td>TN</td>
			    <td>SS</td>
			    <td>PH</td>
			    <th><a href="javascript:" onclick="addClomnOut();" class="btn"><i class="icon-plus"></i></a></th>
			  </tr>
			  <tr>
			    <td><input type="text" id="outDate" name ="outDate" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><input type="text" id="outCOD" name ="outCOD" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><input type="text" id="outNh3h" name ="outNh3h" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><input type="text" id="outTp" name ="outTp" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><input type="text" id="outTn" name ="outTn" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><input type="text" id="outSs" name ="outSs" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><input type="text" id="outPh" name ="outPh" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><a href="javascript:" onclick="deleteClomn(this);" class="btn"><i class="icon-minus"></i></a> </td>
			  </tr>
			</table>
 
		</div>
		
		<div class="control-group">
			<label class="lbl">二,运行能耗及污泥产量</label><br>
			<table class="table table-striped table-bordered table-condensed">
			  <tr>
			    <td>加药名称</td>
			    <td>PAC</td>
			    <td>PAM-</td>
			    <td>PAM+</td>
			    <td>液氯</td>
			    <td>漂水</td>
			  </tr>
			  <tr>
			    <td>投药量</td>
			    <td><form:input path="pacQty" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="pamSubQty" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="pamPlusQty" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="yeluQty" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			    <td><form:input path="waterQty" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			  </tr>
			  <tr>
			    <td colspan="2">产泥量：<form:input path="genMudQty" htmlEscape="false" maxlength="20" class="input-mini "/>吨 </td>
			    <td colspan="2">电耗量：<form:input path="powerQty" htmlEscape="false" maxlength="20" class="input-mini "/> Kw.h</td>
			    <td colspan="2">自来水耗量：<form:input path="waterConsumQty" htmlEscape="false" maxlength="20" class="input-mini "/>吨</td>
			  </tr>
			</table>
		</div>
		<div class="control-group">
			<label class="lbl">三，设备运行情况及维修保养记录</label><br>
			<div class="controls">
				<form:textarea id="content" htmlEscape="true" path="runAndFixDesc" class="input-small"/>
				<sys:ckeditor replace="content" height="100px" />
			</div>
		</div>
		<div class="control-group">
			<label class="lbl">四，运行存在问题及解决措施</label><br>
			<label class="lbl">运行存在问题</label><br>
			<div class="controls">
				<form:textarea id="problemDesc" htmlEscape="true" path="problemDesc"  class="input-small"/>
				<sys:ckeditor replace="problemDesc" height="100px" />
			</div>
			<label class="lbl">解决措施</label><br>
			<div class="controls">
				<form:textarea id="actionDesc" htmlEscape="true" path="actionDesc"  class="input-small"/>
				<sys:ckeditor replace="actionDesc" height="100px" />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="report:month:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>