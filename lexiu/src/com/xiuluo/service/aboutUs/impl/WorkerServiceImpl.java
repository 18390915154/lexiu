package com.xiuluo.service.aboutUs.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.xiuluo.model.MineAssess;
import com.xiuluo.model.aboutUs.Assess;
import com.xiuluo.model.aboutUs.Checkout;
import com.xiuluo.model.aboutUs.Order;
import com.xiuluo.model.aboutUs.RechargeWorker;
import com.xiuluo.model.aboutUs.User;
import com.xiuluo.model.aboutUs.Worker;
import com.xiuluo.model.aboutUs.WorkerBankcard;
import com.xiuluo.model.aboutUs.WorkerGps;
import com.xiuluo.service.aboutUs.WorkerService;
import com.xiuluo.util.AllMapper;
import com.xiuluo.util.CommonUtils;


@Service("workerService")
public class WorkerServiceImpl extends AllMapper implements WorkerService {

	/**
	 * 师傅报结订单
	 */
	@Override
	public String insertorder(Integer orderid, String content, String fault,String price) {
		//查询订单信息
		Order order = orderMapper.selectByPrimaryKey(orderid);
		String message;
		if(order != null){
			order.setContent(content);
			order.setFault(fault);
			order.setType((short)2);
			order.setOvertime(new Date());
			order.setAddtime(new Date());
			//计算订单价格
			Date start = order.getStarttime();
			Date over = order.getOvertime();
			int date = over.getDate() - start.getDate();
			
			//总计用时
			int cont = (((date*24)+over.getHours()) - start.getHours())*60 + (over.getMinutes() - start.getMinutes());
			order.setCounttime(String.valueOf(cont));
			
			//判断是维修订单还是家政订单
			String str = order.getOrdernum().substring(0, 1);
			if("A".equals(str)){
				//获取家政订单抽成比例
				Checkout checkout = checkoutMapper.selectByType(Integer.valueOf(1));
				//手动输入价格
				double money = CommonUtils.parseDouble(price, 0);
				money = money + money*(checkout.getNumber()/100);
				BigDecimal bd = new BigDecimal(money);
				order.setPrice(bd);
			}else if("B".equals(str)){
				//获取维修订单抽成比例
				Checkout checkout = checkoutMapper.selectByType(Integer.valueOf(2));
				//计算金额
				if(date == 0){
					//当天订单
					if(over.getHours() - start.getHours() >= 5){
						double money = 300;
						double a = money * (checkout.getNumber()/100);
						money = money + a;
						BigDecimal bd = new BigDecimal(money);
						order.setPrice(bd);
					}else if(over.getHours() - start.getHours() < 5){
						double minutes = (over.getHours() - start.getHours())*60+(over.getMinutes() - start.getMinutes());
						double money = 50;
						if(minutes <= 60){
							money = 50;
						}else{
							//除去起步价的1个小时
							double a = minutes - 60;
							//剩余的几个半小时
							double b = a/30;
							int c = (int)b;
							boolean bool = true;
							if(b - (double) c != 0.0){
								bool = false;
							}
							if(!bool){
								money = money + ((c*25)+25);
							}else{
								money = money + (c*25);
							}
							double a1 = money * (checkout.getNumber()/100);
							money = money + a1;							
						}
						BigDecimal bd = new BigDecimal(money);
						order.setPrice(bd);
					} 
				}else if(date == 1){
					double money = 50;
					//隔夜订单
					double minutes = ((over.getHours()+24) - start.getHours())*60+(over.getMinutes() - start.getMinutes());
					//除去起步价的1个小时
					double a = minutes - 60;
					//剩余的几个半小时
					double b = a/30;
					int c = (int)b;
					boolean bool = true;
					if(b - (double) c != 0.0){
						bool = false;
					}
					if(!bool){
						money = money + ((c*25)+25);
					}else{
						money = money + (c*25);
					}
					double a1 = money * (checkout.getNumber()/100);
					money = money + a1;
					BigDecimal bd = new BigDecimal(money);
					order.setPrice(bd);
				}else{
					double money = 300;
					double a = money * (checkout.getNumber()/100);
					money = money + a;
					BigDecimal bd = new BigDecimal(money);
					order.setPrice(bd);
				}	
			}
			int code = orderMapper.updateByPrimaryKeySelective(order);
			if(code == 1){
				message = "成功";
			}else{
				message="报结失败";
			}
		}else{
			message = "订单信息错误!";
		}
		return message;
	}

	
	
	/**
	 * 完善师傅信息
	 */
	@Override
	public String updatework(Integer workerid, String nickname, String birthday,Integer age, String sex, String phone,
			String contacttel, String companies, String companiestel, String skill, String idcard ,String worktype,List<String> paths) {
		//查询师傅信息
		Worker worker = workerMapper.selectByPrimaryKey(workerid);
		String message ;
		if(worker != null){
			worker.setNickname(nickname);
			worker.setSex(sex);
			worker.setBirthday(CommonUtils.getDateFormat(birthday, "yyyy-MM-dd"));
			worker.setAge(age);
			worker.setAddtime(new Date());
			worker.setContactTel(contacttel);
			worker.setTel(phone);
			worker.setCompanyid(CommonUtils.parseInt(companies, 0));
			worker.setCompaniesTel(companiestel);
			worker.setSkill(skill);
			worker.setIdcard(idcard);
			worker.setTypeid(CommonUtils.parseInt(worktype, 0));
			//循环获取文件路径
			for (int i=0 ;i<paths.size();i++) {
				worker.setIdentityFront(paths.get(0));
				worker.setIdentityRear(paths.get(1));
				worker.setCredentialsFront(paths.get(2));
				worker.setCredentialsRear(paths.get(3));
			}
			int code = workerMapper.updateByPrimaryKeySelective(worker);
			if(code == 1){
				message = "成功";
			}else{
				message = "更新失败!";
			}
		}else{
			message = "更新失败!";
		}
		return message;
	}

	
	/**
	 *添加师傅银行卡信息 
	 */
	@Override
	public String addbankcard(Integer workerid, String cardnumber, String bankname, String name, String phone) {
		WorkerBankcard workerbank = workerbankCardMapper.selectByCardnumber(cardnumber);
		String message;
		if(workerbank != null){
			message = "银行信息有误!";
		}else{
			WorkerBankcard newbank = new WorkerBankcard();
			newbank.setWorkerid(workerid);
			newbank.setUsername(name);
			newbank.setPhone(phone);
			newbank.setCardnumber(cardnumber);
			newbank.setBankname(bankname);
			newbank.setAddtime(new Date());
			int code = workerbankCardMapper.insertSelective(newbank);
			if(code == 1){
				message = "添加成功!";
			}else{
				message = "添加失败!";
			}
		}
		return message;
	}

	
	/**
	 * 查询我的银行卡
	 */
	@Override
	public List<WorkerBankcard> selectmybank(Integer workerid) {
		List<WorkerBankcard> list = workerbankCardMapper.selectByWorkerid(workerid);
		if(list != null && list.size()>0){
			for (WorkerBankcard workerBankcard : list) {
				String a = workerBankcard.getCardnumber();
				String b = a.substring(0, 2);
				String c = a.substring(a.length()-4,a.length());
				workerBankcard.setCardnumber(b+"**********"+c);
			}
		}
		return list;
	}
	
	
	/**
	 * 删除我的银行卡
	 */
	@Override
	public String delectmybank(Integer cardid) {
		int code = workerbankCardMapper.deleteByPrimaryKey(cardid);
		String message;
		if(code == 1){
			message = "成功";
		}else{
			message = "删除失败!";
		}
		return message;
	}

	
	/**
	 *查询师傅信息 
	 */
	@Override
	public Worker selectbyWorkerid(Integer workerid) {
		Worker worker = workerMapper.selectByPrimaryKey(workerid);
		return worker;
	}

	
	/**
	 * 查询师傅流水信息
	 */
	@Override
	public List<RechargeWorker> workerwalletlist(Integer workerid) {
		List<RechargeWorker> list = rechargeWorkerMapper.selectByWorkerid(workerid);
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		for (RechargeWorker rechargeWorker : list) {
			rechargeWorker.setFormattime(sdf.format(rechargeWorker.getAddtime()));
		}
		return list;
	}
	
	
	/**
	 * 查询所有可接订单
	 */
	@Override
	public List<Order> selectAcceptOrder(Integer workerid) {
		Worker worker = workerMapper.selectByPrimaryKey(workerid);
		List<Order> list = new ArrayList<Order>();
		List<Order> all = new ArrayList<Order>();
		if(worker != null){
			 //获取师傅类型
			Integer typeid = worker.getTypeid();
			list = orderMapper.selectTypeByTypeid(typeid);
			if(list != null && list.size()>0){
				for (Order order : list) {
					all.add(order);
				}
			}
			//获取师傅公司ID
			Integer companyid = worker.getCompanyid();
			list = orderMapper.selectTypeByCompanyid(companyid);
			if(list != null && list.size()>0){
				for (Order order : list) {
					all.add(order);
				}
			}
		}
		return all;
	}

	
	/**
	 * 将师傅信息插入订单中
	 */
	@Override
	public String insertworkertoorder(Integer orderid, Integer workerid) {
		Order order = orderMapper.selectByPrimaryKey(orderid);
		String message;
		if(order == null){
			message = "订单有误";
		}else if(order.getType() != 0){
			message = "此订单已被抢";
		}else if(order.getDelflg() == 1){
			message = "此订单已被取消";
		}
		//将师傅信息插入订单中
		order.setWorkerid(workerid);
		order.setType((short)1);
		//语音还未写
		int code = orderMapper.updateByPrimaryKeySelective(order);
		if(code == 1){
			message="成功";
		}else{
			message="抢单失败";
		}
		return message;
	}

	
	/**
	 * 查询所有待完成订单
	 */
	@Override
	public List<Order> undoneorder(Integer workerid) {
		List<Order> list = orderMapper.selectundonebyworkerid(workerid);
		return list;
	}

	
	/**
	 * 查询所有完成订单
	 */
	@Override
	public List<Order> doneorder(Integer workerid) {
		List<Order> list = orderMapper.selectdonebyworkerid(workerid);
		return list;
	}

	
	/**
	 *修改支付密码 
	 */
	@Override
	public String updatepaypassword(String phone, String password) {
		Worker worker = workerMapper.selectByPhone(phone);
		String message;
		if(worker != null){
			worker.setPaypassword(password);
			int code = workerMapper.updateByPrimaryKeySelective(worker);
			if(code == 1){
				message = "成功";
			}else{
				message = "操作失败";
			}
		}else{
			message = "信息有误";
		}
		return message;
	}

	
	/**
	 * 查询师傅评价信息
	 */
	@Override
	public List<MineAssess> selectassessbyworkerid(Integer workerid) {
		List<MineAssess> list = new ArrayList<MineAssess>();
		//查询订单评论信息
		List<Order> orderlist = orderMapper.selectdonebyworkerid(workerid);
		for (Order order : orderlist) {
			MineAssess mineassess = new MineAssess(); 
			//根据订单ID查询评论
			Assess assess = assessMapper.selectByOrderid(order.getOrderid());
			if(assess != null){
				mineassess.setGrade(assess.getGrade());				
				//根据用户ID查询用户姓名
				User user = userMapper.selectByPrimaryKey(order.getUserid());
				if(user!=null){
					mineassess.setNickname(user.getNickname());
					mineassess.setAvatar(user.getAvatar());	
				}
				list.add(mineassess);
			}
		}
		return list;
	}

	
	/**
	 * 通过师傅ID更新师傅定位信息
	 */
	@Override
	public String updateworkgps(Integer workerid, String longitude, String latitude) {
		//查询师傅经纬度信息
		String message = null;
		WorkerGps gps = workerGpsMapper.selectByWorkerid(workerid);
		if(gps != null){
			BigDecimal blatiude = new BigDecimal(latitude);
			gps.setLatitude(blatiude);
			BigDecimal blongitude = new BigDecimal(longitude);
			gps.setLongitude(blongitude);
			gps.setAddtime(new Date());
			int code = workerGpsMapper.updateByPrimaryKeySelective(gps);
			if(code == 1){
				message = "成功";
			}else{
				message = "操作失败";
			}
		}
		return message;
	}


	/**
	 * 查询支付过的订单
	 */
	@Override
	public List<Order> searchpayorder(Integer workerid) {
		List<Order> list = orderMapper.selectpayorder(workerid);
		return list;
	}
}
