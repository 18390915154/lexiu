package com.xiuluo.service.aboutUs;

import java.util.List;
import com.xiuluo.model.MineAssess;
import com.xiuluo.model.aboutUs.Order;
import com.xiuluo.model.aboutUs.RechargeWorker;
import com.xiuluo.model.aboutUs.Worker;
import com.xiuluo.model.aboutUs.WorkerBankcard;

public interface WorkerService {
	public String insertorder(Integer orderid,String content,String fault,String price);//师傅报结订单
	
	public String updatework(Integer workerid,String nickname,String birthday,Integer age,String sex,
			String phone,String contacttel,String companies,String companiestel,
			String skill , String idcard , String worktype,List<String> paths);//完善师傅信息
	
	public String addbankcard(Integer workerid,String cardnumber ,String bankname,String name,
			String phone);//添加师傅银行卡信息
	
	public List<WorkerBankcard> selectmybank(Integer workerid);//查询我的银行卡

	public String delectmybank(Integer cardid);//删除我的银行卡
	
	public Worker selectbyWorkerid(Integer workerid);
	
	public List<RechargeWorker> workerwalletlist(Integer workerid);//查询师傅流水

	public List<Order> selectAcceptOrder(Integer workerid);//查询所有可接订单
	
	public String insertworkertoorder(Integer orderid,Integer workerid);//将师傅信息插入订单中

	public List<Order> undoneorder(Integer workerid);//查询所有待完成订单
	
	public List<Order> doneorder(Integer workerid);//查询所有完成订单
	
	public String updatepaypassword(String phone , String password);
	
	public List<MineAssess> selectassessbyworkerid(Integer workerid);//查询师傅评价
	
	public String updateworkgps(Integer workerid,String longitude,String latitude);//更新师傅GPS定位
	
	public List<Order> searchpayorder(Integer workerid);//查询支付过的订单
}
