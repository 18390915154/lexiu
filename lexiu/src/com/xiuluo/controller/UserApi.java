package com.xiuluo.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;
import com.xiuluo.model.aboutUs.HomeBanner;
import com.xiuluo.model.aboutUs.Order;
import com.xiuluo.model.aboutUs.User;
import com.xiuluo.model.aboutUs.Worker;
import com.xiuluo.service.aboutUs.UserService;
import com.xiuluo.util.CommonUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/user")
public class UserApi {
	
	@Resource
	private UserService userService;
	
	/**
	 * 阿里发送短信验证码
	 * @param response
	 * @param request
	 */
//	@RequestMapping("sendvcode")
//	public void sendvcode(HttpServletRequest request,
//			HttpServletResponse response){
//		try {
//	        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI6bzpbyh9w0QN", "5AeYGa5DQQE8eKUESfyy91KJTEs5pv");
//	        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms",  "sms.aliyuncs.com");
//	        IAcsClient client = new DefaultAcsClient(profile);
//	        SingleSendSmsRequest smsrequest = new SingleSendSmsRequest();
//            smsrequest.setSignName("测试账号");//控制台创建的签名名称
//    		smsrequest.setTemplateCode("SMS_52135396");//控制台创建的模板CODE
//    		String vcode = CommonUtils.getRandomVcode();
//    		String param = "{\"number\":\""+vcode+"\"}";
//    		System.out.println(request.getSession().getId());
//    		request.getSession().setAttribute("vcode", vcode);
//    		request.getSession().setAttribute("vcodetime", new Date());
//    		smsrequest.setParamString(param);
//    		String phone = request.getParameter("phone");
//            smsrequest.setRecNum(phone);//接收号码
//            SingleSendSmsResponse httpResponse = client.getAcsResponse(smsrequest);
//            response.setCharacterEncoding("utf-8");
//			} catch (ServerException e) {
//	            e.printStackTrace();
//	        }
//	        catch (ClientException e) {
//	            e.printStackTrace();
//	        }
//	  }
	
	
	/**
	 * 融合通信发送短信验证码
	 * @param response
	 * @param request
	 */
	@RequestMapping("sendvcode")
	public void sendvcode(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try {
			String phone = request.getParameter("phone");
			JSONObject json = new JSONObject();// 所要返回的json值 
			if(CommonUtils.isEmptyString(phone)){
				String message = "缺少接口参数";
				json.put("message", message);
				response.getWriter().print(json.toString());
				return;
			}
			String userid = "lerlex";
			String pass = "leren01";
			String vcode = CommonUtils.getRandomVcode();
			String msg ="【乐修】尊敬的用户,您本次的验证码为:"+vcode+",验证码仅可使用一次,"
					+ "有效期为30分钟,请尽快完成使用。";
			//存入session
			String message = userService.sendSms(userid, pass, phone, msg, null);
			request.getSession().setAttribute("vcode", vcode);
			request.getSession().setAttribute("vcodetime", new Date());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 */
	@RequestMapping("login")
	public void login(HttpServletRequest request,
			HttpServletResponse response){
		try {
			String callback = request.getParameter("jsonpcallback");
		    String phone = request.getParameter("phone");
			String password = request.getParameter("password");
			String type = request.getParameter("type");
			response.setCharacterEncoding("utf-8");
			if(CommonUtils.isEmptyString(phone)||
					CommonUtils.isEmptyString(password)||
					CommonUtils.isEmptyString(type)){
				String message = "缺少接口参数";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String message;
			String nickname = null;
			Integer workerid=null;
			Order order = new Order();
			//判断用户端登录还是师傅登录
			if(type.equals("0")){
				//用户端登录
				//验证用户信息	
				message = userService.userlogin(phone, password);
				if("成功".equals(message)){
					//查询用户信息
					User user = userService.selectuserbyphone(phone);
					nickname = user.getNickname();
					workerid = user.getUserid();
					request.getSession().setAttribute("userid", user.getUserid());
					//查询用户最新一条订单
					order = userService.searchneworder(user.getUserid());
				}
			}else{
				//师傅段登录
				message = userService.worklogin(phone, password);
				if("成功".equals(message)){
					//查询师傅信息
					Worker worker = userService.selectworkbyphone(phone);
					nickname = worker.getNickname();
					workerid = worker.getWorkerid();
					request.getSession().setAttribute("workerid", worker.getWorkerid());
				}
			}
			JSONObject json = new JSONObject();			
			if(order != null){
				json = CommonUtils.setobjecttojson(order);
			}
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'},{ name:'user',value:'"+nickname+"'}"
					+ ",{ name:'workerid',value:'"+workerid+"'},{ name:'worker',value:'"+nickname+"'},{ name:'order',value:'"+json+"'} ]);");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 */
	@RequestMapping("userRegister")
	public void userRegister(HttpServletRequest request,
			HttpServletResponse response){
		try {
			String callback = request.getParameter("jsonpcallback");
			String phone = request.getParameter("phone");
			String vcode = request.getParameter("vcode");
			String password = request.getParameter("password");
			String type = request.getParameter("type");
			response.setCharacterEncoding("utf-8");
			if(CommonUtils.isEmptyString(phone)||
					CommonUtils.isEmptyString(vcode)||
					CommonUtils.isEmptyString(password)||
					CommonUtils.isEmptyString(type)){
				String message = "缺少接口参数";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			//判断验证码是否过期
			System.out.println(request.getSession().getId());
			if(request.getSession().getAttribute("vcode")==null || request.getSession().getAttribute("vcodetime")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String sessionvcode = request.getSession().getAttribute("vcode").toString();
			Date vcodetime = (Date)request.getSession().getAttribute("vcodetime");
			if(CommonUtils.timeoutvcode(vcodetime)){
				String message = "验证码已经过期,请重新获取!";	
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}else if(!vcode.equals(sessionvcode)){
				String message = "验证码错误！";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String message;
			Integer workerid = null;
			if(type.equals("0")){
				//用户端查询注册信息	
				message = userService.adduser(phone, password);
				if(("成功").equals(message)){
					//查询用户信息
					User user = userService.selectuserbyphone(phone);
					request.getSession().setAttribute("userid", user.getUserid());
				}
			}else{
				//师傅端查询注册信息	
				message = userService.addwork(phone, password);
				if(("成功").equals(message)){
					//插入师傅经纬度信息
					String message1 = userService.insertworkergps(phone);
					System.out.println(message1);
					//查询师傅信息
					Worker worker = userService.selectworkbyphone(phone);
					workerid = worker.getWorkerid();
					request.getSession().setAttribute("workerid", worker.getWorkerid());
				}
			}
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'},{ name:'workerid',value:'"+workerid+"'}] );");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 忘记密码
	 * @param request
	 * @param response
	 */
	@RequestMapping("forgetPassword")
	public void forgetPassword(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try {
			String phone = request.getParameter("phone");
			String vcode = request.getParameter("vcode");
			String password = request.getParameter("password");
			String type = request.getParameter("type");
			String callback = request.getParameter("jsonpcallback");
			response.setCharacterEncoding("utf-8");
			if(CommonUtils.isEmptyString(phone)||
					CommonUtils.isEmptyString(vcode)||
					CommonUtils.isEmptyString(password)||
					CommonUtils.isEmptyString(type)){
				String message = "缺少接口参数";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			//判断验证码是否过期
			if(request.getSession().getAttribute("vcode")==null || request.getSession().getAttribute("vcodetime") == null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String sessionvcode = request.getSession().getAttribute("vcode").toString();
			Date vcodetime = (Date)request.getSession().getAttribute("vcodetime");
			if(CommonUtils.timeoutvcode(vcodetime)){
				String message = "验证码已经过期,请重新获取!";	
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}else if(!vcode.equals(sessionvcode)){
				String message = "验证码错误！";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String message;
			if(type.equals("0")){
				//查询用户注册信息	
				message = userService.updateuserpassword(phone, password);
			}else{
				//查询师傅注册信息	
				message = userService.updateworkerpassword(phone, password);
			}
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 */
	@RequestMapping("changePassword")
	public void changePassword(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try {
			String phone = request.getParameter("phone");
			String vcode = request.getParameter("vcode");
			String password = request.getParameter("password");
			String type = request.getParameter("type");
			String callback = request.getParameter("jsonpcallback");
			response.setCharacterEncoding("utf-8");
			if(CommonUtils.isEmptyString(phone)||
					CommonUtils.isEmptyString(vcode)||
					CommonUtils.isEmptyString(password)||
					CommonUtils.isEmptyString(type)){
				String message = "缺少接口参数";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			//判断验证码是否过期
			if(request.getSession().getAttribute("vcode")==null || request.getSession().getAttribute("vcodetime") == null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String sessionvcode = request.getSession().getAttribute("vcode").toString();
			Date vcodetime = (Date)request.getSession().getAttribute("vcodetime");
			if(CommonUtils.timeoutvcode(vcodetime)){
				String message = "验证码已经过期,请重新获取!";	
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}else if(!vcode.equals(sessionvcode)){
				String message = "验证码错误！";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String message;
			if(type.equals("0")){
				//查询用户注册信息	
				message = userService.updateuserpassword(phone, password);
			}else{
				//查询师傅注册信息	
				message = userService.updateworkerpassword(phone, password);
			}
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 修改常用地址
	 * @param request
	 * @param response
	 */
	@RequestMapping("editaddress")
	public void editaddress(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try {
			String callback = request.getParameter("jsonpcallback");
			String address = request.getParameter("address");
			response.setCharacterEncoding("utf-8");
			//获取用户ID
			if(request.getSession().getAttribute("userid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String userid = request.getSession().getAttribute("userid").toString();
			//修改用户地址
			String message = userService.updateuseraddress(CommonUtils.parseInt(userid, 0), address);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询常用地址
	 * @param request
	 * @param response
	 */
	@RequestMapping("selectaddress")
	public void selectaddress(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try {
			String callback = request.getParameter("jsonpcallback");
			response.setCharacterEncoding("utf-8");
			//获取用户ID
			if(request.getSession().getAttribute("userid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String userid = request.getSession().getAttribute("userid").toString();
			//查询用户地址
			String message = null;
			String address = userService.selectaddress(CommonUtils.parseInt(userid, 0));
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'},{ name:'address',value:'"+address+"'}] );");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询历史地址
	 * @param request
	 * @param response
	 */
	@RequestMapping("historyaddress")
	public void historyaddress(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try {
			String callback = request.getParameter("jsonpcallback");
			response.setCharacterEncoding("utf-8");
			//获取用户ID
			if(request.getSession().getAttribute("userid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String userid = request.getSession().getAttribute("userid").toString();
			//修改用户历史收货地址
			String message = null;
			List<Order> addresslist = userService.historyaddress(CommonUtils.parseInt(userid, 0));
			JSONArray json = CommonUtils.setlisttojson(addresslist);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'},{ name:'addresslist',value:'"+json+"'}] );");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 修改支付密码
	 * @param request
	 * @param response
	 */
	@RequestMapping("editpaypassword")
	public void editpaypassword(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try {
			String phone = request.getParameter("phone");
			String vcode = request.getParameter("vcode");
			String password = request.getParameter("password");
			String callback = request.getParameter("jsonpcallback");
			response.setCharacterEncoding("utf-8");
			if(CommonUtils.isEmptyString(phone)||
					CommonUtils.isEmptyString(vcode)||
					CommonUtils.isEmptyString(password)){
				String message = "缺少接口参数";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			//判断验证码是否过期
			if(request.getSession().getAttribute("vcode")==null || request.getSession().getAttribute("vcodetime") == null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String sessionvcode = request.getSession().getAttribute("vcode").toString();
			Date vcodetime = (Date)request.getSession().getAttribute("vcodetime");
			if(CommonUtils.timeoutvcode(vcodetime)){
				String message = "验证码已经过期,请重新获取!";	
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}else if(!vcode.equals(sessionvcode)){
				String message = "验证码错误！";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String message;
			//查询用户注册信息	
			message = userService.updatepaypassword(phone, password);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取首页APP轮播图
	 * @param request
	 * @param response
	 */
	@RequestMapping("homebannerlist")
	public void homebannerlist(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try {
			response.setCharacterEncoding("utf-8");
			//修改用户历史收货地址
			String message = null;
			List<HomeBanner> list = userService.homebannerlist();
			JSONObject json = new JSONObject();
			json.put("message", message);
			json.put("bannerlist", list);
			response.getWriter().print(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
