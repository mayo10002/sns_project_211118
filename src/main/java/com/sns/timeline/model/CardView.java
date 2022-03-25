package com.sns.timeline.model;

import java.util.List;

import com.sns.post.bo.PostBO;

public class CardView {

	// 글 하나 , 하나의 카드를 의미
	
//	private Post post;
//	// 댓글들, ,좋아요들, 그외 구성들 등등
//	private List<Comment> commentList;
//	private List<Like> likeList;
//	

	private PostBO postBO;
	private List<Comment> commentList;

	public PostBO getPostBO() {
		return postBO;
	}

	public void setPostBO(PostBO postBO) {
		this.postBO = postBO;
	}
	
	
	
}
