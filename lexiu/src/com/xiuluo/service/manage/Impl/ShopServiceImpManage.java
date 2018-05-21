package com.xiuluo.service.manage.Impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xiuluo.model.aboutUs.Goods;
import com.xiuluo.model.aboutUs.Shop;
import com.xiuluo.model.aboutUs.ShopBanner;
import com.xiuluo.service.manage.ShopServiceManage;
import com.xiuluo.util.AllMapper;
import com.xiuluo.util.CommonUtils;

@Service("shopServiceManage")
public class ShopServiceImpManage extends AllMapper implements ShopServiceManage {

	/**
	 * 查询商家轮播图
	 */
	@Override
	public List<ShopBanner> selectbanner(Integer shopid) {
		List<ShopBanner> list = shopBannerMapper.selectByShopid(shopid);
		return list;
	}

	
	/**
	 * 修改轮播图
	 */
	@Override
	public String updatebanner(Integer shopbannerid, String path) {
		ShopBanner shopbanner = shopBannerMapper.selectByPrimaryKey(shopbannerid);
		if(shopbanner != null){
			shopbanner.setPicurl(path);
		}
		int code = shopBannerMapper.updateByPrimaryKeySelective(shopbanner);
		String message;
		if(code == 1){
			message = "成功";
		}else{
			message = "修改失败";
		}
		return message;
	}


	/**
	 * 查询所有商家
	 */
	@Override
	public List<Shop> selectshop() {
		List<Shop> list = shopMapper.selectAll();
		return list;
	}

	
	/**
	 *查询商家详情 
	 */
	@Override
	public Shop shopdetail(Integer shopid) {
		Shop shop = shopMapper.selectByPrimaryKey(shopid);
		return shop;
	}

	
	/**
	 * 修改商家信息
	 */
	@Override
	public String updateshop(Integer shopid, String name, String address, String contact, String tel, String path) {
		Shop shop = shopMapper.selectByPrimaryKey(shopid);
		String message;
		if(shop != null){
			shop.setName(name);
			shop.setAddress(address);
			Map<String,String> map = CommonUtils.getPoint(address);
			BigDecimal latbd = new BigDecimal(map.get("lat"));
			shop.setLatitude(latbd);
			BigDecimal linbd = new BigDecimal(map.get("lin"));
			shop.setLongitude(linbd);
			shop.setContact(contact);
			shop.setTel(tel);
			shop.setPicurl(path);
			int code = shopMapper.updateByPrimaryKeySelective(shop);
			if(code == 1){
				message = "成功";
			}else{
				message = "修改失败";
			}
		}else{
			message = "修改失败";
		}
		return message;
	}


	/**
	 * 新增商家信息
	 */
	@Override
	public String insertshop(String name, String address, String contact, String tel, String path) {
		//判断商家名称是否重名
		List<Shop> list = shopMapper.selectByName(name);
		String message;
		if(list != null && list.size()>0){
			message = "店铺名已存在";
		}else{
			Shop shop = new Shop();
			shop.setName(name);
			shop.setAddress(address);
			Map<String,String> map = CommonUtils.getPoint(address);
			BigDecimal latbd = new BigDecimal(map.get("lat"));
			shop.setLatitude(latbd);
			BigDecimal linbd = new BigDecimal(map.get("lin"));
			shop.setLongitude(linbd);
			shop.setContact(contact);
			shop.setTel(tel);
			shop.setPicurl(path);
			shop.setDelflg((short)0);
			shop.setAddtime(new Date());
			int code = shopMapper.insertSelective(shop);
			if(code == 1){
				message = "成功";
			}else{
				message ="新增失败";
			}
			//默认新增三条轮播图
			List<Shop> list1 = shopMapper.selectByName(name);
			if(list1 != null && list1.size()>0){
				for (Shop shop2 : list1) {
					for(int i = 0; i<3;i++){
						ShopBanner shopbanner = new ShopBanner();
						shopbanner.setShopid(shop2.getShopid());
						shopbanner.setPicurl("lexiu/upload/123.jpg");
						shopbanner.setSort(0);
						shopbanner.setAddtime(new Date());
						shopBannerMapper.insertSelective(shopbanner);
					}
					
				}	
			}
		}
		return message;
	}

	
	/**
	 *查询所有商品信息 
	 */
	@Override
	public List<Goods> selectgoodslist(Integer shopid) {
		List<Goods> list = goodsMapper.selectByShopid(shopid);
		return list;
	}

	
	/**
	 * 修改商家信息
	 */
	@Override
	public String updategoods(Integer goodsid, Float score, String name, String profile, String path) {
		Goods goods = goodsMapper.selectByPrimaryKey(goodsid);
		String message;
		if(goods != null){
			goods.setScore(score);
			goods.setName(name);
			goods.setProfile(profile);
			goods.setPicurl(path);
			int code = goodsMapper.updateByPrimaryKeySelective(goods);	
			if(code == 1){
				message = "成功";
			}else{
				message = "修改失败";
			}
		}else{
			message = "修改失败";
		}
		return message;
	}


	/**
	 * 新增商家信息
	 */
	@Override
	public String insertgoods(Integer shopid, Float score, String name, String profile, String path) {
		Goods goods = new Goods();
		String message;
		goods.setShopid(shopid);
		goods.setScore(score);
		goods.setName(name);
		goods.setProfile(profile);
		goods.setPicurl(path);
		goods.setDelstate((short)0);
		goods.setAddtime(new Date());
		int code = goodsMapper.insertSelective(goods);
		if(code == 1){
			message = "成功";
		}else{
			message = "新增失败";
		}
		return message;
	}

	
	/**
	 * 删除商家信息
	 */
	@Override
	public String delectshop(Integer shopid) {
		String message;
		int code = shopMapper.deleteByPrimaryKey(shopid);
		if(code == 1){
			message = "成功";
		}else{
			message = "删除失败";
		}
		List<Goods> list = goodsMapper.selectByShopid(shopid);
		if(list != null && list.size()>0){
			for (Goods goods : list) {
				goodsMapper.deleteByPrimaryKey(goods.getGoodsid());
			}				
		}
		return message;
	}


	/**
	 * 删除商品信息
	 */
	@Override
	public String delectgoods(Integer goodsid) {
		String message;
		int code = goodsMapper.deleteByPrimaryKey(goodsid);
		if(code == 1){
			message = "成功";
		}else{
			message = "删除失败";
		}
		return message;
	}


	/**
	 * 查询某个商家信息
	 */
	@Override
	public Shop selectshopbyshop(Integer shopid) {
		Shop shop = shopMapper.selectByPrimaryKey(shopid);
		return shop;
	}


	/**
	 * 查询商品详情
	 */
	@Override
	public Goods selectgoodsdetail(Integer goodsid) {
		Goods goods = goodsMapper.selectByPrimaryKey(goodsid);
		return goods;
	}

}
