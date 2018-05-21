package com.xiuluo.service.aboutUs;

import java.util.List;

import com.xiuluo.model.MineAssess;
import com.xiuluo.model.aboutUs.RechargeSet;
import com.xiuluo.model.aboutUs.User;
import com.xiuluo.model.aboutUs.UserBankcard;
import com.xiuluo.model.aboutUs.Worker;

public interface MineService {
	public User selectuserbyuserid(Integer userid);//根据用户ID查询用户
	
	public Worker selectworkerbyphone(String phone);//根据手机查询师傅
	
	public List<RechargeSet> selectwalletbyuserid(Integer userid);//查询用户流水
	
	public List<MineAssess> selectassessbyuserid(Integer userid);//查询用户评价
	
	public String usertowork(String nickname,String birthday,Integer age ,String sex,
			String phone,String contacttel,String companies,String companiestel,
			String skill ,String idcard,String worktype, List<String> paths,Integer userid);//用户注册零工
	
	public String updateuser(Integer userid,String nickname ,String birthday,String sex,
			String phone,Integer age);//完善用户信息
	
	public String addbankcard(Integer userid,String cardnumber ,String bankname,String name,
			String phone);//添加用户银行卡信息
	
	public List<UserBankcard> selectuserbank(Integer userid);//查询用户银行卡信息
	
	public String deleteuserbank(Integer cardid);//删除银行卡信息
	
	public String savecomment(Integer id ,String type , String content , String code);
}
