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

import com.xiuluo.model.aboutUs.Company;
import com.xiuluo.model.aboutUs.CompanyType;
import com.xiuluo.service.manage.CompanyServiceManage;
import com.xiuluo.util.CommonUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("manage/company")
public class CompanyApiManage {

	@Resource
	private CompanyServiceManage companyServiceManage;
	
	
	/**
	 * 查询所有公司信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("seachcompany")
	public void seachcompany(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//查询公司信息
			List<Company> list = companyServiceManage.selectallcompany();
			json.put("companylist", list);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 新增公司信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("insertcompany")
	public void insertcompany(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="myfiles",required=false) MultipartFile myfiles,
			String[] typeid){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String contact = request.getParameter("contact");
			String phone = request.getParameter("phone");
			
			//获取图片
			String path;
			String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
            //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
            FileUtils.copyInputStreamToFile(myfiles.getInputStream(), new File(realPath, myfiles.getOriginalFilename())); 
            String filename = myfiles.getOriginalFilename();
            path = "lexiu/upload/"+filename;
			
			//新增公司信息
			String message = companyServiceManage.insertcompany(name, address, contact, phone, path, typeid);
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询公司详情
	 * @param request
	 * @param response
	 */
	@RequestMapping("companydetail")
	public void companydetail(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			String companyid = request.getParameter("companyid");
			//查询公司信息
			Company list = companyServiceManage.companydetail(CommonUtils.parseInt(companyid,0));
			json.put("company", list);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 修改公司信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("updatecompany")
	public void updatecompany(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="myfiles",required=false) MultipartFile myfiles,
			String[] typeid){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String contact = request.getParameter("contact");
			String phone = request.getParameter("phone");
			String companyid = request.getParameter("companyid");
			
			//获取图片
			String path;
			String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
            //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
            FileUtils.copyInputStreamToFile(myfiles.getInputStream(), new File(realPath, myfiles.getOriginalFilename())); 
            String filename = myfiles.getOriginalFilename();
            path = "lexiu/upload/"+filename;
            
            String message = companyServiceManage.updatecompany(CommonUtils.parseInt(companyid, 0), name, address, contact, phone, path, typeid);
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除公司信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("delcompany")
	public void delcompany(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			String companyid = request.getParameter("companyid");
			//删除公司信息
			String message = companyServiceManage.delectcompany(CommonUtils.parseInt(companyid, 0));
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询公司类型
	 * @param request
	 * @param response
	 */
	@RequestMapping("selecttype")
	public void selecttype(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			List<CompanyType> list = companyServiceManage.seltype();
			json.put("typelist", list);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
