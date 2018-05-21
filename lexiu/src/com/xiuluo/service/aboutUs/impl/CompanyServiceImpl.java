package com.xiuluo.service.aboutUs.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import com.xiuluo.model.aboutUs.Company;
import com.xiuluo.model.aboutUs.CompanyType;
import com.xiuluo.model.aboutUs.Order;
import com.xiuluo.model.aboutUs.Type;
import com.xiuluo.service.aboutUs.CompanyService;
import com.xiuluo.util.AllMapper;
import com.xiuluo.util.CommonUtils;


@Service("companyService")
public class CompanyServiceImpl extends AllMapper implements CompanyService {

	/**
	 * 查询附近公司信息
	 */
	@Override
	public List<Company> nearcompany(String type) {
		CompanyType type1 = companyTypeMapper.selectByName(type);
		List<Company> list = new ArrayList<Company>();
		if(type1 != null){
			list = companyMapper.nearcompany(String.valueOf(type1.getCompanytypeid()));	
		}
		return list;
	}

	/**
	 * 新增用户订单
	 */
	@Override
	public String insertorder(Integer companyid,Integer userid, String address, String starttime, String detail, String longitude,
			String latitude, String type,List<String> paths) {
		String message = null;
		try {
			CompanyType type1 = companyTypeMapper.selectByName(type);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Order order = new Order();
			order.setUserid(userid);
			order.setCompanyid(companyid);
			order.setAddress(address);
			order.setStarttime(new Date());
			order.setDetail(detail);
			order.setTypeid(type1.getCompanytypeid());
			BigDecimal blatiude = new BigDecimal(latitude);
			order.setLatitude(blatiude);
			BigDecimal blongitude = new BigDecimal(longitude);
			order.setLongitude(blongitude);
			order.setType((short)0);
			order.setAddtime(new Date());
			//生成订单号
			Calendar calendar = Calendar.getInstance();
			Integer year = calendar.get(Calendar.YEAR);
			String nowDay = calendar.get(Calendar.DAY_OF_YEAR) + "";
			if (nowDay.length() == 2) {
				nowDay = "0" + nowDay;
			}
			if (nowDay.length() == 1) {
				nowDay = "00" + nowDay;
			}
			Date date = new Date();
			String nowHour = date.getHours() + "";
			if (nowHour.length() == 1) {
				nowHour = "0" + nowHour;
			}
			// 当前小时时间内段内的订单数量
			String num = orderMapper.selectCountOrderNum() + "";
			if (CommonUtils.isEmptyString(num)) {
				num = "000";
			}
			if (num.length() == 1) {
				num = "00" + num;
			}
			if (num.length() == 2) {
				num = "0" + num;
			}
			String ordernum = "A" + year + nowDay + nowHour + num;
			order.setOrdernum(ordernum);
			//循环list获取图片路径
			int i = 0;
			String url = null;
			for (String str : paths) {
				if(i == 0){
					url = str;
					i++;
				}else{
					url = url+","+str;
				}
			}
			order.setPicurl(url);
			int code = orderMapper.insertSelective(order);
			if(code == 1){
				message = "成功";
			}else{
				message = "操作失败";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	/**
	 * 查询所有分类信息
	 */
	@Override
	public List<Type> selectalltype() {
		List<Type> list = typeMapper.selectall();
		return list;
	}

}
