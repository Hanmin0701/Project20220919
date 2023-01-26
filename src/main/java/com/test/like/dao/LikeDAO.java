package com.test.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.test.like.model.Like;

@Repository
public interface LikeDAO {
	//	public boolean existLike(
	//	@Param("postId") int postId, 
	//	@Param("userId") int userId);

	public void insertLike(
		@Param("postId") int postId, 
		@Param("userId") int userId);

	public void deleteLikeByPostIdUserId(
		@Param("postId") int postId, 
		@Param("userId") int userId);

	//public int selectLikeCountByPostId(int postId);

	public int selectLikeCountByPostIdOrUserId(
		@Param("postId") int postId, 
		@Param("userId") Integer userId);
	
	public void deleteLikeByPostId(int postId);
}
