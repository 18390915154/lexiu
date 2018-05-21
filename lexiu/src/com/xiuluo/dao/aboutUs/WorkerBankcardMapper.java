package com.xiuluo.dao.aboutUs;

import java.util.List;

import com.xiuluo.model.aboutUs.WorkerBankcard;

public interface WorkerBankcardMapper {
    int deleteByPrimaryKey(Integer cardid);

    int insert(WorkerBankcard record);

    int insertSelective(WorkerBankcard record);

    WorkerBankcard selectByPrimaryKey(Integer cardid);
    
    WorkerBankcard selectByCardnumber(String cardnumber);
   
    List<WorkerBankcard> selectByWorkerid(Integer workerid);
    
    List<WorkerBankcard> selectAll();

    int updateByPrimaryKeySelective(WorkerBankcard record);

    int updateByPrimaryKey(WorkerBankcard record);
}