package com.test.memo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;

@SpringBootTest // 이게 들어 있으면 모든 것이 수행이 된다.
class ProjectApplicationTests {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 단축키 alt + shift + x       ,  t
	@Test  // 이 어노테이션을 가지고 있어야하지만 테스트가 수행이 된다.
	void contextLoads() {
		logger.info("############ logger  test");
	}
	
	@Test
	void 더하기테스트() {
		logger.info("$$$$$$$$$ 더하기 테스트 시작");
		int a = 10;
		int b = 20;
		
		assertEquals(30, a + b);
	}
	
	@Test
	void 널체크() {
		String a = null;
		if (ObjectUtils.isEmpty(a)) {
			logger.info("비어있다.");
		} else {
			logger.info("비어있지 않다.");
		}
		
		List<String> list = null;
		logger.info(ObjectUtils.isEmpty(list) + "");  // 리스트 비어있는지 확인 하는 방법
	}
}