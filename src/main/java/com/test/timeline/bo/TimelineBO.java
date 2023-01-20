package com.test.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.comment.bo.CommentBO;
import com.test.comment.model.CommentView;
import com.test.post.bo.PostBO;
import com.test.post.model.Post;
import com.test.timeline.model.CardView;
import com.test.user.bo.UserBO;

@Service
public class TimelineBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	// 로그인이 되지 않은 사람도 카드 내용이 보여야 한다.(전체 리스트를 최신순으로 가져와야 한다.)
	public List<CardView> generateCardList() {
		
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글 목록 가져오기(post에서)
		List<Post> postList = postBO.getPostList();
		
		// postList 반복문 => cardView => cardViewList에 넣는다.
		for (Post post : postList) {
			CardView card = new CardView();

			// 글
			card.setPost(post);
			
			// 글쓴이
			userBO.getUserById(post.getUserId());
			post.getUserId(); // 글쓴이 번호
			
			// 글 하나에 해당하는 댓글들
			List<CommentView> commentList = commentBO.generateCommentViewListByPostId(post.getId());
			card.setCommentList(commentList);
			
			// 내가 좋아요를 눌렀는지 filledLike (비로그인 사람도 오류가 없도록 설정을 해야한다.)
			
			
			
			// 카드 리스트에 채우기!!!!!!
			cardViewList.add(card);
		}
		
		return cardViewList;
	}
}