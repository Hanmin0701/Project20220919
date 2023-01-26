package com.test.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.test.comment.model.Comment;
import com.test.comment.model.CommentView;

@Repository
public interface CommentDAO {

	public String insertComment(
			@Param("userId") int userId,
			@Param("postId") int postId, 
			@Param("content") String content);
	
	public List<Comment> selectCommentListByPostId(int postId);
	
	public List<CommentView> generateCommentViewListByPostId(int postId);
	
	// 댓글 삭제
	public void deleteCommentsByPostId(int postId);
}
