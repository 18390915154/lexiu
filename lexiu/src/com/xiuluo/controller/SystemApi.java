package com.xiuluo.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opensymphony.xwork2.ActionContext;
import com.xiuluo.model.aboutUs.Type;
import com.xiuluo.model.aboutUs.WorkerGps;
import com.xiuluo.service.aboutUs.SystemService;
import com.xiuluo.util.CommonUtils;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("api/system")
public class SystemApi {

	@Resource
	private SystemService systemService;
	
	
	/**
	 * 提交用户提现申请
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveuserwithdraw")
	public void saveuserwithdraw(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String callback = request.getParameter("jsonpcallback");
			String monery = request.getParameter("monery");
			String account = request.getParameter("account");
			response.setCharacterEncoding("UTF-8");
			if(CommonUtils.isEmptyString(monery)||
					CommonUtils.isEmptyString(account)){
				String message = "缺少接口参数";
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
			String message = systemService.insertuserwithdraw(CommonUtils.parseInt(userid, 0), monery, account);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 提交师傅提现申请
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveworkerwithdraw")
	public void saveworkerwithdraw(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String callback = request.getParameter("jsonpcallback");
			String monery = request.getParameter("monery");
			String account = request.getParameter("account");
			response.setCharacterEncoding("UTF-8");
			if(CommonUtils.isEmptyString(monery)||
					CommonUtils.isEmptyString(account)){
				String message = "缺少接口参数";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			
			//获取用户ID
			if(request.getSession().getAttribute("workerid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String workerid = request.getSession().getAttribute("workerid").toString();
			String message = systemService.insertworkerwithdraw(CommonUtils.parseInt(workerid, 0), monery, account);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询所有维修类别
	 * @param request
	 * @param response
	 */
	@RequestMapping("selecttype")
	public void selecttype(HttpServletRequest request,
			HttpServletResponse response){
		try{
			response.setCharacterEncoding("UTF-8");
			JSONObject json = new JSONObject();
			List<Type> list = systemService.selecttypelist();
			json.put("typelist", list);
			response.getWriter().write(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 分类查询维修类别
	 * @param request
	 * @param response
	 */
	@RequestMapping("selectsort")
	public void selectsort(HttpServletRequest request,
			HttpServletResponse response){
		try{
			response.setCharacterEncoding("UTF-8");
			String typenum = request.getParameter("typenum");
			JSONObject json = new JSONObject();
			if(CommonUtils.isEmptyString("typenum")){
				String message = "缺少接口参数";
				json.put("message", message);
				response.getWriter().write(json.toString());
			}
			List<Type> list = systemService.selectscorebynum(typenum);
			json.put("typelist", list);
			response.getWriter().write(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 分类查询维修类别
	 * @param request
	 * @param response
	 */
	@RequestMapping("seachworkbytypeid")
	public void seachworkbytypeid(HttpServletRequest request,
			HttpServletResponse response){
		try{
			response.setCharacterEncoding("UTF-8");
			String typenum = request.getParameter("typeid");
			JSONObject json = new JSONObject();
			if(CommonUtils.isEmptyString("typenum")){
				String message = "缺少接口参数";
				json.put("message", message);
				response.getWriter().write(json.toString());
			}
			String message = "成功";
			List<WorkerGps> gpslist = systemService.selectworkbytypeid(CommonUtils.parseInt(typenum, 0));
			json.put("message", message);
			json.put("gpslist", gpslist);
			response.getWriter().write(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 用户退出登录
	 * @param request
	 * @param response
	 */
	@RequestMapping("exituser")
	public void exituser(HttpServletRequest request,
			HttpServletResponse response){
		request.getSession().removeAttribute("userid");
		request.getSession().removeAttribute("vcode");
		request.getSession().removeAttribute("vcodetime");
		request.getSession().invalidate();
	}
	
	
	/**
	 * 师傅退出登录
	 * @param request
	 * @param response
	 */
	@RequestMapping("exitworker")
	public void exitworker(HttpServletRequest request,
			HttpServletResponse response){
		request.getSession().removeAttribute("workerid");
		request.getSession().removeAttribute("vcode");
		request.getSession().removeAttribute("vcodetime");
		request.getSession().invalidate();
	}
	
}
