package com.test.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.user.dao.UserDAO;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	public boolean existLoginId(String loginId) {
		return userDAO.existLoginId(loginId); 
	}
	
	public Object addUser(String loginId, String password, String confirmPassword, String name, String email) {
		return userDAO.insertUser(loginId, password, confirmPassword, name, email);
	}
}
