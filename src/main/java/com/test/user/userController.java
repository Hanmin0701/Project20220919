package com.test.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class userController {
	
	/** 이 주석은 메소드가 완성이 되고 확인하면 한번에 확인가능
	 * 회원 가입 화면
	 * @return
	 */
	// 회원가입 화면
	@GetMapping("/sign_up")
	public String signUp(Model model) {
		model.addAttribute("viewName", "user/signUp");  
		// user/signUp 여기에 넣고 jsp에는 ${viewName} 이렇게 하면 출력은 같다.
		// 이렇게 jsp는 고정인데 나머지가 다르게 적용 될 경우에 이렇게 하면은 더욱 효과적으로 할 수 있다.
		return "/template/layout_signUp";
	}
	
	// 로그인 화면
	@GetMapping("/sign_in")
	public String signIn(Model model) {
		model.addAttribute("viewSignIn", "user/signIn");
		return "/template/layout_signIn";
	}
	
}
