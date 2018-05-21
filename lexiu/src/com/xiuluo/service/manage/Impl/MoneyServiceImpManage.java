package com.xiuluo.service.manage.Impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.xiuluo.model.aboutUs.RechargeSet;
import com.xiuluo.model.aboutUs.RechargeWorker;
import com.xiuluo.model.aboutUs.User;
import com.xiuluo.model.aboutUs.UserBankcard;
import com.xiuluo.model.aboutUs.Worker;
import com.xiuluo.model.aboutUs.WorkerBankcard;
import com.xiuluo.service.manage.MoneyServiceManage;
import com.xiuluo.util.AllMapper;


@Service("moneyServiceManage")
public class MoneyServiceImpManage extends AllMapper implements MoneyServiceManage {

	/**
	 * 查询用户流水
	 */
	@Override
	public List<RechargeSet> selectuser() {
		List<RechargeSet> list = rechargeSetMapper.selectAll();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(list != null && list.size()>0){
			for (RechargeSet rechargeSet : list) {
				User user = userMapper.selectByPrimaryKey(rechargeSet.getUserid());
				rechargeSet.setUsername(user.getNickname());
				rechargeSet.setFormattime(sdf.format(rechargeSet.getAddtime()));
			}
		}
		return list;
	}

	/**
	 * 查询师傅流水
	 */
	@Override
	public List<RechargeWorker> selectworker() {
		List<RechargeWorker> list = rechargeWorkerMapper.selectAll();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(list != null && list.size()>0){
				for (RechargeWorker rechargeWorker : list) {
					Worker worker = workerMapper.selectByPrimaryKey(rechargeWorker.getWorkerid());
					rechargeWorker.setWorkername(worker.getNickname());
					rechargeWorker.setFormattime(sdf.format(rechargeWorker.getAddtime()));
				}
			}
		return list;
	}

	/**
	 * 查询用户银行卡信息
	 */
	@Override
	public List<UserBankcard> selectuserbank() {
		List<UserBankcard> list = userbankCardMapper.selectAll();
		return list;
	}

	/**
	 * 查询师傅银行卡信息
	 */
	@Override
	public List<WorkerBankcard> selectworkerbank() {
		List<WorkerBankcard> list = workerbankCardMapper.selectAll();
		return list;
	}

}
