package com.xiuluo.service.aboutUs.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

import com.xiuluo.model.AllGps;
import com.xiuluo.model.aboutUs.Assess;
import com.xiuluo.model.aboutUs.Checkout;
import com.xiuluo.model.aboutUs.CompanyType;
import com.xiuluo.model.aboutUs.Order;
import com.xiuluo.model.aboutUs.RechargeSet;
import com.xiuluo.model.aboutUs.RechargeWorker;
import com.xiuluo.model.aboutUs.Type;
import com.xiuluo.model.aboutUs.User;
import com.xiuluo.model.aboutUs.Worker;
import com.xiuluo.model.aboutUs.WorkerGps;
import com.xiuluo.service.aboutUs.OrderService;
import com.xiuluo.util.AllMapper;
import com.xiuluo.util.CommonUtils;

@Service("orderService")
public class OrderServiceImpl extends AllMapper implements OrderService {

	/**
	 * 查询用户未完成订单
	 */
	@Override
	public List<Order> undoneorderbyuserid(Integer userid) {
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Order> list = orderMapper.selectundonebyuserid(userid);
		if(list != null && list.size()>0){
			for (Order order : list) {
				//判断订单类型
				switch(order.getType()){
				case 0:
					order.setTypename("待开始订单");
					break;
				case 1:
					order.setTypename("待报结订单");
					break;
				case 2:
					order.setTypename("待支付订单");
					break;
				case 3:
					order.setTypename("待评价订单");
					break;
				}
				Integer workerid = order.getWorkerid();
				//查询师傅信息
				if(workerid != null){
					Worker worker = workerMapper.selectByPrimaryKey(workerid);
					if(worker != null){
						order.setWorkname(worker.getNickname());
						order.setAvatar(worker.getAvatar());
						order.setUserphone(worker.getPhone());
					}
				}else{
					order.setWorkname(null);
				}
				//格式化开始时间
				if(order.getStarttime() != null){
					order.setFormatstart(dateFormater.format(order.getStarttime()));
				}else{
					order.setFormatstart(null);
				}
				//格式化结束时间
				if(order.getOvertime() != null){
					order.setFormatover(dateFormater.format(order.getOvertime()));
				}else{
					order.setFormatover(null);
				}
				//格式化添加时间
				if(order.getAddtime() != null){
					order.setFormatadd(dateFormater.format(order.getAddtime()));
				}else{
					order.setFormatadd(null);
				}
				if(order.getCompanyid() == 0){
					//维修订单
					Type type = typeMapper.selectByPrimaryKey(order.getTypeid());
					if(type != null){
						order.setWorktypename(type.getName());
					}
				}else{
					//家政订单
					CompanyType companyType = companyTypeMapper.selectByPrimaryKey(order.getTypeid());
					if(companyType != null){
						order.setWorktypename(companyType.getName());
					}
				}
			}
		}
		return list;
	}

	/**
	 * 查询订单详情
	 */
	@Override
	public Order selectorderdetail(Integer orderid) {
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Order order = orderMapper.selectByPrimaryKey(orderid);
		//格式化开始时间
		if(order.getStarttime() != null){
			order.setFormatstart(dateFormater.format(order.getStarttime()));
		}else{
			order.setFormatstart(null);
		}
		//格式化结束时间
		if(order.getOvertime() != null){
			order.setFormatover(dateFormater.format(order.getOvertime()));
		}else{
			order.setFormatover(null);
		}
		//格式化添加时间
		if(order.getAddtime() != null){
			order.setFormatadd(dateFormater.format(order.getAddtime()));
		}else{
			order.setFormatadd(null);
		}
		User user = userMapper.selectByPrimaryKey(order.getUserid());
		order.setUsername(user.getNickname());
		order.setAvatar(user.getAvatar());
		order.setUserphone(user.getPhone());
		return order;
	}

	/**
	 * 查询师傅信息
	 */
	@Override
	public Worker selectWorker(Integer workerid) {
		Worker worker = workerMapper.selectByPrimaryKey(workerid);
		return worker;
	}

	/**
	 * 查询用户已完成订单
	 */
	@Override
	public List<Order> doneorderbyuserid(Integer userid) {
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Order> list = orderMapper.selectdonebyuserid(userid);
		if(list != null && list.size()>0){
			for (Order order : list) {
				Integer workerid = order.getWorkerid();
				//查询师傅信息
				Worker worker = workerMapper.selectByPrimaryKey(workerid);
				order.setWorkname(worker.getNickname());
				order.setUserphone(worker.getPhone());
				//格式化开始时间
				if(order.getStarttime() != null){
					order.setFormatstart(dateFormater.format(order.getStarttime()));
				}else{
					order.setFormatstart(null);
				}
				//格式化结束时间
				if(order.getOvertime() != null){
					order.setFormatover(dateFormater.format(order.getOvertime()));
				}else{
					order.setFormatover(null);
				}
				//格式化添加时间
				if(order.getAddtime() != null){
					order.setFormatadd(dateFormater.format(order.getAddtime()));
				}else{
					order.setFormatadd(null);
				}
				order.setAvatar(worker.getAvatar());
				if(order.getCompanyid() == 0){
					//维修订单
					Type type = typeMapper.selectByPrimaryKey(order.getTypeid());
					if(type != null){
						order.setWorktypename(type.getName());
					}
				}else{
					//家政订单
					CompanyType companyType = companyTypeMapper.selectByPrimaryKey(order.getTypeid());
					if(companyType != null){
						order.setWorktypename(companyType.getName());
					}
				}
			}
		}
		return list;
	}

	/**
	 * 保存评论信息
	 */
	@Override
	public String saveAccess(Integer orderid, Integer grade, String content,String detail) {
		//保存评论信息
		Assess assess = new Assess();
		assess.setOrderid(orderid);
		assess.setGrade(grade);
		assess.setContent(content);
		assess.setDetail(detail);
		assess.setAddtime(new Date());
		int code = assessMapper.insertSelective(assess);
		String message;
		if(code == 1){
			message = "成功";
		}else{
			message = "失败";
			return message;
		}
		
		//更新订单状态
		Order order = orderMapper.selectByPrimaryKey(orderid);
		order.setType((short)4);
		int code1 = orderMapper.updateByPrimaryKeySelective(order);
		if(code1 == 1){
			message = "成功";
		}else{
			message = "失败";
			return message;
		}
		//计算师傅平均星级
		List<Order> orderlist = orderMapper.selectbyworkerid(order.getWorkerid());
		int sum = 0;
		if(orderlist != null && orderlist.size()>0){
			for (Order workorder : orderlist) {
				Assess workassess = assessMapper.selectByOrderid(workorder.getOrderid());
				if(workassess != null){
					sum = sum + workassess.getGrade();
				}
			}	
			int size =orderlist.size(); 		
			float average = (float)sum/(float)size;
			//更新师傅的平均星级信息
			Worker work = workerMapper.selectByPrimaryKey(order.getWorkerid());
			work.setGrade(average);
			int code2 = workerMapper.updateByPrimaryKeySelective(work);
			if(code2 == 1){
				message = "成功";
			}else{
				message = "失败";
				return message;
			}
		}
		return message;
	}

	/**
	 * 新增订单信息
	 */
	@Override
	public String insertorder(Integer userid,String address, String longitude, String latitude,String worktype) {
		Order order = new Order();
		order.setAddress(address);
		BigDecimal blatiude = new BigDecimal(latitude);
		order.setLatitude(blatiude);
		BigDecimal blongitude = new BigDecimal(longitude);
		order.setTypeid(CommonUtils.parseInt(worktype, 0));
		order.setLongitude(blongitude);
		order.setType((short)0);
		order.setUserid(userid);
		order.setStarttime(new Date());
		order.setAddtime(new Date());
		//生成订单号
		Calendar calendar = Calendar.getInstance();
		Integer year = calendar.get(Calendar.YEAR);
		String nowDay = calendar.get(Calendar.DAY_OF_YEAR) + "";
		if (nowDay.length() == 2) {
			nowDay = "0" + nowDay;
		}
		if (nowDay.length() == 1) {
			nowDay = "00" + nowDay;
		}
		Date date = new Date();
		String nowHour = date.getHours() + "";
		if (nowHour.length() == 1) {
			nowHour = "0" + nowHour;
		}
		// 当前小时时间内段内的订单数量
		String num = orderMapper.selectCountOrderNum() + "";
		if (CommonUtils.isEmptyString(num)) {
			num = "000";
		}
		if (num.length() == 1) {
			num = "00" + num;
		}
		if (num.length() == 2) {
			num = "0" + num;
		}
		String ordernum = "B" + year + nowDay + nowHour + num;
		order.setOrdernum(ordernum);
		int code = orderMapper.insertSelective(order);
		String message ;
		if(code == 1){
			message = "成功";
		}else{
			message = "操作失败";
		}
		return message;
	}

	
	/**
	 *查询订单评论信息 
	 */
	@Override
	public Assess selectbyorderid(Integer orderid) {
		Assess assess = assessMapper.selectByOrderid(orderid);
		return assess;
	}

	
	/**
	 * 取消订单
	 */
	@Override
	public String deleteorder(Integer orderid,String reason) {
		Order order = orderMapper.selectByPrimaryKey(orderid);
		String message = "取消失败!";
		if(order != null){
			short type = order.getType();
			if(type == 0 || type == 1){
				//可以取消的订单类型
				order.setDelflg((short)1);
				order.setReason(reason);
				int code = orderMapper.updateByPrimaryKeySelective(order);
				if(code == 1){
					message="成功";
				}else{
					message="取消失败!";
				}
			}else{
				//不可以取消的订单类型
				message = "此订单不可取消!";
			}
		}
		return message;
	}

	
	/**
	 * 分类查询师傅信息 
	 */
	@Override
	public List<AllGps> selectworkerbyworktype() {
		List<Worker> list = workerMapper.selectworkerbyworktype();
		List<AllGps> gpslist = new ArrayList<AllGps>();
		if(list != null && list.size()>0){
			for (Worker worker : list) {
				AllGps allgps = new AllGps();
				allgps.setWorktype(worker.getTypeid());
				//查询师傅经纬度信息
				WorkerGps gps = workerGpsMapper.selectByWorkerid(worker.getWorkerid());
				if(gps != null){
					allgps.setLongitude(gps.getLongitude());
					allgps.setLatitude(gps.getLatitude());
				}
				gpslist.add(allgps);
			}
		}
		return gpslist;
	}

	
	/**
	 * 查询用户最新信息
	 */
	@Override
	public Order newuserorder(Integer userid) {
		Order order = orderMapper.newuserorder(userid);
		return order;
	}

	
	/**
	 * 查询师傅经纬度
	 */
	@Override
	public WorkerGps selectWorkerGps(Integer workerid) {
		WorkerGps gps = workerGpsMapper.selectByWorkerid(workerid);
		return gps;
	}

	
	/**
	 * 余额支付
	 */
	@Override
	public String userpaytoworker(Integer orderid,String paypassword) {
		Order order = orderMapper.selectByPrimaryKey(orderid);
		Integer userid;
		String message;
		String str = null;
		String aaa = null;
		if(order != null){
			userid = order.getUserid();
			//查询用户信息
			User user = userMapper.selectByPrimaryKey(userid);
			if(user != null){
				if(paypassword.equals(user.getPaypassword())){
					//判断用户余额是否充足
					int a = user.getMoney().compareTo(order.getPrice());
					if(a >= 0){
						//获取订单金额从用户账户扣除，师傅账户增加,管理员账号增加
						BigDecimal pay = user.getMoney().subtract(order.getPrice());
						user.setMoney(pay);
						int code = userMapper.updateByPrimaryKeySelective(user);
						if(code == 1){
							Worker worker = workerMapper.selectByPrimaryKey(order.getWorkerid());
							str = order.getOrdernum().substring(0,1);
							 Checkout checkout = new Checkout();
				              if ("B".equals(str)){
				                checkout = checkoutMapper.selectByType(Integer.valueOf(2));
				              } else if ("A".equals(str)){
				                checkout = checkoutMapper.selectByType(Integer.valueOf(1));
				              }
							BigDecimal b = new BigDecimal(1+(checkout.getNumber()/100));
							//原有收入加上现在的收入
							BigDecimal pay1 = worker.getMoney().add(order.getPrice().divide(b,2,RoundingMode.HALF_UP));
							//现在的收入
							BigDecimal tostr = order.getPrice().divide(b,2,RoundingMode.HALF_UP);
							aaa = tostr.toString();
							worker.setMoney(pay1);
							int code1 = workerMapper.updateByPrimaryKeySelective(worker);
							if(code1 == 1){
								//收取手续费
								User admin = userMapper.selectByPhone("00000000000");
								BigDecimal pay2 = admin.getMoney().add(order.getPrice().subtract(order.getPrice().divide(b,2,RoundingMode.HALF_UP)));
								admin.setMoney(pay2);
								int code2 = userMapper.updateByPrimaryKeySelective(admin);
								if(code2 == 1){
									//更新订单状态
									order.setType((short)3);
									int code3 = orderMapper.updateByPrimaryKeySelective(order);
									if(code3 == 1){
										message = "成功";
									}else{
										message = "支付失败";
									}
								}else{
									message = "支付失败";
								}
							}else{
								message = "支付失败";
							}
						}else{
							message = "支付失败";
						}
					}else{
						message = "余额不足";
					}	
				}else{
					message = "支付密码错误";
				}
			}else{
				message = "订单有误";
			}
		}else{
			message = "订单有误";
		}
		//添加师傅，用户流水记录
		if("成功".equals(message)){
			RechargeSet usermoney = new RechargeSet();
			usermoney.setUserid(order.getUserid());
			usermoney.setMoney(order.getPrice());
			usermoney.setDetail("维修支出");
			usermoney.setType((short)0);
			usermoney.setScore(0);
			usermoney.setDelstate((short)0);
			usermoney.setAddtime(new Date());
			int code = rechargeSetMapper.insertSelective(usermoney);
			if(code == 1){
				RechargeWorker workmoney = new RechargeWorker();
				workmoney.setWorkerid(order.getWorkerid());
				workmoney.setMoney(new BigDecimal(aaa));
				workmoney.setDetail("收入");
				workmoney.setType((short)0);
				workmoney.setScore(0);
				workmoney.setDelstate((short)0);
				workmoney.setAddtime(new Date());
				int code1 = rechargeWorkerMapper.insertSelective(workmoney);
				if(code1 == 1){
					message = "成功";
				}else{
					message = "支付失败";
				}
			}else{
				message = "支付失败";
			}
		}
		return message;
	}

	
	/**
	 * 保存录音
	 */
	@Override
	public String savevoice(Integer orderid,String path) {
		Order order = orderMapper.selectByPrimaryKey(orderid);
		String message;
		if(order != null){
			order.setVoice(path);
			int code = orderMapper.updateByPrimaryKeySelective(order);
			if(code == 1){
				message = "成功";
			}else{
				message = "上传失败";
			}
		}else{
			message = "上传失败";
		}
		return message;
	}
	
	
	/**
	 * 根据订单号查询
	 */
	@Override
	public Order searchbyordernum(String ordernum) {
		Order order = orderMapper.selectbyordernum(ordernum);
		return order;
	}

	/**
	 * 查询用户余额
	 */
	@Override
	public User searchusermoney(Integer userid) {
		User user = userMapper.selectByPrimaryKey(userid);
		return user;
	}
}
