package com.test.user;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.common.EncryptUtils;
import com.test.user.bo.UserBO;

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
		boolean isDuplicated = userBO.existLoginId(loginId);
		if(isDuplicated) { // 중복 일 때 
			result.put("code", 1);
			result.put("result", true);
		} else { // 중복 아니고 사용가능 일 때
			result.put("code", 1);
			result.put("result", false);
		}
		return result;
	}
	
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
}
