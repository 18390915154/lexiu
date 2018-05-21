package com.xiuluo.dao.aboutUs;

import com.xiuluo.model.aboutUs.WorkerGps;

public interface WorkerGpsMapper {
    int deleteByPrimaryKey(Integer gpsid);

    int insert(WorkerGps record);

    int insertSelective(WorkerGps record);

    WorkerGps selectByPrimaryKey(Integer gpsid);

    int updateByPrimaryKeySelective(WorkerGps record);

    int updateByPrimaryKey(WorkerGps record);
   
    WorkerGps selectByWorkerid(Integer workerid);
    
}