package com.thinkgem.jeesite.modules.report.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.report.entity.MonthReport;

@MyBatisDao
public interface MonthReportDao extends CrudDao<MonthReport> {
	List<MonthReport> getMonthReportCollect(@Param(value="reportMonth") String reportMonth);
}
