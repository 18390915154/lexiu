package com.xiuluo.dao.aboutUs;

import java.util.List;

import com.xiuluo.model.aboutUs.HomeBanner;

public interface HomeBannerMapper {
    int deleteByPrimaryKey(Integer bannerid);

    int insert(HomeBanner record);

    int insertSelective(HomeBanner record);

    HomeBanner selectByPrimaryKey(Integer bannerid);
    
    List<HomeBanner> selectAll();

    int updateByPrimaryKeySelective(HomeBanner record);

    int updateByPrimaryKey(HomeBanner record);
}