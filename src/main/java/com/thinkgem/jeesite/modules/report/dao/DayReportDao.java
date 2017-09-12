package com.thinkgem.jeesite.modules.report.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.report.entity.DayReport;

@MyBatisDao
public interface DayReportDao extends CrudDao<DayReport> {
	List<DayReport> getCollectReport(DayReport dayReport);
	List<DayReport> getRangeReport(@Param(value="officeId") String officeId,@Param(value="startDate") String startDate,@Param(value="endDate") String endDate,@Param(value="delFlag") String delFlag);
}
