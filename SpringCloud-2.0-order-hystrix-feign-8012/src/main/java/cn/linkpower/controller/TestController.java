package cn.linkpower.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.linkpower.service.IMemberFeignService;

@RestController
public class TestController {
	
	@Autowired
	private IMemberFeignService memberFeignService;
	
	@RequestMapping("/getMembers")
	public String getMembers(){
		return memberFeignService.getMember();
	}
}
