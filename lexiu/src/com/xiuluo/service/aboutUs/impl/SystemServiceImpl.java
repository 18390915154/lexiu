package com.xiuluo.service.aboutUs.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.xiuluo.model.aboutUs.Type;
import com.xiuluo.model.aboutUs.Withdraw;
import com.xiuluo.model.aboutUs.Worker;
import com.xiuluo.model.aboutUs.WorkerGps;
import com.xiuluo.service.aboutUs.SystemService;
import com.xiuluo.util.AllMapper;


@Service("systemService")
public class SystemServiceImpl extends AllMapper implements SystemService {

	/**
	 * 用户提现申请
	 */
	@Override
	public String insertuserwithdraw(Integer userid, String monery, String account) {
		Withdraw withdraw = new Withdraw();
		withdraw.setPersionid(userid);
		BigDecimal bd = new BigDecimal(monery);
		withdraw.setMonery(bd);
		withdraw.setAccount(account);
		withdraw.setIsok((short)0);
		withdraw.setAddtime(new Date());
		withdraw.setUserorwork((short)0);
		int code = withdrawMapper.insertSelective(withdraw);
		String message;
		if(code == 1){
			message = "成功";
		}else{
			message = "失败";
		}
		return message;
	}

	
	/**
	 * 师傅提现申请
	 */
	@Override
	public String insertworkerwithdraw(Integer workerid, String monery, String account) {
		Withdraw withdraw = new Withdraw();
		withdraw.setPersionid(workerid);
		BigDecimal bd = new BigDecimal(monery);
		withdraw.setMonery(bd);
		withdraw.setAccount(account);
		withdraw.setIsok((short)0);
		withdraw.setAddtime(new Date());
		withdraw.setUserorwork((short)1);
		int code = withdrawMapper.insertSelective(withdraw);
		String message;
		if(code == 1){
			message = "成功";
		}else{
			message = "失败";
		}
		return message;
	}


	/**
	 * 查询所有类型
	 */
	@Override
	public List<Type> selecttypelist() {
		List<Type> list = typeMapper.selectall();
		return list;
	}


	/**
	 * 分类查询分类信息
	 */
	@Override
	public List<Type> selectscorebynum(String num) {
		List<Type> list = typeMapper.selectscorenum(num);
		return list;
	}

	
	/**
	 * 根据分类查询师傅经纬度信息 
	 */
	@Override
	public List<WorkerGps> selectworkbytypeid(Integer typeid) {
		List<Worker> list = workerMapper.selectbytypeid(typeid);
		List<WorkerGps> gpslist = new ArrayList<WorkerGps>();
		if(list != null && list.size()>0){
			for (Worker worker : list) {
				WorkerGps gps = workerGpsMapper.selectByWorkerid(worker.getWorkerid());
				gpslist.add(gps);
			}
		}
		return gpslist;
	}
}
