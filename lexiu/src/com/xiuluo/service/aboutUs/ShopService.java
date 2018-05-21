package com.xiuluo.service.aboutUs;

import java.util.List;

import com.xiuluo.model.aboutUs.Goods;
import com.xiuluo.model.aboutUs.Shop;
import com.xiuluo.model.aboutUs.ShopBanner;

public interface ShopService {
	public List<Shop> allshoplist();//查询所有商品信息
	
	public Shop shopbyshopid(Integer shopid);//查询商品详情
	
	public List<ShopBanner> shopbannerbyshopid(Integer shopid);//查询商家轮播图
	
	public List<Goods> goodsbyshopid(Integer shopid);//查询商家商品信息
}
