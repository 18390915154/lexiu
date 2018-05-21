package com.xiuluo.controller.manage;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xiuluo.model.aboutUs.User;
import com.xiuluo.service.manage.UserServiceManage;
import com.xiuluo.util.CommonUtils;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("manage/user")
public class UserApiMange {
	
	@Resource
	private UserServiceManage userServiceManage;
	
	/**
	 * 管理员登陆
	 * @param request
	 * @param response
	 */
	@RequestMapping("login")
	public void login(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			response.setCharacterEncoding("UTF-8");
			JSONObject json = new JSONObject();
			if(CommonUtils.isEmptyString(username)||
					CommonUtils.isEmptyString(password)){
				String message = "缺少接口参数";
				json.put("message", message);
				response.getWriter().print(json.toString());
				return;
			}
			//查询用户信息并登陆
			String message = userServiceManage.adminlogin(username, password);
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询用户信息(含分类)
	 * @param request
	 * @param response
	 */
	@RequestMapping("seachuser")
	public void seachuser(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//查询用户信息
			List<User> list = userServiceManage.seachuser();
			json.put("userlist", list);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
