package com.xiuluo.dao.aboutUs;

import java.util.List;

import com.xiuluo.model.aboutUs.CompanyType;

public interface CompanyTypeMapper {
    int deleteByPrimaryKey(Integer companytypeid);

    int insert(CompanyType record);

    int insertSelective(CompanyType record);

    CompanyType selectByPrimaryKey(Integer companytypeid);

    int updateByPrimaryKeySelective(CompanyType record);

    int updateByPrimaryKey(CompanyType record);
    
    CompanyType selectByName(String name);
    
    List<CompanyType> selectall();
}