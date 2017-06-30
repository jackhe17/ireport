package com.thinkgem.jeesite.modules.report.web;

import java.util.List;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcelJxls;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.report.entity.WeekReport;
import com.thinkgem.jeesite.modules.report.entity.DayReport;
import com.thinkgem.jeesite.modules.report.entity.MonthReport;
import com.thinkgem.jeesite.modules.report.entity.Overproof;
import com.thinkgem.jeesite.modules.report.service.WeekReportService;
import com.thinkgem.jeesite.modules.report.service.OverproofService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.OfficeUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/report/week")
public class WeekReportController extends BaseController {

	@Autowired
	private WeekReportService reportService;
	@Autowired
	private OverproofService overproofService;

	@RequiresPermissions("report:week:view")
	@RequestMapping(value = { "index" })
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		WeekReport report = new WeekReport();
		if (!UserUtils.getUser().isAdmin() && !UserUtils.isSuperUser()) {
			report.setOfficeId(UserUtils.getUser().getCompany().getId());
		}
		Page<WeekReport> page = reportService.find(new Page<WeekReport>(request, response), report);
		model.addAttribute("page", page);
		return "modules/report/weekReportList";
	}

	@ModelAttribute("weekReport")
	public WeekReport get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			WeekReport r = reportService.get(id);
			return r;
		} else {
			return new WeekReport();
		}
	}

	@RequiresPermissions("report:week:view")
	@RequestMapping(value = { "list", "" })
	public String list(WeekReport report, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (!UserUtils.getUser().isAdmin() && !UserUtils.isSuperUser()) {
			report.setOfficeId(UserUtils.getUser().getCompany().getId());
		}
		String officeID = report.getOfficeId();
		// if (report == null || officeID ==null || "".equals(officeID) ) {
		// report = new WeekReport();
		// }else {
		officeID = report.getOfficeId();
		// }
		Page<WeekReport> page = reportService.find(new Page<WeekReport>(request, response), report);
		model.addAttribute("page", page);
		model.addAttribute("report", report);
		model.addAttribute("officeId", officeID);
		Office office = OfficeUtils.getOffice(officeID);
		if (null == office) {
			// office = UserUtils.getUser().getCompany();
			office = new Office();
			office.setId("");
			office.setName("");
		}

		model.addAttribute("office", office);
		return "modules/report/weekReportList";
	}

	@RequiresPermissions("report:week:view")
	@RequestMapping(value = "form")
	public String form(WeekReport report, Model model) {
		String officeID = report.getOfficeId();
		Office office = OfficeUtils.getOffice(officeID);
		if (office != null) {
			report.setOfficeName(office.getName());
		}
		model.addAttribute("report", report);
		model.addAttribute("user", UserUtils.getUser());
		return "modules/report/weekReportForm";
	}

	@RequiresPermissions("report:week:view")
	@RequestMapping(value = "noteditform")
	public String noteditform(WeekReport report, Model model) {
		String officeID = report.getOfficeId();
		Office office = OfficeUtils.getOffice(officeID);
		report.setOfficeName(office.getName());
		model.addAttribute("report", report);
		model.addAttribute("user", UserUtils.getUser());
		if (report != null) {
			Overproof inEntity = new Overproof();
			inEntity.setDelFlag(Overproof.DEL_FLAG_NORMAL);
			inEntity.setMonthReportId(Integer.parseInt(report.getId()));
			inEntity.setType("3");
			model.addAttribute("inOverproofList", overproofService.findList(inEntity));

			Overproof outEntity = new Overproof();
			outEntity.setDelFlag(Overproof.DEL_FLAG_NORMAL);
			outEntity.setMonthReportId(Integer.parseInt(report.getId()));
			outEntity.setType("4");
			model.addAttribute("outOverproofList", overproofService.findList(outEntity));
		}
		return "modules/report/weekReportFormNotEdit";
	}

	@RequiresPermissions("report:week:edit")
	@RequestMapping(value = "save")
	public String save(WeekReport report, Model model, RedirectAttributes redirectAttributes,
			@RequestParam("inDate") String[] inDates, @RequestParam("inCOD") String[] inCODs,
			@RequestParam("inNh3h") String[] inNh3hs, @RequestParam("inTp") String[] inTps,
			@RequestParam("inTn") String[] inTns, @RequestParam("inSs") String[] inSss,
			@RequestParam("inPh") String[] inPhs, @RequestParam("outDate") String[] outDates,
			@RequestParam("outCOD") String[] outCODs, @RequestParam("outNh3h") String[] outNh3hs,
			@RequestParam("outTp") String[] outTps, @RequestParam("outTn") String[] outTns,
			@RequestParam("outSs") String[] outSss, @RequestParam("outPh") String[] outPhs) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/report/week/index";
		}
		if (!beanValidator(model, report)) {
			// return form(report, model);
		}
		int id = reportService.saveAndReturn(report);
		int inSize = inDates.length;
		for (int i = 0; i < inSize; i++) {
			Overproof inOverproof = new Overproof();
			inOverproof.setType("3");
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
		for (int i = 0; i < outSize; i++) {
			Overproof outOverproof = new Overproof();
			outOverproof.setType("4");
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
		return "redirect:" + adminPath + "/report/week/index";
	}

	@RequiresPermissions("report:week:edit")
	@RequestMapping(value = "save2")
	public String save2(WeekReport report, Model model, RedirectAttributes redirectAttributes,
			@RequestParam("inId") String[] inIds, @RequestParam("inDate") String[] inDates,
			@RequestParam("inCOD") String[] inCODs, @RequestParam("inNh3h") String[] inNh3hs,
			@RequestParam("inTp") String[] inTps, @RequestParam("inTn") String[] inTns,
			@RequestParam("inSs") String[] inSss, @RequestParam("inPh") String[] inPhs,
			@RequestParam("outId") String[] outIds, @RequestParam("outDate") String[] outDates,
			@RequestParam("outCOD") String[] outCODs, @RequestParam("outNh3h") String[] outNh3hs,
			@RequestParam("outTp") String[] outTps, @RequestParam("outTn") String[] outTns,
			@RequestParam("outSs") String[] outSss, @RequestParam("outPh") String[] outPhs) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/report/week/index";
		}
		if (!beanValidator(model, report)) {
			// return form(report, model);
		}
		int id = Integer.valueOf(report.getId());
		reportService.saveAndReturn(report);
		int inSize = inDates.length;
		for (int i = 0; i < inSize; i++) {
			Overproof inOverproof = new Overproof();
			inOverproof.setId(inIds[i]);
			inOverproof.setType("3");
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
		for (int i = 0; i < outSize; i++) {
			Overproof outOverproof = new Overproof();
			outOverproof.setId(outIds[i]);
			outOverproof.setType("4");
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
		return "redirect:" + adminPath + "/report/week/index";
	}

	@RequiresPermissions("report:week:edit")
	@RequestMapping(value = "delete")
	public String delete(WeekReport report, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/report/Week/index";
		}
		reportService.delete(report);
		int weekReportId = Integer.valueOf(report.getId());
		overproofService.deleteByMonthReportId(weekReportId);
		addMessage(redirectAttributes, "删除生产运行日报表成功");
		return "redirect:" + adminPath + "/report/Week/index";
	}

	// @RequiresPermissions("range:report:view")
	// @RequestMapping(value = {"generate", ""})
	// public String generate(RangeReport report, HttpServletRequest request,
	// HttpServletResponse response, Model model) {
	// DayReport dayReport = new DayReport();
	// Page<DayReport> page = reportService.findRangeReport(new
	// Page<DayReport>(request, response), dayReport,report);
	// model.addAttribute("page", page);
	// return "modules/report/rangeReportIndex";
	// }
	//
	@RequiresPermissions("report:Week:collect:view")
	@RequestMapping(value = { "collect" })
	public String collect(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/report/WeekReportCollect";
	}

	@RequiresPermissions("report:Week:collect:view")
	@RequestMapping(value = { "collectGenerate", "" })
	public String collectGenerate(WeekReport report, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<WeekReport> page = reportService.findWeekCollect(new Page<WeekReport>(request, response), report);
		model.addAttribute("page", page);
		model.addAttribute("report", report);
		return "modules/report/WeekReportCollect";
	}

	@RequiresPermissions("report:week:view")
	@RequestMapping(value = "export")
	public String export(String id, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		WeekReport report = reportService.get(id);
		try {
			String company = OfficeUtils.getOfficeName(report.getOfficeId());
			String fileName = company + "_" + "周报表" + report.getReportDate() + ".xls";
			List inOverproofList = null;
			List outOverproofList = null;
			if (report != null) {
				Overproof inEntity = new Overproof();
				inEntity.setDelFlag(Overproof.DEL_FLAG_NORMAL);
				inEntity.setMonthReportId(Integer.parseInt(report.getId()));
				inEntity.setType("3");
				inOverproofList = overproofService.findList(inEntity);

				Overproof outEntity = new Overproof();
				outEntity.setDelFlag(Overproof.DEL_FLAG_NORMAL);
				outEntity.setMonthReportId(Integer.parseInt(report.getId()));
				outEntity.setType("4");
				outOverproofList = overproofService.findList(outEntity);
			}
			new ExportExcelJxls(4).setWeekReport(report).setCompany(company).setOvInList(inOverproofList)
					.setOvOutList(outOverproofList).write(response, fileName);
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出周报表失败！失败信息：" + e.getMessage());
		}
		return "modules/report/weekReportList";
	}
}
