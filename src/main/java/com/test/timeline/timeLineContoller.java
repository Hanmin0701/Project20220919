package com.test.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.timeline.bo.TimelineBO;
import com.test.timeline.model.CardView;


@Controller
@RequestMapping("/timeline")
public class timeLineContoller {
	
	@Autowired
	private TimelineBO timelineBO;
	
	@GetMapping("/timeline/timeline_view")
	public String timelineView(Model model) {
//		List<Post> postList = postBO.getPostList();
//		model.addAttribute("postList", postList);

		List<CardView> cardList = timelineBO.generateCardList();
		model.addAttribute("cardList", cardList);
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}
	
}
