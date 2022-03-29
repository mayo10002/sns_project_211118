package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {
	@Autowired
	private LikeDAO likeDAO;
	
	public void addLike(int postId, int userId) {
		//if()
		likeDAO.insertLike(postId, userId);
	}
	public boolean existLike(int postId, Integer userId) {
		if(userId == null) {
			return false;
		}
		return true;
	}
	public int getLikeCountByPostId(int postId) {
		return likeDAO.selectLikeCountByPostIdOrUserId(postId, null);
	}
	public void deleteLikeCountByPostIdAndUserId(int postId, int userId) {
		likeDAO.deleteLikeCountByPostIdAndUserId(postId, userId);
	}
	
}
