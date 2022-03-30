package com.sns.user.model;

import com.sns.follow.model.Follow;

public class ProfileView {
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
