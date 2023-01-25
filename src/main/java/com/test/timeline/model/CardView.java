package com.test.timeline.model;

import java.util.*;

import com.test.comment.model.Comment;
import com.test.comment.model.CommentView;
import com.test.post.model.Post;
import com.test.user.model.User;

// View용 객체 => 화면에 뿌릴 용도
// 글 1개의 대한 매핑이다.
public class CardView {
	
	// 글(게시물) 1개
	private Post post; // post를 다 가져오는 것
	
	// 글쓴이 정보
	private User user;
	
	// 댓글들 N개(게시물 1개의)
	private List<CommentView> commentList;

	// 내가(로그인 된 사람) 좋아요를 눌렀는지? (boolean)
	private boolean filledLike;
	
	// 좋아요 개수
	private int likeCount;

	// getters, setters
	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public boolean isFilledLike() {
		return filledLike;
	}

	public void setFilledLike(boolean filledLike) {
		this.filledLike = filledLike;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CommentView> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentView> commentList) {
		this.commentList = commentList;
	}
}
