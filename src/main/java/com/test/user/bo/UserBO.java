package com.test.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.user.dao.UserDAO;
import com.test.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	// ID 중복 확인
	public boolean existLoginId(String loginId) {
		return userDAO.existLoginId(loginId); 
	}
	
	// 회원가입
	public void addUser(String loginId, String password, String confirmPassword, String name, String email) {
		userDAO.insertUser(loginId, password, confirmPassword, name, email);
	}
	
	// 로그인
	public User getUserByLoginIdPassword(String loginId, String password) {
		return userDAO.selectUserByLoginIdPassword(loginId, password);
	}
	
	public User getUserById(int userId) {
		return userDAO.selectUserById(userId);
	}
}
