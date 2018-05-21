package com.xiuluo.dao.aboutUs;

import java.util.List;

import com.xiuluo.model.aboutUs.ShopBanner;

public interface ShopBannerMapper {
    int deleteByPrimaryKey(Integer shopBannerid);

    int insert(ShopBanner record);

    int insertSelective(ShopBanner record);

    ShopBanner selectByPrimaryKey(Integer shopBannerid);
    
    List<ShopBanner> selectByShopid(Integer shopid);
    
    List<ShopBanner> selectAll();

    int updateByPrimaryKeySelective(ShopBanner record);

    int updateByPrimaryKey(ShopBanner record);
}