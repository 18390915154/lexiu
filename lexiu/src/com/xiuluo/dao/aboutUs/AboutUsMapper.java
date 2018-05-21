package com.xiuluo.dao.aboutUs;

import com.xiuluo.model.aboutUs.AboutUs;;

public interface AboutUsMapper {
    int deleteByPrimaryKey(Integer aboutId);

    int insert(AboutUs record);

    int insertSelective(AboutUs record);

    AboutUs selectByPrimaryKey(Integer aboutId);

    int updateByPrimaryKeySelective(AboutUs record);

    int updateByPrimaryKey(AboutUs record);
}