package com.xiuluo.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xiuluo.dao.aboutUs.CompanyTypeMapper;
import com.xiuluo.dao.aboutUs.TypeMapper;
import com.xiuluo.model.MineAssess;
import com.xiuluo.model.aboutUs.Assess;
import com.xiuluo.model.aboutUs.CompanyType;
import com.xiuluo.model.aboutUs.Order;
import com.xiuluo.model.aboutUs.RechargeWorker;
import com.xiuluo.model.aboutUs.Type;
import com.xiuluo.model.aboutUs.User;
import com.xiuluo.model.aboutUs.Worker;
import com.xiuluo.model.aboutUs.WorkerBankcard;
import com.xiuluo.service.aboutUs.MineService;
import com.xiuluo.service.aboutUs.OrderService;
import com.xiuluo.service.aboutUs.UserService;
import com.xiuluo.service.aboutUs.WorkerService;
import com.xiuluo.util.CommonUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("api/worker")
public class WorkerApi {

	@Resource
	private WorkerService workerService;
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private MineService mineService;

	@Resource
	private TypeMapper typeMapper;
	
	@Resource
	private CompanyTypeMapper companyTypeMapper;
	
	
	/**
	 * 报结工单
	 * @param response
	 * @param request
	 */
	@RequestMapping("retaliate")
	public void retaliate(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try{
			String callback = request.getParameter("jsonpcallback");
			String orderid = request.getParameter("orderid");
			String content = request.getParameter("content");
			String fault = request.getParameter("fault");
			String price = request.getParameter("price");
			response.setCharacterEncoding("utf-8");
			if(CommonUtils.isEmptyString(orderid)||
					CommonUtils.isEmptyString(content)||
					CommonUtils.isEmptyString(fault)){
				String message = "缺少接口参数";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			//报结用户订单
			String message = workerService.insertorder(CommonUtils.parseInt(orderid, 0),
					content, fault , price);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询师傅信息
	 * @param response
	 * @param request
	 */
	@RequestMapping("selwork")
	public void selwork(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try{
			String callback = request.getParameter("jsonpcallback");
			response.setCharacterEncoding("utf-8");
			//获取师傅ID
			if(request.getSession().getAttribute("workerid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String workerid = request.getSession().getAttribute("workerid").toString();
			String message = null;
			//查询师傅信息
			Worker worker = orderService.selectWorker(CommonUtils.parseInt(workerid, 0));
			JSONObject json = CommonUtils.setobjecttojson(worker);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'},{ name:'worker',value:'"+json+"'}] );");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 完善师傅信息
	 * @param response
	 * @param request
	 */
	@RequestMapping("workerPerfect")
	public void workerPerfect(@RequestParam(value="myfiles",required=false) MultipartFile[] myfiles,
			HttpServletRequest request,HttpServletResponse response,HttpSession session){
		try{
			String nickname = request.getParameter("nickname");
			String birthday = request.getParameter("birthday");
			String sex = request.getParameter("sex");
			String tel = request.getParameter("tel");
			String contacttel = request.getParameter("contacttel");
			String companies = request.getParameter("companies");
			String companiestel = request.getParameter("companiestel");
			String skill = request.getParameter("skill");
			String id_card = request.getParameter("id_card");
			String worktype = request.getParameter("worktype");
			String workerid = request.getParameter("workerid");
			response.setCharacterEncoding("utf-8");
			JSONObject json = new JSONObject();
			if(CommonUtils.isEmptyString(nickname)||
					CommonUtils.isEmptyString(birthday)||
					CommonUtils.isEmptyString(sex)||
					CommonUtils.isEmptyString(tel)||
					CommonUtils.isEmptyString(contacttel)||
					CommonUtils.isEmptyString(skill)||
					CommonUtils.isEmptyString(worktype)||
					CommonUtils.isEmptyString(id_card)||
					CommonUtils.isEmptyString(workerid)){
				String message = "缺少接口参数";
				json.put("message", message);
				response.getWriter().println(json.toString());
				return;
			}
			//获取File类型文件
			List<String> paths = new ArrayList<String>();
			for (MultipartFile multipartFile : myfiles) {
					String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
	                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
	                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(realPath, multipartFile.getOriginalFilename())); 
	                String filename = multipartFile.getOriginalFilename();
	                paths.add("lexiu/upload/"+filename);
			}
			//计算年龄
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(birthday);
			Date now = new Date();
			Integer age = now.getYear()-date.getYear();
			//更新师傅信息
			String message = workerService.updatework(CommonUtils.parseInt(workerid, 0),
					nickname, birthday,age, sex, tel,contacttel, 
					companies, companiestel, skill,id_card,worktype,paths);	
			json.put("message", message);
			response.getWriter().println(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 添加银行卡(师傅端)
	 * @param response
	 * @param request
	 */
	@RequestMapping("addbank")
	public void addbank(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try{
			String callback = request.getParameter("jsonpcallback");
			String cardnumber = request.getParameter("cardnumber");
			String bankname = request.getParameter("bankname");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String vcode = request.getParameter("vcode");
			response.setCharacterEncoding("utf-8");
			if(CommonUtils.isEmptyString(cardnumber)||
					CommonUtils.isEmptyString(bankname)||
					CommonUtils.isEmptyString(name)||
					CommonUtils.isEmptyString(phone)||
					CommonUtils.isEmptyString(vcode)){
				String message = "缺少接口参数";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			//获取师傅ID
			if(request.getSession().getAttribute("workerid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String workerid = request.getSession().getAttribute("workerid").toString();
			//判断验证码是否过期
			if(request.getSession().getAttribute("vcode")==null || request.getSession().getAttribute("vcodetime") == null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String sessionvcode = request.getSession().getAttribute("vcode").toString();
			Date vcodetime = (Date)request.getSession().getAttribute("vcodetime");
			if(CommonUtils.timeoutvcode(vcodetime)){
				String message = "验证码已经过期,请重新获取!";	
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;			
			}else if(!vcode.equals(sessionvcode)){
				String message = "验证码错误！";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			//添加师傅银行卡信息
			String message = workerService.addbankcard(CommonUtils.parseInt(workerid, 0),
					cardnumber, bankname, name, phone);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询我的银行卡
	 * @param response
	 * @param request
	 */
	@RequestMapping("mybank")
	public void mybank(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try{
			String callback = request.getParameter("jsonpcallback");
			response.setCharacterEncoding("utf-8");
			//获取师傅ID
			if(request.getSession().getAttribute("workerid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String workerid = request.getSession().getAttribute("workerid").toString();
			//查询师傅信息
			String message = null;
			List<WorkerBankcard> cardlist = workerService.selectmybank(CommonUtils.parseInt(workerid, 0));
			JSONArray json = CommonUtils.setlisttojson(cardlist);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'},{ name:'cardlist',value:'"+json+"'}] );");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除我的银行卡
	 * @param response
	 * @param request
	 */
	@RequestMapping("delbank")
	public void delbank(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try{
			String cardid = request.getParameter("cardid");
			JSONObject json = new JSONObject();// 所要返回的json值
			response.setCharacterEncoding("utf-8");
			if(CommonUtils.isEmptyString(cardid)){
				String message = "缺少接口参数";
				json.put("message", message);
				response.getWriter().print(json.toString());
				return;
			}
			//删除师傅银行卡
			String message = workerService.delectmybank(CommonUtils.parseInt(cardid, 0));
			json.put("message", message);
			response.getWriter().print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 我的钱包
	 * @param response
	 * @param request
	 */
	@RequestMapping("mywallet")
	public void mywallet(HttpServletRequest request,HttpServletResponse response){
		try{
			
			String callback = request.getParameter("jsonpcallback");
			response.setCharacterEncoding("utf-8");
			if(request.getSession().getAttribute("workerid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String workerid = request.getSession().getAttribute("workerid").toString();
			String money = null;
			JSONArray jsonlist = new JSONArray();
			String message = null;
			//查询师傅账户余额
			Worker work = workerService.selectbyWorkerid(CommonUtils.parseInt(workerid, 0));
			if(work != null){
				money = work.getMoney().toString();
			}
			//查询师傅流水信息
            List<RechargeWorker> walletlist = workerService.workerwalletlist(CommonUtils.parseInt(workerid, 0));
			if(walletlist != null && walletlist.size()>0){
            	jsonlist = CommonUtils.setlisttojson(walletlist);
            }
            response.getWriter().write(callback+"([{ name:'message',value:'"+message+"'},{ name:'money',value:'"+money+"'},{ name:'walletlist',value:'"+jsonlist+"'}]);");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询可以抢的单
	 * @param response
	 * @param request
	 */
	@RequestMapping("getorder")
	public void getorder(HttpServletRequest request,HttpServletResponse response){
		try{
			response.setCharacterEncoding("utf-8");
			String callback = request.getParameter("jsonpcallback");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			//获取师傅ID
//			if(request.getSession().getAttribute("workerid")==null){
//				String message = "超时";
//				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
//				return;
//			}
//			String workerid = request.getSession().getAttribute("workerid").toString();
			String workerid = "1";
			JSONArray json = new JSONArray();
			String message = null;
			//判断师傅名下是否存在未完成订单
			List<Order> paylist = workerService.searchpayorder(CommonUtils.parseInt(workerid, 0));
			if(paylist != null && paylist.size()>0){
				message = "有订单尚未支付,无法抢单";
			}else{
				//查询可接订单信息
				List<Order> orderlist = workerService.selectAcceptOrder(CommonUtils.parseInt(workerid, 0));
				List<Order> list = new ArrayList<Order>();
				if(orderlist.size()>0 && orderlist != null){
					for (Order order : orderlist) {
						//判断是维修订单还是家政订单
						if(order.getOrdernum().substring(0, 1).equals("B")){
							//判断订单时间是否超过5分钟
							Date date = new Date();
							int minutes = date.getMinutes() - order.getStarttime().getMinutes();
							int hours = date.getHours() - order.getStarttime().getHours();
							if(minutes < 5 && minutes >= 0 && hours == 0){
								//格式化时间并查询用户姓名
								order.setFormatstart(format.format(order.getStarttime()));
								Integer userid = order.getUserid();
								User user = mineService.selectuserbyuserid(userid);
								if(user != null){
									order.setUsername(user.getNickname());
									order.setAvatar(user.getAvatar());
									if(order.getCompanyid() == 0){
										//维修订单
										Type type = typeMapper.selectByPrimaryKey(order.getTypeid());
										if(type != null){
											order.setTypename(type.getName());
										}
									}else{
										//家政订单
										CompanyType companyType = companyTypeMapper.selectByPrimaryKey(order.getTypeid());
										if(companyType != null){
											order.setTypename(companyType.getName());
										}
									}
								}
								list.add(order);
							}
						}else{
							//格式化时间并查询用户姓名
							order.setFormatstart(format.format(order.getStarttime()));
							Integer userid = order.getUserid();
							User user = mineService.selectuserbyuserid(userid);
							if(user != null){
								order.setUsername(user.getNickname());
								order.setAvatar(user.getAvatar());
								if(order.getCompanyid() == 0){
									//维修订单
									Type type = typeMapper.selectByPrimaryKey(order.getTypeid());
									if(type != null){
										order.setTypename(type.getName());
									}
								}else{
									//家政订单
									CompanyType companyType = companyTypeMapper.selectByPrimaryKey(order.getTypeid());
									if(companyType != null){
										order.setTypename(companyType.getName());
									}
								}
							}
							list.add(order);
						}
					}				
				}
				json = CommonUtils.setlisttojson(list);
			}
			response.getWriter().write(callback+"([{ name:'message',value:'"+message+"'},{ name:'orderlist',value:'"+json+"'}]);");

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 抢单
	 * @param response
	 * @param request
	 */
	@RequestMapping("haveorder")
	public void haveorder(HttpServletRequest request,HttpServletResponse response){
		try{
			String callback = request.getParameter("jsonpcallback");
			String orderid = request.getParameter("orderid");
			response.setCharacterEncoding("utf-8");
			if(CommonUtils.isEmptyString(orderid)){
				String message = "缺少接口参数";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			if(request.getSession().getAttribute("workerid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String workerid = request.getSession().getAttribute("workerid").toString();
			//将师傅信息插入订单中
			String message = workerService.insertworkertoorder(CommonUtils.parseInt(orderid, 0),
					CommonUtils.parseInt(workerid, 0));
          response.getWriter().write(callback+"([{ name:'message',value:'"+message+"'},{ name:'orderid',value:'"+orderid+"'}]);");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询师傅信息
	 * @param response
	 * @param request
	 */
	@RequestMapping("gominepage")
	public void gominepage(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String callback = request.getParameter("jsonpcallback");
			response.setCharacterEncoding("utf-8");
			if(request.getSession().getAttribute("workerid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String workerid = request.getSession().getAttribute("workerid").toString();
			//查询用户信息
			String message = null;
			Worker worker = workerService.selectbyWorkerid(CommonUtils.parseInt(workerid, 0));
			if(worker!=null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				worker.setFormatbirthday(sdf.format(worker.getBirthday()));
			}
			JSONObject json = CommonUtils.setobjecttojson(worker);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'},{ name:'worker',value:'"+json+"'}] );");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询所有待完成订单
	 * @param request
	 * @param response
	 */
	@RequestMapping("undoneorder")
	public void undoneorder(HttpServletRequest request,
			HttpServletResponse response){
		try{
			String callback = request.getParameter("jsonpcallback");
			response.setCharacterEncoding("utf-8");
			if(request.getSession().getAttribute("workerid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String workerid = request.getSession().getAttribute("workerid").toString();
			String message = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//查询待完成订单
			List<Order> orderlist = workerService.undoneorder(CommonUtils.parseInt(workerid, 0));
			if(orderlist.size()>0 && orderlist != null){
				for (Order order : orderlist) {
					//判断订单类型
					switch(order.getType()){
					case 0:
						order.setTypename("待开始订单");
						break;
					case 1:
						order.setTypename("待报结订单");
						break;
					case 2:
						order.setTypename("待支付订单");
						break;
					case 3:
						order.setTypename("待评价订单");
						break;
					}
					//格式化时间并查询用户姓名
					order.setFormatstart(format.format(order.getStarttime()));
					Integer userid = order.getUserid();
					User user = mineService.selectuserbyuserid(userid);
					if(user != null){
						order.setUsername(user.getNickname());
						order.setAvatar(user.getAvatar());
						order.setUserphone(user.getPhone());
					}
					//格式化开始时间
					if(order.getStarttime() != null){
						order.setFormatstart(format.format(order.getStarttime()));
					}else{
						order.setFormatstart(null);
					}
					//格式化结束时间
					if(order.getOvertime() != null){
						order.setFormatover(format.format(order.getOvertime()));
					}else{
						order.setFormatover(null);
					}
					//格式化添加时间
					if(order.getAddtime() != null){
						order.setFormatadd(format.format(order.getAddtime()));
					}else{
						order.setFormatadd(null);
					}
					if(order.getCompanyid() == 0){
						//维修订单
						Type type = typeMapper.selectByPrimaryKey(order.getTypeid());
						if(type != null){
							order.setWorktypename(type.getName());
						}
					}else{
						//家政订单
						CompanyType companyType = companyTypeMapper.selectByPrimaryKey(order.getTypeid());
						if(companyType != null){
							order.setWorktypename(companyType.getName());
						}
					}
					if(order.getType() == 1){
						//判断订单时间是否超过10分钟
						Date date = new Date();
						Date starttime = order.getStarttime();
						int sb = date.getDate() - starttime.getDate();
						if(sb == 0){
							double cont = (date.getHours() - starttime.getHours())*60+(date.getMinutes() - starttime.getMinutes());
							if(cont >= 10){
								order.setGomap(0);						
							}else{
								order.setGomap(1);
							}
						}else if(sb == 1){
							double cont = ((date.getHours()+24) - starttime.getHours())*60+(date.getMinutes() - starttime.getMinutes());
							if(cont >= 10){
								order.setGomap(0);						
							}else{
								order.setGomap(1);
							}
						}
					}
				}
			}
			JSONArray json = CommonUtils.setlisttojson(orderlist);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'},{ name:'orderlist',value:'"+json+"'}] );");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 查询所有已完成订单
	 * @param request
	 * @param response
	 */
	@RequestMapping("doneorder")
	public void doneorder(HttpServletRequest request,
			HttpServletResponse response){
		try {
			String callback = request.getParameter("jsonpcallback");
			response.setCharacterEncoding("utf-8");
			if(request.getSession().getAttribute("workerid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String workerid = request.getSession().getAttribute("workerid").toString();
			String message = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//查询已完成订单
			List<Order> orderlist = workerService.doneorder(CommonUtils.parseInt(workerid, 0));
			if(orderlist.size()>0 && orderlist != null){
				for (Order order : orderlist) {
					//格式化时间并查询用户姓名
					order.setFormatstart(format.format(order.getStarttime()));
					order.setFormatover(format.format(order.getOvertime()));
					Integer userid = order.getUserid();
					User user = mineService.selectuserbyuserid(userid);
					if(user != null){
						order.setUsername(user.getNickname());
						order.setAvatar(user.getAvatar());
						order.setUserphone(user.getPhone());
					}
					if(order.getCompanyid() == 0){
						//维修订单
						Type type = typeMapper.selectByPrimaryKey(order.getTypeid());
						if(type != null){
							order.setWorktypename(type.getName());
						}
					}else{
						//家政订单
						CompanyType companyType = companyTypeMapper.selectByPrimaryKey(order.getTypeid());
						if(companyType != null){
							order.setWorktypename(companyType.getName());
						}
					}
				}
			}
			JSONArray json = CommonUtils.setlisttojson(orderlist);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'},{ name:'orderlist',value:'"+json+"'}] );");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 修改支付密码
	 * @param request
	 * @param response
	 */
	@RequestMapping("editpaypassword")
	public void editpaypassword(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try {
			String phone = request.getParameter("phone");
			String vcode = request.getParameter("vcode");
			String password = request.getParameter("password");
			String callback = request.getParameter("jsonpcallback");
			response.setCharacterEncoding("utf-8");
			if(CommonUtils.isEmptyString(phone)||
					CommonUtils.isEmptyString(vcode)||
					CommonUtils.isEmptyString(password)){
				String message = "缺少接口参数";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			//判断验证码是否过期
			if(request.getSession().getAttribute("vcode")==null || request.getSession().getAttribute("vcodetime") == null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String sessionvcode = request.getSession().getAttribute("vcode").toString();
			Date vcodetime = (Date)request.getSession().getAttribute("vcodetime");
			if(CommonUtils.timeoutvcode(vcodetime)){
				String message = "验证码已经过期,请重新获取!";	
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}else if(!vcode.equals(sessionvcode)){
				String message = "验证码错误！";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String message;
			//查询用户注册信息	
			message = workerService.updatepaypassword(phone, password);
			response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 我的评价
	 * @param response
	 * @param request
	 */
	@RequestMapping("myassess")
	public void myassess(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try{
			String callback = request.getParameter("jsonpcallback");
			response.setCharacterEncoding("utf-8");
			//获取用户ID
			if(request.getSession().getAttribute("workerid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String workerid = request.getSession().getAttribute("workerid").toString();
			String message = null;
			List<MineAssess> assesslist = workerService.selectassessbyworkerid(CommonUtils.parseInt(workerid, 0));
			JSONArray json = CommonUtils.setlisttojson(assesslist);
			response.getWriter().write(callback+"([{ name:'message',value:'"+message+"'},{ name:'assesslist',value:'"+json+"'}] );");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 更新师傅经纬度
	 * @param response
	 * @param request
	 */
	@RequestMapping("updategps")
	public void updategps(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try{
			String callback = request.getParameter("jsonpcallback");
			String longitude = request.getParameter("longitude");
			String latitude = request.getParameter("latitude");
			response.setCharacterEncoding("utf-8");
			if(CommonUtils.isEmptyString(longitude)||CommonUtils.isEmptyString(latitude)){
				String message = "缺少接口参数";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			//获取师傅ID
			if(request.getSession().getAttribute("workerid")==null){
				String message = "超时";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			String workerid = request.getSession().getAttribute("workerid").toString();
			//更新师傅经纬度信息
			String message = workerService.updateworkgps(CommonUtils.parseInt(workerid, 0), 
					longitude,latitude);
			response.getWriter().write(callback+"([{ name:'message',value:'"+message+"'}] );");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 订单详情
	 * @param response
	 * @param request
	 */
	@RequestMapping("orderdetail")
	public void orderdetail(HttpServletRequest request,
			HttpServletResponse response,HttpSession session){
		try{
			String orderid = request.getParameter("orderid");
			JSONObject json = new JSONObject();
			response.setCharacterEncoding("utf-8");
			if(CommonUtils.isEmptyString(orderid)){
				String message = "缺少接口参数";
				json.put("message", message);
				response.getWriter().write(json.toString());
				return;
			}
			String message = "成功";
			Order order = orderService.selectorderdetail(CommonUtils.parseInt(orderid, 0));
			//查询评论信息
			Assess assess = orderService.selectbyorderid(order.getOrderid());
			json.put("assess", assess);
			json.put("message", message);			
			json.put("orderdetail", order);
			response.getWriter().write(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
