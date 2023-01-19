package com.test.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.post.bo.PostBO;
import com.test.post.model.Post;


@Controller
@RequestMapping("/timeline")
public class timeLineContoller {

	@Autowired
	private PostBO postBO;
	
//	@GetMapping("/timeline/timeline_view")
//	public String timelineView(Model model) {
//		List<Post> postList = postBO.getPostList();
//		model.addAttribute("postList", postList);
//		model.addAttribute("viewName", "timeline/timeline");
//		return "template/layout";
//	}
	
}
