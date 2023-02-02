package com.test.user;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.test.user.bo.UserBO;
import com.test.user.model.User;

@SpringBootTest
class userRestControllerTest {
	
	@Autowired
	UserBO userBO;
	
	// src/main/클래스 > new > JUnit Test Case 테스트 파일 생성
	@Test
	void test() {
		User user = userBO.getUserByLoginIdPassword("aaaa", "ad5dapisfapadsfofos");
		assertNotNull(user);
	}
	
//	@Transactional    // rollback    
//	@Test
//	void 유저추가테스트() {
//		userBO.addUser("bbbb1111", "bbbb1111", "테스트", "test@test.com");
//	}
}
