package cn.linkpower.hystrix;

import org.springframework.stereotype.Component;

import cn.linkpower.service.IMemberFeignService;

@Component //必须使用此注解，将此类注册至IOC容器中，不加会报错
public class MemberFeignHystrix implements IMemberFeignService {
	
	@Override
	public String getMember() {
		return "request   error";
	}

}
