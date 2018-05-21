package com.xiuluo.dao.aboutUs;

import java.util.List;

import com.xiuluo.model.aboutUs.Withdraw;

public interface WithdrawMapper {
    int deleteByPrimaryKey(Integer withdrawid);

    int insert(Withdraw record);

    int insertSelective(Withdraw record);

    Withdraw selectByPrimaryKey(Integer withdrawid);

    int updateByPrimaryKeySelective(Withdraw record);

    int updateByPrimaryKey(Withdraw record);
    
    List<Withdraw> selectall();
}