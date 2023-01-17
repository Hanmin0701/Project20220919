<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Memo 게시판</title>
		<!-- jquery : bootstrap, datepicker -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>  

        <!-- bootstrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

		<!-- datepicker -->
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        
        <!-- css -->
        <link rel="stylesheet" type="text/css" href="/static/css/style.css">
</head>
<body>
	<div id="wrap">
		<h1>게시물</h1>
		<!-- header -->
		<header class="bg-danger">
			<jsp:include page="../include/header_signIn.jsp"/> 
		</header>
		
		<!-- section -->
		<section class="contents bg-info">
			<jsp:include page="../${viewName}.jsp"/>
		</section>
		<footer class="bg-primary">
			<jsp:include page="../include/footer.jsp"/>
		</footer>
	</div>
<script type="text/javascript">
	$(document).ready(function(){
		// 목록 버튼 클릭 => 글 목록으로 이동
		$('#postListBtn').on('click', function(){
			locaiton.href="/post/post_list_view";
		});
		
		// 모두 지우기 버튼 클릭 => 제목, 글 내용 지운다.
		$('#clearBtn').on('click', function(){
			$('#subject').val(""); // 이 id를 안에 내용을 채우려고 할 때
			$('#content').val("");
		});
		
		// 글 저장
		$('#postCreateBtn').on('click', function(){
			let subject = $('#subject').val().trim();
			let content = $('#content').val().trim();
			
			if(subject == '') {
				alert("제목을 입력하세요.");
			}
			
			console.log(content);
			
			let file = $('#file').val(); // C:\fakepath\p_pysic_module.py
			// alert(file);
			
			// 파일이 업로드 된 경우에만 확장자 체크
			if(file != '') {
				//alert(file.split(".").pop().toLowerCase()); // 검사할 때는 다 소문자로 하거나 대문자로 해서 검사를 해야한다.
				let ext = file.split(".").pop().toLowerCase(); // ext = extension
				if($.inArray(ext, ['jpg', 'jpeg', 'png', 'gif'])  == -1) {
					alert("이미지 파일만 업로드 할 수 있습니다.");
					$('#file').val(""); // 파일을 비운다. => 아까 선택된 파일을 없어려고 하는 방법이면서 사용이 안되는 파일을 선택 안되게 하려고 하는 방법
					return;
				}
			}
				
			// 서버 - ajax
			
			// 이미지를 업로드 할 때는 form 태그가 있어야 한다. (자바스크립트에서 만듦)
			// append로 넣는 값은 form 태그의 name으로 넣는 것과 같다.(request parameter)
			let formData = new FormData();
			formData.append("subject", subject); // requestParam명이 되는 것이다.
			formData.append("content", content);
			formData.append("file", $('#file')[0].files[0]); // 이미지 하나만 올리는 방법
			
			// ajax 통신으로 formData에 있는 데이터 전송
			$.ajax({
				// request
				type:"post"
				, url:"/post/create"
				, data:formData				   // form 객체를 통째로 가져와준다.
				, enctype:"mutipart/form-data" // 파일 업로드를 위한 필수 설정
				, processData:false 		   // 파일 업로드를 위한 필수 설정
				, contentType:false 		   // 파일 업로드를 위한 필수 설정
				
				// response
				, success:function(data) {
					if(data.code == 1) {
						// 성공
						alert("메모가 저장되었습니다.");
						location.href="/post/post_list_view";
					} else{
						// 실패
						alert(data.errorMessage);
					} 
				}
				, error:function(e) {
					alert("메모 저장에 실패했습니다.");
				}				
			});
			
		});
	});
</script>
</body>
</html>