package com.xiuluo.service.manage;

import java.util.List;
import com.xiuluo.model.aboutUs.Company;
import com.xiuluo.model.aboutUs.CompanyType;

public interface CompanyServiceManage {
	public List<Company> selectallcompany();
	
	public String insertcompany(String name , String address,String contact,String phone,String path ,String[] typeid);
	
	public String updatecompany(Integer companyid, String name , String address,String contact,String phone,String path ,String[] typeid);

	public String delectcompany(Integer companyid);
	
	public List<CompanyType> seltype();
	
	public Company companydetail(Integer companyid);
}
