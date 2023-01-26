package com.test.post.dao;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.test.post.model.Post;

@Repository
public interface PostDAO {
	
	public List<Map<String, Object>> selectPostListTest();
	
	public int insertPost(
			@Param("userId") int userId, 
			@Param("subject") String subject, 
			@Param("content") String content, 
			@Param("imagePath") String imagePath);
	
	public List<Post> selectPostListByUserId(int userId);
	
	public Post selectPostByPostIdUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	public List<Post> selectPostList();
	
	// 글 수정
	public void updatePostByPostIdUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId,
			@Param("subject") String subject, 
			@Param("content") String content, 
			@Param("imagePath") String imagePath);
	
	// 글 삭제
	public int deletePostByPostIdUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	// 댓글 삭제
	public void deleteCommentByPostId(int postId);
}
