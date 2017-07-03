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
	</script>
</head>
<body>
<ul class="nav nav-tabs">
		<li ><a href="${ctx}/report/day/list">日报表列表</a></li>
		<shiro:hasPermission name="report:day:edit"><li class="active"><a href="${ctx}/report/day/form" >日报表添加</a></li></shiro:hasPermission>
	</ul>
	<br/>
	<form:form id="inputForm" modelAttribute="dayReport" action="${ctx}/report/day/save" method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<form:hidden path="id"/>
		<div class="control-group">
			<label class="lbl">${report.officeName}生产运行日报表</label><br>
		</div>
		<div class="control-group">
		<!-- required -->
		<table  class="table table-striped table-bordered table-condensed">
		  <tr>
		    <td>日期</td>
		    <td><input id="reportDate" name="reportDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
							value="<fmt:parseDate value="${report.reportDate}" pattern="yyyy-MM-dd" var="myDate"/> <fmt:formatDate value="${myDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/></td>
		    <td>天气</td>
		    <td><form:input path="weather" htmlEscape="false" maxlength="50" class="input-mini "/></td>
		    <td>温度</td>
		    <td><form:input path="temperature" htmlEscape="false" maxlength="50" class="input-mini "/></td>
		    <td>湿度</td>
		    <td><form:input path="humidity" htmlEscape="false" maxlength="50" class="input-mini "/></td>
		  </tr>
		  <tr>
		    <td>值班人员</td>
		    <td colspan="2">早班：<form:input path="earlyWorker" htmlEscape="false" maxlength="50" class="input-mini2 "/></td>
		    <td colspan="2">中班：<form:input path="middleWorker" htmlEscape="false" maxlength="50" class="input-mini2 "/></td>
		    <td colspan="3">晚班：<form:input path="lastWorker" htmlEscape="false" maxlength="50" class="input-mini2 "/></td>
		  </tr>
		  <tr>
		    <td rowspan="2">日处理水量（m3）</td>
		     <td><form:input path="dwt1" htmlEscape="false" maxlength="50" class="input-mini "/></td>
		    <td rowspan="2">污泥量（吨）</td>
		     <td rowspan="2"><form:input path="sq1" htmlEscape="false" maxlength="50" class="input-mini "/></td>
		    <td>电表读数(kw.h)</td>
		    <td><form:input path="meter" htmlEscape="false" maxlength="50" class="input-mini "/></td>
		    <td>用电量(kw.h)</td>
		     <td><form:input path="electricity" htmlEscape="false" maxlength="50" class="input-mini "/></td>
		  </tr>
		  <tr>
		    <td><form:input path="dwt2" htmlEscape="false" maxlength="50" class="input-mini "/></td>
		    <%-- <td><form:input path="sq2" htmlEscape="false" maxlength="50" class="input-mini "/></td> --%>
		    <td>自来水表读数(吨)</td>
		     <td><form:input path="waterMeter" htmlEscape="false" maxlength="50" class="input-mini "/></td>
		    <td>用水量（吨）</td>
		     <td><form:input path="water" htmlEscape="false" maxlength="50" class="input-mini "/></td>
		  </tr>
		  <tr>
		    <td>PAC（吨）</td>
		     <td><form:input path="pac" htmlEscape="false" maxlength="50" class="input-mini "/></td>
		    <td>铁盐/除磷剂（吨）</td>
		     <td><form:input path="ipra" htmlEscape="false" maxlength="50" class="input-mini "/></td>
		    <td>PAM-（kg）</td>
		     <td><form:input path="pam1" htmlEscape="false" maxlength="50" class="input-mini "/></td>
		    <td>PAM+（kg）</td>
		     <td><form:input path="pam2" htmlEscape="false" maxlength="50" class="input-mini "/></td>
		  </tr>
		  <tr>
		    <td >消毒药剂1（M3/kg）</p>
		    <td ><form:input path="disin" htmlEscape="false" maxlength="50" class="input-mini "/></td>
		    <td >消毒药剂2（M3/kg）</p>
		    <td ><form:input path="disin2" htmlEscape="false" maxlength="50" class="input-mini "/></td>
		    <td >其他药剂（M3/kg）</p>
		    <td ><form:input path="disin3" htmlEscape="false" maxlength="50" class="input-mini "/></td>
		  </tr>
		  <tr>
		    <td>停进水时间</td>
		     <td><form:input path="stopwatertime" htmlEscape="false" maxlength="50" class="input-mini "/></td>
		    <td>停产原因</td>
		    <td colspan="3"><form:textarea path="stopreason" htmlEscape="false" rows="1" maxlength="500" class="input-xlarge" /></td>
		  </tr>
		  <tr>
		    <td>恢复进水时间</td>
		     <td><form:input path="recoverytime" htmlEscape="false" maxlength="50" class="input-mini "/></td>
		    <td>情况说明</td>
		    <td colspan="3"><form:textarea path="reportDesc" htmlEscape="false" rows="1" maxlength="200" class="input-xlarge"/></td>
		  </tr>
		</table>
		</div>
		<div class="control-group">
			<table class="table table-striped table-bordered table-condensed">
			  <tr>
			    <td colspan="9"><div align="center">出水水质情况</div></td>
			  </tr>
			  <tr>
			    <td rowspan="2">水质指标</td>
			    <td colspan="2">实验室水质(mg/L)</td>
			    <td colspan="6">在线监测系统</td>
			  </tr>
			  <tr>
			    <td>进水</td>
			    <td>出水</td>
			    <td>00:00</td>
			    <td>04:00</td>
			    <td>08:00</td>
			    <td>12:00</td>
			    <td>16:00</td>
			    <td>20:00</td>
			  </tr>
			  <tr>
			    <td>COD</td>
			    <td><form:input path="cod1" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="cod2" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="cod3" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="cod4" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="cod5" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="cod6" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="cod7" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="cod8" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			  </tr>
			  <tr>
			    <td>氨氮</td>
			    <td><form:input path="an1" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="an2" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="an3" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="an4" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="an5" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="an6" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="an7" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="an8" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			  </tr>
			  <tr>
			    <td>TN</td>
			    <td><form:input path="tn1" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="tn2" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="tn3" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="tn4" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="tn5" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="tn6" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="tn7" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="tn8" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			  </tr>
			  <tr>
			    <td>TP</td>
			    <td><form:input path="tp1" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="tp2" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="tp3" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="tp4" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="tp5" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="tp6" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="tp7" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="tp8" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			  </tr>
			  <tr>
			    <td>沉淀池SS</td>
			    <td><form:input path="stss1" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="stss2" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="stss3" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="stss4" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="stss5" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="stss6" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="stss7" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="stss8" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			  </tr>
			  <tr>
			    <td>沉淀池TP</td>
			    <td><form:input path="sttp1" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="sttp2" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="sttp3" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="sttp4" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="sttp5" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="sttp6" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="sttp7" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="sttp8" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			  </tr>
			  <tr>
			    <td>颜色</td>
			    <td><form:input path="color1" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="color2" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="color3" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="color4" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="color5" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="color6" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="color7" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="color8" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			  </tr>
			  <tr>
			    <td>气味</td>
			    <td><form:input path="smell1" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="smell2" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="smell3" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="smell4" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="smell5" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="smell6" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="smell7" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="smell8" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			  </tr>
			</table>
		</div>
		<div class="control-group">
			<table class="table table-striped table-bordered table-condensed">
			  <tr>
			    <td colspan="8">设备运行情况</td>
			  </tr>
			  <tr>
			    <td rowspan="2">主要设备</td>
			    <td rowspan="2">运行时长</td>
			    <td colspan="6">值班人员巡查记录（正常运行、有异常现象、故障）</td>
			  </tr>
			  <tr>
			    <td>00:00</td>
			    <td>04:00</td>
			    <td>08:00</td>
			    <td>12:00</td>
			    <td>16:00</td>
			    <td>20:00</td>
			  </tr>
			  <tr>
			    <td>提升泵1</td>
			    <td><form:input path="pumpFirst1" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pumpFirst2" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pumpFirst3" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pumpFirst4" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pumpFirst5" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pumpFirst6" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pumpFirst7" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			  </tr>
			  <tr>
			    <td>提升泵2</td>
			    <td><form:input path="pumpSecond1" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pumpSecond2" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pumpSecond3" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pumpSecond4" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pumpSecond5" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pumpSecond6" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pumpSecond7" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			  </tr>
			  <tr>
			    <td>提升泵3</td>
			    <td><form:input path="pumpThird1" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pumpThird2" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pumpThird3" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pumpThird4" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pumpThird5" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pumpThird6" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pumpThird7" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			  </tr>
			  <tr>
			    <td>沉淀池吸/刮泥机</td>
			    <td><form:input path="stsos1" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="stsos2" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="stsos3" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="stsos4" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="stsos5" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="stsos6" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="stsos7" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			  </tr>
			  <tr>
			    <td>紫外消毒灯</td>
			    <td><form:input path="lamp1" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="lamp2" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="lamp3" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="lamp4" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="lamp5" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="lamp6" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="lamp7" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			  </tr>
			  <tr>
			    <td>压泥机</td>
			    <td><form:input path="pmm1" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pmm2" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pmm3" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pmm4" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pmm5" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pmm6" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="pmm7" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			  </tr>
			</table>
		</div>
		<div class="control-group">
			<table class="table table-striped table-bordered table-condensed">
			  <tr>
			    <td rowspan="2">生化池</td>
			    <td>SV30(%)</td>
			    <td>污泥浓度(g/L)</td>
			    <td>溶解氧（mg/L）</td>
			    <td>混合液回流比（%）</td>
			    <td>污泥回流比（%）</td>
			  </tr>
			  <tr>
			    <td><form:input path="sv30" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="sludge" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="oxygen" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="mixture" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="reflux" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			  </tr>
			</table>
		</div>
		
		<div class="control-group">
			<table class="table table-striped table-bordered table-condensed">
			  <tr>
			    <td rowspan="3">快渗池情况</td>
			    <td><p align="center">养护情况</td>
			    <td colspan="4"><form:textarea path="conditionDesc" htmlEscape="false" rows="1" maxlength="500" class="input-xxlarge"/></td>
			  </tr>
			  <tr>
			    <td rowspan="2">落干时间(min)</td>
			    <td>≤60</td>
			    <td>60-150</td>
			    <td>120-180</td>
			    <td>180以上</td>
			  </tr>
			  <tr>
			    <td><form:input path="lt60" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="g6l12" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="g12l18" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			    <td><form:input path="gt180" htmlEscape="false" maxlength="50" class="input-mini "/></td>
			  </tr>
			</table>
		</div>
		<div class="control-group">
			<table class="table table-striped table-bordered table-condensed">
			  <tr>
			    <td rowspan="2">设备运行工况</td>
			    <td >机电设备运行维护</td>
			    <td ><form:textarea path="equipment" htmlEscape="false" rows="1" style="width: 90%; height: 50px;"/></td>
			  </tr>
			  <tr>
			    <td>中控仪表</td>
			    <td><form:textarea path="instrument" htmlEscape="false" rows="1" style="width: 90%; height: 50px;"/></td>
			  </tr>
			</table>
		</div> 
		
		<%-- <div class="control-group">
			<label class="lbl">设备运行工况</label><br>
			<label class="lbl">机电设备运行维护</label><br>
			<div class="controls">
				<form:textarea id="equipment" htmlEscape="true" path="equipment"  style="width: 90%; height: 100px;"/>
			</div>
			<label class="lbl">中控仪表</label><br>
			<div class="controls">
				<form:textarea id="instrument" htmlEscape="true" path="instrument"  style="width: 90%; height: 100px;"/>
			</div>
		</div> --%>
		
		<div class="control-group">
			<table class="table table-striped table-bordered table-condensed">
			  <tr>
			    <td>工艺运行情况:</td>
			    <td><form:textarea path="handicraft" htmlEscape="false" rows="1" style="width: 90%; height: 50px;"/></td>
			  </tr>
			  <tr>
			    <td>存在问题及需要上级沟通解决问题:</td>
			    <td><form:textarea path="problem" htmlEscape="false" rows="1"  style="width: 90%; height: 50px;"/></td>
			  </tr>
			</table>
		</div>
		<div class="control-group">
			<table class="table table-striped table-bordered table-condensed">
			  <tr>
			    <td>报表初审意见:</td>
			    <td><form:textarea path="firstOpinion" htmlEscape="false" rows="1" style="width: 90%; height: 50px;"/></td>
			  </tr>
			  <tr>
			    <td>厂区回复意见:</td>
			    <td><form:textarea path="replyOpinion" htmlEscape="false" rows="1"  style="width: 90%; height: 50px;"/></td>
			  </tr>
			</table>
		</div>
		<%-- <div class="control-group">
			<table class="table table-striped table-bordered table-condensed">
			  <tr>
			    <td>制表人:</td>
			    <td><form:input path="lt60" htmlEscape="false" maxlength="50" class="input-mini " /></td>
			    <td>审核人:</td>
			    <td><form:input path="lt60" htmlEscape="false" maxlength="20" class="input-mini "/></td>
			  </tr>
			</table>
		</div>
		<div class="control-group">
		注：PAC含固态与液态两种，铁盐/除磷剂含聚铁和除磷剂，消毒剂含漂水（m3）、液氯（kg）、二氧化氯（kg）、固态漂白粉（kg）等。<br>
		养护情况和落干时间填写相应池号。工艺运行情况填写各单元运行是否正常。存在问题及解决方案填写日常管理情况及对外联系(需当天及时反馈)。
		<div class="control-group"> --%>
		<div class="form-actions">
			<shiro:hasPermission name="report:day:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>