package com.xiuluo.dao.aboutUs;

import java.util.List;

import com.xiuluo.model.aboutUs.UserBankcard;

public interface UserBankcardMapper {
    int deleteByPrimaryKey(Integer cardid);

    int insert(UserBankcard record);

    int insertSelective(UserBankcard record);

    UserBankcard selectByPrimaryKey(Integer cardid);

    int updateByPrimaryKeySelective(UserBankcard record);

    int updateByPrimaryKey(UserBankcard record);
    
    UserBankcard selectByCardnumber(String cardnumber);
    
    List<UserBankcard> selectByUserid(Integer userid);
    
    List<UserBankcard> selectAll();
}