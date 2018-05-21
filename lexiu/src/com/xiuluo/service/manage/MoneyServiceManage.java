package com.xiuluo.service.manage;

import java.util.List;

import com.xiuluo.model.aboutUs.RechargeSet;
import com.xiuluo.model.aboutUs.RechargeWorker;
import com.xiuluo.model.aboutUs.UserBankcard;
import com.xiuluo.model.aboutUs.WorkerBankcard;

public interface MoneyServiceManage {

	public List<RechargeSet> selectuser();
	
	public List<RechargeWorker> selectworker();
	
	public List<UserBankcard> selectuserbank();
	
	public List<WorkerBankcard> selectworkerbank();
}
