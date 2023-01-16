package com.test.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.test.user.model.User;

@Repository
public interface UserDAO {

	// ID 중복 확인
	public boolean existLoginId(String loginId);
	
	// 회원가입 
	public void insertUser(
			@Param("loginId") String loginId, 
			@Param("password") String password, 
			@Param("confirmPassword") String confirmPassword, 
			@Param("name") String name, 
			@Param("email") String email);
	
	// 로그인
	public User selectUserByLoginIdPassword(
			@Param("loginId") String loginId, 
			@Param("password") String password); 
}
