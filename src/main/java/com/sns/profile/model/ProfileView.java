package com.sns.profile.model;

import java.util.List;

import com.sns.post.model.Post;
import com.sns.user.model.User;

public class ProfileView {
	private User user;
	private List<Post> post;
	private List<User> follower;
	private List<User> followee;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Post> getPost() {
		return post;
	}
	public void setPost(List<Post> post) {
		this.post = post;
	}
	public List<User> getFollower() {
		return follower;
	}
	public void setFollower(List<User> follower) {
		this.follower = follower;
	}
	public List<User> getFollowee() {
		return followee;
	}
	public void setFollowee(List<User> followee) {
		this.followee = followee;
	}

		
	
}
