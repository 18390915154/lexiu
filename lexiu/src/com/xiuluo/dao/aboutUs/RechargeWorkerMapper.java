package com.xiuluo.dao.aboutUs;

import java.util.List;

import com.xiuluo.model.aboutUs.RechargeWorker;

public interface RechargeWorkerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RechargeWorker record);

    int insertSelective(RechargeWorker record);

    RechargeWorker selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RechargeWorker record);

    int updateByPrimaryKey(RechargeWorker record);
    
    List<RechargeWorker> selectByWorkerid(Integer workerid);
   
    List<RechargeWorker> selectAll();
}