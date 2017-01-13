/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.report.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.report.entity.DayReport;

/**
 * 区域DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface DayReportDao extends CrudDao<DayReport> {
	List<DayReport> getCollectReport(String reportDate);
	List<DayReport> getRangeReport(@Param(value="officeId") String officeId,@Param(value="startDate") String startDate,@Param(value="endDate") String endDate);
}
