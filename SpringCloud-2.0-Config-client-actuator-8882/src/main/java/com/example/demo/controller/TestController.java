package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope  //此注解修饰的类，会手动请求刷新时，进行数据刷新
public class TestController {
	@Value("${foo}")
	private String foo;
	
	@Value("${democonfigclient.message}")
	private String msg;
	
	@RequestMapping(value = "/hi")
	public String hi(){
		return foo+" "+msg;
	}
}
