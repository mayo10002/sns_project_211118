package com.sns.profile;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.follow.bo.FollowBO;
import com.sns.follow.model.Follow;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.profile.model.ProfileView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@RequestMapping("/profile")
@Controller
public class ProfileController {
	@Autowired
	private UserBO userBO;
	@Autowired
	private PostBO postBO;
	@Autowired
	private FollowBO followBO;
	@RequestMapping("/profile_view/{userLoginId}")
	public String profileView(
			@PathVariable String userLoginId,
			Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		ProfileView profileView = new ProfileView();
		User user = userBO.getUserByLoginId(userLoginId);
		int userId = user.getId();
		List<Post> post = postBO.getPostByUserId(userId);
		List<Follow> follower = followBO.getFollowerListByUserId(userId);
		List<Follow> followee = followBO.getFolloweeListByFollowingId(userId);
		profileView.setUser(user);
		profileView.setPost(post);
		model.addAttribute("profileView",profileView);
		model.addAttribute("follower",follower);
		model.addAttribute("followee",followee);
		model.addAttribute("viewName","profile/profile");
		return "template/layout";
	}
}
