package cn.linkpower.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;

@RestController
public class TestController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/getorder")
	@HystrixCommand(fallbackMethod="getOrderError")
	public String getOrder() {
		// order 使用rpc 远程调用技术 调用 会员服务
		String memberUrl = "http://app-bunana-member/getMember";
		String result = restTemplate.getForObject(memberUrl, String.class);
		System.out.println("会员服务调用订单服务,result:" + result);
		return result;
	}
	
	public String getOrderError(){
		return "getOrderError";
	}
}
