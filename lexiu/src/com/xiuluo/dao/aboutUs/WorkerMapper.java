package com.xiuluo.dao.aboutUs;

import java.util.List;

import com.xiuluo.model.aboutUs.Worker;

public interface WorkerMapper {
    int deleteByPrimaryKey(Integer workerid);

    int insert(Worker record);

    int insertSelective(Worker record);

    Worker selectByPrimaryKey(Integer workerid);

    Worker selectByPhone(String phone);

    int updateByPrimaryKeySelective(Worker record);

    int updateByPrimaryKey(Worker record);
    
    List<Worker> selectworkerbyworktype();
    
    List<Worker> selectallworker();
    
    List<Worker> selectWorkerManageByPhone(String phone , Integer isok);
    
    List<Worker> selectbycompanyid(Integer companyid);
   
    List<Worker> selectbytypeid(Integer typeid);
}