package com.xiuluo.dao.aboutUs;

import java.util.List;

import com.xiuluo.model.aboutUs.Goods;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer goodsid);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer goodsid);

    List<Goods> selectByShopid(Integer shopid);
    
    List<Goods> selectAll();

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}