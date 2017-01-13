<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>日运行情况汇总</title>
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
		$("#searchForm").attr("action","${ctx}/report/day/collect");
		$("#searchForm").submit();
    	return false;
    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<%-- <li class="active"><a href="${ctx}/report/day/list">汇总报表列表</a></li> --%>
		<%-- <li><a href="${ctx}/report/day/form">日报表添加</a></li> --%>
	</ul>
	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="dayReport" action="${ctx}/report/day/collect" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>日期：</label><input id="reportDate" name="reportDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				value="<fmt:parseDate value="${report.reportDate}" pattern="yyyy-MM-dd" var="myDate"/> <fmt:formatDate value="${myDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<div class="control-group"><label>项目厂区日运行情况汇总表${report.reportDate}</label></div>
	<c:choose> 
		<c:when test="${page.list.size()>0}">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
				  	<tr>
					    <td rowspan="3">序   号</td>
					    <td rowspan="3">厂区</td>
					    <td rowspan="3">天气</td>
					    <td colspan="2" rowspan="3">日处理水量<br />
					    (m3)</td>
					    <td rowspan="3">用电量(kw.h)</td>
					    <td rowspan="3">用水量（吨）</td>
					    <td colspan="10">实验室水质(mg/L)</td>
					    <td colspan="5">生化池</td>
					    <td colspan="5">药剂消耗量</td>
					    <td rowspan="3">污泥量（吨）</td>
					    <td colspan="5">快渗池情况</td>
				  	</tr>
				  	<tr>
					    <td colspan="4">进水</td>
					    <td colspan="2">沉淀池出水</td>
					    <td colspan="4">出水</td>
					    <td rowspan="2">SV30（%）</td>
					    <td rowspan="2">污泥浓度（g/L）</td>
					    <td rowspan="2">溶解氧（mg/L）</td>
					    <td rowspan="2">混合液回流比（%）</td>
					    <td rowspan="2">污泥回流比（%）</td>
					    <td rowspan="2">PAC（吨）</td>
					    <td rowspan="2">铁盐/除磷剂（吨）</td>
					    <td rowspan="2">PAM-(kg)</td>
					    <td rowspan="2">PAM+ (kg)</td>
					    <td rowspan="2">消毒药剂(M3/kg)</td>
					    <td rowspan="2">养护情况</td>
					    <td colspan="4">落干时间(MIN)</td>
				  	</tr>
				  	<tr>
					    <td >COD</td>
					    <td >氨氮</td>
					    <td >TN</td>
					    <td >TP</td>
					    <td >沉淀池SS</td>
					    <td >沉淀池TP</td>
					    <td >COD</td>
					    <td >氨氮</td>
					    <td >TN</td>
					    <td >TP</td>
					    <td >≦60</td>
					    <td>60-120</td>
					    <td >120-180</td>
					    <td >180以上</td>
				  	</tr>
			  	</thead>
				<tbody>
				<c:forEach items="${page.list}" var="dayReport">
					<tr>
						<td>${dayReport.id}</td>
					    <td>${fns:getOfficeName(dayReport.officeId)}</td>
					    <td>${dayReport.weather}</td>
					    <td>${dayReport.dwt1}</td>
					    <td>${dayReport.dwt2}</td>
					    <td>${dayReport.electricity}</td>
					    <td>${dayReport.waterMeter}</td>
					    <td>${dayReport.cod1}</td>
					    <td>${dayReport.an1}</td>
					    <td>${dayReport.tn1}</td>
					    <td>${dayReport.tp1}</td>
					    <td>${dayReport.stss2}</td>
					    <td>${dayReport.sttp2}</td>
					    <td>${dayReport.cod2}</td>
					    <td>${dayReport.an2}</td>
					    <td>${dayReport.tn2}</td>
					    <td>${dayReport.tp2}</td>
					    <td>${dayReport.sv30}</td>
					    <td>${dayReport.sludge}</td>
					    <td>${dayReport.oxygen}</td>
					    <td>${dayReport.mixture}</td>
					    <td>${dayReport.reflux}</td>
					    <td>${dayReport.pac}</td>
					    <td>${dayReport.ipra}</td>
					    <td>${dayReport.pam1}</td>
					    <td>${dayReport.pam2}</td>
					    <td>${dayReport.disin}</td>
					    <td>${dayReport.sq1}</td>
					    <td>${dayReport.conditionDesc}</td>
					    <td>${dayReport.lt60}</td>
					    <td>${dayReport.g6l12}</td>
					    <td>${dayReport.g12l18}</td>
					    <td>${dayReport.gt180}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<tr>
					    <td width="210" rowspan="2">设备运行工况</td>
					    <td width="113">机电设备运行维护</td>
					    <td width="594"><c:forEach items="${page.list}" var="dayReport">${fns:getOfficeName(dayReport.officeId)}:${dayReport.equipment}<br></c:forEach><br></td>
					  </tr>
					  <tr>
					    <td>中控仪表</td>
					    <td><c:forEach items="${page.list}" var="dayReport">${fns:getOfficeName(dayReport.officeId)}:${dayReport.instrument}<br></c:forEach><br></td>
					  </tr>
					  <tr>
					    <td colspan="2">工艺运行情况</td>
					    <td><c:forEach items="${page.list}" var="dayReport">${fns:getOfficeName(dayReport.officeId)}:${dayReport.handicraft}<br></c:forEach><br></td>
					  </tr>
					  <tr>
					    <td colspan="2">存在问题及需要上级沟通解决问题</td>
					    <td><c:forEach items="${page.list}" var="dayReport">${fns:getOfficeName(dayReport.officeId)}:${dayReport.problem}<br></c:forEach><br></td>
					  </tr>
			</table>
		</c:when>
		<c:otherwise>没有数据
   		</c:otherwise>  
	</c:choose>
	<%-- <div class="pagination">${page}</div> --%>
</body>
</html>