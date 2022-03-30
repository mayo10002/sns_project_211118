package com.sns.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
	/**
	 * 회원가입 화면
	 * @param model
	 * @return
	 */
	@RequestMapping("/sign_up_view")
	public String signUpView(Model model) {
		model.addAttribute("viewName","user/sign_up");
		return "template/layout";
	}
	/**
	 * 로그인
	 * @param model
	 * @return
	 */
	@RequestMapping("/sign_in_view")
	public String signInView(Model model) {
		model.addAttribute("viewName","user/sign_in");
		return "template/layout";
	}
	/**
	 * 로그아웃
	 * @param request
	 * @return
	 */
	@RequestMapping("/sign_out")
	public String signOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// 로그아웃 할 때 세션에 있는 모든 것들을 비운다.
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		return "redirect:/user/sign_in_view";
	}
	/**
	 * 프로필 화면
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/profile_view")
	public String profileView(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute("viewName","user/profile");
		return "template/layout";
	}
}
