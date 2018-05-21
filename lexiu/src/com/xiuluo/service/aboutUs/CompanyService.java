package com.xiuluo.service.aboutUs;

import java.util.List;
import com.xiuluo.model.aboutUs.Company;
import com.xiuluo.model.aboutUs.Type;


public interface CompanyService {
	public List<Company> nearcompany(String type);
	
	public String insertorder(Integer companyid , Integer userid,String address,String starttime,String detail, String longitude, String latitude,String type,List<String> paths);
	
	public List<Type> selectalltype();
}
