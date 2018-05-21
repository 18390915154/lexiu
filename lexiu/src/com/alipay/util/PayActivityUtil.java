package com.alipay.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alipay.config.AlipayConfig;

public final class PayActivityUtil {
	// 商户PID
	public static final String PARTNER = "";
	// 商户收款账号
	public static final String SELLER = "";
	// 商户私钥，pkcs8格式
	public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAN1i+6VkuhBCzJqyT8of/3rqYbLGXqs8Knsz9mZMet4KAg0l5qf1rcsUCht3zgqL/icdToQIT6TZyFbphfrU0FddYJRfowkc/w7gyFQUAmCs7R297viibAnIWl6RM3wX9sja9r4tKW8Uy9DUVARPR2fFbWT7d7Zq+pPdJQQ//9/5AgMBAAECgYEAuGPuBrabhlmswpgLfuUhzAWVc+pnBSYql80Sx+ehWnhpdvv/XhtxMjixpmklsoEhIQD22V92vm4pv8HmtqE0GFNyA3n+p2KKH5+suOdqxoWOyT1Rf7xosI4NO7gCRLJid2fGVQmxyzpDZRsdkUgi7DKI5RvPPGEK4IlYDj071lUCQQDxRM/ZoqL8szCrCtvqXb8e2WZ8mxBCum7G4FjYjA7YuGvon8BA+yew+kq0o3j9DM7CkknBGUVmiV5iF3lukdYnAkEA6udmdkyOldZ4BZxDDtKnVz7dOqVVheaRrvLqc/EdyYG68iJ6xe4zX33NXInRTRQRfk792NJFxl109ms3W/+M3wJAWr8xFoDPoRCc+5iIAuzj1+yZWcDRKugIhpEXmcuEQguLuuFrZIWS9kQOGUdrWHKayiBP0Wt56Qk+ENMXf3km/QJBANWAiKJRJu+NRP1kb5aTZEFqA81/5GGVlSf75ZiHuFkkLnRRXmF0BQFv6C2JndB+wv+YcCSXyY9n9hzaijB17uUCQAUj2YW9FhIEx/fTaFmSB4UxI2tK04cRATdRf6z/dU5ghlMTMoMfSj6lv9Ht4oyIDIhTA7oyt9V/svQcpwP5cA4=";
//	public static final String RSA_PRIVATE = "MIICWwIBAAKBgQC8L80p0czZF2d66f4Gj+HHAvw8Dmp6Kx2vF05/60dKp9frfddJjHNtuOtSRbkOqMjrf6gB9LhlHFPxOsWJnBVbTGVsRA65mTXSVQQ3LaXA8wlXVVUx86+I+Usw1BMSPHxytMgXb++fzGjtoNzUahFYJEvflfWRBo8X2V4ajzYNCwIDAQABAoGATDkMX6Dz2QaqylVWp+/FYmdqJSUtqZgnKNFG4Xc55BpB0ceh2M+DvXCGGEfXYgGpiAGn2kr2GTY1JeNJOai32RYz3Y7KtLCTyz9KOD2H/TGHfYZ14XoM28Rg+vPe5uW8Ly8SOFPGWOO8fjhH6ciNU3BeYUbqCrEB5OytP+fDdQECQQDdH1Gy63J53ZxVBIz2lGCjkpgVBmaUUVvRkMURAwRqHVixYfvdk0S/Obr88YWGBhwoh8qRR7J7uwo8/38ghWeLAkEA2d6T5sOsFS9NguHv3dHwzS0sJtf9luoBqwihbqjp5EeUp1BlLL68w4KZfHHrI27PB0rSZQn5Kz2xt58v+pKggQJAWY9F1+HlC9bmasjdD0IpohbN4jYldg090zN/ndRLBW+9bJrzGFt9jLYlNi3cltwCq6fUM+OGNvcavnn9U3604QJAVNdhPx75tzI5e/th/j6QCEZMCEBaJ1evGJx+yOoMUvIxCEq9a4RHW9dSz+xIxf6TrmJLtj8BAf6/YyMhevF4gQJAHJb6VEr3Rv75xyylCOIfzXU5Sf+Nbmgj+PitMKc50J0Tir3K72egX8teCGKFjcaQyxOxpdRL9dtkM0dbfcuz2g==";
	// 支付宝公钥
	public static final String RSA_PUBLIC = "";
	/**
	 * @author 刘博
	 * @date 20151224
	 * @function 拼装数据
	 */
	public String getOrderInfo(String orderid,String price,String url) {

		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + AlipayConfig.partner + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" +  AlipayConfig.ali_seller_email+ "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + orderid + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + "booking playground" + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + "booking playground"+ "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + AlipayConfig.apiPayReturn
				+ "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}
	
	
	/**
	 * @author 刘博
	 * @date 20151224
	 * @function 拼装数据
	 */
	public String getOrderRefundInfo(String orderid) {

		
		// 服务接口名称， 固定值
		String	orderInfo = "service=\"refund_fastpay_by_platform_pwd\"";
		// 签约合作者身份ID
		orderInfo += "&partner=" + "\"" + AlipayConfig.partner + "\"";
		orderInfo += "&_input_charset=" + "\"" + AlipayConfig.input_charset + "\"";
		
		// 签约卖家支付宝账号
		orderInfo += "&seller_email=" + "\"" + AlipayConfig.ali_seller_email + "\"";

		// 商户网站唯一订单号
		orderInfo += "&refund_date=" + "\"" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "\"";
		orderInfo += "&batch_no=" + "\"" + new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"001" + "\"";
		orderInfo += "&batch_num=" + "\"" + "1" + "\"";
		orderInfo += "&detail_data=" + "\"" + orderid +"^0.05^退款理由" + "\"";

		
		// 商品名称
//		orderInfo += "&subject=" + "\"" + "booking playground" + "\"";

		// 商品详情
//		orderInfo += "&body=" + "\"" + "booking playground"+ "\"";

		// 商品金额
//		orderInfo += "&total_fee=" + "\"" + String.valueOf(area_Order.getfAreaBookingSpace()) + "\"";

		// 服务器异步通知页面路径
		/*orderInfo += "&notify_url=" + "\"" + "http://www.jsxin.com:8080/Sport/payController/alipayReturn"
				+ "\"";*/



		// 支付类型， 固定值
//		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
//		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
//		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
//		orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";
		return orderInfo;
	}
	/**
	 * @author 刘博
	 * @date 20151224
	 * @function RSA加密
	 */
	public String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}
	
	
	/**
	 * 拼装数据
	 * @param pid
	 * @param app_id
	 * @param target_id
	 * @param rsa2
	 * @return
	 */
	public Map<String, String> buildAuthInfoMap() {
		   Map<String, String> keyValues = new HashMap<String, String>();

		   // 商户签约拿到的app_id，如：2013081700024223
		   keyValues.put("app_id", AlipayConfig.appid);

		   // 商户签约拿到的pid，如：2088102123816631
		   keyValues.put("pid", AlipayConfig.partner);

		   // 服务接口名称， 固定值
		   keyValues.put("apiname", "com.alipay.account.auth");

		   // 商户类型标识， 固定值
		   keyValues.put("app_name", "mc");

		   // 业务类型， 固定值
		   keyValues.put("biz_type", "openservice");

		   // 产品码， 固定值
		   keyValues.put("product_id", "APP_FAST_LOGIN");

		   // 授权范围， 固定值
		   keyValues.put("scope", "kuaijie");

		   // 商户唯一标识，如：kkkkk091125
		   //keyValues.put("target_id", target_id);

		   // 授权类型， 固定值
		   keyValues.put("auth_type", "AUTHACCOUNT");

		   // 签名类型
		   keyValues.put("sign_type", "RSA");
		   
		   // 私钥
		   keyValues.put("rsa_private", AlipayConfig.private_key);
		   
		   // 公钥
		   keyValues.put("rsa_public", AlipayConfig.ali_public_key);

		   return keyValues;
		}
}
