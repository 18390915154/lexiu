package com.xiuluo.service.aboutUs.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xiuluo.model.aboutUs.Order;
import com.xiuluo.model.aboutUs.User;
import com.xiuluo.model.aboutUs.Worker;
import com.xiuluo.service.aboutUs.PayService;
import com.xiuluo.util.AllMapper;
import com.xiuluo.util.CommonUtils;

@Service("payService")
public class PayServiceImpl extends AllMapper implements PayService{

	@Override
	public String updateorder(Order order) {
		int code = orderMapper.updateByPrimaryKeySelective(order);
		String message;
		if(code == 1){
			message = "更新成功";
		}else{
			message = "更新失败";
		}
		return message;
	}

	
	@Override
	public User user(Integer userid) {
		User user = userMapper.selectByPrimaryKey(userid);
		return user;
	}


	@Override
	public Worker worker(Integer workerid) {
		Worker worker = workerMapper.selectByPrimaryKey(workerid);
		return worker;
	}

	
	@Override
	public Order wokerorder(Integer workerid) {
		Order order = orderMapper.selectworkerfirstorder(workerid);
		return order;
	}


	@Override
	public Order userorder(Integer userid) {
		Order order = orderMapper.selectuserfirstorder(userid);
		return order;
	}


	@Override
	public Map<String,String> insertorder(Integer id, Integer type, double money) {
		Order order = new Order();
		order.setAddress("充值");
		BigDecimal blatiude = new BigDecimal(0.00);
		order.setLatitude(blatiude);
		BigDecimal blongitude = new BigDecimal(0.00);
		order.setTypeid(0);
		order.setLongitude(blongitude);
		order.setType((short)9);
		if(type == 0){
			//用户
			order.setUserid(id);
		}else{
			//师傅
			order.setWorkerid(id);
		}
		BigDecimal price = new BigDecimal(money);
		order.setPrice(price);
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
		String ordernum = "C" + year + nowDay + nowHour + num;
		order.setOrdernum(ordernum);
		int code = orderMapper.insertSelective(order);
		String message ;
		if(code == 1){
			message = "成功";
		}else{
			message = "操作失败";
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("message", message);
		map.put("money", String.valueOf(money));
		map.put("ordernum", ordernum);
		return map;
	}

}
