package com.test.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.like.dao.LikeDAO;
import com.test.like.model.Like;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	public Like addLike(int postId) {
		return likeDAO.insertLike(postId);
	}
}
