package com.test.comment.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDAO {

	public String insertComment(
			@Param("userId") int userId,
			@Param("postId") int postId, 
			@Param("content") String content);
}
