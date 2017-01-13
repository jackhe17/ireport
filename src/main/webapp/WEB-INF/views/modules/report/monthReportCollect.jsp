<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>区域管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#searchForm").attr("action","${ctx}/sys/user/export");
					$("#searchForm").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
		$("#btnImport").click(function(){
			$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
				bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
		});
	});
	function page(n,s){
		if(n) $("#pageNo").val(n);
		if(s) $("#pageSize").val(s);
		$("#searchForm").attr("action","${ctx}/report/month/collectGenerate");
		$("#searchForm").submit();
    	return false;
    }
	</script>
</head>
<body>
	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="monthReport" action="${ctx}/report/month/collectGenerate" method="post" class="breadcrumb form-search ">
		<ul class="ul-form">
			<li><label class="input-label" >汇报月度</label></li>
			<li><input id="reportMonth" name="reportMonth" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
								value="<fmt:parseDate value="${report.reportMonth}" pattern="yyyy-MM" var="myDate"/> <fmt:formatDate value="${myDate}" pattern="yyyy-MM"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true});"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<div class="control-group">
		<label class="lbl">各项目公司生产月报汇总</label><br>
		<label class="lbl">汇报月度：${report.reportMonth}</label><br>
	</div>
	<c:choose> 
		<c:when test="${page.list.size()>0}">
		<div class="control-group">
			<label class="lbl">一,厂区基本运营情况</label><br>
			<label class="lbl">（1）处理水量及成本</label><br>
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th scope="col">类型</th>
					    <th scope="col">厂区</th>
					    <th colspan="2" scope="col">处理水量（m3）</th>
					    <th scope="col">月度总计成本（元）</th>
					    <th scope="col">应收水费（元）</th>
					    <th scope="col">实收水费（元）</th>
					    <th scope="col">历史欠款（元）</th>
				    </tr>
				</thead>
				<tbody>
				<c:forEach items="${page.list}" var="report">
					<tr>
						<td>${fns:getOfficeType(report.officeId)}</td>
					    <td>${fns:getOfficeName(report.officeId)}</td>
					    <td>${report.waterTreatment1}</td>
					    <td>${report.waterTreatment2}</td>
					    <td>${report.monthCost}</td>
					    <td>${report.receivableWaterRent}</td>
					    <td>${report.netWaterRent}</td>
					    <td>${report.arrears}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<label class="lbl">（2）实验室检验进出水水质</label><br>
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>自测月均值</th>
						<th>COD</th>
						<th>NH3-H</th>
						<th>TP</th>
						<th>TN</th>
						<th>SS</th>
						<th>PH</th>
				    </tr>
				</thead>
				<tbody>
				<c:forEach items="${page.list}" var="report">
					<tr>
					    <td>${fns:getOfficeName(report.officeId)}</td>
					    <td>${report.codSelfAverageIn}/${report.codSelfAverageOut}</td>
					    <td>${report.nhhSelfAverageIn}/${report.nhhSelfAverageOut}</td>
					    <td>${report.tpSelfAverageIn}/${report.tpSelfAverageOut}</td>
					    <td>${report.tnSelfAverageIn}/${report.tnSelfAverageOut}</td>
					    <td>${report.ssSelfAverageIn}/${report.ssSelfAverageOut}</td>
					    <td>${report.phSelfAverageIn}/${report.phSelfAverageOut}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<label class="lbl">说明：（1）水质记录模式为：进水/出水，红色字体为水质超标</label><br>
			<label class="lbl">（2）滨州</label><br>
			<label class="lbl">（3）神定河</label><br>
			<br>
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>在线检测月均值</th>
						<th>COD</th>
						<th>NH3-H</th>
						<th>TP</th>
						<th>TN</th>
						<th>SS</th>
						<th>PH</th>
				    </tr>
				</thead>
				<tbody>
				<c:forEach items="${page.list}" var="report">
					<tr>
					    <td>${fns:getOfficeName(report.officeId)}</td>
					    <td>${report.codOnLineAverageIn}/${report.codOnLineAverageOut}</td>
					    <td>${report.nhhOnLineAverageIn}/${report.nhhOnLineAverageOut}</td>
					    <td>${report.tpOnLineAverageIn}/${report.tpOnLineAverageOut}</td>
					    <td>${report.tnOnLineAverageIn}/${report.tnOnLineAverageOut}</td>
					    <td>${report.ssOnLineAverageIn}/${report.ssOnLineAverageOut}</td>
					    <td>${report.phOnLineAverageIn}/${report.phOnLineAverageOut}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<label class="lbl">说明：（1）水质记录模式为：进水/出水，红色字体为水质超标</label><br>
			<label class="lbl">（2）滨州</label><br>
			<label class="lbl">（3）神定河</label><br>		
			
			<label class="lbl">超标情况</label><br>
		</div>
		<div class="control-group">
			<label class="lbl">二,运行能耗及污泥产量</label><br>
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th scope="col">厂区</th>
					    <th scope="col">PAC（吨）</th>
					    <th scope="col">PAM-（吨）</th>
					    <th scope="col">PAM+（吨）</th>
					    <th scope="col">除磷剂（吨）</th>
					    <th scope="col">液氯（吨）</th>
					    <th scope="col">漂水（吨）</th>
					    <th scope="col">产泥量（吨）</th>
					    <th scope="col">电耗量（吨）</th>
					    <th scope="col">自来水耗量（吨）</th>
				    </tr>
				</thead>
				<tbody>
				<c:forEach items="${page.list}" var="report">
					<tr>
					    <td>${fns:getOfficeName(report.officeId)}</td>
					    <td>${report.pacQty}</td>
					    <td>${report.pamSubQty}</td>
					    <td>${report.pamPlusQty}</td>
					    <td>？？</td>
					    <td>${report.yeluQty}</td>
					    <td>${report.waterQty}</td>
					    <td>${report.genMudQty}</td>
					    <td>${report.powerQty}</td>
					    <td>${report.waterConsumQty}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="control-group">
			<label class="lbl">三，设备运行情况及维修保养记录</label><br>
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th scope="col">厂区</th>
    					<th scope="col">设备运行情况及维修保养记录</th>
				    </tr>
				</thead>
				<tbody>
				<c:forEach items="${page.list}" var="report">
					<tr>
					   <td>${fns:getOfficeName(report.officeId)}</td>
				    	<td>${report.runAndFixDesc}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="control-group">
			<label class="lbl">四，运行存在问题及解决措施</label><br>
			
			<c:forEach items="${page.list}" var="report">
			<div class="control-group">
					${fns:getOfficeName(report.officeId)}<br>
					<label class="lbl">运行存在问题</label><br>
					${report.problemDesc}
					<label class="lbl">解决措施</label><br>
					${report.actionDesc}
				</div>
			</c:forEach>
		</div>
	</c:when>
		<c:otherwise>没有数据
   		</c:otherwise>  
	</c:choose>
	<%-- <div class="pagination">${page}</div> --%>
</body>
</html>