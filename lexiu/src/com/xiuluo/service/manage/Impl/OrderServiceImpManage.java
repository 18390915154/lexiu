package com.xiuluo.service.manage.Impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.xiuluo.model.aboutUs.Assess;
import com.xiuluo.model.aboutUs.CompanyType;
import com.xiuluo.model.aboutUs.Order;
import com.xiuluo.model.aboutUs.Type;
import com.xiuluo.model.aboutUs.User;
import com.xiuluo.model.aboutUs.Worker;
import com.xiuluo.service.manage.OrderServiceManage;
import com.xiuluo.util.AllMapper;
import com.xiuluo.util.CommonUtils;

@Service("orderServiceManage")
public class OrderServiceImpManage extends AllMapper implements OrderServiceManage {

	/**
	 * 查询所有订单信息
	 */
	@Override
	public List<Order> seachorder() {
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Order> list = orderMapper.selectall();
		if(list != null && list.size()>0){
			for (Order order : list) {
				User user = userMapper.selectByPrimaryKey(order.getUserid());
				if(user!= null){
					order.setUsername(user.getNickname());
				}
				Worker worker = workerMapper.selectByPrimaryKey(order.getWorkerid());
				if(worker != null){
					order.setWorkname(worker.getNickname());
				}
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
				case 4:
					order.setTypename("已完成订单");
					break;
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
				//判断订单类型，获取订单类型
				if("A".endsWith(order.getOrdernum().substring(0, 1))){
					//家政订单
					CompanyType companytype = companyTypeMapper.selectByPrimaryKey(order.getTypeid());
					if(companytype != null){
						order.setWorktypename(companytype.getName());
					}
				}else if("B".endsWith(order.getOrdernum().substring(0, 1))){
					//维修订单
					Type type = typeMapper.selectByPrimaryKey(order.getTypeid());
					if(type != null){
						order.setWorktypename(type.getName());
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
	public Order selectorderdetail(String ordernum) {
		Order order = orderMapper.selectbyordernum(ordernum);
		return order;
	}

	
	/**
	 *编辑订单信息 
	 */
	@Override
	public String updateorder(String ordernum, String price, String worktype, String content,
			String fault) {
		Order order = orderMapper.selectbyordernum(ordernum);
		String message = null;
		try{
			if(order != null){
				BigDecimal bd = new BigDecimal(price);
				order.setPrice(bd);
				order.setTypeid(CommonUtils.parseInt(worktype, 0));
				order.setContent(content);
				order.setFault(fault);
				int code = orderMapper.updateByPrimaryKeySelective(order);
				if(code == 1){
					message = "成功";
				}else{
					message = "修改失败";
				}
			}else{
				message = "修改失败";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return message;
	}

	
	/**
	 *删除订单信息 
	 */
	@Override
	public String delectorder(String ordernum) {
		int code = orderMapper.deleteByOrdernum(ordernum);
		String message;
		if(code == 1){
			message = "成功";
		}else{
			message= "取消失败";
		}
		return message;
	}

	/**
	 * 查询订单评论信息
	 */
	@Override
	public Assess searchassess(String ordernum) {
		Order order = orderMapper.selectbyordernum(ordernum);
		Assess assess = null;
		if(order != null){
			assess = assessMapper.selectByOrderid(order.getOrderid());
		}
		return assess;
	}

}
