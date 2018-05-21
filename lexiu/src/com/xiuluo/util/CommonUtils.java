/**
 * 
 */
package com.xiuluo.util;

import java.io.File;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.json.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qiuch
 * 
 */
public class CommonUtils {

	/**
	 * 读取配置文件
	 */
	public static Properties properties = new Properties();

	static {
		try {
			String path = "config.properties";
			InputStream inStream = CommonUtils.class.getClassLoader().getResourceAsStream(path);
			if (inStream == null) {
				inStream = CommonUtils.class.getClassLoader().getResourceAsStream("/" + path);
			}
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 检查路径是否存在，不存在则创建路径
	 * 
	 * @param path
	 */
	public static void checkPath(String path) {
		String[] paths = null;
		if (path.contains("/")) {
			paths = path.split(File.separator);
		} else {
			paths = path.split(File.separator + File.separator);
		}
		if (paths == null || paths.length == 0) {
			return;
		}
		String pathdir = "";
		for (String string : paths) {
			pathdir = pathdir + string + File.separator;
			File file = new File(pathdir);
			if (!file.exists()) {
				file.mkdir();
			}
		}
	}

	/**
	 * 判断String是否为空
	 * 
	 */
	public static boolean isEmptyString(String value) {
		if (value == null || value.length() == 0) {
			return true;
		}
		return false;
	}
	  
    /**
     * 只判断多个String是否为空(无论有没全角或半角的空格)
     * 若非空则返回true,否则返回false
     * @param str
     * @return boolean
     */
    public static boolean isEmptyAllString(String ...str) {
        if(null == str) return true;
        for (String s : str) {
            if(isEmptyString(s)){
            	return true;
            }
        }
        return false;
    }
     
	/**
	 * String -> int
	 * 
	 * @param String
	 * @return int
	 */
	public static int parseInt(String string, int def) {
		if (isEmptyString(string))
			return def;
		Integer num = def;
		try {
			num = Integer.parseInt(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	public static short parseShort(String string, short def) {
		if (isEmptyString(string))
			return def;
		short num = def;
		try {
			num = Short.parseShort(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	/**
	 * String -> long
	 * 
	 * @param String
	 * @return long
	 */
	public static long parseLong(String string, long def) {
		if (isEmptyString(string))
			return def;
		long num;
		try {
			num = Long.parseLong(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	/**
	 * String -> double
	 * 
	 * @param String
	 * @return long
	 */
	public static double parseDouble(String string, double def) {
		if (isEmptyString(string))
			return def;
		double num;
		try {
			num = Double.parseDouble(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	/**
	 * String -> float
	 * 
	 * @param String
	 * @return long
	 */
	public static float parseFloat(String string, float def) {
		if (isEmptyString(string))
			return def;
		float num;
		try {
			num = Float.parseFloat(string);
		} catch (Exception e) {
			num = def;
		}
		return num;
	}

	/**
	 * String -> float
	 * 
	 * @param String
	 * @return long
	 */
	public static float parseFloat(String string, float def, int digit) {
		if (isEmptyString(string))
			return def;
		float num;
		try {
			num = Float.parseFloat(string);
		} catch (Exception e) {
			num = def;
		}
		if (digit > 0 && num != def) {
			BigDecimal bigDecimal = new BigDecimal(num);
			float twoDecimalP = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			return twoDecimalP;
		} else {
			return num;
		}
	}

	/**
	 * @param date
	 * @param string
	 * @return
	 */
	public static String getTimeFormat(Date date, String string) {
		SimpleDateFormat sdFormat;
		if (isEmptyString(string)) {
			sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else {
			sdFormat = new SimpleDateFormat(string);
		}
		try {
			return sdFormat.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	public static int hasNext(List<?> a) {
		if (a != null && a.size() > 0) {
			return 1;
		}
		return 0;
	}

	/**
	 * MD5加密
	 * 
	 * @param 要加密的String
	 * @return 加密后String(大写)
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			// 使用MD5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str).toUpperCase();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 格式化时间 String转换Date "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param 需要格式化的时间和格式
	 * @return 格式化之后的时间
	 */
	public static Date getDateFormat(String date, String format) {
		if (isEmptyString(date))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 过滤字符串中的可能存在XSS攻击的非法字符
	 * 
	 * @param value
	 * @return
	 */
	public static String cleanXSS(String value) {
		value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
		value = value.replaceAll("'", "& #39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replaceAll("script", "");
		return value;
	}

	/**
	 * 获取用户当前请求的IP地址
	 * 
	 * @param request
	 * @return
	 */

	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/**
	 * 获取随机6位验证码
	 * 
	 * @return
	 */
	public static String getRandomVcode() {
		Random random = new Random();
		String result = "";
		for (int i = 0; i < 6; i++) {
			result += random.nextInt(10);
		}
		return result;
	}

	// 文件访问路径
	public static String getFileRootUrl() {
		return properties.getProperty("fileRootUrl");
	}
	//工程访问路径
	public static String getWebRootUrl() {
		return properties.getProperty("webRootUrl");
	}
	//上传文件的目录
	public static String getLocationPath() {
		return properties.getProperty("uploadFilePath");
	}
	
	
	/**
	 * 将map转为json返回
	 * @param response
	 * @param map
	 */
	public static void setMaptoJson(HttpServletResponse response, Map<String, Object> map) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();
		;
		String json = gson.toJson(map);
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 判断验证码是否过期
	 * @param session
	 * @return
	 * @throws ParseException 
	 */
	public static boolean timeoutvcode(Date vcodetime) throws ParseException{
		boolean code = false ;
		SimpleDateFormat formatter = new SimpleDateFormat();
		Date date = new Date();
		long i = date.getMinutes()-vcodetime.getMinutes();
		if(i > 30){
			code = true;
		}
		return code;
	}
	
	
	/**
	 * 将list转为json格式
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static JSONArray setlisttojson(List list){
		JSONArray json = JSONArray.fromObject(list);
		return json;
	}
	
	
	/**
	 * 将object转为json
	 * @param obj
	 * @return
	 */
	public static JSONObject setobjecttojson(Object obj){
		JSONObject json = JSONObject.fromObject(obj);
		return json;
	}
	
	
	/**
     * 逆向匹配接口
     * 根据地址名称，匹配得到经纬度坐标
     * @param address
     * @return
     */
	public static Map<String,String> getPoint(String address){  
		HashMap<String,String> map = new HashMap<String,String>();
        try {       
           java.io.InputStream l_urlStream;    
           java.net.URL l_url = new java.net.URL("http://api.map.baidu.com/geocoder/v2/?address="+address.replaceAll(" ", "")+"&output=json&ak=702632E1add3d4953d0f105f27c294b9&callback=showLocation");    
           java.net.HttpURLConnection l_connection = (java.net.HttpURLConnection) l_url.openConnection();    
           l_connection.connect();    
           l_urlStream = l_connection.getInputStream();    
           java.io.BufferedReader l_reader = new java.io.BufferedReader(new java.io.InputStreamReader(l_urlStream));     
           String str=l_reader.readLine();  
           //用经度分割返回的网页代码  
           String s=","+"\""+"lat"+"\""+":";  
           String strs[]=str.split(s, 2);  
           String s1="\""+"lng"+"\""+":";  
          String a[]=strs[0].split(s1, 2);  
          map.put("lin",a[1]);  
          s1="}"+","+"\"";  
         String a1[]=strs[1].split(s1, 2);  
         map.put("lat",a1[0]);  
       } catch (Exception e) {    
           e.printStackTrace();    
       }    
        return map;
   }
}
