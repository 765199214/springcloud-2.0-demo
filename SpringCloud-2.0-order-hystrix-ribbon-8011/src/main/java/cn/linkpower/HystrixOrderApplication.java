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

@SpringBootApplication // springbootӦ�ñ�ʶ
@EnableEurekaClient // ��ʾ������Ҫ��ע������ע����Ϣ
@EnableHystrix // ע���ʾ������·��
// @SpringCloudApplication //��������ע�����ʹ����һ��ע���滻
@EnableHystrixDashboard // ����hystrix�Ǳ���
public class HystrixOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixOrderApplication.class, args);
	}

	// ʹ��ribbonʵ������Ч��
	@Bean
	@LoadBalanced // �ܹ�����ע�������ϵı���
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	//��������Ǳ��̽�����ʾ----��������
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
