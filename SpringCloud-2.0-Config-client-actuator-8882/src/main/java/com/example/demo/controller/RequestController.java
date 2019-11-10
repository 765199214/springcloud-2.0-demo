package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RequestController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/toRefresh")
	public String toRefresh(){
		String result = "";
		try {
			result = restTemplate.postForObject("http://localhost:8882/actuator/refresh", null, String.class);
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
}
