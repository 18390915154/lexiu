package com.xiuluo.controller.manage;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiuluo.model.aboutUs.Assess;
import com.xiuluo.model.aboutUs.Order;
import com.xiuluo.service.manage.OrderServiceManage;
import com.xiuluo.util.CommonUtils;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("manage/order")
public class OrderApiManage {
	
	@Resource
	private OrderServiceManage orderServiceManage;
	
	/**
	 * 查询所有订单信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("seachorder")
	public void seachorder(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//查询所有订单信息
			List<Order> list = orderServiceManage.seachorder();
			json.put("orderlist", list);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询订单详情
	 * @param request
	 * @param response
	 */
	@RequestMapping("orderdetail")
	public void orderdetail(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String ordernum = request.getParameter("ordernum");
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//查询订单详情
			Order order = orderServiceManage.selectorderdetail(ordernum);
			json.put("order", order);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 编辑订单
	 * @param request
	 * @param response
	 */
	@RequestMapping("updateorder")
	public void updateorder(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String ordernum = request.getParameter("ordernum");
			String price = request.getParameter("price");
			String worktype = request.getParameter("worktype");
			String content = request.getParameter("content");
			String fault = request.getParameter("fault");
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//查询订单详情
			String message = orderServiceManage.updateorder(ordernum, price, worktype, content, fault);
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除订单
	 * @param request
	 * @param response
	 */
	@RequestMapping("delorder")
	public void delorder(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String ordernum = request.getParameter("ordernum");
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//删除订单详情
			String message = orderServiceManage.delectorder(ordernum);
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询订单评论信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("searchassess")
	public void searchassess(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String ordernum = request.getParameter("ordernum");
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//查询订单评论信息
			Assess assess = orderServiceManage.searchassess(ordernum);
			json.put("assess", assess);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
