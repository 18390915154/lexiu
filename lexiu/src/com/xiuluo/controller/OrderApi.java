package com.xiuluo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xiuluo.model.AllGps;
import com.xiuluo.model.aboutUs.Assess;
import com.xiuluo.model.aboutUs.Order;
import com.xiuluo.model.aboutUs.User;
import com.xiuluo.model.aboutUs.Worker;
import com.xiuluo.model.aboutUs.WorkerGps;
import com.xiuluo.service.aboutUs.OrderService;
import com.xiuluo.util.CommonUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("api/order")
public class OrderApi {
	
	@Resource
	private OrderService orderService;
	
	
	/**
	 * 查询所有待完成订单
	 * @param request
	 * @param response
	 */
	@RequestMapping("undoneorder")
	public void undoneorder(HttpServletRequest request,
			HttpServletResponse response){
		String callback = request.getParameter("jsonpcallback");
		response.setCharacterEncoding("utf-8");
		try {
			//获取用户ID
			if(request.getSession().getAttribute("userid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String userid = request.getSession().getAttribute("userid").toString();
			String message = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//查询待完成订单
			List<Order> orderlist = orderService.undoneorderbyuserid(CommonUtils.parseInt(userid, 0));
			JSONArray json = CommonUtils.setlisttojson(orderlist);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'},{ name:'orderlist',value:'"+json+"'}] );");
		} catch (IOException e) {
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
		String orderid = request.getParameter("orderid");
		JSONObject json = new JSONObject();// 所要返回的json值
		response.setCharacterEncoding("utf-8");
		try {
			if(CommonUtils.isEmptyString(orderid)){
				String message = "缺少接口参数!";
			    json.put("message", message);
			    response.getWriter().print(json.toString());
			    return;
			}
			//查询订单详情
			Order order = orderService.selectorderdetail(CommonUtils.parseInt(orderid, 0));
			if(order != null){
				Integer workerid = order.getWorkerid();
				Worker worker = orderService.selectWorker(workerid);
				json.put("worker", worker);
				//查询评论信息
				Assess assess = orderService.selectbyorderid(order.getOrderid());
				json.put("assess", assess);
				//查询师傅经纬度
				WorkerGps gps = orderService.selectWorkerGps(workerid);
				json.put("gps", gps);
				//查询用户余额
				User user = orderService.searchusermoney(order.getUserid());
				json.put("money", user.getMoney());
			}
			json.put("order", order);
		    response.getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询所有已完成订单
	 * @param request
	 * @param response
	 */
	@RequestMapping("doneorder")
	public void doneorder(HttpServletRequest request,
			HttpServletResponse response){
		String callback = request.getParameter("jsonpcallback");
		response.setCharacterEncoding("utf-8");
		try {
			//获取用户ID
			if(request.getSession().getAttribute("userid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String userid = request.getSession().getAttribute("userid").toString();
			String message = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//查询已完成订单
			List<Order> orderlist = orderService.doneorderbyuserid(CommonUtils.parseInt(userid, 0));
			JSONArray json = CommonUtils.setlisttojson(orderlist);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'},{ name:'orderlist',value:'"+json+"'}] );");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 提交评价
	 * @param request
	 * @param response
	 */
	@RequestMapping("savecomment")
	public void savecomment(HttpServletRequest request,
			HttpServletResponse response){
		String callback = request.getParameter("jsonpcallback");
		String orderid = request.getParameter("orderid");
		String grade = request.getParameter("grade");
		String content = request.getParameter("content");
		String detail = request.getParameter("detail");
		JSONObject json = new JSONObject();// 所要返回的json值
		response.setCharacterEncoding("utf-8");
		try {
			if(CommonUtils.isEmptyString(orderid)||
					CommonUtils.isEmptyString(grade)){
				String message = "缺少接口参数!";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
			    return;
			}
			//获取用户ID
			if(request.getSession().getAttribute("userid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			
			//处理字符串
			StringBuffer sb = new StringBuffer(content);
			for (int i = 0; i < sb.length()-4; i+=5) {
				sb.insert(i+4, ",");
			}
			String str = new String(sb);
			
			String userid = request.getSession().getAttribute("userid").toString();
			String message = null;
			//保存评价信息更新订单及师傅信息
			message = orderService.saveAccess(CommonUtils.parseInt(orderid, 0),
					CommonUtils.parseInt(grade, 0),str,detail);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 新增订单
	 * @param request
	 * @param response
	 */
	@RequestMapping("createorder")
	public void createorder(HttpServletRequest request,
			HttpServletResponse response){
		String callback = request.getParameter("jsonpcallback");
		String address = request.getParameter("address");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		String worktype = request.getParameter("worktype");
		response.setCharacterEncoding("utf-8");
		try {
			if(CommonUtils.isEmptyString(address)||
					CommonUtils.isEmptyString(latitude)||
					CommonUtils.isEmptyString(longitude)||
					CommonUtils.isEmptyString("worktype")){
				String message = "缺少接口参数!";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
			    return;
			}
			//获取用户ID
			if(request.getSession().getAttribute("userid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String userid = request.getSession().getAttribute("userid").toString();
			//新增订单
			String message;
			message = orderService.insertorder(CommonUtils.parseInt(userid, 0), 
					address, longitude, latitude,worktype);
			Order order = new Order();
			if(("成功").equals(message)){
				//返回用户最新待完成评价信息
				order = orderService.newuserorder(CommonUtils.parseInt(userid, 0));
			}
			JSONObject json = CommonUtils.setobjecttojson(order);	
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'},{ name:'orderlist',value:'"+json+"'}] );");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 取消订单
	 * @param request
	 * @param response
	 */
	@RequestMapping("cancelorder")
	public void cancelorder(HttpServletRequest request,
			HttpServletResponse response){
		String orderid = request.getParameter("orderid");
		String reason = request.getParameter("reason");
		JSONObject json = new JSONObject();// 所要返回的son值
		response.setCharacterEncoding("utf-8");
		try {
			if(CommonUtils.isEmptyString(orderid)||
					CommonUtils.isEmptyString(reason)){
				String message = "缺少接口参数!";
			    json.put("message", message);
			    response.getWriter().print(json.toString());
			    return;
			}
			//查询订单并取消
			String message = orderService.deleteorder(CommonUtils.parseInt(orderid, 0),reason);
			json.put("message", message);
		    response.getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 分类查询师傅信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("workerbytype")
	public void workerbytype(HttpServletRequest request,
			HttpServletResponse response){
		String callback = request.getParameter("jsonpcallback");
		response.setCharacterEncoding("utf-8");
		try {
			String message = null;
			List<AllGps> workerlist = orderService.selectworkerbyworktype();
			JSONArray json = CommonUtils.setlisttojson(workerlist);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'},{ name:'workerlist',value:'"+json+"'}] );");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 账户余额支付
	 * @param request
	 * @param response
	 */
	@RequestMapping("balancepay")
	public void balancepay(HttpServletRequest request,
			HttpServletResponse response){
		String callback = request.getParameter("jsonpcallback");
		String orderid = request.getParameter("orderid");
		String paypassword = request.getParameter("paypassword");
		response.setCharacterEncoding("utf-8");
		String message = null;
		try {
			if(CommonUtils.isEmptyString(orderid)||
					CommonUtils.isEmptyString(paypassword)){
				message = "缺少接口参数!";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
			    return;
			}
			message = orderService.userpaytoworker(CommonUtils.parseInt(orderid, 0), paypassword);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 上传录音
	 * @param request
	 * @param response
	 */
	@RequestMapping("onloadvoice")
	public void onloadvoice(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="file",required=false) MultipartFile file){
		try{
			String orderid = request.getParameter("orderid");
			response.setCharacterEncoding("UTF-8");
			JSONObject json = new JSONObject();
			if(CommonUtils.isEmptyString(orderid)){
				String message = "缺少接口参数";
				json.put("message", message);
				response.getWriter().write(json.toString());
				return;
			}
			String path;
			String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
            //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, file.getOriginalFilename())); 
            String filename = file.getOriginalFilename();
            path = "lexiu/upload/"+filename;
			
			String message = orderService.savevoice(CommonUtils.parseInt(orderid, 0), path);
			json.put("message", message);
			response.getWriter().write(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
