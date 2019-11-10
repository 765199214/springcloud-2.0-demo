package cn.linkpower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

@SpringBootApplication // springboot应用标识
@EnableEurekaClient // 表示服务，需要向注册中心注册信息
@EnableHystrix // 注解表示开启断路器
// @SpringCloudApplication //以上三个注解可以使用这一个注解替换
@EnableHystrixDashboard // 开启hystrix仪表盘
public class HystrixOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixOrderApplication.class, args);
	}

	// 使用ribbon实现请求效果
	@Bean
	@LoadBalanced // 能够解析注册中心上的别名
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	//请求进入仪表盘界面显示----必须配置
	@Bean
	public ServletRegistrationBean getServlet() {
		HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
		registrationBean.setLoadOnStartup(1);
		registrationBean.addUrlMappings("/hystrix.stream");
		registrationBean.setName("HystrixMetricsStreamServlet");
		registrationBean.setName("HystrixMetricsStreamServlet");
		return registrationBean;
	}
}
