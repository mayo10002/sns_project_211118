package com.sns.post.bo;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {
	@Autowired 
	private PostDAO postDAO;
	@Autowired 
	private FileManagerService fileManagerService;
	public List<Post> getPostList(){
		return postDAO.selectPostList();
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
}
