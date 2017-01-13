package com.thinkgem.jeesite.modules.report.entity;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class OpLogList extends DataEntity<OpLogList> {
	List<OpLog> opLogList;

	public List<OpLog> getOpLogList() {
		return opLogList;
	}

	public void setOpLogList(List<OpLog> opLogList) {
		this.opLogList = opLogList;
	}
	
}
