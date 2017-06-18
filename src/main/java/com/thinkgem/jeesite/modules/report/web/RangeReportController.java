package com.thinkgem.jeesite.modules.report.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcelJxls;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.report.entity.DayReport;
import com.thinkgem.jeesite.modules.report.entity.RangeReport;
import com.thinkgem.jeesite.modules.report.service.DayReportService;
import com.thinkgem.jeesite.modules.sys.utils.OfficeUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/range/report")
public class RangeReportController extends BaseController {

	@Autowired
	private DayReportService reportService;
	
	@RequiresPermissions("range:report:view")
	@RequestMapping(value = {"index"})
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		 DayReport report = new DayReport();
		 RangeReport rangeReport  = new RangeReport();
			rangeReport.setOfficeId(UserUtils.getUser().getCompany().getId());
			Date date = new Date();
			rangeReport.setStartDate(DateUtils.getDistanceDay(date, -30));
			rangeReport.setEndDate(DateUtils.getDistanceDay(date, -1));
			Page<DayReport> page = reportService.findRangeReport(new Page<DayReport>(request, response), report,rangeReport);
	        model.addAttribute("page", page);
	        model.addAttribute("user", UserUtils.getUser());
	        model.addAttribute("rangeReport", rangeReport);
		return "modules/report/rangeReportIndex";
	}
	
	@ModelAttribute("dayReport")
	public DayReport get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			DayReport r = reportService.get(id);
			return r;
		}else{
			return new DayReport();
		}
	}

	@RequiresPermissions("range:report:view")
	@RequestMapping(value = {"generate", ""})
	public String generate(RangeReport rangeReport, HttpServletRequest request, HttpServletResponse response, Model model) {
		DayReport report = new DayReport();
		if(rangeReport !=null && !UserUtils.getUser().isAdmin()&& !UserUtils.isSuperUser()){
			rangeReport.setOfficeId(UserUtils.getUser().getCompany().getId());
		}
		if(rangeReport==null){
			rangeReport = new RangeReport();
			rangeReport.setOfficeId(UserUtils.getUser().getCompany().getId());
			Date date = new Date();
			rangeReport.setStartDate(DateUtils.getDistanceDay(date, -30));
			rangeReport.setEndDate(DateUtils.getDistanceDay(date, -1));
		}
		Page<DayReport> page = reportService.findRangeReport(new Page<DayReport>(request, response), report,rangeReport);
        model.addAttribute("page", page);
        model.addAttribute("user", UserUtils.getUser());
        model.addAttribute("rangeReport", rangeReport);
		return "modules/report/rangeReportIndex";
	}
	
	@RequiresPermissions("range:report:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String collectExportFile(RangeReport rangeReport, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			DayReport report = new DayReport();
			if(rangeReport !=null && !UserUtils.getUser().isAdmin()&& !UserUtils.isSuperUser()){
				rangeReport.setOfficeId(UserUtils.getUser().getCompany().getId());
			}else{
			}
			String company = OfficeUtils.getOfficeName(rangeReport.getOfficeId());
            String fileName = company+"_"+rangeReport.getStartDate()+"-"+rangeReport.getEndDate()+".xls";
            Page<DayReport> page = reportService.findRangeReport(new Page<DayReport>(request, response), report,rangeReport);
    		new ExportExcelJxls(2).setDataList(page.getList())
					    		.setCompany(company)
					    		.setTime(rangeReport.getStartDate(), rangeReport.getEndDate())
					    		.write(response, fileName);
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出日报表失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/report/dayReportList";
    }
}
