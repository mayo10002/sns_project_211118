package com.sns.follow;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/follow")
@RestController
public class FollowRestController {

	@RequestMapping("/{userId}")
	public Map<String, Object> follow(
			@PathVariable int userId ,
			HttpServletRequest request){
		Map<String, Object> result = new HashMap<>();
		
	}
	
}
