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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.report.entity.DayReport;
import com.thinkgem.jeesite.modules.report.service.DayReportService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/report/day")
public class DayReportController extends BaseController {

	@Autowired
	private DayReportService reportService;
	
	@RequiresPermissions("report:day:view")
	@RequestMapping(value = {"index"})
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		DayReport report = new DayReport();
		report.setReportDate(DateUtils.getDistanceDay(new Date(), -1));
		if(!UserUtils.getUser().isAdmin()){
			report.setOfficeId(UserUtils.getUser().getCompany().getId());
		}
		Page<DayReport> page = reportService.find(new Page<DayReport>(request, response), report);
        model.addAttribute("page", page);
        model.addAttribute("user", UserUtils.getUser());
        model.addAttribute("report", report);
		return "modules/report/dayReportList";
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

	@RequiresPermissions("report:day:view")
	@RequestMapping(value = {"list", ""})
	public String list(DayReport report, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(report!=null && !UserUtils.getUser().isAdmin()){
			report.setOfficeId(UserUtils.getUser().getCompany().getId());
		}
		Page<DayReport> page = reportService.find(new Page<DayReport>(request, response), report);
        model.addAttribute("page", page);
        model.addAttribute("user", UserUtils.getUser());
        model.addAttribute("report", report);
		return "modules/report/dayReportList";
	}

	/**
	 * 导出用户数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("report:day:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(DayReport report, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "日报表"+report.getReportDate()+".xlsx";
            Page<DayReport> page = reportService.find(new Page<DayReport>(request, response), report);
    		new ExportExcel("日报表", DayReport.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出日报表失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/report/dayReportList";
    }
	
	@RequiresPermissions("report:day:view")
	@RequestMapping(value = "form")
	public String form(DayReport report, Model model) {
		model.addAttribute("report", report);
		model.addAttribute("user", UserUtils.getUser());
		return "modules/report/dayReportForm";
	}
	
	@RequiresPermissions("report:day:edit")
	@RequestMapping(value = "save")
	public String save(DayReport report, Model model, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/report/day/index";
		}
		if (!beanValidator(model, report)){
//			return form(report, model);
		}
		reportService.save(report);
		addMessage(redirectAttributes, "保存生产运行日报表'" + report.getReportDate() + "'成功");
		return "redirect:" + adminPath + "/report/day/index";
	}
	
	@RequiresPermissions("report:day:edit")
	@RequestMapping(value = "delete")
	public String delete(DayReport report, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/report/day/index";
		}
		reportService.delete(report);
		addMessage(redirectAttributes, "删除生产运行日报表成功");
		return "redirect:" + adminPath + "/report/day/index";
	}
	@RequiresPermissions("report:day:collect")
	@RequestMapping(value = "collectindex")
	public String collectindex(HttpServletRequest request, HttpServletResponse response, Model model) {
		DayReport report = new DayReport();
		report.setReportDate(DateUtils.getDistanceDay(new Date(), -1));
		if(!UserUtils.getUser().isAdmin()){
			report.setOfficeId(UserUtils.getUser().getCompany().getId());
		}
		Page<DayReport> page = reportService.find(new Page<DayReport>(request, response), report);
        model.addAttribute("page", page);
        model.addAttribute("report", report);
        model.addAttribute("user", UserUtils.getUser());
		return "modules/report/dayReportCollect";
	}
	
	@RequiresPermissions("report:day:collect")
	@RequestMapping(value = "collect")
	public String collect(DayReport report, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(!UserUtils.getUser().isAdmin()){
			report.setOfficeId(UserUtils.getUser().getCompany().getId());
		}
		Page<DayReport> page = reportService.find(new Page<DayReport>(request, response), report);
        model.addAttribute("page", page);
        model.addAttribute("report", report);
        model.addAttribute("user", UserUtils.getUser());
		return "modules/report/dayReportCollect";
	}
	
	
}
