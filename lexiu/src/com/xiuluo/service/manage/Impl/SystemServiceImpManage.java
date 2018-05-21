package com.xiuluo.service.manage.Impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.xiuluo.model.aboutUs.Checkout;
import com.xiuluo.model.aboutUs.CompanyType;
import com.xiuluo.model.aboutUs.RechargeSet;
import com.xiuluo.model.aboutUs.RechargeWorker;
import com.xiuluo.model.aboutUs.Type;
import com.xiuluo.model.aboutUs.User;
import com.xiuluo.model.aboutUs.Withdraw;
import com.xiuluo.model.aboutUs.Worker;
import com.xiuluo.service.manage.SystemServiceManage;
import com.xiuluo.util.AllMapper;


@Service("systemServiceManage")
public class SystemServiceImpManage extends AllMapper implements SystemServiceManage{

	/**
	 * 查询所有分类信息
	 */
	@Override
	public List<Type> selecttypelist() {
		List<Type> list = typeMapper.selectall();
		return list;
	}

	
	/**
	 * 新增类型
	 */
	@Override
	public String inserttype(String name,String typenum) {
		Type type = new Type();
		type.setName(name);
		type.setTypenum(typenum);
		type.setAddtime(new Date());
		int code = typeMapper.insertSelective(type);
		String message;
		if(code == 1){
			message = "成功";
		}else{
			message = "删除失败";
		}
		return message;
	}

	
	/**
	 * 删除类型
	 */
	@Override
	public String delecttype(Integer typeid) {
		int code = typeMapper.deleteByPrimaryKey(typeid);
		String message;
		if(code == 1){
			message = "成功";
		}else{
			message = "删除失败";
		}
		return message;
	}


	@Override
	public List<CompanyType> selectcompanytypelist() {
		List<CompanyType> list = companyTypeMapper.selectall();
		return list;
	}


	@Override
	public String insertcompanytype(String name) {
		CompanyType type = new CompanyType();
		type.setName(name);
		type.setAddtime(new Date());
		int code = companyTypeMapper.insertSelective(type);
		String message;
		if(code == 1){
			message = "成功";
		}else{
			message = "删除失败";
		}
		return message;
	}


	@Override
	public String delectcompanytype(Integer companytypeid) {
		int code = companyTypeMapper.deleteByPrimaryKey(companytypeid);
		String message;
		if(code == 1){
			message = "成功";
		}else{
			message = "删除失败";
		}
		return message;
	}


	/**
	 * 查询所有提现申请
	 */
	@Override
	public List<Withdraw> selectwithdraw() {
		List<Withdraw> list = withdrawMapper.selectall();
		if(list != null && list.size() > 0){
			for (Withdraw withdraw : list) {
				short a = withdraw.getUserorwork();
				if(a == 0){
					//用户
					Integer userid = withdraw.getPersionid();
					User user = userMapper.selectByPrimaryKey(userid);
					if(user != null){
						withdraw.setName(user.getNickname());
					}
				}else{
					//师傅
					Integer workerid = withdraw.getPersionid();
					Worker worker = workerMapper.selectByPrimaryKey(workerid);
					if(worker != null){
						withdraw.setName(worker.getNickname());
					}
				}
			}
		}
		return list;
	}


	/**
	 * 修改提现申请
	 */
	@Override
	public String updatewithdraw(Integer withdrawid) {
		Withdraw withdraw = withdrawMapper.selectByPrimaryKey(withdrawid);
		String message = "审核失败";
		if(withdraw != null){
			withdraw.setIsok((short)1);
			int code = withdrawMapper.updateByPrimaryKeySelective(withdraw);
			if(code == 1){
				message = "成功";
			}else{
				message = "审核失败";
			}
		}
		
		if("成功".equals(message)){
			//审核成功增加流水信息
			short a = withdraw.getUserorwork();
			if(a == 0){
				//用户流水
				RechargeSet rechargeSet = new RechargeSet();
				rechargeSet.setUserid(withdraw.getPersionid());
				rechargeSet.setMoney(withdraw.getMonery());
				rechargeSet.setDetail("提现");
				rechargeSet.setType((short)2);
				rechargeSet.setScore(0);
				rechargeSet.setDelstate((short)0);
				rechargeSet.setAddtime(new Date());
				rechargeSetMapper.insertSelective(rechargeSet);
				//用户金额变更
				User user = userMapper.selectByPrimaryKey(withdraw.getPersionid());
				BigDecimal bd = user.getMoney().subtract(withdraw.getMonery());
				user.setMoney(bd);
				userMapper.updateByPrimaryKeySelective(user);
			}else{
				//师傅流水
				RechargeWorker rechargeSet = new RechargeWorker();
				rechargeSet.setWorkerid(withdraw.getPersionid());
				rechargeSet.setMoney(withdraw.getMonery());
				rechargeSet.setDetail("提现");
				rechargeSet.setType((short)2);
				rechargeSet.setScore(0);
				rechargeSet.setDelstate((short)0);
				rechargeSet.setAddtime(new Date());
				rechargeWorkerMapper.insertSelective(rechargeSet);
				//师傅金额变更
				Worker worker = workerMapper.selectByPrimaryKey(withdraw.getPersionid());
				BigDecimal bd = worker.getMoney().subtract(withdraw.getMonery());
				worker.setMoney(bd);
				workerMapper.updateByPrimaryKeySelective(worker);
			}	
		}
		return message;
	}
	
	
	public List<Checkout> selectcheckout(){
	    List<Checkout> list = checkoutMapper.selectAll();
	    return list;
	  }

	
	  public String updatecheckout(Integer checkoutid, double number)
	  {
	    String message;
	    Checkout checkout = checkoutMapper.selectByPrimaryKey(checkoutid);
	    checkout.setNumber(Double.valueOf(number));
	    int code = checkoutMapper.updateByPrimaryKeySelective(checkout);

	    if (code == 1)
	      message = "修改成功";
	    else
	      message = "修改失败";

	    return message;
	  }
}
