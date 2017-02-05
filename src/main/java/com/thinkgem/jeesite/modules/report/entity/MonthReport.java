package com.thinkgem.jeesite.modules.report.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class MonthReport extends DataEntity<MonthReport> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5349840503258398496L;
	String officeId;
//	String officeName;
	String officeType;
	String reportDate;//汇报时间
	String reportMonth;//汇报月度
	String waterTreatment1;//处理水量
	String waterTreatment2;//处理水量
	String monthCost;//月度总计成本
	String receivableWaterRent;//应收水费
	String netWaterRent;//实收水费
	String arrears;//历史欠款
	//进出水水质
	//自测月均值
	String codSelfAverageIn;
	String nhhSelfAverageIn;
	String tpSelfAverageIn;
	String tnSelfAverageIn;
	String ssSelfAverageIn;
	String phSelfAverageIn;
	
	String codSelfAverageOut;
	String nhhSelfAverageOut;
	String tpSelfAverageOut;
	String tnSelfAverageOut;
	String ssSelfAverageOut;
	String phSelfAverageOut;
	//在线监测月均值
	String codOnLineAverageIn;
	String nhhOnLineAverageIn;
	String tpOnLineAverageIn;
	String tnOnLineAverageIn;
	String ssOnLineAverageIn;
	String phOnLineAverageIn;
	
	String codOnLineAverageOut;
	String nhhOnLineAverageOut;
	String tpOnLineAverageOut;
	String tnOnLineAverageOut;
	String ssOnLineAverageOut;
	String phOnLineAverageOut;
	
	//超标情况
	
	//运行能耗及污泥产量
	String pac;
	String ipra;
	String pam1;
	String pam2;
	String disin;
	
	String electricity;//产泥量
	String water;//电耗量
	String sq1;//自来水消耗量
	
	String runAndFixDesc;//设备运行状况及维修保养情况
	String monthFinishWork;//
	
	String problemDesc;//运行存在问题
	
	String actionDesc;//解决措施
	String nextMonthPlan;//解决措施
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getReportMonth() {
		return reportMonth;
	}
	public void setReportMonth(String reportMonth) {
		this.reportMonth = reportMonth;
	}
	public String getWaterTreatment1() {
		return waterTreatment1;
	}
	public void setWaterTreatment1(String waterTreatment1) {
		this.waterTreatment1 = waterTreatment1;
	}
	public String getWaterTreatment2() {
		return waterTreatment2;
	}
	public void setWaterTreatment2(String waterTreatment2) {
		this.waterTreatment2 = waterTreatment2;
	}
	public String getMonthCost() {
		return monthCost;
	}
	public void setMonthCost(String monthCost) {
		this.monthCost = monthCost;
	}
	public String getReceivableWaterRent() {
		return receivableWaterRent;
	}
	public void setReceivableWaterRent(String receivableWaterRent) {
		this.receivableWaterRent = receivableWaterRent;
	}
	public String getNetWaterRent() {
		return netWaterRent;
	}
	public void setNetWaterRent(String netWaterRent) {
		this.netWaterRent = netWaterRent;
	}
	public String getArrears() {
		return arrears;
	}
	public void setArrears(String arrears) {
		this.arrears = arrears;
	}
	public String getCodSelfAverageIn() {
		return codSelfAverageIn;
	}
	public void setCodSelfAverageIn(String codSelfAverageIn) {
		this.codSelfAverageIn = codSelfAverageIn;
	}
	public String getNhhSelfAverageIn() {
		return nhhSelfAverageIn;
	}
	public void setNhhSelfAverageIn(String nhhSelfAverageIn) {
		this.nhhSelfAverageIn = nhhSelfAverageIn;
	}
	public String getTpSelfAverageIn() {
		return tpSelfAverageIn;
	}
	public void setTpSelfAverageIn(String tpSelfAverageIn) {
		this.tpSelfAverageIn = tpSelfAverageIn;
	}
	public String getTnSelfAverageIn() {
		return tnSelfAverageIn;
	}
	public void setTnSelfAverageIn(String tnSelfAverageIn) {
		this.tnSelfAverageIn = tnSelfAverageIn;
	}
	public String getSsSelfAverageIn() {
		return ssSelfAverageIn;
	}
	public void setSsSelfAverageIn(String ssSelfAverageIn) {
		this.ssSelfAverageIn = ssSelfAverageIn;
	}
	public String getPhSelfAverageIn() {
		return phSelfAverageIn;
	}
	public void setPhSelfAverageIn(String phSelfAverageIn) {
		this.phSelfAverageIn = phSelfAverageIn;
	}
	public String getCodSelfAverageOut() {
		return codSelfAverageOut;
	}
	public void setCodSelfAverageOut(String codSelfAverageOut) {
		this.codSelfAverageOut = codSelfAverageOut;
	}
	public String getNhhSelfAverageOut() {
		return nhhSelfAverageOut;
	}
	public void setNhhSelfAverageOut(String nhhSelfAverageOut) {
		this.nhhSelfAverageOut = nhhSelfAverageOut;
	}
	public String getTpSelfAverageOut() {
		return tpSelfAverageOut;
	}
	public void setTpSelfAverageOut(String tpSelfAverageOut) {
		this.tpSelfAverageOut = tpSelfAverageOut;
	}
	public String getTnSelfAverageOut() {
		return tnSelfAverageOut;
	}
	public void setTnSelfAverageOut(String tnSelfAverageOut) {
		this.tnSelfAverageOut = tnSelfAverageOut;
	}
	public String getSsSelfAverageOut() {
		return ssSelfAverageOut;
	}
	public void setSsSelfAverageOut(String ssSelfAverageOut) {
		this.ssSelfAverageOut = ssSelfAverageOut;
	}
	public String getPhSelfAverageOut() {
		return phSelfAverageOut;
	}
	public void setPhSelfAverageOut(String phSelfAverageOut) {
		this.phSelfAverageOut = phSelfAverageOut;
	}
	public String getCodOnLineAverageIn() {
		return codOnLineAverageIn;
	}
	public void setCodOnLineAverageIn(String codOnLineAverageIn) {
		this.codOnLineAverageIn = codOnLineAverageIn;
	}
	public String getNhhOnLineAverageIn() {
		return nhhOnLineAverageIn;
	}
	public void setNhhOnLineAverageIn(String nhhOnLineAverageIn) {
		this.nhhOnLineAverageIn = nhhOnLineAverageIn;
	}
	public String getTpOnLineAverageIn() {
		return tpOnLineAverageIn;
	}
	public void setTpOnLineAverageIn(String tpOnLineAverageIn) {
		this.tpOnLineAverageIn = tpOnLineAverageIn;
	}
	public String getTnOnLineAverageIn() {
		return tnOnLineAverageIn;
	}
	public void setTnOnLineAverageIn(String tnOnLineAverageIn) {
		this.tnOnLineAverageIn = tnOnLineAverageIn;
	}
	public String getSsOnLineAverageIn() {
		return ssOnLineAverageIn;
	}
	public void setSsOnLineAverageIn(String ssOnLineAverageIn) {
		this.ssOnLineAverageIn = ssOnLineAverageIn;
	}
	public String getPhOnLineAverageIn() {
		return phOnLineAverageIn;
	}
	public void setPhOnLineAverageIn(String phOnLineAverageIn) {
		this.phOnLineAverageIn = phOnLineAverageIn;
	}
	public String getCodOnLineAverageOut() {
		return codOnLineAverageOut;
	}
	public void setCodOnLineAverageOut(String codOnLineAverageOut) {
		this.codOnLineAverageOut = codOnLineAverageOut;
	}
	public String getNhhOnLineAverageOut() {
		return nhhOnLineAverageOut;
	}
	public void setNhhOnLineAverageOut(String nhhOnLineAverageOut) {
		this.nhhOnLineAverageOut = nhhOnLineAverageOut;
	}
	public String getTpOnLineAverageOut() {
		return tpOnLineAverageOut;
	}
	public void setTpOnLineAverageOut(String tpOnLineAverageOut) {
		this.tpOnLineAverageOut = tpOnLineAverageOut;
	}
	public String getTnOnLineAverageOut() {
		return tnOnLineAverageOut;
	}
	public void setTnOnLineAverageOut(String tnOnLineAverageOut) {
		this.tnOnLineAverageOut = tnOnLineAverageOut;
	}
	public String getSsOnLineAverageOut() {
		return ssOnLineAverageOut;
	}
	public void setSsOnLineAverageOut(String ssOnLineAverageOut) {
		this.ssOnLineAverageOut = ssOnLineAverageOut;
	}
	public String getPhOnLineAverageOut() {
		return phOnLineAverageOut;
	}
	public void setPhOnLineAverageOut(String phOnLineAverageOut) {
		this.phOnLineAverageOut = phOnLineAverageOut;
	}
	public String getPac() {
		return pac;
	}
	public void setPac(String pac) {
		this.pac = pac;
	}
	public String getIpra() {
		return ipra;
	}
	public void setIpra(String ipra) {
		this.ipra = ipra;
	}
	public String getPam1() {
		return pam1;
	}
	public void setPam1(String pam1) {
		this.pam1 = pam1;
	}
	public String getPam2() {
		return pam2;
	}
	public void setPam2(String pam2) {
		this.pam2 = pam2;
	}
	public String getDisin() {
		return disin;
	}
	public void setDisin(String disin) {
		this.disin = disin;
	}
	public String getElectricity() {
		return electricity;
	}
	public void setElectricity(String electricity) {
		this.electricity = electricity;
	}
	public String getWater() {
		return water;
	}
	public void setWater(String water) {
		this.water = water;
	}
	public String getSq1() {
		return sq1;
	}
	public void setSq1(String sq1) {
		this.sq1 = sq1;
	}
	public String getRunAndFixDesc() {
		return runAndFixDesc;
	}
	public void setRunAndFixDesc(String runAndFixDesc) {
		this.runAndFixDesc = runAndFixDesc;
	}
	public String getMonthFinishWork() {
		return monthFinishWork;
	}
	public void setMonthFinishWork(String monthFinishWork) {
		this.monthFinishWork = monthFinishWork;
	}
	public String getProblemDesc() {
		return problemDesc;
	}
	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}
	public String getActionDesc() {
		return actionDesc;
	}
	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}
	public String getNextMonthPlan() {
		return nextMonthPlan;
	}
	public void setNextMonthPlan(String nextMonthPlan) {
		this.nextMonthPlan = nextMonthPlan;
	}
	public String getOfficeType() {
		return officeType;
	}
	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}
	
}
