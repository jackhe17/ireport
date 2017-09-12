package com.thinkgem.jeesite.modules.report.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.report.entity.OpLog;

@MyBatisDao
public interface OpLogDao extends CrudDao<OpLog> {
}
