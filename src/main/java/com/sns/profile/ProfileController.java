package com.sns.profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/profile")
@Controller
public class ProfileController {
	@RequestMapping("/profile_view")
	public String profileView(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute("viewName","profile/profile");
		return "template/layout";
	}
}
