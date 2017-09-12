package com.thinkgem.jeesite.modules.report.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcelJxls;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.report.entity.DayReport;
import com.thinkgem.jeesite.modules.report.service.DayReportService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.OfficeUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 
 *
 * alter table `report_day` Add column disin2 varchar(50) default NULL AFTER `disin`; 
 * alter table `report_day` Add column disin3 varchar(50) default NULL AFTER `disin`;
 * 
 * alter table `report_day` Add column replyOpinion varchar(2000) default NULL AFTER `problem`; 
 * alter table `report_day` Add column firstOpinion varchar(2000) default NULL AFTER `problem`; 
 * @author Jack
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/report/day")
public class DayReportController extends BaseController {

	@Autowired
	private DayReportService reportService;

	@RequiresPermissions("report:day:view")
	@RequestMapping(value = { "index" })
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		DayReport report = new DayReport();
		// report.setReportDate(DateUtils.getDistanceDay(new Date(), -1));
		if (!UserUtils.getUser().isAdmin() && !UserUtils.isSuperUser()) {
			report.setOfficeId(UserUtils.getUser().getCompany().getId());
		}
		Page<DayReport> page = reportService.find(new Page<DayReport>(request, response), report);
		model.addAttribute("page", page);
		model.addAttribute("user", UserUtils.getUser());
		model.addAttribute("report", report);
		return "modules/report/dayReportList";
	}

	@ModelAttribute("dayReport")
	public DayReport get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			DayReport r = reportService.get(id);
			return r;
		} else {
			return new DayReport();
		}
	}

	@RequiresPermissions("report:day:view")
	@RequestMapping(value = { "list", "" })
	public String list(DayReport report, HttpServletRequest request, HttpServletResponse response, Model model) {

		if (report != null && !UserUtils.getUser().isAdmin() && !UserUtils.isSuperUser()) {
			report.setOfficeId(UserUtils.getUser().getCompany().getId());
		}
		String officeID = report.getOfficeId();
		// if (report == null || officeID ==null || "".equals(officeID) ) {
		// report = new DayReport();
		// }else {
		officeID = report.getOfficeId();
		// }
		Page<DayReport> page = reportService.find(new Page<DayReport>(request, response), report);
		model.addAttribute("page", page);
		model.addAttribute("user", UserUtils.getUser());
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
		return "modules/report/dayReportList";
	}

	@RequiresPermissions("report:day:view")
	@RequestMapping(value = "form")
	public String form(DayReport report, Model model) {
		String officeID = report.getOfficeId();
		Office office = OfficeUtils.getOffice(officeID);
		if (office != null) {
			report.setOfficeName(office.getName());
		}
		model.addAttribute("report", report);
		model.addAttribute("user", UserUtils.getUser());
		return "modules/report/dayReportForm";
	}

	@RequiresPermissions("report:day:view")
	@RequestMapping(value = "noeditform")
	public String noeditform(DayReport report, Model model) {
		String officeID = report.getOfficeId();
		Office office = OfficeUtils.getOffice(officeID);
		report.setOfficeName(office.getName());
		model.addAttribute("report", report);
		model.addAttribute("user", UserUtils.getUser());
		return "modules/report/dayReportNoEditForm";
	}

	@RequiresPermissions("report:day:edit")
	@RequestMapping(value = "save")
	public String save(DayReport report, Model model, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/report/day/index";
		}
		if (!beanValidator(model, report)) {
			// return form(report, model);
		}
		reportService.save(report);
		addMessage(redirectAttributes, "保存生产运行日报表'" + report.getReportDate() + "'成功");
		return "redirect:" + adminPath + "/report/day/index";
	}

	@RequiresPermissions("report:day:edit")
	@RequestMapping(value = "delete")
	public String delete(DayReport report, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
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
		if (!UserUtils.getUser().isAdmin() && !UserUtils.isSuperUser()) {
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
		if (!UserUtils.getUser().isAdmin() && !UserUtils.isSuperUser()) {
			report.setOfficeId(UserUtils.getUser().getCompany().getId());
		}
		Page<DayReport> page = reportService.find(new Page<DayReport>(request, response), report);
		List<DayReport> list = page.getList();
		if (null != list && list.size() > 0) {
			int id = 1;
			for (DayReport dayReport : list) {
				dayReport.setId(String.valueOf(id));
				id++;
			}
		}
		model.addAttribute("page", page);
		model.addAttribute("report", report);
		model.addAttribute("user", UserUtils.getUser());
		return "modules/report/dayReportCollect";
	}

	@RequiresPermissions("report:day:view")
	@RequestMapping(value = "dayReportCollectExport", method = RequestMethod.POST)
	public String collectExportFile(DayReport report, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "汇总日报表" + report.getReportDate() + ".xls";
			Page<DayReport> page = reportService.find(new Page<DayReport>(request, response), report);
			// new ExportExcel("日报表",
			// DayReport.class).setDataList(page.getList()).write(response,
			// fileName).dispose();
			new ExportExcelJxls(1).setDataList(page.getList()).write(response, fileName);
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出日报表失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/report/dayReportCollect";
	}

	@RequiresPermissions("report:day:view")
	@RequestMapping(value = "export")
	public String export(String id, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		DayReport dayReport = reportService.get(id);
		try {
			String company = OfficeUtils.getOfficeName(dayReport.getOfficeId());
			String fileName = company + "_" + "日报表" + dayReport.getReportDate() + ".xls";
			new ExportExcelJxls(3).setDayReport(dayReport).setCompany(company).write(response, fileName);
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出日报表失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/report/day/list";
	}
	
	@RequiresPermissions("report:day:view")
	@RequestMapping(value = "batchExport")
	public String batchExport(String ids, final HttpServletRequest request, final HttpServletResponse response,
			final RedirectAttributes redirectAttributes) {
		if (ids!=null && ids.length()>0) {
			String[] idArray = ids.split(",");
			String partentPath = request.getSession().getServletContext().getRealPath("fileDir");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("日报_").append(idArray.length).append("_").append(RandomStringUtils.randomAlphanumeric(5))
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
				DayReport dayReport = reportService.get(id);
				try {
					String company = OfficeUtils.getOfficeName(dayReport.getOfficeId());
					String fileName = company + "_" + "日报表" + dayReport.getReportDate() + ".xls";
					new ExportExcelJxls(3).setDayReport(dayReport).setCompany(company).writeFile(tempDir.getPath()+"\\"+fileName);
				} catch (Exception e) {
					e.printStackTrace();
					addMessage(redirectAttributes, "导出日报表失败！失败信息：" + e.getMessage());
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
		return "redirect:" + adminPath + "/report/day/list";
	}
}
