package com.xiuluo.dao.aboutUs;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiuluo.model.aboutUs.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer orderid);
    
    int deleteByOrdernum(String ordernum);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderid);
    
    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    List<Order> selectTypeByTypeid(@Param("typeid")Integer typeid);
    
    List<Order> selectTypeByCompanyid(@Param("companyid")Integer companyid);
    
    List<Order> selectundonebyuserid(Integer userid);

    List<Order> selectdonebyuserid(Integer userid);

    List<Order> selectundonebyworkerid(Integer workerid);
    
    List<Order> selectdonebyworkerid(Integer workerid);
    
    List<Order> selectbyworkerid(Integer workerid);
    
    List<Order> selectall();
    
    int selectCountOrderNum();
    
    Order newuserorder(Integer userid);
    
    Order selectbyordernum(String ordernum);

    Order selectneworder(Integer userid);
    
    List<Order> selectpayorder(Integer workerid);
    
    Order selectuserfirstorder(Integer userid);
    
    Order selectworkerfirstorder(Integer workerid);
}