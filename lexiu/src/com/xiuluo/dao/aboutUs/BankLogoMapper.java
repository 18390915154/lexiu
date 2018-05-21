package com.xiuluo.dao.aboutUs;

import com.xiuluo.model.aboutUs.BankLogo;

public interface BankLogoMapper {
    int deleteByPrimaryKey(Integer logoid);

    int insert(BankLogo record);

    int insertSelective(BankLogo record);

    BankLogo selectByPrimaryKey(Integer logoid);

    int updateByPrimaryKeySelective(BankLogo record);

    int updateByPrimaryKey(BankLogo record);
}