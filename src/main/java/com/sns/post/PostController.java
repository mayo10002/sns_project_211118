package com.sns.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;

@RequestMapping("/post")
@Controller
public class PostController {
	@Autowired
	private PostBO postBO;
	@RequestMapping("/post_list_view")
	public String postListView(
			Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		int userId = (int)request.getAttribute("userId");
		List<Post> postList = postBO.getPostList();
		model.addAttribute("postList", postList);
		
		return "test/example";
		/// 지금 postcontroller가 필요없는 상황? > timeline Controller로 전부 이전? > list view는 cardview가 되는 거? 
	}
}
