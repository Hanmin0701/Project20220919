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
		<h1>로그인</h1>
		<!-- header -->
		<header class="bg-danger">
			<jsp:include page="../include/header_signIn.jsp"/> 
		</header>
		
		<!-- section -->
		<section class="contents bg-info">
			<jsp:include page="../${viewSignIn}.jsp"/>
		</section>
		<footer class="bg-primary">
			<jsp:include page="../include/footer.jsp"/>
		</footer>
	</div>
<script type="text/javascript">
	$(document).ready(function() { 
		$('#loginForm').on('submit', function(e){
			// 서브밋 기능 중단
			e.preventDefault();
			
			// validation
			// return false;
			let loginId = $('input[name=loginId]').val().trim();
			let password = $('input[name=password]').val();
			
			if (loginId == "") {
				alert("아이디를 입력하세요.");
				return false;
			}
			
			if (password == "") {
				alert("비밀번호를 입력하세요.");
				return false;
			}
			
			// ajax
			let url = $(this).attr('action');
			console.log();
			let params = $(this).serialize(); // loginId=aaaa&password=aaaa
			console.log();
			
			$.post(url, params); // request
			.done(function(data) { // response
				if(data.code == 1) { // 성공
					document.location.href="/post/post_list_view"; // 글 목록을 이동
				} else { // 실패
					alert(data.errorMessage);	
				}
		 	};
		});
	});
</script>
</body>
</html>