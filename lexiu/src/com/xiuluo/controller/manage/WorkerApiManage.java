package com.xiuluo.controller.manage;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xiuluo.model.aboutUs.Worker;
import com.xiuluo.service.manage.WorkerServiceManage;
import com.xiuluo.util.CommonUtils;
import net.sf.json.JSONObject;



@Controller
@RequestMapping("manage/worker")
public class WorkerApiManage {

	@Resource
	private WorkerServiceManage workerServiceManage;
	
	
	/**
	 * 查询师傅信息(含分类)
	 * @param request
	 * @param response
	 */
	@RequestMapping("seachworker")
	public void seachworker(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//查询用户信息
			List<Worker> list = workerServiceManage.seachworkerlist();
			json.put("workerlist", list);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询某个师傅信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("workerdetail")
	public void workerdetail(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String phone = request.getParameter("phone");
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//查询用户信息
			Worker work = workerServiceManage.workerdetail(phone);
			json.put("work", work);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 审核师傅信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("editisok")
	public void editisok(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String phone = request.getParameter("phone");
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//更改师傅信息
			String message = workerServiceManage.updateisok(phone);
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
