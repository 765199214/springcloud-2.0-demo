package cn.linkpower.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.linkpower.hystrix.MemberFeignHystrix;

//引用注册中心上的别名
@FeignClient(value="app-bunana-member",fallback=MemberFeignHystrix.class)
public interface IMemberFeignService {
	
	//调用注册中心上指定别名的服务包含的请求方式
	@RequestMapping("/getMember")
	String getMember();
}
