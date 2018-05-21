package com.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	public static String appid="2017051107207547";
	
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088621617325003";
	// 商户的私钥
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIzhOTgt/JdQsCjILu0N6PcYT1TjDbfP8ekIrUZwy8Fxbg33MALP3f5g69Add2d4U2mSJz0FKGKK7IS36f0ikdMXmwZu+zkDmyKC8hIaRzFUqLtv9RXj3SpNpYdiKoiFRwMHpd0toY5O+U3J/O0zNKEEwShxxZHwToXEvtXBkXKHAgMBAAECgYBH169DXAg87hP3qCZuEzLjl40VP/jF7RBzg05B5tGxo3O5QX5FYNXXnHuFFRHIPU9kPfkuoAu5PRlikRuNUGzGVICTXL0bsx50tNVboPwlq9F96xl8W4n+U1aPJuubdjU3MgVxC2ScFI51UY5H4IsH/S4pwwJMlsBO73QRauNeGQJBAMh0J0OTgEssqA98xGkG3jec92T0DUywEyizIj8JK6pI3bxxc4dxJTOQNRQZBKy8+5qCflx55KYKrLD9MIEAJPMCQQCz6v7m5E5FCtUmLW1ykJJqLfA8XmNrv+5MqAt1qscPjKtknYFhc8SIqK6TURaraVtALPptE7t/rZBjEQiCSnEdAkAF/3vBSOOY3EA3HmueHOd/UIISkj7noYkhOaL3seWNldqUrexgDCGp1lHQRSNfq9P3fpobWEZgNYePUDQoWAHDAkEAmM40UguDglYR9AvPEedBwxZjHBKaZBAtp0NuADDcQhOaGalAEnGNcPe61XmgRFMF4jqRMoT40RarM8hqrqXk7QJBAKOFURvHpfbhTiMvPbvxxP+XLO9wJuXAu+4HzYWqwfp2T4KenhuiaviZcJCHZ2MtjZ/KIkezPIm7g4hGOuh9tYQ=";
/*	MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB 
*/	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
//	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
//	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
    //商家账号
	public static String ali_seller_email = "info@lerlex.com";
	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "C:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";
	
	
	//支付宝支付回调地址
    public static String apiPayReturn ="https://www.lerlex.com/lexiu/api/pay/alipayReturn";
//    public static String apiPayReturn ="http://192.168.100.100:8080/lexiu/api/pay/alipayReturn";

}
