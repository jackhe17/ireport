package com.thinkgem.jeesite.modules.report.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.report.entity.DayReport;
import com.thinkgem.jeesite.modules.report.entity.MonthReport;
import com.thinkgem.jeesite.modules.report.entity.Overproof;
import com.thinkgem.jeesite.modules.report.entity.RangeReport;
import com.thinkgem.jeesite.modules.report.service.MonthReportService;
import com.thinkgem.jeesite.modules.report.service.OverproofService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/report/month")
public class MonthReportController extends BaseController {

	@Autowired
	private MonthReportService reportService;
	@Autowired
	private OverproofService overproofService;
	
	@RequiresPermissions("report:month:view")
	@RequestMapping(value = {"index"})
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		MonthReport report = new MonthReport();
		Page<MonthReport> page = reportService.find(new Page<MonthReport>(request, response), report);
        model.addAttribute("page", page);
		return "modules/report/monthReportList";
	}
	
	@ModelAttribute("monthReport")
	public MonthReport get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			MonthReport r = reportService.get(id);
			return r;
		}else{
			return new MonthReport();
		}
	}


	@RequiresPermissions("report:month:view")
	@RequestMapping(value = {"list", ""})
	public String list(MonthReport report, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MonthReport> page = reportService.find(new Page<MonthReport>(request, response), report);
        model.addAttribute("page", page);
		return "modules/report/monthReportList";
	}


	@RequiresPermissions("report:month:view")
	@RequestMapping(value = "form")
	public String form(MonthReport report, Model model) {
		model.addAttribute("report", report);
		model.addAttribute("user", UserUtils.getUser());
		return "modules/report/monthReportForm";
	}
	@RequiresPermissions("report:month:view")
	@RequestMapping(value = "noteditform")
	public String noteditform(MonthReport report, Model model) {
		model.addAttribute("report", report);
		model.addAttribute("user", UserUtils.getUser());
		if (report!=null) {
			Overproof inEntity = new Overproof();
			inEntity.setDelFlag(Overproof.DEL_FLAG_NORMAL);
			inEntity.setMonthReportId(Integer.parseInt(report.getId()));
			inEntity.setType("1");
			model.addAttribute("inOverproofList", overproofService.findList(inEntity));
			
			Overproof outEntity = new Overproof();
			outEntity.setDelFlag(Overproof.DEL_FLAG_NORMAL);
			outEntity.setMonthReportId(Integer.parseInt(report.getId()));
			outEntity.setType("2");
			model.addAttribute("outOverproofList", overproofService.findList(outEntity));
		}
		return "modules/report/monthReportFormNotEdit";
	}
	@RequiresPermissions("report:month:edit")
	@RequestMapping(value = "save")
	public String save(MonthReport report, Model model, RedirectAttributes redirectAttributes,
			@RequestParam("inDate")String[] inDates,  
			@RequestParam("inCOD")String[] inCODs,  
			@RequestParam("inNh3h")String[] inNh3hs, 
			@RequestParam("inTp")String[] inTps,  
			@RequestParam("inTn")String[] inTns,
			@RequestParam("inSs")String[] inSss,  
			@RequestParam("inPh")String[] inPhs,
			@RequestParam("outDate")String[] outDates,  
			@RequestParam("outCOD")String[] outCODs,  
			@RequestParam("outNh3h")String[] outNh3hs, 
			@RequestParam("outTp")String[] outTps,  
			@RequestParam("outTn")String[] outTns,
			@RequestParam("outSs")String[] outSss,  
			@RequestParam("outPh")String[] outPhs) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/report/month/index";
		}
		if (!beanValidator(model, report)){
//			return form(report, model);
		}
		int id = reportService.saveAndReturn(report);
		int inSize = inDates.length;
		for(int i =0;i<inSize;i++){
			Overproof inOverproof = new Overproof();
			inOverproof.setType("1");
			inOverproof.setMonthReportId(id);
			inOverproof.setOccurDate(inDates[i]);
			inOverproof.setCod(inCODs[i]);
			inOverproof.setNhh(inNh3hs[i]);
			inOverproof.setTp(inTps[i]);
			inOverproof.setTn(inTns[i]);
			inOverproof.setSs(inSss[i]);
			inOverproof.setPh(inPhs[i]);
			overproofService.save(inOverproof);
		}
		int outSize = outDates.length;
		for(int i =0;i<outSize;i++){
			Overproof outOverproof = new Overproof();
			outOverproof.setType("2");
			outOverproof.setMonthReportId(id);
			outOverproof.setOccurDate(outDates[i]);
			outOverproof.setCod(outCODs[i]);
			outOverproof.setNhh(outNh3hs[i]);
			outOverproof.setTp(outTps[i]);
			outOverproof.setTn(outTns[i]);
			outOverproof.setSs(outSss[i]);
			outOverproof.setPh(outPhs[i]);
			overproofService.save(outOverproof);
		}
		addMessage(redirectAttributes, "保存生产运行日报表'" + report.getId() + "'成功");
		return "redirect:" + adminPath + "/report/month/index";
	}
	
	@RequiresPermissions("report:month:edit")
	@RequestMapping(value = "delete")
	public String delete(MonthReport report, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/report/month/index";
		}
		reportService.delete(report);
		int monthReportId = Integer.valueOf(report.getId());
		overproofService.deleteByMonthReportId(monthReportId);
		addMessage(redirectAttributes, "删除生产运行日报表成功");
		return "redirect:" + adminPath + "/report/month/index";
	}
	
//	@RequiresPermissions("range:report:view")
//	@RequestMapping(value = {"generate", ""})
//	public String generate(RangeReport report, HttpServletRequest request, HttpServletResponse response, Model model) {
//		DayReport dayReport = new DayReport();
//		Page<DayReport> page = reportService.findRangeReport(new Page<DayReport>(request, response), dayReport,report);
//        model.addAttribute("page", page);
//		return "modules/report/rangeReportIndex";
//	}
	//
	@RequiresPermissions("report:month:collect:view")
	@RequestMapping(value = {"collect"})
	public String collect(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/report/monthReportCollect";
	}
	
	@RequiresPermissions("report:month:collect:view")
	@RequestMapping(value = {"collectGenerate", ""})
	public String collectGenerate(MonthReport report, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MonthReport> page = reportService.find(new Page<MonthReport>(request, response), report);
        model.addAttribute("page", page);
        model.addAttribute("report", report);
		return "modules/report/monthReportCollect";
	}
}
