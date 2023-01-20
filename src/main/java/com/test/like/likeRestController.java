package com.test.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.like.bo.LikeBO;
import com.test.like.model.Like;

import jakarta.servlet.http.HttpSession;

@RestController
public class likeRestController {
	
	@Autowired
	private LikeBO likeBO;
	
	// /like?postId=13 이렇게도 가능  @RequestParam
	// /like/13   	   이렇게도 가능  @PathVariable
	@GetMapping("/like/{postId}")  // {} 와일드 카드 
	public Map<String, Object> like(
			@PathVariable int postId, HttpSession session) {
		
		Like like = likeBO.addLike(postId);
		
		Map<String, Object> result = new HashMap<>();
		
		if (like != null) {
			// 하트
		} else {
			// 빈칸 하트
		}
		
		return result;
	}
}
