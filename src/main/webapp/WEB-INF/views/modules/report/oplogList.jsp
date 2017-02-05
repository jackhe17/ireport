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
			top.$.jBox.confirm("确认要导出运行日志数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#searchForm").attr("action","${ctx}/report/oplog/export");
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
		$("#searchForm").attr("action","${ctx}/report/oplog/list");
		$("#searchForm").submit();
    	return false;
    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/report/oplog/list">运行日志查询</a></li>
		<li><a href="${ctx}/report/oplog/form">运行日志添加</a></li>
	</ul>
	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="oplog" action="${ctx}/report/oplog/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
		<li><label>厂区：</label><sys:treeselect id="office" name="officeId" value="${office.id}" labelName="office.name" labelValue="${office.name}" 
				title="厂区" url="/officetreedata?type=1" cssClass="input-block-level required" allowClear="true"/></li>
			<li><label>日期：</label><input id="logDate" name="logDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
							value="<fmt:parseDate value="${opLog.logDate}" pattern="yyyy-MM-dd" var="myDate"/> <fmt:formatDate value="${myDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<div class="control-group">
			<label >${fns:getOfficeName(officeId)}快渗池运行日志</label><br>
			
		</div>
		<div class="control-group">
			<table  id ="tbl1" class="table table-striped table-bordered table-condensed">
				<thead>
				<tr>
				    <th colspan="4">日 期:${logDate}</th>
				    <th colspan="4">天气:${weather}</th>
				  </tr>
				  <tr>
				    <th rowspan="2">布水池号</th>
				    <th colspan="2">布水时间</th>
				    <th rowspan="2">布水量（m³）</th>
				    <th rowspan="2">落干时刻</th>
				    <th rowspan="2">落干时间（min）</th>
				    <th rowspan="2">备注</th>
				    <th rowspan="2">记录人员</th>
				  </tr>
				  <tr>
				    <td>开始</td>
				    <td>结束</td>
				  </tr>
				  </thead>
				  <c:choose> 
				<c:when test="${page.list.size()>0}">
				  <c:forEach items="${page.list}" var="oplog">
					   <tr>
					    <td>${oplog.poolId}</td>
					    <td>${oplog.waterStartTime}</td>
					    <td>${oplog.waterEndTime}</td>
					    <td>${oplog.waterUsage}</td>
					    <td>${oplog.dryHour}</td>
					    <td>${oplog.dryMin}</td>
					    <td>${oplog.logRemarks}</td>
					    <td>${oplog.logRecorder}</td>
					  </tr>
				  </c:forEach>
				  </c:when>
				<c:otherwise>
				<tr><td colspan="8">没有数据<td></tr>
		   		</c:otherwise>  
			</c:choose>
			</table>
		</div>
	<%-- <div class="pagination">${page}</div> --%>
</body>
</html>