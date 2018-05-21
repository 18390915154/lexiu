
package com.xiuluo.controller;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Logger;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alipay.util.PayActivityUtil;
import com.xiuluo.dao.aboutUs.CheckoutMapper;
import com.xiuluo.dao.aboutUs.RechargeSetMapper;
import com.xiuluo.dao.aboutUs.RechargeWorkerMapper;
import com.xiuluo.dao.aboutUs.UserMapper;
import com.xiuluo.dao.aboutUs.WorkerMapper;
import com.xiuluo.model.aboutUs.Checkout;
import com.xiuluo.model.aboutUs.Order;
import com.xiuluo.model.aboutUs.RechargeSet;
import com.xiuluo.model.aboutUs.RechargeWorker;
import com.xiuluo.model.aboutUs.User;
import com.xiuluo.model.aboutUs.Worker;
import com.xiuluo.service.aboutUs.OrderService;
import com.xiuluo.service.aboutUs.PayService;
import com.xiuluo.util.CommonUtils;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("api/pay")
public class PayApi {

	Logger logger = Logger.getLogger(PayApi.class);
	
	@Resource
	private PayService payService;
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private WorkerMapper workerMapper;
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private CheckoutMapper checkoutMapper;
	
	@Resource
	private RechargeSetMapper rechargeSetMapper;
	
	@Resource
	private RechargeWorkerMapper rechargeWorkerMapper;
	
	
	/**
	 * @author 
	 * @date 20151225
	 * @function 阿里支付宝支付方法
	 */
	@RequestMapping("/alipayUrl")
	public void alipayUrl(HttpServletRequest request,HttpServletResponse response){
		try{
			String callback = request.getParameter("jsonpcallback");
			String orderid = request.getParameter("orderid");
			request.setCharacterEncoding("UTF-8");
			if(CommonUtils.isEmptyString(orderid)){
				String message = "缺少接口参数";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			//调用支付宝支付
			String url = "https://www.lerlex.com/lexiu/api/pay/alipayReturn";
			Order order = orderService.selectorderdetail(CommonUtils.parseInt(orderid, 0));
			PayActivityUtil payActivityUtil = new PayActivityUtil();
			
			//获取数据1
//			String orderInfo = payActivityUtil.getOrderInfo(order.getOrdernum(), order.getPrice().toString(),url);
//			String sign = payActivityUtil.sign(orderInfo);
//			sign = URLEncoder.encode(sign, "UTF-8");
//			String linkString = orderInfo + "&sign=\"" + sign + "\"&"
//					+ "sign_type=\"RSA\"";
//			PrintWriter out = response.getWriter();
//			out.print(map);
			
			//获取数据2
			Map<String,String> map = payActivityUtil.buildAuthInfoMap();
			map.put("url", url);
			JSONObject json = new JSONObject();
			json.put("map", map);
			response.getWriter().println(json);
			logger.info("阿里支付宝支付方法结束");
		}catch(Exception e){
			e.printStackTrace();
			logger.error("阿里支付宝支付报错", e);
		}
	}
	
	
	/**
	 * @author 刘博
	 * @date 20151225
	 * @function 阿里支付宝支付回掉函数
	 */
	@RequestMapping("/alipayReturn")
	public void alipayReturn(HttpServletRequest request,HttpServletResponse response){
		try {
			logger.info("阿里支付宝回掉函数开始");
			Map<String,String> params = new HashMap<String,String>();
			Map<?, ?> requestParams = request.getParameterMap();
			for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			
			//交易号
			String trade_no= new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//订单号
			String out_trade_no= new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			logger.info(trade_status);
			logger.info(out_trade_no);
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
//			if(AlipayNotify.verify(params)){//验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				//请在这里加上商户的业务逻辑程序代码
				//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				logger.info("开始逻辑处理");
				if(trade_status.equals("TRADE_FINISHED")){
					logger.info("支付宝支付回调接口返回状态为:TRADE_FINISHED");
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					//注意：
					//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				} else if (trade_status.equals("TRADE_SUCCESS")){
					logger.info("支付宝支付回调接口返回状态为:TRADE_SUCCESS");
					if(!CommonUtils.isEmptyString(out_trade_no)){
						logger.info("阿里支付回调方法返回订单ordernum="+out_trade_no);
						Order order = orderService.searchbyordernum(out_trade_no);
						order.setType((short)3);
						//更新订单状态
						payService.updateorder(order);
						
						//增加师傅余额
						String str;
						String aaa;
						Worker worker = workerMapper.selectByPrimaryKey(order.getWorkerid());
						str = order.getOrdernum().substring(0,1);
						 Checkout checkout = new Checkout();
			              if ("B".equals(str)){
			                checkout = checkoutMapper.selectByType(Integer.valueOf(2));
			              } else if ("A".equals(str)){
			                checkout = checkoutMapper.selectByType(Integer.valueOf(1));
			              }
						BigDecimal b = new BigDecimal(1+(checkout.getNumber()/100));
						//原有收入加上现在的收入
						BigDecimal pay1 = worker.getMoney().add(order.getPrice().divide(b,2,RoundingMode.HALF_UP));
						//现在的收入
						BigDecimal tostr = order.getPrice().divide(b,2,RoundingMode.HALF_UP);
						aaa = tostr.toString();
						worker.setMoney(pay1);
						workerMapper.updateByPrimaryKeySelective(worker);
						
						//增加用户流水记录
						RechargeSet usermoney = new RechargeSet();
						usermoney.setUserid(order.getUserid());
						usermoney.setMoney(order.getPrice());
						usermoney.setDetail("维修支出");
						usermoney.setType((short)0);
						usermoney.setScore(0);
						usermoney.setDelstate((short)0);
						usermoney.setAddtime(new Date());
						rechargeSetMapper.insertSelective(usermoney);
						//增加师傅流水记录
						RechargeWorker workmoney = new RechargeWorker();
						workmoney.setWorkerid(order.getWorkerid());
						workmoney.setMoney(new BigDecimal(aaa));
						workmoney.setDetail("收入");
						workmoney.setType((short)0);
						workmoney.setScore(0);
						workmoney.setDelstate((short)0);
						workmoney.setAddtime(new Date());
						rechargeWorkerMapper.insertSelective(workmoney);
						
						logger.info("支付宝支付回调接口成功");

					}
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					//注意：
					//付款完成后，支付宝系统发送该交易状态通知
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				}
				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
//				System.out.println("success");	//请不要修改或删除
				//////////////////////////////////////////////////////////////////////////////////////////
//			}else{//验证失败
//				System.out.println("fail");
//			}
		} catch (Exception e) {
			logger.error("阿里支付宝支付回掉函数失败", e);
		}
		logger.info("阿里支付宝回掉函数结束");
	}
	
	
	
	/**
	 * @author 
	 * @date 20151225
	 * @function 阿里支付宝支付方法
	 */
	@RequestMapping("/addalipayUrl")
	public void addalipayUrl(HttpServletRequest request,HttpServletResponse response){
		try{
			String callback = request.getParameter("jsonpcallback");
			String money = request.getParameter("money");
			String type = request.getParameter("type");
			request.setCharacterEncoding("UTF-8");
			if(CommonUtils.isEmptyString(money)||
					CommonUtils.isEmptyString(type)){
				String message = "缺少接口参数";
				response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
				return;
			}
			//调用支付宝支付
			String url = "https://www.lerlex.com/lexiu/api/pay/addalipayReturn";
			PayActivityUtil payActivityUtil = new PayActivityUtil();
			
			String id;
			//获取id
			if(type.equals("0")){
				//获取用户ID
				if(request.getSession().getAttribute("userid")==null){
					String message = "超时";
					response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
					return;
				}
				id = request.getSession().getAttribute("userid").toString();
			}else{
				//获取用户ID
				if(request.getSession().getAttribute("workerid")==null){
					String message = "超时";
					response.getWriter().write(callback+"([ { name:'message',value:'"+message+"'}] );");
					return;
				}
				id = request.getSession().getAttribute("workerid").toString();
			}
			
			//生成提现订单
			Map<String,String> ordermap = payService.insertorder(CommonUtils.parseInt(id, 0), CommonUtils.parseInt(type, 0), 
					CommonUtils.parseDouble(money, 0));
			
			
			//获取数据2
			Map<String,String> map = payActivityUtil.buildAuthInfoMap();
			map.put("url", url);
			map.put("ordernum", ordermap.get("ordernum"));
			map.put("money", ordermap.get("money"));
			map.put("message", ordermap.get("message"));
			JSONObject json = CommonUtils.setobjecttojson(map);
			response.getWriter().write(callback+"([ { name:'map',value:'"+json+"'}] );");
			logger.info("阿里支付宝支付方法结束");
		}catch(Exception e){
			e.printStackTrace();
			logger.error("阿里支付宝支付报错", e);
		}
	}
	
	
	
	/**
	 * @author 刘博
	 * @date 20151225
	 * @function 阿里支付宝充值回掉函数
	 */
	@RequestMapping("/addalipayReturn")
	public void addalipayReturn(HttpServletRequest request,HttpServletResponse response){
		try {
			logger.info("阿里支付宝回掉函数开始");
			Map<String,String> params = new HashMap<String,String>();
			Map<?, ?> requestParams = request.getParameterMap();
			for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			
			//交易号
			String trade_no= new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//订单号
			String out_trade_no= new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			logger.info(trade_status);
			logger.info(out_trade_no);
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
//			if(AlipayNotify.verify(params)){//验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				//请在这里加上商户的业务逻辑程序代码
				//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				logger.info("开始逻辑处理");
				if(trade_status.equals("TRADE_FINISHED")){
					logger.info("支付宝支付回调接口返回状态为:TRADE_FINISHED");
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					//注意：
					//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				} else if (trade_status.equals("TRADE_SUCCESS")){
					logger.info("支付宝支付回调接口返回状态为:TRADE_SUCCESS");
					if(!CommonUtils.isEmptyString(out_trade_no)){
						logger.info("阿里支付回调方法返回订单ordernum="+out_trade_no);
						Order order = orderService.searchbyordernum(out_trade_no);
						if(order.getUserid() == null){
							//增加师傅余额
							Worker worker = workerMapper.selectByPrimaryKey(order.getWorkerid());
							//原有收入加上现在的收入
							BigDecimal pay1 = worker.getMoney().add(order.getPrice());
							//现在的收入
							BigDecimal tostr = order.getPrice();
							worker.setMoney(pay1);
							workerMapper.updateByPrimaryKeySelective(worker);
							//增加师傅流水记录
							RechargeWorker workmoney = new RechargeWorker();
							workmoney.setWorkerid(order.getWorkerid());
							workmoney.setMoney(tostr);
							workmoney.setDetail("充值");
							workmoney.setType((short)1);
							workmoney.setScore(0);
							workmoney.setDelstate((short)0);
							workmoney.setAddtime(new Date());
							rechargeWorkerMapper.insertSelective(workmoney);
						}else{
							//增加用户余额
							User user = userMapper.selectByPrimaryKey(order.getUserid());
							//原有收入加上现在的收入
							BigDecimal pay1 = user.getMoney().add(order.getPrice());
							//现在的收入
							BigDecimal tostr = order.getPrice();
							user.setMoney(pay1);
							userMapper.updateByPrimaryKeySelective(user);
							//增加用户流水记录
							RechargeSet usermoney = new RechargeSet();
							usermoney.setUserid(order.getUserid());
							usermoney.setMoney(tostr);
							usermoney.setDetail("充值");
							usermoney.setType((short)1);
							usermoney.setScore(0);
							usermoney.setDelstate((short)0);
							usermoney.setAddtime(new Date());
							rechargeSetMapper.insertSelective(usermoney);
						}
						logger.info("支付宝支付回调接口成功");

					}
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					//注意：
					//付款完成后，支付宝系统发送该交易状态通知
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				}
				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
//				System.out.println("success");	//请不要修改或删除
				//////////////////////////////////////////////////////////////////////////////////////////
//			}else{//验证失败
//				System.out.println("fail");
//			}
		} catch (Exception e) {
			logger.error("阿里支付宝支付回掉函数失败", e);
		}
		logger.info("阿里支付宝回掉函数结束");
	}
}
