package com.xiuluo.service.aboutUs.impl;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import com.xiuluo.model.aboutUs.HomeBanner;
import com.xiuluo.model.aboutUs.Order;
import com.xiuluo.model.aboutUs.User;
import com.xiuluo.model.aboutUs.Worker;
import com.xiuluo.model.aboutUs.WorkerGps;
import com.xiuluo.service.aboutUs.UserService;
import com.xiuluo.util.AllMapper;
import com.xiuluo.util.CommonUtils;

import java.io.InputStream;
import java.io.OutputStream; 
import java.io.OutputStreamWriter; 
import java.net.URLConnection;
import java.net.*;
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


@Service("userService")
public class UserServiceImpl extends AllMapper implements UserService {
	
	/**
	 * 用户登录验证信息
	 */
	@Override
	public String userlogin(String phone, String password) {
		User user = userMapper.selectByPhone(phone);
		String message;
		if(user != null){
			if(password.equals(user.getPassword())){
				message="成功";
			}else{
				message="账号或密码错误,请重新输入!";
			}
		}else{
			message = "账号或密码有误,请重新输入!";
		}
		return message;
	}
	
	/**
	 * 添加用户信息
	 */
	@Override
	public String adduser(String phone, String password) {
		User user = userMapper.selectByPhone(phone);
		String message;
		if(user != null){
			message="该手机号已被注册!";
		}else{
			User newuser = new User();
			String random = CommonUtils.getRandomVcode();			
			newuser.setNickname("user_"+random);
			newuser.setPassword(password);
			newuser.setPhone(phone);
			newuser.setAddtime(new Date());
			int code = userMapper.insertSelective(newuser);
			if(code == 1){
				message="成功";
			}else{
				message="注册失败!";
			}
		}
		return message;
	}

	/**
	 * 修改密码
	 */
	@Override
	public String updateuserpassword(String phone, String password) {
		User user = userMapper.selectByPhone(phone);
		String message;
		if(user != null){
			user.setPassword(password);
			user.setAddtime(new Date());
			int code = userMapper.updateByPrimaryKeySelective(user);
			if(code == 1){
				message="成功";
			}else{
				message="修改失败!";
			}
		}else{
			message = "手机号有误,请重新输入!";
		}
		return message;
	}

	/**
	 * 获取首页轮播图
	 */
	@Override
	public List<HomeBanner> homebannerlist() {
		List<HomeBanner> list = homeBannerMapper.selectAll();
		return list;
	}

	/**
	 * 师傅登录
	 */
	@Override
	public String worklogin(String phone, String password) {
		Worker worker = workerMapper.selectByPhone(phone);
		String message;
		if(worker != null){
			if(worker.getIsok() == 0){
				message="还未审核通过";
			}else if(CommonUtils.isEmptyString(worker.getNickname())){
				message="用户信息未完善";
			}else if(password.equals(worker.getPassword())){
				message="成功";
			}else{
				message="账号或密码错误,请重新输入!";
			}
		}else{
			message = "账号或密码有误,请重新输入!";
		}
		return message;
	}

	/**
	 * 师傅端注册
	 */
	@Override
	public String addwork(String phone, String password) {
		Worker worker = workerMapper.selectByPhone(phone);
		String message;
		if(worker != null){
			message="该手机号已被注册!";
		}else{
			Worker newwork = new Worker();
			String random = CommonUtils.getRandomVcode();		
			newwork.setNickname("work_"+random);
			newwork.setPassword(password);
			newwork.setPhone(phone);
			newwork.setCompanyid(0);
			newwork.setGrade((float) 5);
			newwork.setAddtime(new Date());
			int code = workerMapper.insertSelective(newwork);
			if(code == 1){
				message="成功";
			}else{
				message="注册失败!";
			}
		}
		return message;
	}

	/**
	 * 师傅修改密码
	 */
	@Override
	public String updateworkerpassword(String phone, String password) {
		Worker worker = workerMapper.selectByPhone(phone);
		String message;
		if(worker != null){
			worker.setPassword(password);
			worker.setAddtime(new Date());
			int code = workerMapper.updateByPrimaryKeySelective(worker);
			if(code == 1){
				message="成功";
			}else{
				message="修改失败!";
			}
		}else{
			message = "手机号有误,请重新输入!";
		}
		return message;
	}

	
	/**
	 *修改用户常用地址 
	 */
	@Override
	public String updateuseraddress(Integer userid, String address) {
		//查询用户信息
		User user = userMapper.selectByPrimaryKey(userid);
		String message="修改失败!";
		if(user != null){
			user.setAddress(address);
			int code = userMapper.updateByPrimaryKeySelective(user);
			if(code == 1){
				message="成功";
			}else{
				message="修改失败!";
			}
		}
		return message;
	}

	
	/**
	 *查询用户常用地址 
	 */
	@Override
	public String selectaddress(Integer userid) {
		User user = userMapper.selectByPrimaryKey(userid);
		String address = null;
		if(user != null){
			address = user.getAddress();
		}
		return address;
	}

	/**
	 * 查询历史登录信息
	 */
	@Override
	public List<Order> historyaddress(Integer userid) {
		List<Order> list = orderMapper.selectdonebyuserid(userid);
		return list;
	}

	/**
	 * 通过手机查询用户信息
	 */
	@Override
	public User selectuserbyphone(String phone) {
		User user = userMapper.selectByPhone(phone);
		return user;
	}

	/**
	 * 通过手机查询师傅信息
	 */
	@Override
	public Worker selectworkbyphone(String phone) {
		Worker worker = workerMapper.selectByPhone(phone);
		return worker;
	}

	/**
	 * 发送短信验证码
	 */
	public String sendSms(String userid,String pass,String mobiles,String msg,String time)
    {
        String result = "-12";
		try
        {
            Document doc;
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db=dbf.newDocumentBuilder();
            InputStream is=getSoapInputStream(userid,pass,mobiles,msg,time);
            if(is!=null){
	            doc=db.parse(is);
	            NodeList nl=doc.getElementsByTagName("SendMessagesResult");
	            Node n=nl.item(0);
	            result=n.getFirstChild().getNodeValue();
	            is.close();
            }            	
            return result;
        }
        catch(Exception e)
        {
        	System.out.print("SmsSoap.sendSms error:"+e.getMessage());
            return "-12";
        }
    }
	
	public String getSoapSmssend(String userid,String pass,String mobiles,String msg,String time)
    {
        try 
        {
            String soap = "";
            soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
            		+"<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
            		+"<soap:Body>"
            		+"<SendMessages xmlns=\"http://tempuri.org/\">"
            		+"<uid>"+userid+"</uid>"
            		+"<pwd>"+pass+"</pwd>"
            		+"<tos>"+mobiles+"</tos>"
            		+"<msg>"+msg+"</msg>"
            		+"<otime>"+time+"</otime>"            		
            		+"</SendMessages>"
            		+"</soap:Body>"
            		+"</soap:Envelope>";                        
            return soap;
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
	
	  
	private InputStream getSoapInputStream(String userid,String pass,String mobiles,String msg,String time)throws Exception
    {
		URLConnection conn = null;
		InputStream is = null;
        try
        {
            String soap=getSoapSmssend(userid,pass,mobiles,msg,time);            
            if(soap==null)
            {
                return null;
            }
            try{
             
            	URL url=new URL("http://service2.winic.org:8003/Service.asmx");     
            	
            	conn=url.openConnection();
            	conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);                           
                conn.setRequestProperty("Content-Length", Integer.toString(soap.length()));
                conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
                conn.setRequestProperty("HOST","service2.winic.org");
                conn.setRequestProperty("SOAPAction","\"http://tempuri.org/SendMessages\"");

                OutputStream os=conn.getOutputStream();
                OutputStreamWriter osw=new OutputStreamWriter(os,"utf-8");
                osw.write(soap);
                osw.flush();                
            }catch(Exception ex){
            	System.out.print("SmsSoap.openUrl error:"+ex.getMessage());
            }                                            
            try{
            	is=conn.getInputStream();            	
            }catch(Exception ex1){
            	System.out.print("SmsSoap.getUrl error:"+ex1.getMessage());
            }
            
            return is;   
        }
        catch(Exception e)
        {
        	System.out.print("SmsSoap.InputStream error:"+e.getMessage());
            return null;
        }
    }

	
	/**
	 * 修改支付密码
	 */
	@Override
	public String updatepaypassword(String phone, String password) {
		User user = userMapper.selectByPhone(phone);
		String message;
		if(user != null){
			user.setPaypassword(password);
			int code = userMapper.updateByPrimaryKeySelective(user);
			if(code == 1){
				message = "成功";
			}else{
				message = "操作失败";
			}
		}else{
			message = "信息有误";
		}
		return message;
	}

	
	/**
	 * 插入一条师傅经纬度信息
	 */
	@Override
	public String insertworkergps(String phone) {
		//通过手机查询师傅ID
		Worker worker = workerMapper.selectByPhone(phone);
		String message = null;
		if(worker != null){
			//插入师傅经纬度
			WorkerGps gps = new WorkerGps();
			gps.setWorkerid(worker.getWorkerid());
			gps.setLongitude(null);
			gps.setLatitude(null);
			gps.setAddtime(new Date());
			int code = workerGpsMapper.insertSelective(gps);
			if(code == 1){
				message = "成功";
			}else{
				message = "操作失败";
			}		
		}
		return message;
	}

	
	/**
	 * 查询最新一条订单信息 
	 */
	@Override
	public Order searchneworder(Integer userid) {
		Order order = orderMapper.selectneworder(userid);
		return order;
	}
}
