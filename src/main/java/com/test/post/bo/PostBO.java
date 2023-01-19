package com.test.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.test.common.fileManagerService;
import com.test.post.dao.PostDAO;
import com.test.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private fileManagerService fileManagerService;
	
	public int addPost(int userId, String userLoginId, String subject, String content, MultipartFile file) {
		
		// 파일 업로드 => 경로 설정
		String imagePath = null;
		if (file != null) {
			// 파일이 있을 때만 업로드 => 경로를 얻어냄
			fileManagerService.saveFile(userLoginId, file);
		}
		
		
		return 1;
		
		// dao insert
		// return postDAO.insertPost(userId, subject, content, null);
	}
	
	public List<Post> getPostListByUserId(int userId) {
		return postDAO.selectPostListByUserId(userId);
	}
	
	public Post getPostByPostIdUserId(int userId, int postId) {
		return postDAO.selectPostByPostIdUserId(userId, postId);
	}
	
}
