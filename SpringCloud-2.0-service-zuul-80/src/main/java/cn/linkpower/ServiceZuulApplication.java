package cn.linkpower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient  //表明是需要注册至注册中心的一个服务
@EnableZuulProxy //开启zuul功能    默认开启了Ribbon本地负载均衡功能
public class ServiceZuulApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceZuulApplication.class, args);
	}
}
