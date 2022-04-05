package com.sns.profile.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.follow.bo.FollowBO;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.profile.model.ProfileView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class ProfileBO {
	@Autowired
	private UserBO userBO;
	@Autowired
	private PostBO postBO;
	@Autowired
	private FollowBO followBO;
	public ProfileView generateProfileView(int userId) {
		User user = userBO.getUserByUserId(userId);
		ProfileView profileView = new ProfileView();
		
		List<Post> post = postBO.getPostByUserId(userId);
		List<User> follower = followBO.getFollowerListByUserId(userId);
		List<User> followee = followBO.getFolloweeListByFollowingId(userId);
		profileView.setUser(user);
		profileView.setPost(post);
		profileView.setFollower(follower);
		profileView.setFollowee(followee);
		
		return profileView;
	}

}
