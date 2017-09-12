package com.thinkgem.jeesite.modules.report.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.report.entity.WeekReport;

@MyBatisDao
public interface WeekReportDao extends CrudDao<WeekReport> {
	List<WeekReport> getWeekReportCollect(@Param(value="reportDate") String reportDate);
}
