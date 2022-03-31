package com.sns.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
@RequestMapping("/post")
@RestController
public class PostRestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PostBO postBO;
	
	@RequestMapping("/post_list")
	public List<Post> postList(){
		return postBO.getPostList();
	}
	/**
	 * 글쓰기
	 * @param content
	 * @param file
	 * @param request
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> createPost(
			@RequestParam("content") String content,
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request){
		HttpSession session = request.getSession();
		Map<String, Object> result = new HashMap<>();
		int userId = (int)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		if( userLoginId == null ) {
			result.put("result", "error");
			logger.error("로그인 세션이 없습니다.");
			return result;
		}
		// DB insert
		int row = postBO.addPost(userLoginId, userId, content, file);
		
		result.put("result", "success");
		if(row < 1) {
			result.put("result", "error");
			result.put("error_message", "게시에 실패했습니다.");
		}
		
		// return
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("postId")int postId,
			HttpSession session){
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer)session.getAttribute("userId");
		if(userId == null) {
			logger.error("[post delete] not found login session. postId:{}",postId);
			result.put("result", "error");
			result.put("error_message", "로그인을 다시 해주세요.");
			return result;
		}
		
		// Delete DB
		int row = postBO.deletePost(postId, userId);
		result.put("result","success");
		if(row < 1) {
			result.put("result","error");
			result.put("error_message","삭제에 실패했습니다.");
		}
		
		return result;
	}
	
}
