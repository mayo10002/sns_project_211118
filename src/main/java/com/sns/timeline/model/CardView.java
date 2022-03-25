package com.sns.timeline.model;

import java.util.List;

import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;
import com.sns.post.model.Post;
import com.sns.user.model.User;

public class CardView {

	// 글 하나 , 하나의 카드를 의미
	
//	private Post post;
//	// 댓글들, ,좋아요들, 그외 구성들 등등
//	private List<Comment> commentList;
//	private List<Like> likeList;
//	

	private Post post;
	
	// 글쓴이 정보  >  글에 대한.
	private User user;
	// 댓글들
	private List<CommentView> commentList;
	//좋아요들
	private int count;
	// 로그인 된 사용자가 좋아요 눌렀는지 여부.
	private boolean filledLike; // boolean 변수 만들 때 이름이 is로 시작되면 setter 이름이 이상해짐)
	

	public List<CommentView> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentView> commentList) {
		this.commentList = commentList;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isFilledLike() {
		return filledLike;
	}

	public void setFilledLike(boolean filledLike) {
		this.filledLike = filledLike;
	}
	
	
	
}
