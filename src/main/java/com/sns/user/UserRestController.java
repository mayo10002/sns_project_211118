package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@RequestMapping("/user")
@RestController
public class UserRestController {
	@Autowired
	private UserBO userBO;

	@RequestMapping("/is_duplicated_id")
	public Map<String, Boolean> isDuplicatedId(
			@RequestParam("loginId") String loginId){
		Map<String, Boolean> result = new HashMap<>();
		
		result.put("result", userBO.existUserByLoginId(loginId));
		return result;
		
	}
	@PostMapping("/sign_up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("address") String address){
		String encryptPassword = EncryptUtils.md5(password);
		// insert DB
		int row = userBO.addUser(loginId, encryptPassword, name, address);
		// Response
		Map<String, Object> result = new HashMap<>();
		
		if( row < 1 ) {
			result.put("result", "error");

		}else {
			result.put("result", "success");
		}
		
		return result;
	}
	
	
	@PostMapping("/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("loginId")String loginId,
			@RequestParam("password")String password,
			HttpServletRequest request){
		// password 암호화
		String encryptPassword = EncryptUtils.md5(password);
		
		//select DB, select Password 
		
		User user = userBO.getUserByLoginIdPassword(loginId, encryptPassword);
		
		Map<String, Object> result = new HashMap<>();
		if(user != null) {
			result.put("result", "success");
			
			HttpSession session = request.getSession();			
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
		}else {
			result.put("result", "error");
			result.put("error_message", "존재하지 않는 사용자입니다.");
		}
		return result;
	}
}
