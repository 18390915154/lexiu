package com.xiuluo.controller.manage;

import java.io.File;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xiuluo.model.aboutUs.Feedback;
import com.xiuluo.model.aboutUs.HomeBanner;
import com.xiuluo.service.manage.HomeServiceManage;
import com.xiuluo.util.CommonUtils;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("manage/banner")
public class HomeApiManage {
	
	@Resource
	private HomeServiceManage homeServiceManage;
	
	/**
	 * 查询所有轮播图信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("seachbanner")
	public void seachbanner(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//查询所有轮播图信息
			List<HomeBanner> list = homeServiceManage.selectbannerlist();
			json.put("bannerlist", list);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除轮播图信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("delbanner")
	public void delbanner(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String bannerid = request.getParameter("bannerid");
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//删除轮播图
			String message = homeServiceManage.delectbanner(CommonUtils.parseInt(bannerid, 0));
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 修改轮播图信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("updatebanner")
	public void updatebanner(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="myfiles",required=false) MultipartFile myfiles){
		try{
			String bannerid = request.getParameter("bannerid");
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			
			//获取图片
			String path;
			String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
            //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
            FileUtils.copyInputStreamToFile(myfiles.getInputStream(), new File(realPath, myfiles.getOriginalFilename())); 
            String filename = myfiles.getOriginalFilename();
            path = "lexiu/upload/"+filename;
            
			//修改轮播图
			String message = homeServiceManage.updatebanner(CommonUtils.parseInt(bannerid, 0), path);
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询意见反馈
	 * @param request
	 * @param response
	 */
	@RequestMapping("myfeedback")
	public void myfeedback(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			List<Feedback> list = homeServiceManage.selectAll();
			json.put("feedback", list);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
