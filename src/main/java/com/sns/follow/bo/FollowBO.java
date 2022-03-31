package com.sns.follow.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.follow.dao.FollowDAO;

@Service
public class FollowBO {
	@Autowired
	private FollowDAO followDAO;
	public void addFollow(int userId, int followingId) {
		followDAO.insertFollow(userId, followingId);
	}
}
