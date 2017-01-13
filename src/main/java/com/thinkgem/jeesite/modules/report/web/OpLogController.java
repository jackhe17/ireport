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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.report.entity.OpLog;
import com.thinkgem.jeesite.modules.report.service.OpLogService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.OfficeUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/report/oplog")
public class OpLogController extends BaseController {

	@Autowired
	private OpLogService opLogService;
	
	@RequiresPermissions("report:oplog:view")
	@RequestMapping(value = {"index"})
	public String index(Model model) {
		return "modules/report/oplogList";
	}
	
	@ModelAttribute("oplog")
	public OpLog get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			OpLog r = opLogService.get(id);
			return r;
		}else{
			return new OpLog();
		}
	}
	@RequestMapping("/opLogList")
    public String opLogList() {
        return "opLogList";
    }
	@RequiresPermissions("report:oplog:view")
	@RequestMapping(value = {"list", ""})
	public String list(OpLog opLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		String officeID = opLog.getOfficeId();
		String logDate ="";
		if (opLog == null || officeID ==null || "".equals(officeID) ) {
			opLog = new OpLog();
			officeID = UserUtils.getUser().getCompany().getId();
			logDate = DateUtils.getDistanceDay(new Date(), -1);
			opLog.setOfficeId(officeID);
			opLog.setLogDate(logDate);
		}else {
			officeID = opLog.getOfficeId();
			logDate = opLog.getLogDate();
		}
		Page<OpLog> page = opLogService.find(new Page<OpLog>(request, response), opLog);
		if (page!=null && page.getList()!=null && page.getList().size()>0) {
			String weather = page.getList().get(0).getWeather();
			model.addAttribute("weather", weather);
		}
		model.addAttribute("logDate", logDate);
		model.addAttribute("officeId", officeID);
		
        model.addAttribute("page", page);
        Office office = OfficeUtils.getOffice(officeID);
        if (null == office) {
        	office = UserUtils.getUser().getCompany();
		}
        
        model.addAttribute("office", office);
		return "modules/report/oplogList";
	}

	@RequiresPermissions("report:day:view")
	@RequestMapping(value = "form")
	public String form(OpLog report, Model model) {
		model.addAttribute("report", report);
		model.addAttribute("user", UserUtils.getUser());
		return "modules/report/opLogForm";
	}
	
	@RequiresPermissions("report:day:edit")
	@RequestMapping(value = "save")
	public String save(OpLog oplog,@RequestParam("poolId")String[] poolIds,  
            						@RequestParam("waterStartTime")String[] waterStartTimes,  
            						@RequestParam("waterEndTime")String[] waterEndTimes, 
            						@RequestParam("waterUsage")String[] waterUsages,  
            						@RequestParam("dryHour")String[] dryHours,
            						@RequestParam("dryMin")String[] dryMins,  
            						@RequestParam("logRemarks")String[] logRemarks,
            						@RequestParam("logRecorder")String[] logRecorders,
            						Model model, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/report/report";
		}
		if (!beanValidator(model, oplog)){
//			return form(report, model);
		}
		int size = poolIds.length;
		for(int i =0;i<size;i++){
			OpLog ol = new OpLog();
			ol.setLogDate(oplog.getLogDate());
			ol.setWeather(oplog.getWeather());
			ol.setPoolId(poolIds[i]);
			ol.setWaterStartTime(waterStartTimes[i]);
			ol.setWaterEndTime(waterEndTimes[i]);
			ol.setWaterUsage(waterUsages[i]);
			ol.setDryHour(dryHours[i]);
			ol.setDryMin(dryMins[i]);
			ol.setLogRemarks(logRemarks[i]);
			ol.setLogRecorder(logRecorders[i]);
			opLogService.save(ol);
		}
		
		addMessage(redirectAttributes, "保存生产运行日报表'" + oplog.getLogDate() + "'成功");
		return "redirect:" + adminPath + "/report/oplog/index";
	}
	
	@RequiresPermissions("report:day:edit")
	@RequestMapping(value = "delete")
	public String delete(OpLog report, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/report/area";
		}
//		if (Area.isRoot(id)){
//			addMessage(redirectAttributes, "删除区域失败, 不允许删除顶级区域或编号为空");
//		}else{
//			areaService.delete(area);
			addMessage(redirectAttributes, "删除生产运行日报表成功");
//		}
		return "redirect:" + adminPath + "/report/oplog/index";
	}

}
