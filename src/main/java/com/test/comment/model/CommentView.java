package com.test.comment.model;

import com.test.user.model.User;

// 댓글 1개와 MAPPING
public class CommentView {
	// 댓글 한개 
	private Comment comment;
	
	
	// 댓글 글쓴이 정보
	private User user;


	
	public Comment getComment() {
		return comment;
	}


	public void setComment(Comment comment) {
		this.comment = comment;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
}
