package com.xiuluo.service.manage;

import java.util.List;

import com.xiuluo.model.aboutUs.Goods;
import com.xiuluo.model.aboutUs.Shop;
import com.xiuluo.model.aboutUs.ShopBanner;

public interface ShopServiceManage {
	
	public List<ShopBanner> selectbanner(Integer shopid);
	
	public Shop shopdetail(Integer shopid);
	
	public String updatebanner(Integer shopbannerid,String path);
	
	public List<Shop> selectshop();
	
	public String updateshop(Integer shopid,String name,String address,String contact,String tel,String path);
	
	public String insertshop(String name,String address,String contact,String tel,String path);
	
	public String delectshop(Integer shopid);

	public List<Goods> selectgoodslist(Integer shopid);
	
	public String updategoods(Integer goodsid,Float score,String name,String profile,String path);

	public String insertgoods(Integer shopid,Float score,String name,String profile,String path);
	
	public String delectgoods(Integer goodsid);
	
	public Shop selectshopbyshop(Integer shopid);
	
	public Goods selectgoodsdetail(Integer goodsid);
}
