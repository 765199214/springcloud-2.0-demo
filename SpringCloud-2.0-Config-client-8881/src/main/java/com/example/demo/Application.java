package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		try {
			SpringApplication.run(Application.class, args);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Value("${foo}")
	private String foo;
	
	@Value("${democonfigclient.message}")
	private String msg;
	
	//创建一个文件中不存在的信息
//	@Value("${ss}")
//	private String ss;
	
	@RequestMapping(value = "/hi")
	public String hi(){
		return foo+" "+msg;
	}
	
//	@RequestMapping("/ss")
//	public String ss(){
//		return ss;
//	}
}
