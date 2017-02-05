/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.common.utils.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.modules.report.entity.DayReport;
import com.thinkgem.jeesite.modules.report.entity.MonthReport;
import com.thinkgem.jeesite.modules.report.entity.WeekReport;
import com.thinkgem.jeesite.modules.sys.utils.OfficeUtils;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

/**
 * 导出Excel文件（导出“XLSX”格式，支持大数据量导出 @see org.apache.poi.ss.SpreadsheetVersion）
 * 
 * @author ThinkGem
 * @version 2013-04-21
 * @param <E>
 */
public class ExportExcelJxls {

	private static Logger log = LoggerFactory.getLogger(ExportExcelJxls.class);
	List list ;
	List ovInList;
	List ovOutList;
	DayReport dayReport;
	WeekReport weekReport;
	MonthReport monthReport;
	Workbook wb ;
	int type;
	String startDate;
	String endDate;
	String company;
	
	List oplogList;
	String weather;
	String logDate;
	String reportMonth;
	public ExportExcelJxls() {
		super();
	}

	public ExportExcelJxls setOplogList(List oplogList) {
		this.oplogList = oplogList;
		return this;
	}

	public ExportExcelJxls setWeather(String weather) {
		this.weather = weather;
		return this;
	}

	public ExportExcelJxls setLogDate(String logDate) {
		this.logDate = logDate;
		return this;
	}
	public ExportExcelJxls setReportMonth(String reportMonth) {
		this.reportMonth = reportMonth;
		return this;
	}
	public ExportExcelJxls(int type){
		this.type = type;
	}
	
	/**
	 * 输出数据流
	 * @param os 输出数据流
	 */
	public ExportExcelJxls write(OutputStream os) throws IOException{
		switch (type) {
		case 1:
			getDayReportCollectWorkBook().write(os);
			break;
		case 2:
			getRangeCollectionReportWorkBook().write(os);
			break;
		case 3:
			getDayReportWorkBook().write(os);
			break;
		case 4:
			getWeekReportWorkBook().write(os);
			break;
		case 5:
			getOpLogWorkBook().write(os);
			break;
		case 6:
			getMonthReportWorkBook().write(os);
			break;
		case 7:
			getMonthCollectReportWorkBook().write(os);
			break;
		default:
			break;
		}
		return this;
	}
	
	public ExportExcelJxls setDataList(List list){
		this.list = list;
		return this;
	}
	
	public ExportExcelJxls setOvInList(List ovInList) {
		this.ovInList = ovInList;
		return this;
	}

	public ExportExcelJxls setOvOutList(List ovOutList) {
		this.ovOutList = ovOutList;
		return this;
	}

	public ExportExcelJxls setTime(String startDate,String endDate){
		this.startDate= startDate;
		this.endDate = endDate;
		return this;
	}
	public ExportExcelJxls setCompany(String company){
		this.company = company;
		return this;
	}
	public ExportExcelJxls setDayReport(DayReport dayReport){
		this.dayReport = dayReport;
		return this;
	}
	public ExportExcelJxls setWeekReport(WeekReport weekReport){
		this.weekReport = weekReport;
		return this;
	}
	public ExportExcelJxls setMonthReport(MonthReport monthReport){
		this.monthReport = monthReport;
		return this;
	}
	public Workbook getRangeCollectionReportWorkBook(){
		InputStream is = ExportExcelJxls.class.getResourceAsStream("monthReport_template.xls");
		Map<String, Object> beanParams = new HashMap<String, Object>();
		beanParams.put("reports", this.list);
		beanParams.put("company", this.company);
		beanParams.put("startDate", this.startDate);
		beanParams.put("endDate", this.endDate);
		XLSTransformer former = new XLSTransformer();
		try {
			Workbook wb = former.transformXLS(is, beanParams);
			return wb;
		} catch (Exception e) {
			log.error("", e);
		} 
		return null;
	}
	public Workbook getMonthCollectReportWorkBook(){
		InputStream is = ExportExcelJxls.class.getResourceAsStream("monthCollect_template.xls");
		Map<String, Object> beanParams = new HashMap<String, Object>();
		beanParams.put("reports", this.list);
		
		beanParams.put("reportMonth", this.reportMonth);
		XLSTransformer former = new XLSTransformer();
		try {
			Workbook wb = former.transformXLS(is, beanParams);
			return wb;
		} catch (Exception e) {
			log.error("", e);
		} 
		return null;
	}
	public Workbook getDayReportWorkBook(){
		InputStream is = ExportExcelJxls.class.getResourceAsStream("company_dayReport_template.xls");
		Map<String, Object> beanParams = new HashMap<String, Object>();
		beanParams.put("report", this.dayReport);
		beanParams.put("company", this.company);
		XLSTransformer former = new XLSTransformer();
		try {
			Workbook wb = former.transformXLS(is, beanParams);
			return wb;
		} catch (Exception e) {
			log.error("", e);
		} 
		return null;
	}
	public Workbook getOpLogWorkBook(){
		InputStream is = ExportExcelJxls.class.getResourceAsStream("oplog_template.xls");
		Map<String, Object> beanParams = new HashMap<String, Object>();
		beanParams.put("oplogList", this.oplogList);
		beanParams.put("company", this.company);
		beanParams.put("weather", this.weather);
		beanParams.put("logDate", this.logDate);
		XLSTransformer former = new XLSTransformer();
		try {
			Workbook wb = former.transformXLS(is, beanParams);
			return wb;
		} catch (Exception e) {
			log.error("", e);
		} 
		return null;
	}
	public Workbook getWeekReportWorkBook(){
		InputStream is = ExportExcelJxls.class.getResourceAsStream("company_weekReport_template.xls");
		Map<String, Object> beanParams = new HashMap<String, Object>();
		beanParams.put("report", this.weekReport);
		beanParams.put("company", this.company);
		beanParams.put("ovInList", this.ovInList);
		beanParams.put("ovOutList", this.ovOutList);
		String date = this.weekReport.getReportDate();
		if (null != date && date.split("-").length==3) {
			String[] dateArray = date.split("-");
			beanParams.put("year", dateArray[0]);
			beanParams.put("month", dateArray[1]);
			beanParams.put("day", dateArray[2]);
		}
		XLSTransformer former = new XLSTransformer();
		try {
			Workbook wb = former.transformXLS(is, beanParams);
			return wb;
		} catch (Exception e) {
			log.error("", e);
		} 
		return null;
	}
	public Workbook getMonthReportWorkBook(){
		InputStream is = ExportExcelJxls.class.getResourceAsStream("company_monthReport_template.xls");
		Map<String, Object> beanParams = new HashMap<String, Object>();
		beanParams.put("report", this.monthReport);
		beanParams.put("company", OfficeUtils.getOfficeName(this.monthReport.getOfficeId()));
		beanParams.put("ovInList", this.ovInList);
		beanParams.put("ovOutList", this.ovOutList);
		String date = this.monthReport.getReportDate();
		if (null != date && date.split("-").length==3) {
			String[] dateArray = date.split("-");
			beanParams.put("year", dateArray[0]);
			beanParams.put("month", dateArray[1]);
			beanParams.put("day", dateArray[2]);
		}
		String reportDate = this.monthReport.getReportMonth();
		if (null != reportDate && reportDate.split("-").length==2) {
			String[] dateArray = date.split("-");
			beanParams.put("reportYear", dateArray[0]);
			beanParams.put("reportMonth", dateArray[1]);
		}
		XLSTransformer former = new XLSTransformer();
		try {
			Workbook wb = former.transformXLS(is, beanParams);
			return wb;
		} catch (Exception e) {
			log.error("", e);
		} 
		return null;
	}
	public Workbook getDayReportCollectWorkBook(){
		InputStream is = ExportExcelJxls.class.getResourceAsStream("dayCollectReport_template.xls");
		if (null != this.list && this.list.size()>0) {
			int id = 1;
			StringBuffer equipmentSB = new StringBuffer();
			StringBuffer instrumentSB = new StringBuffer();
			StringBuffer handicraftSB = new StringBuffer();
			StringBuffer problemSB = new StringBuffer();
			String date = null;
			for (Object object : list) {
				DayReport dayReport = (DayReport) object;
				dayReport.setId(String.valueOf(id));
				String officeName = OfficeUtils.getOfficeName(dayReport.getOfficeId());
				dayReport.setOfficeId(officeName);
				equipmentSB.append(officeName).append(":").append(dayReport.getEquipment()).append(System.lineSeparator());
				instrumentSB.append(officeName).append(":").append(dayReport.getInstrument()).append(System.lineSeparator());
				handicraftSB.append(officeName).append(":").append(dayReport.getHandicraft()).append(System.lineSeparator());
				problemSB.append(officeName).append(":").append(dayReport.getProblem()).append(System.lineSeparator());
				if (date == null ) {
					date = dayReport.getReportDate();
				}
				id++;
			}
			Map<String, Object> beanParams = new HashMap<String, Object>();
			beanParams.put("reports", this.list);
			beanParams.put("equipment", equipmentSB.toString());
			beanParams.put("instrument", instrumentSB.toString());
			beanParams.put("handicraft", handicraftSB.toString());
			beanParams.put("problem", problemSB.toString());
			if (null != date && date.split("-").length==3) {
				String[] dateArray = date.split("-");
				beanParams.put("year", dateArray[0]);
				beanParams.put("month", dateArray[1]);
				beanParams.put("day", dateArray[2]);
			}
			XLSTransformer former = new XLSTransformer();
			try {
				return former.transformXLS(is, beanParams);
			} catch (Exception e) {
				log.error("", e);
			} 
		}
		return null;
	}
	public ExportExcelJxls write(HttpServletResponse response, String fileName) throws IOException{
		response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="+new String(fileName.getBytes(),"iso-8859-1"));
		write(response.getOutputStream());
		return this;
	}
	
	public ExportExcelJxls writeFile(String name,Workbook wb) throws FileNotFoundException, IOException{
		FileOutputStream os = new FileOutputStream(name);
		this.write(os);
		return this;
	}
	/*public static void main(String[] args) {
	ExportExcelJxls excelJxls = new ExportExcelJxls();
	try {
		excelJxls.writeFile("target/aaa.xls", excelJxls.getMonthReportWorkBook());
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}*/
}