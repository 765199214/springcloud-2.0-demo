package cn.linkpower.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class TestController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	public static int requestCount = 1;
	
	@RequestMapping("/getMemberByDiscoverUrl")
	public String disCoverClientUrl(){
		String serviceUrl = getService()+"/getMember";
		System.out.println(String.valueOf(serviceUrl));
		return String.valueOf(serviceUrl);
	}
	
	@RequestMapping("/getMemberValue")
	public String getDisCoverMember(){
		
		if(StringUtils.isEmpty(getService())){
			return "serviceUrl == null";
		}
		String serviceUrl = getService()+"/getMember";
		String result = restTemplate.getForObject(serviceUrl, String.class);
		return result;
	}
	
	private String getService(){
		List<ServiceInstance> instances = discoveryClient.getInstances("app-bunana-member");
		if (instances == null || instances.size() == 0) {
			return null;
		}
		int size = instances.size();
		System.out.println("个数："+size);
		int index = requestCount % size;
		requestCount++;
		System.out.println("请求数："+String.valueOf(requestCount));
		return instances.get(index).getUri().toString();
	}
}
