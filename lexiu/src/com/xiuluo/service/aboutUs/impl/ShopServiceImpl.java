package com.xiuluo.service.aboutUs.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.xiuluo.model.aboutUs.Goods;
import com.xiuluo.model.aboutUs.Shop;
import com.xiuluo.model.aboutUs.ShopBanner;
import com.xiuluo.service.aboutUs.ShopService;
import com.xiuluo.util.AllMapper;

@Service("shopService")
public class ShopServiceImpl extends AllMapper implements ShopService {
	
	/**
	 * 查询所有商家信息
	 */
	@Override
	public List<Shop> allshoplist() {
		List<Shop> list = shopMapper.selectAll();
		return list;
	}

	/**
	 * 查询商家详细信息
	 */
	@Override
	public Shop shopbyshopid(Integer shopid) {
		Shop shop = shopMapper.selectByPrimaryKey(shopid);
		return shop;
	}

	/**
	 * 查询商家轮播图
	 */
	@Override
	public List<ShopBanner> shopbannerbyshopid(Integer shopid) {
		List<ShopBanner> bannerlist = shopBannerMapper.selectByShopid(shopid);
		return bannerlist;
	}

	/**
	 * 查询商家商品信息
	 */
	@Override
	public List<Goods> goodsbyshopid(Integer shopid) {
		List<Goods> goodlist = goodsMapper.selectByShopid(shopid);
		return goodlist;
	}

}
