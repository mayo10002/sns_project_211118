package com.sns.follow.model;

import com.sns.user.model.User;

public class FollowView {
	private User user;
	private Follow follow;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Follow getFollow() {
		return follow;
	}
	public void setFollow(Follow follow) {
		this.follow = follow;
	}
	
	
}
