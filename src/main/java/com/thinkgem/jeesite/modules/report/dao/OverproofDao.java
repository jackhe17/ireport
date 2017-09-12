package com.thinkgem.jeesite.modules.report.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.report.entity.Overproof;

@MyBatisDao
public interface OverproofDao extends CrudDao<Overproof> {
	void deleteByMonthReportId(@Param(value="monthReportId") int monthReportId,@Param(value="delFlag")String delFlag);
}
