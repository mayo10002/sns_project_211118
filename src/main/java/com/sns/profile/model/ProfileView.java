package com.sns.profile.model;

import com.sns.follow.model.Follow;
import com.sns.post.model.Post;
import com.sns.user.model.User;

public class ProfileView {
	private User user;
	private Follow follow;
	private Post post;

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
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	
}
