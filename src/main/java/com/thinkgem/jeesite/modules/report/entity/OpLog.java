package com.thinkgem.jeesite.modules.report.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class OpLog extends DataEntity<OpLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4503872105149032924L;
	private String poolId;
	private String officeId;
	private String logDate;// 日期
	private String weather;// 天气
	private String waterStartTime;
	private String waterEndTime;
	private String waterUsage;
	private String dryHour;
	private String dryMin;
	private String logRemarks;
	private String logRecorder;
	
	public String getPoolId() {
		return poolId;
	}
	public void setPoolId(String poolId) {
		this.poolId = poolId;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getLogDate() {
		return logDate;
	}
	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getWaterStartTime() {
		return waterStartTime;
	}
	public void setWaterStartTime(String waterStartTime) {
		this.waterStartTime = waterStartTime;
	}
	public String getWaterEndTime() {
		return waterEndTime;
	}
	public void setWaterEndTime(String waterEndTime) {
		this.waterEndTime = waterEndTime;
	}
	public String getWaterUsage() {
		return waterUsage;
	}
	public void setWaterUsage(String waterUsage) {
		this.waterUsage = waterUsage;
	}
	public String getDryHour() {
		return dryHour;
	}
	public void setDryHour(String dryHour) {
		this.dryHour = dryHour;
	}
	public String getDryMin() {
		return dryMin;
	}
	public void setDryMin(String dryMin) {
		this.dryMin = dryMin;
	}
	public String getLogRemarks() {
		return logRemarks;
	}
	public void setLogRemarks(String logRemarks) {
		this.logRemarks = logRemarks;
	}
	public String getLogRecorder() {
		return logRecorder;
	}
	public void setLogRecorder(String logRecorder) {
		this.logRecorder = logRecorder;
	}

}
