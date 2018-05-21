package com.xiuluo.service.aboutUs.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiuluo.dao.aboutUs.AboutUsMapper;
import com.xiuluo.model.aboutUs.AboutUs;
import com.xiuluo.service.aboutUs.AboutUsService;

@Service("aboutUsService")
public class AboutUsServiceImpl implements AboutUsService{
	@Autowired
	private AboutUsMapper aboutUsMapper;
	public AboutUsMapper getAboutUsMapper() {
		return aboutUsMapper;
	}
	public void setAboutUsMapper(AboutUsMapper aboutUsMapper) {
		this.aboutUsMapper = aboutUsMapper;
	}
	@Override
	public AboutUs selectAboutUs(Integer aboutId) {
		AboutUs aboutUs = aboutUsMapper.selectByPrimaryKey(aboutId);
		return aboutUs;
	}

}
