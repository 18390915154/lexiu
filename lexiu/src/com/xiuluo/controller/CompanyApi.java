package com.xiuluo.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xiuluo.model.aboutUs.Company;
import com.xiuluo.service.aboutUs.CompanyService;
import com.xiuluo.util.CommonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("api/company")
public class CompanyApi {
	
	@Resource
	private CompanyService companyService;
	
	
	/**
	 * 获取附近公司信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("nearbycompany")
	public void nearbycompany(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String callback = request.getParameter("jsonpcallback");
			String type = request.getParameter("type");
			response.setCharacterEncoding("UTF-8");
			if(CommonUtils.isEmptyString(type)){
				String message = "缺少接口参数";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			//根据类型查询附近公司信息
			String message = null;
			List<Company> list = companyService.nearcompany(type);
			JSONArray json = CommonUtils.setlisttojson(list);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'},{ name:'companylist',value:'"+json+"'}] );");
		}catch(Exception e){
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
			HttpServletResponse response,@RequestParam(value="myfiles",required=false) MultipartFile[] myfiles){
		try{
			response.setCharacterEncoding("UTF-8");
			String typeid = request.getParameter("typeid");
			String address = request.getParameter("address");
			String starttime = request.getParameter("starttime");
			String detail = request.getParameter("detail");
			String longitude = request.getParameter("longitude");
			String latitude = request.getParameter("latitude");
			String userid = request.getParameter("userid");
			String companyid = request.getParameter("companyid");
			JSONObject json = new JSONObject();
			if(CommonUtils.isEmptyString(typeid)||
					CommonUtils.isEmptyString(address)||
					CommonUtils.isEmptyString(detail)||
					CommonUtils.isEmptyString(longitude)||
					CommonUtils.isEmptyString(latitude)||
					CommonUtils.isEmptyString(userid)||
					CommonUtils.isEmptyString(companyid)||
					myfiles == null){
				String message = "缺少接口参数";
				json.put("message", message);
				response.getWriter().println(json.toString());
				return;
			}
			
			//获取File类型文件
			List<String> paths = new ArrayList<String>();
			for (MultipartFile multipartFile : myfiles) {
				if(multipartFile.isEmpty()){
					String message = "缺少接口参数";
					json.put("message", message);
					response.getWriter().println(json.toString());
					return;
				}else{
					String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
	                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
	                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(realPath, multipartFile.getOriginalFilename())); 
	                String filename = multipartFile.getOriginalFilename();
	                paths.add("lexiu/upload/"+filename);
				}
			}
			
			String message;
			message = companyService.insertorder(CommonUtils.parseInt(companyid, 0),CommonUtils.parseInt(userid, 0), address, starttime, detail, longitude, latitude, typeid, paths);
			json.put("message", message);
			response.getWriter().println(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
//	/**
//	 * 获取工种分类信息
//	 * @param request
//	 * @param response
//	 */
//	@RequestMapping("alltype")
//	public void alltype(HttpServletRequest request,
//			HttpServletResponse response){
//		try{
//			String callback = request.getParameter("jsonpcallback");
//			response.setCharacterEncoding("UTF-8");
//			String message = null;
//			List<Type> list = companyService.selectalltype();
//			JSONArray json = CommonUtils.setlisttojson(list);
//			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'},{ name:'typelist',value:'"+json+"'}] );");
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
}
