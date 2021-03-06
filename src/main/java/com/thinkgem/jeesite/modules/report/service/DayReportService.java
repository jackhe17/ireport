package com.thinkgem.jeesite.modules.report.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.report.dao.DayReportDao;
import com.thinkgem.jeesite.modules.report.entity.DayReport;
import com.thinkgem.jeesite.modules.report.entity.RangeReport;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Service
@Transactional(readOnly = true)
public class DayReportService extends CrudService<DayReportDao, DayReport> {
	@Autowired
	DayReportDao dayReportDao;
	public DayReport get(String id) {
		DayReport entity = dao.get(id);
		return entity;
	}
	
	
	public Page<DayReport> find(Page<DayReport> page, DayReport dayReport) {
		dayReport.setPage(page);
		dayReport.setDelFlag(DayReport.DEL_FLAG_NORMAL);
		page.setList(dao.findList(dayReport));
		return page;
	}
	public Page<DayReport> findCollectReport(Page<DayReport> page, DayReport dayReport) {
		dayReport.setPage(page);
		page.setList(dayReportDao.getCollectReport(dayReport));
		return page;
	}
	public Page<DayReport> findRangeReport(Page<DayReport> page,DayReport dayReport, RangeReport rangeReport) {
		dayReport.setPage(page);
		page.setList(dayReportDao.getRangeReport(rangeReport.getOfficeId(),rangeReport.getStartDate(),rangeReport.getEndDate(),dayReport.DEL_FLAG_NORMAL));
		return page;
	}
	/**
	 * 获取通知数目
	 * @param oaNotify
	 * @return
	 */
//	public Long findCount(DayReport dayReport) {
//		return dao.findCount(dayReport);
//	}
	
	@Transactional(readOnly = false)
	public void save(DayReport report) {
		
		if (StringUtils.isBlank(report.getId())){
			report.setOfficeId(UserUtils.getUser().getCompany().getId());
			report.setCreateBy(UserUtils.getUser());
			report.setCreateDate(new Date());
			report.setUpdateBy(UserUtils.getUser());
			report.setUpdateDate(new Date());
			report.setDelFlag("0");
			dayReportDao.insert(report);
		}else{
			report.setUpdateBy(UserUtils.getUser());
			report.setUpdateDate(new Date());
			dayReportDao.update(report);
		}
		
	}
	
	
}
