package com.xiuluo.util;

import javax.annotation.Resource;

import com.xiuluo.dao.aboutUs.AssessMapper;
import com.xiuluo.dao.aboutUs.BankLogoMapper;
import com.xiuluo.dao.aboutUs.CheckoutMapper;
import com.xiuluo.dao.aboutUs.CompanyMapper;
import com.xiuluo.dao.aboutUs.CompanyTypeMapper;
import com.xiuluo.dao.aboutUs.FeedbackMapper;
import com.xiuluo.dao.aboutUs.GoodsMapper;
import com.xiuluo.dao.aboutUs.HomeBannerMapper;
import com.xiuluo.dao.aboutUs.OrderMapper;
import com.xiuluo.dao.aboutUs.RechargeSetMapper;
import com.xiuluo.dao.aboutUs.RechargeWorkerMapper;
import com.xiuluo.dao.aboutUs.ShopBannerMapper;
import com.xiuluo.dao.aboutUs.ShopMapper;
import com.xiuluo.dao.aboutUs.TypeMapper;
import com.xiuluo.dao.aboutUs.UserBankcardMapper;
import com.xiuluo.dao.aboutUs.UserMapper;
import com.xiuluo.dao.aboutUs.WithdrawMapper;
import com.xiuluo.dao.aboutUs.WorkerBankcardMapper;
import com.xiuluo.dao.aboutUs.WorkerGpsMapper;
import com.xiuluo.dao.aboutUs.WorkerMapper;

public class AllMapper {
	@Resource
	public UserMapper userMapper;
	
	@Resource
	public WorkerMapper workerMapper;
	
	@Resource
	public HomeBannerMapper homeBannerMapper;
	
	@Resource
	public ShopMapper shopMapper;

	@Resource
	public GoodsMapper goodsMapper;
	
	@Resource
	public ShopBannerMapper shopBannerMapper;
	
	@Resource
	public AssessMapper assessMapper;
	
	@Resource
	public UserBankcardMapper userbankCardMapper;
	
	@Resource
	public WorkerBankcardMapper workerbankCardMapper;
	
	@Resource
	public FeedbackMapper feedBackMapper;
	
	@Resource
	public OrderMapper orderMapper;
	
	@Resource
	public RechargeSetMapper rechargeSetMapper;
	
	@Resource
	public RechargeWorkerMapper rechargeWorkerMapper;
	
	@Resource
	public WorkerGpsMapper workerGpsMapper;
	
	@Resource
	public BankLogoMapper bankLogoMapper;
	
	@Resource
	public CompanyMapper companyMapper;
	
	@Resource
	public TypeMapper typeMapper;
	
	@Resource
	public CompanyTypeMapper companyTypeMapper;
	
	@Resource
	public WithdrawMapper withdrawMapper;
	
	@Resource
	public CheckoutMapper checkoutMapper;
}
