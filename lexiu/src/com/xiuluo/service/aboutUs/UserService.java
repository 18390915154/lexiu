package com.xiuluo.service.aboutUs;

import java.util.List;
import com.xiuluo.model.aboutUs.HomeBanner;
import com.xiuluo.model.aboutUs.Order;
import com.xiuluo.model.aboutUs.User;
import com.xiuluo.model.aboutUs.Worker;

public interface UserService {
	public String userlogin(String phone,String password);
	
	public String worklogin(String phone,String password);
	
	public String adduser(String phone,String password);
	
	public String addwork(String phone,String password);
	
	public String updateuserpassword(String phone,String password);
	
	public String updateworkerpassword(String phone,String password);
	
	public List<HomeBanner> homebannerlist();
	
	public String updateuseraddress(Integer userid,String address);
	
	public String selectaddress(Integer userid);//查询用户常用地址
	
	public List<Order> historyaddress(Integer userid);//查询历史常用地址
	
	public User selectuserbyphone(String phone);//通过手机查询用户信息
	
	public Worker selectworkbyphone(String phone);
	
	public String sendSms(String userid,String pass,String mobiles,String msg,String time);
	
	public String updatepaypassword(String phone , String password);
	
	public String insertworkergps(String phone);//插入一条师傅经纬度信息
	
	public Order searchneworder(Integer userid);//查询最新一条订单信息
}
