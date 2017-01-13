/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.report.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.report.dao.WeekReportDao;
import com.thinkgem.jeesite.modules.report.entity.WeekReport;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class WeekReportService extends CrudService<WeekReportDao, WeekReport> {
	@Autowired
	WeekReportDao reportDao;
	public WeekReport get(String id) {
		WeekReport entity = dao.get(id);
		return entity;
	}
	
	public Page<WeekReport> find(Page<WeekReport> page, WeekReport report) {
		report.setPage(page);
		page.setList(dao.findList(report));
		return page;
	}
	
	public Page<WeekReport> findWeekCollect(Page<WeekReport> page, WeekReport report) {
		report.setPage(page);
		page.setList(reportDao.getWeekReportCollect(report.getReportDate()));
		return page;
	}
	
	@Transactional(readOnly = false)
	public int saveAndReturn(WeekReport report) {
		
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
	public void delete(WeekReport report){
		report.setDelFlag(WeekReport.DEL_FLAG_DELETE);
		reportDao.delete(report);
	}
	
}
