package com.test.user.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

	public boolean existLoginId(String loginId);
	
	public Object insertUser(String loginId, String password, String confirmPassword, String name, String email);
}
