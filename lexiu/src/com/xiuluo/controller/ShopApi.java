package com.xiuluo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.xiuluo.model.aboutUs.Goods;
import com.xiuluo.model.aboutUs.Shop;
import com.xiuluo.model.aboutUs.ShopBanner;
import com.xiuluo.service.aboutUs.ShopService;
import com.xiuluo.util.CommonUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("api/shop")
public class ShopApi {
	
	@Resource
	private ShopService shopService;
	
	
	/**
	 * 查询所有的商家信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("nearbyshop")
	@ResponseBody
	public void nearbyshop(HttpServletRequest request,
			HttpServletResponse response){
		try {		   
			String callback = request.getParameter("jsonpcallback");
			List<Shop> shoplist = shopService.allshoplist();
			response.setCharacterEncoding("utf-8");
			JSONArray json = CommonUtils.setlisttojson(shoplist);
			response.getWriter().write(callback+"([ { name:'shoplist',value:'"+json+"'}] );");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询商家详细信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("shopdetail")
	public void shopdetail(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String shopid = request.getParameter("shopid");
			JSONObject json = new JSONObject();// 所要返回的json值 
			response.setCharacterEncoding("utf-8");
			if(CommonUtils.isEmptyString(shopid)){
				String message = "缺少接口参数";
				json.put("message", message);
				response.getWriter().print(json.toString());
				return;
			}
			//根据商家ID查询商家信息
			Shop shop = shopService.shopbyshopid(CommonUtils.parseInt(shopid, 0));
			json.put("shop", shop);
			//获取商家轮播图
			List<ShopBanner> bannerlist = shopService.shopbannerbyshopid(CommonUtils.parseInt(shopid, 0));
			json.put("bannerlist", bannerlist);
			//获取商家商品信息
			List<Goods> goodlist = shopService.goodsbyshopid(CommonUtils.parseInt(shopid, 0));
			json.put("goodlist", goodlist);
			response.getWriter().print(json.toString());
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
