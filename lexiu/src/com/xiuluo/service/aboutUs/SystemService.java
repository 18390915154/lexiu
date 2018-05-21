package com.xiuluo.service.aboutUs;

import java.util.List;

import com.xiuluo.model.aboutUs.Type;
import com.xiuluo.model.aboutUs.WorkerGps;

public interface SystemService {

	public String insertuserwithdraw(Integer userid,String monery,String account);
	
	public String insertworkerwithdraw(Integer workerid,String monery,String account);

	public List<Type> selecttypelist();
	
	public List<Type> selectscorebynum(String num);
	
	public List<WorkerGps> selectworkbytypeid(Integer typeid);
}
