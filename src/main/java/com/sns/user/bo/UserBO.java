package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.dao.UserDAO;
import com.sns.user.model.User;

@Service
public class UserBO {
	@Autowired
	private UserDAO userDAO;
	public Boolean existUserByLoginId(String loginId) {
		return userDAO.existUserByLoginId(loginId);
	}
	public User getUserByLoginIdPassword(String loginId, String password) {
		return userDAO.selectUserByLoginIdPassword(loginId, password);
	}
	public int addUser(String loginId, String password, String name, String address) {
		return userDAO.insertUser(loginId, password, name, address);
	}
}
