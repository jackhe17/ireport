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
			top.$.jBox.confirm("确认要导出报表数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#searchForm").attr("action","${ctx}/range/report/export");
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
		$("#searchForm").attr("action","${ctx}/range/report/generate");
		$("#searchForm").submit();
    	return false;
    }
	</script>
</head>
<body>
	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="rangeReport" action="${ctx}/range/report/generate" method="post" class="breadcrumb form-search ">
		<ul class="ul-form">
			<li><label>厂区：</label><sys:treeselect id="office" name="officeId" value=" ${rangeReport.officeId}" labelName="company.name" labelValue="${fns:getOfficeName(rangeReport.officeId)}" 
				title="厂区" url="/officetreedata?type=1" cssClass="input-block-level required" allowClear="true"/></li>
			<li><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开始日期：</label>
							<input id="startDate" name="startDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
								value="<fmt:parseDate value="${rangeReport.startDate}" pattern="yyyy-MM-dd" var="myDate"/> <fmt:formatDate value="${myDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/></li>
							
			<li><label>结束日期：</label><input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
							value="<fmt:parseDate value="${rangeReport.endDate}" pattern="yyyy-MM-dd" var="myDate"/><fmt:formatDate value="${myDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<c:choose> 
		<c:when test="${page.list.size()>0}">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
		    			<th colspan="21" scope="col"> ${fns:getOfficeName(rangeReport.officeId)}${rangeReport.startDate}到${rangeReport.endDate}运行统计表</th>
					</tr>
				 	<tr>
					    <td rowspan="3">日期</td>
					    <td colspan="2" rowspan="3">日处理水量(m3)</td>
					    <td colspan="10">实验室水质(mg/L)</td>
					    <td rowspan="3">用电量(kw.h)</td>
					    <td rowspan="3">用水量（吨）</td>
					    <td colspan="5">药剂消耗量</td>
					    <td rowspan="3">污泥量  (吨)</td>
				  	</tr>
				  	<tr>
					    <td colspan="4">进水</td>
					    <td colspan="2">沉淀池出水</td>
					    <td colspan="4">出水</td>
					    <td rowspan="2">PAC(吨)</td>
					    <td rowspan="2">铁盐/除磷剂（吨）</td>
					    <td rowspan="2">PAM-（kg）</td>
					    <td rowspan="2">PAM+（kg）</td>
					    <td rowspan="2">消毒药剂（M3/kg）</td>
				  	</tr>
					<tr>
					    <td>COD</td>
					    <td>氨氮</td>
					    <td>TN</td>
					    <td>TP</td>
					    <td>沉淀池SS</td>
					    <td>沉淀池TP</td>
					    <td>COD</td>
					    <td>氨氮</td>
					    <td>TN</td>
					    <td>TP</td>
					</tr>
		  		</thead>
				<tbody>
				<c:forEach items="${page.list}" var="dayReport">
					<tr>
						<td>${dayReport.reportDate}</td>
					    <td>${dayReport.dwt1}</td>
					    <td>${dayReport.dwt2}</td>
					    <td>${dayReport.cod1}</td>
					    <td>${dayReport.an1}</td>
					    <td>${dayReport.tn1}</td>
					    <td>${dayReport.tp1}</td>
					    <td>${dayReport.stss1}</td>
					    <td>${dayReport.sttp1}</td>
					    <td>${dayReport.cod2}</td>
					    <td>${dayReport.an2}</td>
					    <td>${dayReport.tn2}</td>
					    <td>${dayReport.tp2}</td>
					    <td>${dayReport.electricity}</td>
					    <td>${dayReport.water}</td>
					    <td>${dayReport.pac}</td>
					    <td>${dayReport.ipra}</td>
					    <td>${dayReport.pam1}</td>
					    <td>${dayReport.pam2}</td>
					    <td>${dayReport.disin}</td>
					    <td>${dayReport.sq1}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<%-- <div class="pagination">${page}</div> --%>
	</c:when>
		<c:otherwise>没有数据
		</c:otherwise> 
	</c:choose> 
</body>
</html>