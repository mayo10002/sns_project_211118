package com.sns.follow.model;

import com.sns.user.model.User;

public class FollowView {
	private User follower;
	private User followee;
	public User getFollower() {
		return follower;
	}
	public void setFollower(User follower) {
		this.follower = follower;
	}
	public User getFollowee() {
		return followee;
	}
	public void setFollowee(User followee) {
		this.followee = followee;
	}
	
	
	
}
