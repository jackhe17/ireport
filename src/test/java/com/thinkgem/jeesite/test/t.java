package com.thinkgem.jeesite.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.modules.report.entity.DayReport;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

public class t {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 String tplPath = "H://dayreport_template.xls";  
	        String destPath = "H://dayreport_template_output.xls";  
	  
	        /*List<Map> reports = new ArrayList<Map>();  
	        
	        Map map1=new HashMap();
	        map1.put("F1", 1);
	        map1.put("F2", "aaa1");
	        map1.put("F3", 3);
	        reports.add(map1);  
	        
	        Map map2=new HashMap();
	        map2.put("F1", 2);
	        map2.put("F2", "aaa2");
	        map2.put("F3", 33);
	        reports.add(map2);  */
	  
	  
	          
	        Map<String, Object> beanParams = new HashMap<String, Object>();  
	        beanParams.put("reports", getDayReportList()); 
	        beanParams.put("company", "AAAAAAAAA"); 
	        beanParams.put("startDate", "bbbbbbbbbbb"); 
	        beanParams.put("endDate", "ccccccc"); 
	        XLSTransformer former = new XLSTransformer();  
	        try {
				
					former.transformXLS(tplPath, beanParams, destPath);
//					Workbook workbook =  former.transformXLS(new FileInputStream(tplPath), beanParams);
//		            OutputStream ouputStream = response.getOutputStream();
//		            workbook.write(ouputStream);  
//		            ouputStream.flush();  
//		            ouputStream.close(); 
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (ParsePropertyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
       
	}
	public static List<DayReport> getDayReportList(){
		List<DayReport> dayReports = Lists.newArrayList();
		DayReport d1 = new DayReport();
		d1.setReportDate("2017-01-01");
		d1.setDwt1("dwt1-1");
		d1.setDwt2("dwt2-1");
		d1.setCod1("cod1-1");
		DayReport d2 = new DayReport();
		d2.setReportDate("2017-01-02");
		d2.setDwt1("dwt1-2");
		d2.setDwt2("dwt2-2");
		d2.setCod1("cod1-2");
		DayReport d3 = new DayReport();
		d3.setReportDate("2017-01-03");
		d3.setDwt1("dwt1-3");
		d3.setDwt2("dwt2-3");
		d3.setCod1("cod1-3");
		DayReport d4 = new DayReport();
		d4.setReportDate("2017-01-04");
		d4.setDwt1("dwt1-4");
		d4.setDwt2("dwt2-4");
		d4.setCod1("cod1-4");
		dayReports.add(d1);
		dayReports.add(d2);
		dayReports.add(d3);
		dayReports.add(d3);
		return dayReports;
	}

}
