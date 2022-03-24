package com.sns.timeline;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;

@RequestMapping("/timeline")
@RestController
public class TimelineRestController {
	@Autowired 
	private PostBO postBO;
	@PostMapping("/create_post")
	public Map<String, Object> createPost(
			@RequestParam("content") String content,
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request){
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		// DB insert
		int row = postBO.addPost(userLoginId, userId, content, file);
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		if(row < 1) {
			result.put("result", "error");
			result.put("error_message", "게시에 실패했습니다.");
		}
		
		// return
		return result;
	}
}
