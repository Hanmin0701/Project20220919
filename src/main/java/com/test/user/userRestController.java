package com.test.user;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.common.EncryptUtils;
import com.test.user.bo.UserBO;
import com.test.user.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class userRestController {
	
	@Autowired
	private UserBO userBO;
	
	// 중복확인
	/**
	 * ID 중복확인 API
	 * @param loginId
	 * @return
	 */
	
	@RequestMapping("/is_duplicated_id")  // postmapping, getmapping 둘 다 가능하면 requestmapping 쓰면 된다.
	public Map<String, Object> isDuplicatedId(@RequestParam("loginId") String loginId) {
		Map<String, Object> result = new HashMap<>();
		boolean isDuplicated = false;
		try {
			isDuplicated = userBO.existLoginId(loginId);
		} catch (Exception e) {
			result.put("code", 500);
			result.put("errorMessage", "중복 확인하는데 실패했습니다.");
		}
		if(isDuplicated) { // 중복 일 때 
			result.put("code", 1);
			result.put("result", true);
		} else { // 중복 아니고 사용가능 일 때
			result.put("code", 1);
			result.put("result", false);
		}
		return result;
	}
	
	/**
	 * 회원가입 API
	 * @param loginId
	 * @param password
	 * @param confirmPassword
	 * @param name
	 * @param email
	 * @return
	 */
	@PostMapping("/sign_up")
	public Map<String, Object> singUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("confirmPassword") String confirmPassword,
			@RequestParam("name") String name,
			@RequestParam("email") String email) {
		
		// 비밀번호 Hashing - md5 
		// 한번 해싱을 하면 복구를 못한다. 나중에 복귀할 수 있는 method가 잇다.
		String hashedPassword = EncryptUtils.md5(password);
		
		// DB insert
		userBO.addUser(loginId, hashedPassword, confirmPassword, name, email);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		
		return result;
	}
	
	// 로그인 
	/**
	 * 로그인 API
	 * @param loginId
	 * @param password
	 * @param request
	 * @return
	 */
	@PostMapping("/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request) {
		
		// 비밀번호 hashing
		String hashedPassword = EncryptUtils.md5(password);
		
		// DB select
		User user = userBO.getUserByLoginIdPassword(loginId, hashedPassword);
		
		Map<String, Object> result = new HashMap<>();
		if(user != null ) {
			// 행이 있으면 로그인
			result.put("code", 1);
			result.put("result", "성공");
			
			// 세션애 유저 정보를 담는다. (로그인 상태 유지)
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getId()); // 지금 로그인된 그 사람의 정보
			session.setAttribute("userLoginId", user.getLoginId()); 
			session.setAttribute("userName", user.getName()); 
			
		} else {
			// 행이 없으면 로그인 실패
			result.put("code", 500);
			result.put("errorMessage", "존재하지 않는 사용자입니다.");
		}
		
		// return Map
		return result;
	}
}