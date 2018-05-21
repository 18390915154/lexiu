package com.xiuluo.service.manage.Impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.xiuluo.model.aboutUs.Company;
import com.xiuluo.model.aboutUs.Worker;
import com.xiuluo.service.manage.WorkerServiceManage;
import com.xiuluo.util.AllMapper;

@Service("workerServiceManage")
public class WorkerServiceImpManage extends AllMapper implements WorkerServiceManage  {

	/**
	 * 查询师傅信息
	 */
	@Override
	public List<Worker> seachworkerlist() {
		List<Worker> list = workerMapper.selectallworker();
		if(list != null && list.size()>0){
			for (Worker worker : list) {
				Company company = companyMapper.selectByPrimaryKey(worker.getCompanyid());
				if(company!=null){
					worker.setCompanyname(company.getName());	
				}
			}
		}
		return list;
	}

	
	/**
	 * 修改师傅信息
	 */
	@Override
	public String updateisok(String phone) {
		Worker worker = workerMapper.selectByPhone(phone);
		String message;
		if(worker != null){
			worker.setIsok((short)1);
			int code = workerMapper.updateByPrimaryKeySelective(worker);
			if(code == 1){
				message = "成功";
			}else{
				message = "修改失败";
			}
		}else{
			message = "修改失败";
		}
		return message;
	}

	
	/**
	 * 查询师傅详情
	 */
	@Override
	public Worker workerdetail(String phone) {
		Worker worker = workerMapper.selectByPhone(phone);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(worker.getBirthday() != null){
			worker.setFormatbirthday(sdf.format(worker.getBirthday()));			
		}
		Company company = companyMapper.selectByPrimaryKey(worker.getCompanyid());
		if(company!=null){
			worker.setCompanyname(company.getName());			
		}
		return worker;
	}
	
}
