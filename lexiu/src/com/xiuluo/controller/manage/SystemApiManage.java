package com.xiuluo.controller.manage;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiuluo.model.aboutUs.Checkout;
import com.xiuluo.model.aboutUs.CompanyType;
import com.xiuluo.model.aboutUs.Type;
import com.xiuluo.model.aboutUs.Withdraw;
import com.xiuluo.service.manage.SystemServiceManage;
import com.xiuluo.util.CommonUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("manage/system")
public class SystemApiManage {

	@Resource
	private SystemServiceManage systemServiceManage;
	
	
	/**
	 * 查询所有分类信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("seachtype")
	public void seachtype(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//查询所有分类信息
			List<Type> list = systemServiceManage.selecttypelist();
			json.put("typelist", list);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 新增分类信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("inserttype")
	public void inserttype(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			String name = request.getParameter("name");
			String typenum = request.getParameter("typenum");
			//新增分类信息
			String message = systemServiceManage.inserttype(name,typenum);
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除所有分类信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("deltype")
	public void deltype(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			String typeid = request.getParameter("typeid");
			//删除分类信息
			String message = systemServiceManage.delecttype(CommonUtils.parseInt(typeid, 0));
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询所有分类信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("seachcompanytype")
	public void seachcompanytype(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//查询所有分类信息
			List<CompanyType> list = systemServiceManage.selectcompanytypelist();
			json.put("typelist", list);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 新增分类信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("insertcompanytype")
	public void insertcompanytype(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			String name = request.getParameter("name");
			//新增分类信息
			String message = systemServiceManage.insertcompanytype(name);
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除所有分类信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("delcompanytype")
	public void delcompanytype(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			String companytypeid = request.getParameter("companytypeid");
			//删除分类信息
			String message = systemServiceManage.delectcompanytype(CommonUtils.parseInt(companytypeid, 0));
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询所有待提现申请
	 * @param request
	 * @param response
	 */
	@RequestMapping("seachwithdraw")
	public void seachwithdraw(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			List<Withdraw> list = systemServiceManage.selectwithdraw();
			json.put("withdrawlist", list);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 修改所有待提现申请
	 * @param request
	 * @param response
	 */
	@RequestMapping("updatewithdraw")
	public void updatewithdraw(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			String withdrawid = request.getParameter("withdrawid");
			String message = systemServiceManage.updatewithdraw(CommonUtils.parseInt(withdrawid, 0));
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询所有抽成比例
	 * @param request
	 * @param response
	 */
	@RequestMapping("searchcheckout")
	  public void searchcheckout(HttpServletRequest request, HttpServletResponse response){
	    JSONObject json = new JSONObject();
	    try
	    {
	      response.setCharacterEncoding("UTF-8");
	      List<Checkout> list = this.systemServiceManage.selectcheckout();
	      json.put("checkoutlist", list);
	      response.getWriter().print(json.toString());
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }

	
	
	/**
	 * 修改抽成比例
	 * @param request
	 * @param response
	 */
	  @RequestMapping("updatecheckout")
	  public void updatecheckout(HttpServletRequest request, HttpServletResponse response){
		  JSONObject json = new JSONObject();
	    try
	    {
	      String checkoutid = request.getParameter("checkoutid");
	      String number = request.getParameter("number");
	      response.setCharacterEncoding("UTF-8");
	      String message = this.systemServiceManage.updatecheckout(Integer.valueOf(CommonUtils.parseInt(checkoutid, 0)), 
	        CommonUtils.parseDouble(number, 0));
	      json.put("message", message);
	      response.getWriter().print(json.toString());
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
}
