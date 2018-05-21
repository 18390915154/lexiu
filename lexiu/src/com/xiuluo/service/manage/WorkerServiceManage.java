package com.xiuluo.service.manage;

import java.util.List;

import com.xiuluo.model.aboutUs.Worker;

public interface WorkerServiceManage {

	public List<Worker> seachworkerlist();
	
	public String updateisok(String phone);
	
	public Worker workerdetail(String phone);
}
