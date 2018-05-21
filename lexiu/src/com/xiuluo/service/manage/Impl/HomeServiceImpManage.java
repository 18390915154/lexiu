package com.xiuluo.service.manage.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xiuluo.model.aboutUs.Feedback;
import com.xiuluo.model.aboutUs.HomeBanner;
import com.xiuluo.model.aboutUs.User;
import com.xiuluo.model.aboutUs.Worker;
import com.xiuluo.service.manage.HomeServiceManage;
import com.xiuluo.util.AllMapper;
import com.xiuluo.util.CommonUtils;

@Service("homeServiceManage")
public class HomeServiceImpManage extends AllMapper implements HomeServiceManage {

	/**
	 * 查询所有轮播图信息
	 */
	@Override
	public List<HomeBanner> selectbannerlist() {
		List<HomeBanner> list = homeBannerMapper.selectAll();
		return list;
	}

	
	/**
	 * 删除轮播图信息
	 */
	@Override
	public String delectbanner(Integer bannerid) {
		int code = homeBannerMapper.deleteByPrimaryKey(bannerid);
		String message;
		if(code ==1){
			message="成功";
		}else{
			message = "删除失败";
		}
		return message;
	}


	/**
	 * 修改轮播图
	 */
	@Override
	public String updatebanner(Integer bannerid, String path) {
		HomeBanner homebanner = homeBannerMapper.selectByPrimaryKey(bannerid);
		if(homebanner != null){
			homebanner.setPicurl(path);
		}
		int code = homeBannerMapper.updateByPrimaryKeySelective(homebanner);
		String message;
		if(code == 1){
			message = "成功";
		}else{
			message = "修改成功";
		}
		return message;
	}

	
	/**
	 * 查询意见反馈
	 */
	@Override
	public List<Feedback> selectAll() {
		List<Feedback> list = feedBackMapper.selectAll();
		if(list != null && list.size()>0){
			for (Feedback feedback : list) {
				if(feedback.getWorkerid() != 0){
					Worker worker = workerMapper.selectByPrimaryKey(feedback.getWorkerid());
					feedback.setName(worker.getNickname());
				}else if(feedback.getUserid() != 0){
					User user = userMapper.selectByPrimaryKey(feedback.getUserid());
					feedback.setName(user.getNickname());
				}
			}
		}
		return list;
	}
	
}
