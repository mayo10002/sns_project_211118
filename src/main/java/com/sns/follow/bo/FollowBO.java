package com.sns.follow.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.follow.dao.FollowDAO;

@Service
public class FollowBO {
	@Autowired
	private FollowDAO followDAO;
	public void addFollow(int userId, int followingId) {
		boolean existFollow = existFollow(userId, followingId);
		if(existFollow) {
			followDAO.deleteFollowByUserIdAndFollowingId(userId, followingId);
		}else {
			followDAO.insertFollow(userId, followingId);
		}
	}
	public boolean existFollow (Integer userId, int followingId) {
		if(userId == null) {
			return false;
		}
		int count = followDAO.selectFollowCountByUserIdAndFollowingId(userId, followingId);
		return count > 0? true : false;
	}
	public void deleteFollowByUserIdAndFollowingId(int userId,int followingId) {
		followDAO.deleteFollowByUserIdAndFollowingId(userId, followingId);
	}
}
