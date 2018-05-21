package com.xiuluo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.*;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.sdk.app.EnvUtils;
import com.xiuluo.controller.manage.ShopApiManage;
import com.xiuluo.model.aboutUs.AboutUs;
import com.xiuluo.service.aboutUs.AboutUsService;

@Controller
@RequestMapping("/testController")
public class TestController {
	Logger logger = Logger.getLogger(TestController.class);
	@Autowired
	private AboutUsService aboutUsService;
	public AboutUsService getAboutUsService() {
		return aboutUsService;
	}
	public void setAboutUsService(AboutUsService aboutUsService) {
		this.aboutUsService = aboutUsService;
	}
	@RequestMapping("selectAboutUs")
	public void selectAboutUs(){
		AboutUs aboutUs = aboutUsService.selectAboutUs(1);
		System.out.println(aboutUs.getAboutContent());
	}
	
	/**
	 * 
	 */
	@RequestMapping("alipaytest")
	public static void alipaytest(){
		try {
			EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
			String key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIzhOTgt/JdQsCjILu0N6PcYT1TjDbfP8ekIrUZwy8Fxbg33MALP3f5g69Add2d4U2mSJz0FKGKK7IS36f0ikdMXmwZu+zkDmyKC8hIaRzFUqLtv9RXj3SpNpYdiKoiFRwMHpd0toY5O+U3J/O0zNKEEwShxxZHwToXEvtXBkXKHAgMBAAECgYBH169DXAg87hP3qCZuEzLjl40VP/jF7RBzg05B5tGxo3O5QX5FYNXXnHuFFRHIPU9kPfkuoAu5PRlikRuNUGzGVICTXL0bsx50tNVboPwlq9F96xl8W4n+U1aPJuubdjU3MgVxC2ScFI51UY5H4IsH/S4pwwJMlsBO73QRauNeGQJBAMh0J0OTgEssqA98xGkG3jec92T0DUywEyizIj8JK6pI3bxxc4dxJTOQNRQZBKy8+5qCflx55KYKrLD9MIEAJPMCQQCz6v7m5E5FCtUmLW1ykJJqLfA8XmNrv+5MqAt1qscPjKtknYFhc8SIqK6TURaraVtALPptE7t/rZBjEQiCSnEdAkAF/3vBSOOY3EA3HmueHOd/UIISkj7noYkhOaL3seWNldqUrexgDCGp1lHQRSNfq9P3fpobWEZgNYePUDQoWAHDAkEAmM40UguDglYR9AvPEedBwxZjHBKaZBAtp0NuADDcQhOaGalAEnGNcPe61XmgRFMF4jqRMoT40RarM8hqrqXk7QJBAKOFURvHpfbhTiMvPbvxxP+XLO9wJuXAu+4HzYWqwfp2T4KenhuiaviZcJCHZ2MtjZ/KIkezPIm7g4hGOuh9tYQ=";
			String publickey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzOlP3EMnIFe7pe/yPPNyzoZZQc1hiH8mCOXWUq0a3GWPNVS5Wn+3BCP3C16jRCjqNyfJIRymajljb7x/6G1/qXHNWc/ch1FhcXpY5Fr1i3ujuaqqwQYSGFIiTd3Y9nLOI5sMqoYrObvm6YpAPnut4Uqc6rtNPVvsBFIxqMPYuLthrFIQNAaGzH+Be1j+WVl9b45Sz0QU9ZwN/cIgaTIn8TBPB0JGgmSUu7Qkgx/9KcNSwhog2L/YSg5ArETCARVqW+DD2/3dOf6kHYUq0Ae5Yk/6M0Kq2FSNADtHHXUWX2nNQ/YI7qaLMCbUeO5q+OtT/PU+DGh6vVwHxvdE9rQuNwIDAQAB";
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do","2016080700189073",key,"UTF-8",publickey,"RSA2");
			AlipayTradePayRequest request = new AlipayTradePayRequest();
			request.setBizContent("{" +
			"\"out_trade_no\":\"20150320010101001\"," +
			"\"scene\":\"bar_code\"," +
			"\"auth_code\":\"28763443825664394\"," +
			"\"subject\":\"Iphone6 16G\"," +
			"\"buyer_id\":\"sxwcpx9118@sandbox.com\"," +
			"\"seller_id\":\"qwlauu6177@sandbox.com\"," +
			"\"total_amount\":88.88," +
			"\"discountable_amount\":8.88," +
			"\"undiscountable_amount\":80.00," +
			"\"body\":\"Iphone6 16G\"," +
			"      \"goods_detail\":[{" +
			"        \"goods_id\":\"apple-01\"," +
			"\"alipay_goods_id\":\"20010001\"," +
			"\"goods_name\":\"ipad\"," +
			"\"quantity\":1," +
			"\"price\":2000," +
			"\"goods_category\":\"34543238\"," +
			"\"body\":\"特价手机\"," +
			"\"show_url\":\"http://www.alipay.com/xxx.jpg\"" +
			"        }]," +
			"\"operator_id\":\"yx_001\"," +
			"\"store_id\":\"NJ_001\"," +
			"\"terminal_id\":\"NJ_T_001\"," +
			"\"alipay_store_id\":\"2016041400077000000003314986\"," +
			"\"extend_params\":{" +
			"\"sys_service_provider_id\":\"2088511833207846\"," +
			"\"hb_fq_num\":\"3\"," +
			"\"hb_fq_seller_percent\":\"100\"" +
			"    }," +
			"\"timeout_express\":\"90m\"," +
			"\"royalty_info\":{" +
			"\"royalty_type\":\"ROYALTY\"," +
			"        \"royalty_detail_infos\":[{" +
			"          \"serial_no\":1," +
			"\"trans_in_type\":\"userId\"," +
			"\"batch_no\":\"123\"," +
			"\"out_relation_id\":\"20131124001\"," +
			"\"trans_out_type\":\"userId\"," +
			"\"trans_out\":\"2088101126765726\"," +
			"\"trans_in\":\"2088101126708402\"," +
			"\"amount\":0.1," +
			"\"desc\":\"分账测试1\"," +
			"\"amount_percentage\":\"100\"" +
			"          }]" +
			"    }," +
			"\"sub_merchant\":{" +
			"\"merchant_id\":\"19023454\"" +
			"    }," +
			"\"disable_pay_channels\":\"credit_group\"," +
			"\"ext_user_info\":{" +
			"\"name\":\"李明\"," +
			"\"mobile\":\"16587658765\"," +
			"\"cert_type\":\"IDENTITY_CARD\"," +
			"\"cert_no\":\"362334768769238881\"," +
			"\"fix_buyer\":\"F\"" +
			"    }" +
			"  }");
			AlipayTradePayResponse response;
			response = alipayClient.execute(request);
			if(response.isSuccess()){
			System.out.println("调用成功");
			} else {
			System.out.println("调用失败");
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		alipaytest();
	}
}
