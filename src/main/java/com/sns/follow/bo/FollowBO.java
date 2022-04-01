package com.sns.follow.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.follow.dao.FollowDAO;
import com.sns.follow.model.Follow;

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
	// 내가 팔로우 하는 사람 select
	public List<Follow> getFollowerListByUserId(int userId){
		return followDAO.selectFollowerListByUserId(userId);
	}
	// 나를 팔로우 하는 사람 select
	public List<Follow> getFolloweeListByFollowingId(int userId){
		return followDAO.selectFolloweeListByFollowingId(userId);
	}
}
