package com.xiuluo.service.manage.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.xiuluo.service.manage.UserServiceManage;
import com.xiuluo.model.aboutUs.User;
import com.xiuluo.util.AllMapper;

@Service("userServiceManage")
public class UserServiceImplManage extends AllMapper implements UserServiceManage {

	/**
	 * 管理员登陆
	 */
	@Override
	public String adminlogin(String username, String password) {
		User user = userMapper.selectByNickName(username);
		String message;
		if(user != null){
			if(password.equals(user.getPassword())){
				message = "成功";
			}else{
				message = "账号或密码错误";
			}
		}else{
			message = "账号或密码错误";
		}
		return message;
	}

	
	/**
	 * 查询用户信息
	 */
	@Override
	public List<User> seachuser() {
		List<User> list = userMapper.selectalluser();
		return list;
	}

}
