package com.sns.follow;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/follow")
@RestController
public class FollowRestController {

	@RequestMapping("/{userId}")
	public Map<String, Object> follow(){
		
	}
	
}
