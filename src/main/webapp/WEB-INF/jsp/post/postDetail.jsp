<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<h1>글 상세/수정</h1>
		
		<input type="text" id="subject" class="form-control" placeholder="제목을 입력하세요." value="${post.subject}">
		<textarea class="form-control" id="content" placeholder="내용을 입력하세요." rows="15">${post.content}</textarea>
		
		<%-- 이미지가 있을 때만 이미지 영역 추가 --%>
		<c:if test="${not empty post.imagePath}">
		<div>
			<img src="${post.imagePath}" alt="업로드 이미지" width="300">
		</div>
		</c:if>
		<div class="d-flex justify-content-end my-4">
			<input type="file" id="file" accept=".jsg, .jpeg, .png, .gif">
		</div>
		
		<div class="d-flex justify-content-between">
			<button type="button" id="postDeleteBtn" class="btn btn-secondary">삭제</button>
			
			<div>
				<button type="button" id="postListBtn" class="btn btn-dark" href="/post/post_list_view">목록으로</button>
				<button type="button" id="postUpdateBtn" class="btn btn-info" data-post-id=${post.id}">저장</button>
			</div>
		</div>
	</div>
</div>
<script>
	$(document).ready(functino(){
		// 수정 버튼
		$('#postUpdateBtn').on('click', function(){
			let subject = $('#subject').val().trim();
			if(subject == "") {
				alert("제목을 입력해주세요.");
				return;
			} 
			
			let content = $('#content').val();
			console.log(content); // 잘 있는지 console에서 확인
			
			let file = $('#file').val(); 
			console.log(file); // 잘 있는지 console에서 확인
			
			// 파일이 업로드 된 경우 확장자 체크 => 글쓰기 로직하고 비슷하다
			if (file != ) {
				//alert(file.split(".").pop().toLowerCase());
				let ext = file.split(".").pop().toLowerCase();
				if($.inArray(ext, ['jpg', 'jpeg', 'png', 'gif'])  == -1) {
					alert("이미지 파일만 업로드 할 수 있습니다.");
					$('#file').val("");
					return;
				}
			}
			
			let postId = $(this).data('post-id');
			// form 태그 자바스크립트에서 만든다
			let formData = new FormData();
			formdata.append("postId", postId);
			formdata.append("subject", subject);
			formdata.append("content", content);
			formdata.append("file", $('#file')[0].files[0]);
			
			// ajax 서버 통신
			$.ajax({
				// request
				type:"PUT"
				, url:"/post/update"
				, data:formData
				, enctype:"multipart/ form-data" // 파일 업로드을 위한 필수 설정
				, processData: false 			 // 파일 업로드을 위한 필수 설정
				, contentType: false 			 // 파일 업로드을 위한 필수 설정
				
				// response
				, success:function(data) {
					if (data.code == 1) {
						alert("메모가 수정되었습니다.");
						location.reload(true);
					} else {
						alert(data.errorMessage);
					}
				}
				, error:function(e) {
					alert("메모를 수정하는데 실패했습니다.");
				}
			});
		});
	});
</script>