/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.report.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.BaseEntity;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.report.dao.OverproofDao;
import com.thinkgem.jeesite.modules.report.entity.Overproof;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OverproofService extends CrudService<OverproofDao,Overproof> {
	@Autowired
	OverproofDao overproofDao;
	public Overproof get(String id) {
		Overproof entity = dao.get(id);
		return entity;
	}
	
	
	public Page<Overproof> find(Page<Overproof> page, Overproof overproof) {
		overproof.setPage(page);
		page.setList(dao.findList(overproof));
		return page;
	}
	@Transactional(readOnly = false)
	public void save(Overproof overproof) {
		
		if (StringUtils.isBlank(overproof.getId())){
			overproof.setCreateBy(UserUtils.getUser());
			overproof.setCreateDate(new Date());
			overproof.setUpdateBy(UserUtils.getUser());
			overproof.setUpdateDate(new Date());
			overproof.setDelFlag("0");
			overproofDao.insert(overproof);
		}else{
			overproof.setUpdateBy(UserUtils.getUser());
			overproof.setUpdateDate(new Date());
			overproofDao.update(overproof);
		}
		
	}
	@Transactional(readOnly = false)
	public void deleteByMonthReportId(int monthReportId ) {
		overproofDao.deleteByMonthReportId(monthReportId,Overproof.DEL_FLAG_DELETE);
	}
	
}
