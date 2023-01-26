package com.test.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.comment.dao.CommentDAO;
import com.test.comment.model.Comment;
import com.test.comment.model.CommentView;
import com.test.timeline.model.CardView;
import com.test.user.bo.UserBO;
import com.test.user.model.User;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private UserBO userBO;
	
	public String createComment(int userId, int postId, String comment) {
		return commentDAO.insertComment(userId, postId, comment);
	}
	
	public List<Comment> getCommentListByPostId(int postId) {
		return commentDAO.selectCommentListByPostId(postId);
	}
	
	
	// input: 글 번호
	// output: 글 번호에 해당하는 댓글(+댓글 작성자 정보)목록을 가져온다.
	public List<CommentView> generateCommentViewListByPostId(int postId) {
		//  결과물 
		List<CommentView> commentViewList = new ArrayList<>();
		
		// 댓글 목록
		List<Comment> CommentList = getCommentListByPostId(postId);
		
		// 반복문 => commentView => 결과물에 넣는다.
		for (Comment comment: CommentList) {
			CommentView commentView = new CommentView();
			
			// 댓글 한개
			commentView.setComment(comment);
			
			// 댓글 작성자
			User user = userBO.getUserById(comment.getUserId());
			commentView.setUser(user);
		}
		
		// 결과물 리턴
		return commentDAO.generateCommentViewListByPostId(postId);
	}
	
	
	public void deleteCommentsByPostId(int postId) {
		commentDAO.deleteCommentsByPostId(postId);
	}
}
