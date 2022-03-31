package com.sns.follow.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowDAO {
	public void insertFollow(
			@Param("userId")int userId, 
			@Param("followingId")int followingId);
}
