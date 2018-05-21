package com.xiuluo.service.manage;

import java.util.List;
import com.xiuluo.model.aboutUs.User;

public interface UserServiceManage {
	public String adminlogin(String username , String password);
	
	public List<User> seachuser();
}
