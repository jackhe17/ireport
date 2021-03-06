package com.thinkgem.jeesite.modules.report.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
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
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcelJxls;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.report.entity.MonthReport;
import com.thinkgem.jeesite.modules.report.entity.Overproof;
import com.thinkgem.jeesite.modules.report.entity.WeekReport;
import com.thinkgem.jeesite.modules.report.service.MonthReportService;
import com.thinkgem.jeesite.modules.report.service.OverproofService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.OfficeUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
/**
 * alter table `report_month` Add column replyOpinion varchar(2000) default NULL AFTER `nextMonthPlan`; 
alter table `report_month` Add column firstOpinion varchar(2000) default NULL AFTER `nextMonthPlan`; 
 * @author Jack
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/report/month")
public class MonthReportController extends BaseController {

	@Autowired
	private MonthReportService reportService;
	@Autowired
	private OverproofService overproofService;

	@RequiresPermissions("report:month:view")
	@RequestMapping(value = { "index" })
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		MonthReport report = new MonthReport();
		if (!UserUtils.getUser().isAdmin() && !UserUtils.isSuperUser()) {
			report.setOfficeId(UserUtils.getUser().getCompany().getId());
		}
		Page<MonthReport> page = reportService.find(new Page<MonthReport>(request, response), report);
		model.addAttribute("page", page);
		return "modules/report/monthReportList";
	}

	@ModelAttribute("monthReport")
	public MonthReport get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			MonthReport r = reportService.get(id);
			return r;
		} else {
			return new MonthReport();
		}
	}

	@RequiresPermissions("report:month:view")
	@RequestMapping(value = { "list", "" })
	public String list(MonthReport report, HttpServletRequest request, HttpServletResponse response, Model model) {
		String officeID = report.getOfficeId();
		// if (report == null || officeID ==null || "".equals(officeID) ) {
		// report = new MonthReport();
		// }else {
		officeID = report.getOfficeId();
		// }
		Page<MonthReport> page = reportService.find(new Page<MonthReport>(request, response), report);
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
		return "modules/report/monthReportList";
	}

	@RequiresPermissions("report:month:view")
	@RequestMapping(value = "form")
	public String form(MonthReport report, Model model) {
		String officeID = report.getOfficeId();
		Office office = OfficeUtils.getOffice(officeID);
		if (office != null) {
			report.setOfficeName(office.getName());
		}
		model.addAttribute("report", report);
		model.addAttribute("user", UserUtils.getUser());
		model.addAttribute("monthOfYear", DateUtils.getMonthOfYear(report.getReportMonth()));
		return "modules/report/monthReportForm";
	}

	@RequiresPermissions("report:month:view")
	@RequestMapping(value = "export")
	public String export(String id, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			MonthReport report = reportService.get(id);
			List inOverproofList = null;
			List outOverproofList = null;
			if (report != null) {
				Overproof inEntity = new Overproof();
				inEntity.setDelFlag(Overproof.DEL_FLAG_NORMAL);
				inEntity.setMonthReportId(Integer.parseInt(report.getId()));
				inEntity.setType("1");
				inOverproofList = overproofService.findList(inEntity);
				Overproof outEntity = new Overproof();
				outEntity.setDelFlag(Overproof.DEL_FLAG_NORMAL);
				outEntity.setMonthReportId(Integer.parseInt(report.getId()));
				outEntity.setType("2");
				outOverproofList = overproofService.findList(outEntity);
			}
			String fileName = OfficeUtils.getOfficeName(report.getOfficeId()) + "_" + "月报表" + report.getReportMonth()
					+ ".xls";
			new ExportExcelJxls(6).setMonthReport(report).setOvInList(inOverproofList).setOvOutList(outOverproofList)
					.write(response, fileName);
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出月报表失败！失败信息：" + e.getMessage());
		}
		return "modules/report/monthReportList";
	}

	@RequiresPermissions("report:month:view")
	@RequestMapping(value = "noteditform")
	public String noteditform(MonthReport report, Model model) {
		String officeID = report.getOfficeId();
		Office office = OfficeUtils.getOffice(officeID);
		report.setOfficeName(office.getName());
		model.addAttribute("report", report);
		model.addAttribute("user", UserUtils.getUser());
		model.addAttribute("monthOfYear", DateUtils.getMonthOfYear(report.getReportMonth()));
		if (report != null) {
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
			@RequestParam("inDate") String[] inDates, @RequestParam("inCOD") String[] inCODs,
			@RequestParam("inNh3h") String[] inNh3hs, @RequestParam("inTp") String[] inTps,
			@RequestParam("inTn") String[] inTns, @RequestParam("inSs") String[] inSss,
			@RequestParam("inPh") String[] inPhs, @RequestParam("outDate") String[] outDates,
			@RequestParam("outCOD") String[] outCODs, @RequestParam("outNh3h") String[] outNh3hs,
			@RequestParam("outTp") String[] outTps, @RequestParam("outTn") String[] outTns,
			@RequestParam("outSs") String[] outSss, @RequestParam("outPh") String[] outPhs) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/report/month/index";
		}
		if (!beanValidator(model, report)) {
			// return form(report, model);
		}
		int id = reportService.saveAndReturn(report);
		int inSize = inDates.length;
		for (int i = 0; i < inSize; i++) {
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
		for (int i = 0; i < outSize; i++) {
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
	@RequestMapping(value = "save2")
	public String save2(MonthReport report, Model model, RedirectAttributes redirectAttributes,
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
			return "redirect:" + adminPath + "/report/month/index";
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
		for (int i = 0; i < outSize; i++) {
			Overproof outOverproof = new Overproof();
			outOverproof.setId(outIds[i]);
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
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/report/month/index";
		}
		reportService.delete(report);
		int monthReportId = Integer.valueOf(report.getId());
		overproofService.deleteByMonthReportId(monthReportId);
		addMessage(redirectAttributes, "删除生产运行日报表成功");
		return "redirect:" + adminPath + "/report/month/index";
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
	@RequiresPermissions("report:month:collect:view")
	@RequestMapping(value = { "collect" })
	public String collect(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/report/monthReportCollect";
	}

	@RequiresPermissions("report:month:collect:view")
	@RequestMapping(value = { "collectGenerate", "" })
	public String collectGenerate(MonthReport report, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<MonthReport> page = reportService.find(new Page<MonthReport>(request, response), report);
		model.addAttribute("page", page);
		model.addAttribute("report", report);
		return "modules/report/monthReportCollect";
	}

	@RequiresPermissions("report:month:collect:view")
	@RequestMapping(value = "collectExport")
	public String collectExport(MonthReport report, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "月报汇总表" + report.getReportMonth() + ".xls";
			Page<MonthReport> page = reportService.find(new Page<MonthReport>(request, response), report);
			List<MonthReport> lists = page.getList();
			for (MonthReport monthReport : lists) {
				monthReport.setOfficeType(OfficeUtils.getOfficeType(monthReport.getOfficeId()));
				monthReport.setOfficeId(OfficeUtils.getOfficeName(monthReport.getOfficeId()));
			}
			new ExportExcelJxls(7).setDataList(lists).setReportMonth(report.getReportMonth()).write(response, fileName);
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出日报表失败！失败信息：" + e.getMessage());
		}
		return "modules/report/monthReportCollect";
	}
	
	@RequiresPermissions("report:month:view")
	@RequestMapping(value = "batchExport")
	public String batchExport(String ids, final HttpServletRequest request, final HttpServletResponse response,
			final RedirectAttributes redirectAttributes) {
		if (ids!=null && ids.length()>0) {
			String[] idArray = ids.split(",");
			String partentPath = request.getSession().getServletContext().getRealPath("fileDir");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("月报_").append(idArray.length).append("_").append(RandomStringUtils.randomAlphanumeric(5))
						.append("_").append(RandomStringUtils.randomAlphanumeric(5)).append("_").append(System.currentTimeMillis());
			String zipFileName = stringBuilder.toString();
			String childPath = "\\"+zipFileName;
			String tempPath = partentPath+childPath;
			File tempDir = new File(tempPath);
			if (tempDir.exists()) {
				tempDir.delete();
			}
			tempDir.mkdirs();
			if (!tempDir.exists() || !tempDir.canWrite()) {
				logger.error("系统错误！");
				 return null;
			}
			for (final String id : idArray) {
				MonthReport report = reportService.get(id);
				try {
					String company = OfficeUtils.getOfficeName(report.getOfficeId());
//					String fileName = company + "_" + "周报表" + report.getReportDate() + ".xls";
					List inOverproofList = null;
					List outOverproofList = null;
					if (report != null) {
						Overproof inEntity = new Overproof();
						inEntity.setDelFlag(Overproof.DEL_FLAG_NORMAL);
						inEntity.setMonthReportId(Integer.parseInt(report.getId()));
						inEntity.setType("1");
						inOverproofList = overproofService.findList(inEntity);
						Overproof outEntity = new Overproof();
						outEntity.setDelFlag(Overproof.DEL_FLAG_NORMAL);
						outEntity.setMonthReportId(Integer.parseInt(report.getId()));
						outEntity.setType("2");
						outOverproofList = overproofService.findList(outEntity);
					}
					String fileName = company + "_" + "月报表" + report.getReportDate()
							+ ".xls";
					new ExportExcelJxls(6).setMonthReport(report).setOvInList(inOverproofList).setOvOutList(outOverproofList)
							.writeFile(tempDir.getPath()+"\\"+fileName);
				} catch (Exception e) {
					e.printStackTrace();
					addMessage(redirectAttributes, "导出月报表失败！失败信息：" + e.getMessage());
				}
			}
			String zipFileFullPath =tempPath+"\\"+zipFileName+".zip";
			FileUtils.zipFiles(tempPath, "*", zipFileFullPath);
			
	        File zipFile = new File(zipFileFullPath);
	        FileUtils.downFile(zipFile, request, response);
	        try {
				FileUtils.deleteDirectory(tempDir);
			} catch (IOException e) {
			}
	        return null;
		}
		return "redirect:" + adminPath + "/report/month/list";
	}
}
