package com.xiuluo.dao.aboutUs;

import java.util.List;

import com.xiuluo.model.aboutUs.Shop;

public interface ShopMapper {
    int deleteByPrimaryKey(Integer shopid);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(Integer shopid);
    
    List<Shop> selectAll();
    
    List<Shop> selectByName(String name);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);
}