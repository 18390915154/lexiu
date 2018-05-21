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

import com.xiuluo.model.aboutUs.Goods;
import com.xiuluo.model.aboutUs.Shop;
import com.xiuluo.model.aboutUs.ShopBanner;
import com.xiuluo.service.manage.ShopServiceManage;
import com.xiuluo.util.CommonUtils;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("manage/shop")
public class ShopApiManage {

	@Resource
	private ShopServiceManage shopServiceManage;
	
	
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
			String shopid = request.getParameter("shopid");
			//查询所有轮播图信息
			List<ShopBanner> list = shopServiceManage.selectbanner(CommonUtils.parseInt(shopid, 0));
			json.put("bannerlist", list);
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
			String message = shopServiceManage.updatebanner(CommonUtils.parseInt(bannerid, 0), path);
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询所有商家信息信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("seachshop")
	public void seachshop(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//查询所有商家信息
			List<Shop> list = shopServiceManage.selectshop();
			json.put("shoplist", list);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询所有商家详细信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("shopdetail")
	public void shopdetail(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String shopid = request.getParameter("shopid");
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//查询所有商家信息
			Shop shop = shopServiceManage.shopdetail(CommonUtils.parseInt(shopid, 0));
			json.put("shopdetail", shop);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 编辑所有商家详细信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("updateshop")
	public void updateshop(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="myfiles",required=false) MultipartFile myfiles){
		try{
			String shopid = request.getParameter("shopid");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String contact = request.getParameter("contact");
			String tel = request.getParameter("tel");
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			
			//获取图片
			String path;
			String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
            //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
            FileUtils.copyInputStreamToFile(myfiles.getInputStream(), new File(realPath, myfiles.getOriginalFilename())); 
            String filename = myfiles.getOriginalFilename();
            path = "lexiu/upload/"+filename;
			
			//修改所有商家信息
			String message = shopServiceManage.updateshop(CommonUtils.parseInt(shopid, 0), name, address, contact, tel, path);
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 新增所有商家详细信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("createshop")
	public void createshop(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="myfiles",required=false) MultipartFile myfiles){
		try{
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String contact = request.getParameter("contact");
			String tel = request.getParameter("tel");
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			
			//获取图片
			String path;
			String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
            //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
            FileUtils.copyInputStreamToFile(myfiles.getInputStream(), new File(realPath, myfiles.getOriginalFilename())); 
            String filename = myfiles.getOriginalFilename();
            path = "lexiu/upload/"+filename;
			
			//新增所有商家信息
			String message = shopServiceManage.insertshop(name, address, contact, tel, path);
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除商家详细信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("delshop")
	public void delshop(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String shopid = request.getParameter("shopid");
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//删除商家信息
			String message = shopServiceManage.delectshop(CommonUtils.parseInt(shopid, 0));
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询所有商家商品详细信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("seachgoods")
	public void seachgood(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String shopid = request.getParameter("shopid");
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//查询所有商品信息
			List<Goods> list = shopServiceManage.selectgoodslist(CommonUtils.parseInt(shopid, 0));
			if(list != null && list.size()>0){
				for (Goods goods : list) {
					//查询商家信息
					Shop shop = shopServiceManage.selectshopbyshop(CommonUtils.parseInt(shopid, 0));
					goods.setShopname(shop.getName());
				}
			}
			json.put("goodslist", list);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 编辑商品详细信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("updategoods")
	public void updategoods(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="myfiles",required=false) MultipartFile myfiles){
		try{
			String goodsid = request.getParameter("goodsid");
			String score = request.getParameter("score");
			String name = request.getParameter("name");
			String profile = request.getParameter("profile");
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			
			//获取图片
			String path;
			String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
            //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
            FileUtils.copyInputStreamToFile(myfiles.getInputStream(), new File(realPath, myfiles.getOriginalFilename())); 
            String filename = myfiles.getOriginalFilename();
            path = "lexiu/upload/"+filename;
			
			//修改商家信息
			String message = shopServiceManage.updategoods(CommonUtils.parseInt(goodsid, 0),CommonUtils.parseFloat(score, 0),name,profile,path);
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 新增商品详细信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("creategoods")
	public void insertgoods(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="myfiles",required=false) MultipartFile myfiles){
		try{
			String shopid = request.getParameter("shopid");
			String score = request.getParameter("score");
			String name = request.getParameter("name");
			String profile = request.getParameter("profile");
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			
			//获取图片
			String path;
			String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
            //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
            FileUtils.copyInputStreamToFile(myfiles.getInputStream(), new File(realPath, myfiles.getOriginalFilename())); 
            String filename = myfiles.getOriginalFilename();
            path = "lexiu/upload/"+filename;
			
			//新增商家信息
			String message = shopServiceManage.insertgoods(CommonUtils.parseInt(shopid, 0),CommonUtils.parseFloat(score, 0),name,profile,path);
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除商家详细信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("delgoods")
	public void delgoods(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String goodsid = request.getParameter("goodsid");
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//删除商家信息
			String message = shopServiceManage.delectgoods(CommonUtils.parseInt(goodsid, 0));
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询商品详细信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("goodsdetail")
	public void goodsdetail(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String goodsid = request.getParameter("goodsid");
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//删除商家信息
			Goods goods = shopServiceManage.selectgoodsdetail(CommonUtils.parseInt(goodsid, 0));
			json.put("goods", goods);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
