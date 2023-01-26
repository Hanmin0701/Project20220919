package com.test.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.comment.bo.CommentBO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/comment")
public class commentRestController {

	@Autowired
	private CommentBO commentBO;

	@PostMapping("create")
	public Map<String, Object> createComment(@RequestParam("postId") int postId,
			@RequestParam("content") String content, HttpSession session) {

		Map<String, Object> result = new HashMap<>();

		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 500);
			result.put("result", "error");
			result.put("errorMessage", "로그인을 다시 해주세요.");
			return result;
//		if (userId != null) {
//			result.put("code", 1); // 로그인 되어있을 때 작성하면
//			result.put("result", "성공");
//		} else { // 비로그인
//			result.put("code", 500); 
//			result.put("errorMessage", "댓글 작성에 실패했습니다. 로그인을 다시 해주세요.");
//		}
		}

		commentBO.createComment(userId, postId, content);
		result.put("code", 100);
		result.put("result", "success");

		return result;
	}
}