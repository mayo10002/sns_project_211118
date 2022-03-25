package com.sns.timeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	@Autowired
	private PostBO postBO;
	
	@RequestMapping("/timeline_view")
	public String timeline(Model model
			) {
		
	//	List<CardView> cardViewList = new 
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}
}
