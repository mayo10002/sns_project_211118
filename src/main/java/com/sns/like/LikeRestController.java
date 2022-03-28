package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

@RequestMapping("/like")
@RestController
public class LikeRestController {
	@Autowired
	private LikeBO likeBO;
	//	url: "/like/" + postId
//	@RequestMapping("/like/{postId}")
//	public Map<String, Object> like(
//			@PathVariable int postId,
//			HttpServletRequest request){
//		
//	}
	@RequestMapping("/{postId}")
	public Map<String, Object> like(
			@PathVariable int postId,
			HttpServletRequest request){
		Map<String, Object> result = new HashMap<>();
		
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		//Insert DB
		likeBO.addLike(postId, postId);
		// userId - null result
		if( userId == null ) {
			result.put("result", "error");
			result.put("error-message","로그인이 되어있지 않습니다.");
		}
		result.put("result", "success");
				
		return result;
	}
}
