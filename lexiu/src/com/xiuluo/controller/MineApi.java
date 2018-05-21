package com.xiuluo.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xiuluo.model.MineAssess;
import com.xiuluo.model.aboutUs.Assess;
import com.xiuluo.model.aboutUs.RechargeSet;
import com.xiuluo.model.aboutUs.User;
import com.xiuluo.model.aboutUs.UserBankcard;
import com.xiuluo.model.aboutUs.Worker;
import com.xiuluo.service.aboutUs.MineService;
import com.xiuluo.util.CommonUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("api/mine")
public class MineApi {
	
	@Resource
	private MineService mineService;
	
	
	/**
	 * 我的钱包
	 * @param response
	 * @param request
	 */
	@RequestMapping("mywallet")
	public void mywallet(HttpServletRequest request,HttpServletResponse response){
		try{
			
			String callback = request.getParameter("jsonpcallback");
			response.setCharacterEncoding("utf-8");
			if(request.getSession().getAttribute("userid") == null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String userid = request.getSession().getAttribute("userid").toString();
			JSONArray jsonlist = new JSONArray();
			String message = null;
			String money = null;
			//查询用户账户余额
			User user = mineService.selectuserbyuserid(CommonUtils.parseInt(userid, 0));
			if(user != null){
				money = user.getMoney().toString();
			}
			//查询用户流水信息
			List<RechargeSet> walletlist = mineService.selectwalletbyuserid(CommonUtils.parseInt(userid, 0));
            if(walletlist != null && walletlist.size()>0){
            	jsonlist = CommonUtils.setlisttojson(walletlist);
            }
            response.getWriter().write(callback+"([{ name:'message',value:'"+message+"'},{ name:'money',value:'"+money+"'},{ name:'walletlist',value:'"+jsonlist+"'}]);");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 我的评价
	 * @param response
	 * @param request
	 */
	@RequestMapping("myassess")
	public void myassess(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try{
			String callback = request.getParameter("jsonpcallback");
			response.setCharacterEncoding("utf-8");
			//获取用户ID
			if(request.getSession().getAttribute("userid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String userid = request.getSession().getAttribute("userid").toString();
			String message = null;
			List<MineAssess> assesslist = mineService.selectassessbyuserid(CommonUtils.parseInt(userid, 0));
			JSONArray json = CommonUtils.setlisttojson(assesslist);
			response.getWriter().write(callback+"([{ name:'message',value:'"+message+"'},{ name:'assesslist',value:'"+json+"'}] );");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 用户注册零工
	 * @param response
	 * @param request
	 */
	@RequestMapping("UserToWorker")
	public void UserToWorker(@RequestParam MultipartFile[] myfiles,HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try{
			String nickname = request.getParameter("nickname");
			String birthday = request.getParameter("birthday");
			String sex = request.getParameter("sex");
			String phone = request.getParameter("phone");
			String contacttel = request.getParameter("contacttel");
			String companies = request.getParameter("companies");
			String companiestel = request.getParameter("companiestel");
			String skill = request.getParameter("skill");
			String id_card = request.getParameter("id_card");
			String worktype = request.getParameter("worktype");
			String userid = request.getParameter("userid");
			JSONObject json = new JSONObject();// 所要返回的json值
			response.setCharacterEncoding("utf-8");
			if(CommonUtils.isEmptyString(nickname)||
					CommonUtils.isEmptyString(birthday)||
					CommonUtils.isEmptyString(sex)||
					CommonUtils.isEmptyString(phone)||
					CommonUtils.isEmptyString(contacttel)||
					CommonUtils.isEmptyString(skill)||
					CommonUtils.isEmptyString(worktype)||
					CommonUtils.isEmptyString(id_card)||
					CommonUtils.isEmptyString(userid)){
				String message = "缺少接口参数";
				json.put("message", message);
				response.getWriter().print(json.toString());
				return;
			}
			if(myfiles == null && myfiles.length <= 4){
				String message = "缺少接口参数";
				json.put("message", message);
				response.getWriter().print(json.toString());
				return;
			}
			//获取File类型文件
			List<String> paths = new ArrayList<String>();
			for (MultipartFile multipartFile : myfiles) {
					String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
	                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
	                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(realPath, multipartFile.getOriginalFilename())); 
	                String filename = multipartFile.getOriginalFilename();
	                paths.add("lexiu/upload/"+filename);
			}
			//计算年龄
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(birthday);
			Date now = new Date();
			Integer age = now.getYear()-date.getYear();
			
			//处理字符串
			StringBuffer sb = new StringBuffer(skill);
			for (int i = 0; i < sb.length()-4; i+=5) {
				sb.insert(i+4, ",");
			}
			String str = new String(sb);
			
			//注册零工
			String message = mineService.usertowork(nickname, birthday,age,sex,phone, contacttel, companies, companiestel, str, id_card,worktype,paths,Integer.parseInt(userid));
            json.put("message", message);
            response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询用户信息
	 * @param response
	 * @param request
	 */
	@RequestMapping("seluser")
	public void seluser(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try{
			String callback = request.getParameter("jsonpcallback");
			response.setCharacterEncoding("utf-8");
			//获取用户ID
			if(request.getSession().getAttribute("userid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String userid = request.getSession().getAttribute("userid").toString();
			String message = null;
			User user = mineService.selectuserbyuserid(CommonUtils.parseInt(userid, 0));
			JSONObject json = CommonUtils.setobjecttojson(user);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'},{ name:'user',value:'"+json+"'}] );");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 完善用户信息
	 * @param response
	 * @param request
	 */
	@RequestMapping("userPerfect")
	public void userPerfect(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try{
			String callback = request.getParameter("jsonpcallback");
			String nickname = request.getParameter("nickname");
			String birthday = request.getParameter("birthday");
			String sex = request.getParameter("sex");
			String phone = request.getParameter("phone");
			JSONObject json = new JSONObject();// 所要返回的json值
			response.setCharacterEncoding("utf-8");
			if(CommonUtils.isEmptyString(nickname)||
					CommonUtils.isEmptyString(birthday)||
					CommonUtils.isEmptyString(sex)||
					CommonUtils.isEmptyString(phone)){
				String message = "缺少接口参数";
				json.put("message", message);
				response.getWriter().print(json.toString());
				return;
			}
			
			//计算年龄
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(birthday);
			Date now = new Date();
			Integer age = now.getYear()-date.getYear();
			
			//获取用户ID
			if(request.getSession().getAttribute("userid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String userid = request.getSession().getAttribute("userid").toString();
			String message = mineService.updateuser(CommonUtils.parseInt(userid, 0),
					nickname, birthday, sex, phone,age);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 用户添加银行卡
	 * @param response
	 * @param request
	 */
	@RequestMapping("addbank")
	public void addbank(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try{
			String callback = request.getParameter("jsonpcallback");
			String cardnumber = request.getParameter("cardnumber");
			String bankname = request.getParameter("bankname");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String vcode = request.getParameter("vcode");
			JSONObject json = new JSONObject();// 所要返回的json值
			response.setCharacterEncoding("utf-8");
			if(CommonUtils.isEmptyString(cardnumber)||
					CommonUtils.isEmptyString(bankname)||
					CommonUtils.isEmptyString(name)||
					CommonUtils.isEmptyString(phone)||
					CommonUtils.isEmptyString(vcode)){
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
			//获取用户ID
			if(request.getSession().getAttribute("userid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String userid = request.getSession().getAttribute("userid").toString();
			String message = mineService.addbankcard(CommonUtils.parseInt(userid, 0),
					cardnumber, bankname, name,phone);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询我的银行卡信息
	 * @param response
	 * @param request
	 */
	@RequestMapping("mybank")
	public void mybank(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try{
			String callback = request.getParameter("jsonpcallback");
			response.setCharacterEncoding("utf-8");
			//获取用户ID
			if(request.getSession().getAttribute("userid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String userid = request.getSession().getAttribute("userid").toString();
			String message = null;
			List<UserBankcard> cardlist = mineService.selectuserbank(CommonUtils.parseInt(userid, 0));
            JSONArray json = CommonUtils.setlisttojson(cardlist);
            response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'},{ name:'cardlist',value:'"+json+"'}] );");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除我的银行卡信息
	 * @param response
	 * @param request
	 */
	@RequestMapping("delbank")
	public void delbank(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try{
			String cardid = request.getParameter("cardid");
			JSONObject json = new JSONObject();// 所要返回的json值
			response.setCharacterEncoding("utf-8");
			if(CommonUtils.isEmptyString(cardid)){
				String message = "缺少接口参数";
				json.put("message", message);
				response.getWriter().print(json.toString());
				return;
			}
			String message = mineService.deleteuserbank(CommonUtils.parseInt(cardid, 0));
            json.put("message", message);
            response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询用户信息
	 * @param response
	 * @param request
	 */
	@RequestMapping("gominepage")
	public void gominepage(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String callback = request.getParameter("jsonpcallback");
			response.setCharacterEncoding("utf-8");
			if(request.getSession().getAttribute("userid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String userid = request.getSession().getAttribute("userid").toString();
			//查询用户信息
			String message = null;
			User user = mineService.selectuserbyuserid(CommonUtils.parseInt(userid, 0));
			if(user != null && user.getBirthday()!=null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				user.setFormatbirthday(sdf.format(user.getBirthday()));
			}
			//查询师傅信息
			Worker worker = mineService.selectworkerbyphone(user.getPhone());
			JSONObject json1 = CommonUtils.setobjecttojson(worker);
			JSONObject json = CommonUtils.setobjecttojson(user);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'},{ name:'user',value:'"+json+"'},"
					+ "{ name:'worker',value:'"+json1+"'}] );");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 建议与反馈
	 * @param response
	 * @param request
	 */
	@RequestMapping("savefeedback")
	public void savefeedback(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String callback = request.getParameter("jsonpcallback");
			String type = request.getParameter("type");
			String content = request.getParameter("content");
			String code = request.getParameter("code");
			response.setCharacterEncoding("utf-8");
			if(CommonUtils.isEmptyString(type)||
					CommonUtils.isEmptyString(content)||
					CommonUtils.isEmptyString(code)){
				String message = "缺少接口参数";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String id;
			if(("0").equals(code)){
				//用户评论
				if(request.getSession().getAttribute("userid")==null){
					String message = "超时";
					response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
					return;
				}
				id = request.getSession().getAttribute("userid").toString();
				response.setCharacterEncoding("utf-8");
			}else{
				//师傅评论
				if(request.getSession().getAttribute("workerid")==null){
					String message = "超时";
					response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
					return;
				}
				id = request.getSession().getAttribute("workerid").toString();
				response.setCharacterEncoding("utf-8");
			}
			//保存评论信息
			String message = mineService.savecomment(CommonUtils.parseInt(id, 0), type, content, code);
			response.getWriter().write(callback+"([ { name:'user',value:'"+message+"'}] );");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
