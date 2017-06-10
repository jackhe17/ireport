<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>月报表管理</title>
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
		$("#searchForm").attr("action","${ctx}/report/week/list");
		$("#searchForm").submit();
    	return false;
    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/report/week/list">周报表列表</a></li>
		<shiro:hasPermission name="report:week:edit"><li><a href="${ctx}/report/week/form">周报表添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="weekReport" action="${ctx}/report/week/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		<%-- <li><label class="input-label">厂区：</label><sys:treeselect id="office" name="officeId" value="${user.company.id}" labelName="company.name" labelValue="${user.company.name}" 
				title="厂区" url="/officetreedata?type=1" cssClass="input-block-level required" allowClear="true"/></li>
		 --%>
		<li><label>厂区：</label><sys:treeselect id="office" name="officeId" value="${office.id}" labelName="office.name" labelValue="${office.name}" 
				title="厂区" url="/officetreedata?type=1" cssClass="input-block-level required" allowClear="true"/></li>
		<li><label class="input-label">时间：</label><input id="reportDate" name="reportDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
						value="<fmt:parseDate value="${report.reportDate}" pattern="yyyy-MM-dd" var="myDate"/> <fmt:formatDate value="${myDate}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/></li>
		<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			<!-- <input id="btnExport" class="btn btn-primary" type="button" value="导出"/> -->
		<li class="clearfix"></li>
		</ul>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<c:choose> 
	<c:when test="${page.list.size()>0}">
		<thead><tr><th>序号</th><th >厂区</th><th >汇报日期</th><shiro:hasPermission name="report:week:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="report">
			<tr>
				<td><a href="${ctx}/report/week/noteditform?id=${report.id}">${report.id}</a></td>
				<td>${fns:getOfficeName(report.officeId)}</a></td>
				<td>${report.reportDate}</td>
				<td>
    				<a href="${ctx}/report/week/noteditform?id=${report.id}">查看</a>
    				<a href="${ctx}/report/week/export?id=${report.id}">导出</a>
    				<shiro:hasPermission name="report:week:edit">
    				<a href="${ctx}/report/week/form?id=${report.id}">修改</a>
					<a href="${ctx}/report/week/delete?id=${report.id}" onclick="return confirmx('确认要删除此报表吗？', this.href)">删除</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
		 </c:when>
				<c:otherwise>
				<tr><td colspan="5">没有数据<td></tr>
		   		</c:otherwise>  
		</c:choose>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>