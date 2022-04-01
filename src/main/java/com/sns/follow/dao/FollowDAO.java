package com.sns.follow.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.follow.model.Follow;

@Repository
public interface FollowDAO {
	public void insertFollow(
			@Param("userId")int userId, 
			@Param("followingId")int followingId);
	public int selectFollowCountByUserIdAndFollowingId(
			@Param("userId")int userId, 
			@Param("followingId")int followingId);
	public void deleteFollowByUserIdAndFollowingId(
			@Param("userId")int userId, 
			@Param("followingId")int followingId);
	public List<Follow> selectFollowerListByUserId(int userId);
	public List<Follow> selectFolloweeListByFollowingId(int followingId);
}
