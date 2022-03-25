package com.sns.timeline.bo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sns.post.bo.PostBO;
import com.sns.timeline.model.CardView;

@Service
public class TimelineBO {
	@Autowired 
	private PostBO postBO;
	@Autowired
	private CommentBO commentBO;
	public List<CardView> generateCardViewList(){
		List<CardView> cardViewList = new ArrayList<>(); // cartViewList 에 add해줘야함. 
		
		//글 list 가져온다
		List<Post> postList = postBO.getPostList();
		for(Post post : postList) {
			CardView card = new CardView();
			
			//글 정보
			card.setPostBO(postBO);
			//댓글들정보
			List<Comment> commentList = commentBO.getCommentListByPostId(post.getId());
			card.setCommentList(commentList);
			
			cardViewList.add(card);
			
		}
		return cardViewList;
	}
}
