package com.xiuluo.service.manage;

import java.util.List;
import com.xiuluo.model.aboutUs.Feedback;
import com.xiuluo.model.aboutUs.HomeBanner;

public interface HomeServiceManage {
	public List<HomeBanner> selectbannerlist();
	
	public String delectbanner(Integer bannerid);
	
	public String updatebanner(Integer bannerid,String path);
	
	public List<Feedback> selectAll();
}
