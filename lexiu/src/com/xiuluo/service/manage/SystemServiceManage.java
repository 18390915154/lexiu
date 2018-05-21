package com.xiuluo.service.manage;

import java.util.List;

import com.xiuluo.model.aboutUs.Checkout;
import com.xiuluo.model.aboutUs.CompanyType;
import com.xiuluo.model.aboutUs.Type;
import com.xiuluo.model.aboutUs.Withdraw;

public interface SystemServiceManage {

	public List<Type> selecttypelist();
	
	public String inserttype(String name,String typenum);
	
	public String delecttype(Integer typeid);
	
	public List<CompanyType> selectcompanytypelist();
	
	public String insertcompanytype(String name);
	
	public String delectcompanytype(Integer companytypeid);
	
	public List<Withdraw> selectwithdraw();
	
	public String updatewithdraw(Integer withdrawid);
	
	public abstract List<Checkout> selectcheckout();

	public abstract String updatecheckout(Integer paramInteger, double paramDouble);
}
