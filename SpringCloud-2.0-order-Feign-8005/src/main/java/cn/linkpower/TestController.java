package cn.linkpower;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@Autowired
	private IMemberFeignService memberFeignService;
	
	@RequestMapping("/getMembers")
	public String getMember(){
		return memberFeignService.getMember();
	}
}
