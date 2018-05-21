package com.xiuluo.service.aboutUs;

import java.util.List;

import com.xiuluo.model.AllGps;
import com.xiuluo.model.aboutUs.Assess;
import com.xiuluo.model.aboutUs.Order;
import com.xiuluo.model.aboutUs.User;
import com.xiuluo.model.aboutUs.Worker;
import com.xiuluo.model.aboutUs.WorkerGps;

public interface OrderService {
	public List<Order> undoneorderbyuserid(Integer userid);//查询用户未完成订单
	
	public List<Order> doneorderbyuserid(Integer userid);//查询用户未完成订单
	
	public Order selectorderdetail(Integer orderid);//查询订单详情
	
	public Worker selectWorker(Integer workerid);//查询师傅信息
	
	public String saveAccess(Integer orderid,Integer grade,String content,String detail);//保存评论信息
	
	public String insertorder(Integer userid,String address,String longitude,String latitude,String worktype);
	
	public Assess selectbyorderid(Integer orderid);
	
	public String deleteorder(Integer orderid,String reason);
	
	public List<AllGps> selectworkerbyworktype();
	
	public WorkerGps selectWorkerGps(Integer workerid);
	
	public Order newuserorder(Integer userid);//查询用户最新订单信息
	
	public String userpaytoworker(Integer orderid,String paypassword);
	
	public String savevoice(Integer orderid,String path);
	
	public Order searchbyordernum(String ordernum);
	
	public User searchusermoney(Integer userid);
}
