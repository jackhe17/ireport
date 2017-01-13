/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.report.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.cms.entity.Article;
import com.thinkgem.jeesite.modules.report.dao.DayReportDao;
import com.thinkgem.jeesite.modules.report.dao.MonthReportDao;
import com.thinkgem.jeesite.modules.report.entity.DayReport;
import com.thinkgem.jeesite.modules.report.entity.MonthReport;
import com.thinkgem.jeesite.modules.report.entity.RangeReport;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class MonthReportService extends CrudService<MonthReportDao, MonthReport> {
	@Autowired
	MonthReportDao reportDao;
	public MonthReport get(String id) {
		MonthReport entity = dao.get(id);
		return entity;
	}
	
	
	public Page<MonthReport> find(Page<MonthReport> page, MonthReport report) {
		report.setPage(page);
		page.setList(dao.findList(report));
		return page;
	}
	
	public Page<MonthReport> findMonthCollect(Page<MonthReport> page, MonthReport report) {
		report.setPage(page);
		page.setList(reportDao.getMonthReportCollect(report.getReportMonth()));
		return page;
	}
	
	@Transactional(readOnly = false)
	public int saveAndReturn(MonthReport report) {
		
		if (StringUtils.isBlank(report.getId())){
			report.setOfficeId(UserUtils.getUser().getCompany().getId());
//			report.setOfficeName(UserUtils.getUser().getCompany().getName());
			report.setCreateBy(UserUtils.getUser());
			report.setCreateDate(new Date());
			report.setUpdateBy(UserUtils.getUser());
			report.setUpdateDate(new Date());
			report.setDelFlag("0");
			reportDao.insert(report);
			return Integer.valueOf(report.getId());
		}else{
			report.setUpdateBy(UserUtils.getUser());
			report.setUpdateDate(new Date());
			return reportDao.update(report);
		}
		
	}
	@Transactional(readOnly = false)
	public void delete(MonthReport report){
		report.setDelFlag(MonthReport.DEL_FLAG_DELETE);
		reportDao.delete(report);
	}
	
}
