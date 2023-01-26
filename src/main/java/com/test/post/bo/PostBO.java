package com.test.post.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.test.comment.bo.CommentBO;
import com.test.common.fileManagerService;
import com.test.like.bo.LikeBO;
import com.test.like.dao.LikeDAO;
import com.test.post.dao.PostDAO;
import com.test.post.model.Post;


@Service
public class PostBO {
	
	// private Logger logger = LoggerFactory.getLogger(PostBO.class);
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private LikeDAO likeDAO;
	
	@Autowired
	private fileManagerService fileManagerService;
	
	public int addPost(int userId, String userLoginId, String subject, String content, MultipartFile file) {
		
		// 파일 업로드 => 경로 설정
		String imagePath = null;
		if (file != null) {
			// 파일이 있을 때만 업로드 => 경로를 얻어냄
			fileManagerService.saveFile(userLoginId, file);
		}
		
		// dao insert
		return postDAO.insertPost(userId, subject, content, null);
	}
	
	// 글 수정
	public void updatePost(int userId, String userLoginId, 
			int postId, String subject, String content, MultipartFile file) {
		
		// 기존 글을 가져온다. => (이미지가 교체될 때 기존 이미지 제거를 위해)
		Post post = getPostByPostIdUserId(postId, userId);  
		if (post == null) {  // null 체크
			logger.warn("[update post] 수정할 메모가 존재하지 않습니다. postId:{}, userId:{}", postId, userId);
			return;
		}
		
		// Multipa7rtFile이 비워져 있지 않다면 업로드 후 imagePate -> 업로드 성공하면 기본 이미지 제거
		String imagePath = null;
		if (file != null) {
			// 업로드 
			imagePath = fileManagerService.saveFile(userLoginId, file);
			
			// 업로드 성공하면 기본 이미지 제거 => 업로드 실패 할 수 있으므로 업로드가 성공한 후 제거
			// imagePath != null, 기존 글에 imagePath가 null이 아닐 경우
			if (imagePath != null && post.getImagePath() != null) {
				// 이미지 제거
				fileManagerService.deleteFile(post.getImagePath());
			}
		}
		
		// DB update
		postDAO.updatePostByPostIdUserId(postId, userId, subject, content, imagePath);
		
	}
	
	// 글 삭제
	public int deletePostByPostIdUserId(int postId, int userId) {
		// 기존 글 가져오기
		Post post = getPostByPostIdUserId(postId, userId);
		
		if (post == null) {
			logger.warn("[글 삭제] post is null. postId:{}, userId:{}", postId, userId);
			return 0;
		}
		
		// (이미지가 있으면 이미지 불러오기)업로드 되었던 이미지가 있으면 파일 삭제
		fileManagerService.deleteFile(post.getImagePath());
		
		// 댓글들 삭제
		commentBO.deleteCommentsByPostId(postId);
		
		// 좋아요들 삭제
		likeBO.deleteLikeByPostId(postId);
		
		// DB delete
		return postDAO.deletePostByPostIdUserId(postId, userId);
	}
	
	public List<Post> getPostListByUserId(int userId) {
		return postDAO.selectPostListByUserId(userId);
	}
	
	public Post getPostByPostIdUserId(int postId, int userId) {
		return postDAO.selectPostByPostIdUserId(postId, userId);
	}
	
	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}
}