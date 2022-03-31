package com.sns.follow;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.follow.bo.FollowBO;

@RequestMapping("/follow")
@RestController
public class FollowRestController {
	@Autowired
	private FollowBO followBO;
	@RequestMapping("/{followerId}")
	public Map<String, Object> follow(
			@PathVariable int followerId ,
			HttpServletRequest request){
		Map<String, Object> result = new HashMap<>();
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		// insert DB
		followBO.addFollow(userId, followerId);
		if(userId == null) {
			result.put("result", "error");
			result.put("error-message","로그인이 되어있지 않습니다.");
		}
		result.put("result", "success");
		return result;
	}
	
}
