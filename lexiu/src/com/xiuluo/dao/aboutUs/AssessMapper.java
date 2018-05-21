package com.xiuluo.dao.aboutUs;

import java.util.List;

import com.xiuluo.model.MineAssess;
import com.xiuluo.model.aboutUs.Assess;

public interface AssessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Assess record);

    int insertSelective(Assess record);

    Assess selectByPrimaryKey(Integer id);
    
    Assess selectByOrderid(Integer orderid);

    int updateByPrimaryKeySelective(Assess record);

    int updateByPrimaryKey(Assess record);
}