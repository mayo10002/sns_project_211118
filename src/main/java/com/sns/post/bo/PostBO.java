package com.sns.post.bo;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;


@Service
public class PostBO {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired 
	private PostDAO postDAO;
	@Autowired 
	private FileManagerService fileManagerService;
	@Autowired
	private CommentBO commentBO;
	@Autowired 
	private LikeBO likeBO;
	
	public List<Post> getPostList(){
		return postDAO.selectPostList();
	}
	
	public List<Post> getPostByUserId(int userId) {
		return postDAO.selectPostByUserId(userId);
	}
	public int addPost(String loginId, int userId, String content, MultipartFile file) {
		String imagePath = null;
		if(file != null) {
		try {
			imagePath = fileManagerService.saveFile(loginId, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		return postDAO.insertPost(userId, content, imagePath);
	}
	public int deletePost(int postId, int userId) {
		// select post
		Post post = postDAO.selectPostById(postId);
		
		// post null 검사 => null이면 logger찍어서 기록으로 남기기, 0 return 
		if(post == null) {
			logger.warn("[post delete] Already deleted post.");
		}
		// 이미지 삭제
		String imagePath = post.getImagePath();
		if(imagePath != null) {
			try {
				fileManagerService.deleteFile(imagePath);
			} catch (IOException e) {
				logger.error("[post delete] Cannot delete image. postId:{}",postId);
			}
		}
		// 글 삭제
		
		// 댓글들 모두 삭제
		commentBO.deleteCommentByPostId(postId);
		// 좋아요들 모두 삭제 byPostId
		likeBO.deleteLikeCountByPostId(postId);
		
		return postDAO.deletePost(postId);
		
	}
}
