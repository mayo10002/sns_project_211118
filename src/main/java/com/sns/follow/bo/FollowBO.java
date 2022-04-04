package com.sns.follow.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.follow.dao.FollowDAO;
import com.sns.follow.model.Follow;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class FollowBO {
	@Autowired
	private FollowDAO followDAO;
	@Autowired
	private UserBO userBO;

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
	public List<User> getFollowerListByUserId(int userId){
		
		List<User> followerList = new ArrayList<>();
		List<Follow> follower = followDAO.selectFollowerListByUserId(userId); /// DAO에서는 userId.
		for(Follow follow : follower) {
			// follow 꺼냄 
			followerList.add(userBO.getUserByUserId(follow.getUserId())) ;
		}
	
		return followerList;
	}
	// 나를 팔로우 하는 사람 select
	public List<User> getFolloweeListByFollowingId(int userId){
		List<User> followeeList = new ArrayList<>();
		List<Follow> followee = followDAO.selectFolloweeListByFollowingId(userId);
		for(Follow follow : followee) {
			followeeList.add(userBO.getUserByUserId(follow.getFollowingId()));
		}
		return followeeList;
	}
	
}
