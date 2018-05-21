package com.xiuluo.dao.aboutUs;

import java.util.List;

import com.xiuluo.model.aboutUs.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);
    
    User selectByPhone(String phone);
    
    User selectByNickName(String nickname);
    
    List<User> selectUserManageByPhone(String phone);
    
    List<User> selectalluser();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}