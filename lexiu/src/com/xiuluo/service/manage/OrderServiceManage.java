package com.xiuluo.service.manage;

import java.util.List;

import com.xiuluo.model.aboutUs.Assess;
import com.xiuluo.model.aboutUs.Order;

public interface OrderServiceManage {
	public List<Order> seachorder();
	
	public Order selectorderdetail(String ordernum);
	
	public String updateorder(String ordernum,String price,String worktype,String content,String fault);

	public String delectorder(String ordernum);
	
	public Assess searchassess(String ordernum);
}
