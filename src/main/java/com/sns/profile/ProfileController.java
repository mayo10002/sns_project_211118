package com.sns.profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.follow.bo.FollowBO;
import com.sns.profile.bo.ProfileBO;
import com.sns.profile.model.ProfileView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@RequestMapping("/profile")
@Controller
public class ProfileController {
	@Autowired
	private ProfileBO profileBO;
	@Autowired
	private FollowBO followBO;
	@Autowired
	private UserBO userBO;
	@RequestMapping("/profile_view/{userLoginId}")
	public String profileView(
			@PathVariable String userLoginId,
			Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		User user = userBO.getUserByLoginId(userLoginId);
		int userId = user.getId();
		ProfileView profileView = profileBO.generateProfileView(userId);
		// followFilled > 세션에서 int userid를 가져오고, 위에서 지정한 userId는 현재 보고 있는 프로필 주인의 id이므로 followingId 변수값으로 들어가야 한다.
		Integer nowUserId = (Integer)session.getAttribute("userId");
		boolean followFilled = followBO.existFollow(nowUserId, userId);
		
		model.addAttribute("profileView",profileView);
		model.addAttribute("followFilled",followFilled);
		model.addAttribute("viewName","profile/profile");
		return "template/layout";
	}
}
