package com.xiuluo.controller.manage;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiuluo.model.aboutUs.RechargeSet;
import com.xiuluo.model.aboutUs.RechargeWorker;
import com.xiuluo.model.aboutUs.UserBankcard;
import com.xiuluo.model.aboutUs.WorkerBankcard;
import com.xiuluo.service.manage.MoneyServiceManage;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("manage/money")
public class MoneyApiManage {

	@Resource
	private MoneyServiceManage moneyServiceManage;
	
	
	/**
	 * 查询用户流水信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("seachusermoney")
	public void seachusermoney(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//查询用户流水
			List<RechargeSet> list = moneyServiceManage.selectuser();
			json.put("usermoneylist", list);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询用户流水信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("seachworkermoney")
	public void seachworkermoney(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//查询师傅流水
			List<RechargeWorker> list = moneyServiceManage.selectworker();
			json.put("workermoneylist", list);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询用户流水信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("seachuserbank")
	public void seachuserbank(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//查询用户银行卡
			List<UserBankcard> list = moneyServiceManage.selectuserbank();
			json.put("userbankcard", list);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询用户流水信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("seachworkerbank")
	public void seachworkerbank(HttpServletRequest request,
			HttpServletResponse response){
		try{
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			//查询用户流水
			List<WorkerBankcard> list = moneyServiceManage.selectworkerbank();
			json.put("workerbankcard", list);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
