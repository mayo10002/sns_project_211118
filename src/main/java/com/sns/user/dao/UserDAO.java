package com.sns.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.user.model.User;

@Repository
public interface UserDAO {
	public Boolean existUserByLoginId(String loginId);
	public User selectUserByLoginId(String loginId);
	public User selectUserByLoginIdPassword(
			@Param("loginId")String loginId, 
			@Param("password")String password);
	public int insertUser(
			@Param ("loginId")String loginId,
			@Param ("password")String password, 
			@Param ("name")String name, 
			@Param ("address")String address);
	public User selectUserByUserId(int userId);
	public List<User> selectUserList();
}
