package com.test.like.dao;

import org.springframework.stereotype.Repository;

import com.test.like.model.Like;

@Repository
public interface LikeDAO {
	
	public Like insertLike(int postId);
}
