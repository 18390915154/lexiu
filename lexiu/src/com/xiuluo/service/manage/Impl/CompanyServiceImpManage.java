package com.xiuluo.service.manage.Impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.xiuluo.model.aboutUs.Company;
import com.xiuluo.model.aboutUs.CompanyType;
import com.xiuluo.model.aboutUs.Worker;
import com.xiuluo.service.manage.CompanyServiceManage;
import com.xiuluo.util.AllMapper;
import com.xiuluo.util.CommonUtils;


@Service("companyServiceManage")
public class CompanyServiceImpManage extends AllMapper implements CompanyServiceManage {

	/**
	 * 查询所有公司信息
	 */
	@Override
	public List<Company> selectallcompany() {
		List<Company> list = companyMapper.selectall();
		return list;
	}

	/**
	 * 新增公司信息
	 */
	@Override
	public String insertcompany(String name, String address, String contact, String phone, String path, String[] typeid) {
		Company company  = new Company();
		company.setName(name);
		company.setAddress(address);
		company.setContact(contact);
		company.setPhone(phone);
		company.setPicurl(path);
		for (String str : typeid) {
			String id = company.getTypeid();
			if(id == null){
				company.setTypeid(str);
			}else{
				company.setTypeid(id+","+str);
			}
		}
		String message;
		Map<String , String> map = CommonUtils.getPoint(address);
		BigDecimal latbd = new BigDecimal(map.get("lat"));
		company.setLatitude(latbd);
		BigDecimal linbd = new BigDecimal(map.get("lin"));
		company.setLongitude(linbd);
		int code = companyMapper.insertSelective(company);
		if(code == 1){
			message = "成功";
		}else{
			message = "添加失败";
		}	
		return message;
	}

	
	/**
	 *修改公司信息 
	 */
	@Override
	public String updatecompany(Integer companyid,String name, String address, String contact, String phone, String path, String[] typeid) {
		Company company = companyMapper.selectByPrimaryKey(companyid);
		company.setName(name);
		company.setAddress(address);
		company.setContact(contact);
		company.setPhone(phone);
		company.setPicurl(path);
		Map<String , String> map = CommonUtils.getPoint(address);
		BigDecimal latbd = new BigDecimal(map.get("lat"));
		company.setLatitude(latbd);
		BigDecimal linbd = new BigDecimal(map.get("lin"));
		company.setLongitude(linbd);
		for (String str : typeid) {
			String id = company.getTypeid();
			if(id == null){
				company.setTypeid(str);
			}else{
				company.setTypeid(id+","+str);
			}
		}
		int code = companyMapper.updateByPrimaryKeySelective(company);
		String message;
		if(code == 1){
			message = "成功";
		}else{
			message = "添加失败";
		}
		return message;
	}

	
	/**
	 * 删除公司信息
	 */
	@Override
	public String delectcompany(Integer companyid) {
		List<Worker> list = workerMapper.selectbycompanyid(companyid);
		String message;
		if(list != null && list.size() > 0){
			message = "该公司不可删除";
		}else{
			int code = companyMapper.deleteByPrimaryKey(companyid);
			if(code == 1){
				message = "成功";
			}else{
				message = "删除失败";
			}	
		}
		return message;
	}

	
	/**
	 * 查询所有分类
	 */
	@Override
	public List<CompanyType> seltype() {
		List<CompanyType> list = companyTypeMapper.selectall();
		return list;
	}

	
	/**
	 * 查询公司详情
	 */
	@Override
	public Company companydetail(Integer companyid) {
		Company company = companyMapper.selectByPrimaryKey(companyid);
		return company;
	}

}
