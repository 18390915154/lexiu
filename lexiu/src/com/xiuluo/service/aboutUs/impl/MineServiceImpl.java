package com.xiuluo.service.aboutUs.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

import com.xiuluo.model.MineAssess;
import com.xiuluo.model.aboutUs.Assess;
import com.xiuluo.model.aboutUs.Feedback;
import com.xiuluo.model.aboutUs.Order;
import com.xiuluo.model.aboutUs.RechargeSet;
import com.xiuluo.model.aboutUs.User;
import com.xiuluo.model.aboutUs.UserBankcard;
import com.xiuluo.model.aboutUs.Worker;
import com.xiuluo.service.aboutUs.MineService;
import com.xiuluo.util.AllMapper;
import com.xiuluo.util.CommonUtils;

@Service("mineService")
public class MineServiceImpl extends AllMapper implements MineService {

	/**
	 * 根据用户ID查询用户
	 */
	@Override
	public User selectuserbyuserid(Integer userid) {
		User user = userMapper.selectByPrimaryKey(userid);
		return user;
	}
	
	
	/**
	 * 根据手机查询师傅
	 */
	@Override
	public Worker selectworkerbyphone(String phone) {
		Worker worker = workerMapper.selectByPhone(phone);
		return worker;
	}

	
	/**
	 * 查询用户流水
	 */
	@Override
	public List<RechargeSet> selectwalletbyuserid(Integer userid) {
		List<RechargeSet> recharge = rechargeSetMapper.selectByUserid(userid);
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		for (RechargeSet rechargeSet : recharge) {
			rechargeSet.setFormattime(sdf.format(rechargeSet.getAddtime()));
		}
		return recharge;
	}

	/**
	 * 查询用户评价
	 */
	@Override
	public List<MineAssess> selectassessbyuserid(Integer userid) {
		List<MineAssess> list = new ArrayList<MineAssess>();
		//查询订单评论信息
		List<Order> orderlist = orderMapper.selectdonebyuserid(userid);
		for (Order order : orderlist) {
			MineAssess mineassess = new MineAssess(); 
			//根据订单ID查询评论
			Assess assess = assessMapper.selectByOrderid(order.getOrderid());
			if(assess!=null){
				mineassess.setGrade(assess.getGrade());	
			}
			//根据师傅ID查询师傅姓名
			Worker worker = workerMapper.selectByPrimaryKey(order.getWorkerid());
			if(worker != null){				
				mineassess.setNickname(worker.getNickname());
				mineassess.setAvatar(worker.getAvatar());
			}
			list.add(mineassess);
		}
		return list;
	}

	/**
	 * 用户注册零工
	 */
	@Override
	public String usertowork(String nickname, String birthday, Integer age ,String sex, String phone, String contacttel,
			String companies, String companiestel, String skill, String id_card,String worktype,List<String> paths,Integer userid) {
		//查询用户信息
		String message;
		User user = userMapper.selectByPrimaryKey(userid);
		if(user != null){
		//通过手机号查询师傅信息
			Worker workers = workerMapper.selectByPhone(user.getPhone());
			if(workers !=null){
				message = "用户已注册零工";
			}else{
				Worker worker = new Worker();
				worker.setPhone(user.getPhone());
				worker.setPassword(user.getPassword());
				worker.setNickname(nickname);
				worker.setSex(sex);
				worker.setBirthday(CommonUtils.getDateFormat(birthday, "yyyy-MM-dd"));
				worker.setAge(age);
				worker.setAddtime(new Date());
				worker.setContactTel(contacttel);
				worker.setTel(phone);
				worker.setCompanyid(CommonUtils.parseInt(companies, 0));
				worker.setCompaniesTel(companiestel);
				worker.setSkill(skill);
				BigDecimal bd = new BigDecimal(0);
				worker.setMoney(bd);
				worker.setScore(0);
				worker.setGrade((float)5);
				worker.setIdcard(id_card);
				worker.setTypeid(CommonUtils.parseInt(worktype, 0));
				//循环获取文件路径
				for (int i=0 ;i<paths.size();i++) {
					worker.setIdentityFront(paths.get(0));
					worker.setIdentityRear(paths.get(1));
					worker.setCredentialsFront(paths.get(2));
					worker.setCredentialsRear(paths.get(3));
				}
				int code = workerMapper.insertSelective(worker);
				if(code == 1){
					message = "成功";
				}else{
					message = "注册失败!";
				}
			}
		}else{
			message = "注册失败!";
		}
		return message;
	}

		
	/**
	 * 完善个人信息
	 */
	@Override
	public String updateuser(Integer userid,String nickname, String birthday, String sex, String phone,Integer age) {
		User user = userMapper.selectByPrimaryKey(userid);
		String message ;
		if(user!=null){
			user.setNickname(nickname);
			user.setBirthday(CommonUtils.getDateFormat(birthday, "yyyy-MM-dd"));
			user.setSex(sex);
			user.setPhone(phone);
			user.setAge(age);
			user.setAddtime(new Date());
			int code = userMapper.updateByPrimaryKeySelective(user);
			if(code ==1){
				message="操作成功!";
			}else{
				message="操作失败!";
			}
		}else{
			message = "操作失败!";
		}
		return message;
	}

	
	/**
	 *添加用户银行卡 
	 */
	@Override
	public String addbankcard(Integer userid, String cardnumber, String bankname, String name, String phone) {
		UserBankcard userbank= userbankCardMapper.selectByCardnumber(cardnumber);
		String message;
		if(userbank != null){
			message = "银行信息有误!";
		}else{
			UserBankcard newbank = new UserBankcard();
			newbank.setAddtime(new Date());
			newbank.setBankname(bankname);
			newbank.setCardnumber(cardnumber);
			newbank.setPhone(phone);
			newbank.setUserid(userid);
			newbank.setUsername(name);
			int code = userbankCardMapper.insertSelective(newbank);
			if(code ==1){
				message="新增成功!";
			}else{
				message="新增失败!";
			}
		}
		return message;
	}

	
	/**
	 *查询我的银行卡信息 
	 */
	@Override
	public List<UserBankcard> selectuserbank(Integer userid) {
		List<UserBankcard> list = userbankCardMapper.selectByUserid(userid);
		return list;
	}

	
	/**
	 * 删除银行卡信息
	 */
	@Override
	public String deleteuserbank(Integer cardid) {
		int code = userbankCardMapper.deleteByPrimaryKey(cardid);
		String message;
		if(code ==1){
			message="删除成功!";
		}else{
			message="删除失败!";
		}
		return message;
	}

	
	/**
	 * 保存反馈信息
	 */
	@Override
	public String savecomment(Integer id,String type, String content, String code) {
		Feedback feedback = new Feedback();
		if(code.equals("0")){
			//用户
			feedback.setUserid(id);
			feedback.setWorkerid(0);
		}else{
			//师傅
			feedback.setUserid(0);
			feedback.setWorkerid(id);
		}
		feedback.setContent(content);
		feedback.setType(Short.valueOf(type));
		feedback.setAddtime(new Date());
		int num = feedBackMapper.insertSelective(feedback);
		String message;
		if(num == 1){
			message = "评价成功";
		}else{
			message="评价失败";
		}
		return message;
	}

}
