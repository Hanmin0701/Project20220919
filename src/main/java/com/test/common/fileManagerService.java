package com.test.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component   // 일반적인 Spring Bin 이다.
public class fileManagerService {
	// 실제 이미지가 저장될 경로(서버)
	public static final String FILE_UPLOAD_PATH = "D:\\Leehanmin\\7. Spring_Project\\memo\\workspace\\Images/";
	
	// input: Mulipartfile, userLoginId 
	// output: image path
	public String saveFile(String userLoginId, MultipartFile file) {
		// 파일 디렉토리 예) aaaa_16205468768/sun.png => 중복이 생기면 안되기 때문이다.
		
		String directoryName = userLoginId + "_" + System.currentTimeMillis() + "/";  // aaaa_16205468768/ 여기까지고
		String filePath = FILE_UPLOAD_PATH + directoryName; // D:\\Leehanmin\\7. Spring_Project\\memo\\workspace\\Images/aaaa_16205468768/sun.png
		
		File directory = new File(filePath);
		if(directory.mkdir() == false) {
			return null; // 폴더 만드는데 실패시 image path null
		}
		
		// 파일 업로드: byte로 단위로 업로드 된다.
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());   // OriginalFilename 사용자가 올린 파일명
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		// 파일 업로드 성공했으면 이미지 url path를 return 한다.
		// http://localhost:8080/images/aaaa_16205468768/sun.png => 이렇게 만들 것이다.
		return "/images/" + directoryName + file.getOriginalFilename(); // => /images/aaaa_16205468768/sun.png 이렇게 된다.
	}
}
