package com.xiuluo.service.aboutUs;

import java.util.Map;

import com.xiuluo.model.aboutUs.Order;
import com.xiuluo.model.aboutUs.User;
import com.xiuluo.model.aboutUs.Worker;

public interface PayService {
	public String updateorder(Order order);
	
	public User user(Integer userid);
	
	public Worker worker(Integer workerid);
	
	public Order wokerorder(Integer workerid);
	
	public Order userorder(Integer userid);
	
	public Map<String,String> insertorder(Integer id,Integer type,double money);
}
