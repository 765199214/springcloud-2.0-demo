package cn.linkpower;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

//指向eureka注册中心上的别名
@FeignClient("app-bunana-member")
public interface IMemberFeignService {
	
	//指明别名对应的服务的接口
	@RequestMapping("/getMember")
	String getMember();
}
