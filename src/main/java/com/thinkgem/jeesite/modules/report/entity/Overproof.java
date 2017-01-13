package com.thinkgem.jeesite.modules.report.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 月报中的超标情况
 * @author Jack
 *
 */
public class Overproof extends DataEntity<Overproof> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -143705417226384084L;
	String occurDate;
	String cod;
	String nhh;
	String tp;
	String tn;
	String ss;
	String ph;
	String type;//进水 OR 出水
	int monthReportId;
	public String getOccurDate() {
		return occurDate;
	}
	public void setOccurDate(String occurDate) {
		this.occurDate = occurDate;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getNhh() {
		return nhh;
	}
	public void setNhh(String nhh) {
		this.nhh = nhh;
	}
	public String getTp() {
		return tp;
	}
	public void setTp(String tp) {
		this.tp = tp;
	}
	public String getTn() {
		return tn;
	}
	public void setTn(String tn) {
		this.tn = tn;
	}
	public String getSs() {
		return ss;
	}
	public void setSs(String ss) {
		this.ss = ss;
	}
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getMonthReportId() {
		return monthReportId;
	}
	public void setMonthReportId(int monthReportId) {
		this.monthReportId = monthReportId;
	}
	
	
}
