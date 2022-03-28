package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	@Autowired
	private CommentBO commentBO;
	@PostMapping("/create")
	public Map<String, Object> commentCreate(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			HttpServletRequest request){
		Map<String, Object> result = new HashMap<>();
		
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		//insert DB 
		commentBO.createComment(userId, postId, content);
		
		// null
		if (userId == null ) {
			result.put("result", "error");
			result.put("errorMessage","댓글 게시에 실패했습니다. 다시 입력해주세요.");
		}
		result.put("result", "success");
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> commentDelete(
			@RequestParam("commentId")int commentId,
			HttpServletRequest request){
		Map<String, Object> result = new HashMap<>();
		
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		commentBO.deleteCommentByCommentIdAndUserId(commentId, userId);
		// null
		if (userId == null ) {
			result.put("result", "error");
			result.put("error_message","로그인이 되어있지 않습니다. 로그인을 다시 해주세요.");
		}
		result.put("result", "success");
		return result;
	}
}
