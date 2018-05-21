package com.xiuluo.dao.aboutUs;

import java.util.List;

import com.xiuluo.model.aboutUs.RechargeSet;

public interface RechargeSetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RechargeSet record);

    int insertSelective(RechargeSet record);

    RechargeSet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RechargeSet record);

    int updateByPrimaryKey(RechargeSet record);
    
    List<RechargeSet> selectByUserid(Integer userid);
    
    List<RechargeSet> selectAll();
}