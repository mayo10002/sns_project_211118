package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.CommentView;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class TimelineBO {
	@Autowired 
	private PostBO postBO;
	@Autowired
	private CommentBO commentBO;
	@Autowired
	private UserBO userBO;
	
	public List<CardView> generateCardViewList(){
		List<CardView> cardViewList = new ArrayList<>(); // cartViewList 에 add해줘야함. 
		
		//글 list 가져온다
		List<Post> postList = postBO.getPostList();
		for(Post post : postList) {
			CardView card = new CardView();
			
			//글 정보
			card.setPost(post);
			//글쓴이 정보
			User user = userBO.getUserByUserId(post.getUserId());
			card.setUser(user);
			//댓글들정보
			List<CommentView> commentList = commentBO.generateCommentViewList(post.getId());
			card.setCommentList(commentList);
			
			// 게시글 별 좋아요 표시
			
			cardViewList.add(card);
			
		}
		return cardViewList;
	}
}
