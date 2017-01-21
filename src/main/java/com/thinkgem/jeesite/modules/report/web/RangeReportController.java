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

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.report.entity.DayReport;
import com.thinkgem.jeesite.modules.report.entity.RangeReport;
import com.thinkgem.jeesite.modules.report.service.DayReportService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/range/report")
public class RangeReportController extends BaseController {

	@Autowired
	private DayReportService reportService;
	
	@RequiresPermissions("range:report:view")
	@RequestMapping(value = {"index"})
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		 model.addAttribute("user", UserUtils.getUser());
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
		if(rangeReport !=null && !UserUtils.getUser().isAdmin()){
			rangeReport.setOfficeId(UserUtils.getUser().getCompany().getId());
		}
		Page<DayReport> page = reportService.findRangeReport(new Page<DayReport>(request, response), report,rangeReport);
        model.addAttribute("page", page);
        model.addAttribute("user", UserUtils.getUser());
        model.addAttribute("rangeReport", rangeReport);
		return "modules/report/rangeReportIndex";
	}
}
