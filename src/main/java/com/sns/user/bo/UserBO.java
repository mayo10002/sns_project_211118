package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.dao.UserDAO;

@Service
public class UserBO {
	@Autowired
	private UserDAO userDAO;
	public Boolean existUserByLoginId(String loginId) {
		return userDAO.existUserByLoginId(loginId);
	}
	
	public int addUser(String loginId, String password, String name, String address) {
		return userDAO.insertUser(loginId, password, name, address);
	}
}
