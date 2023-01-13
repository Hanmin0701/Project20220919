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
		<h1>회원가입</h1>
		<!-- header -->
		<header class="bg-danger">
			<jsp:include page="../include/header.jsp"/> <!-- 상대 경로 ~> ./ ~> 폴더 하나를 위로 올라간다. -->
		</header>
		
		<!-- section -->
		<section class="contents bg-info">
			<jsp:include page="../${viewName}.jsp"/>
		</section>
		<footer class="bg-primary">
			<jsp:include page="../include/footer.jsp"/>
		</footer>
	</div>
<script>
	$(document).ready(function() {
	    $("#loginIdCheckBtn").on('click', function() {
	    	// 초기화
	    	$('#idCheckLength').addClass('d-none') // 숨김
	    	$('#idCheckDuplicated').addClass('d-none') // 숨김
	    	$('#idCheckOk').addClass('d-none') // 숨김

	    	let id = $("input[name=id]").val().trim();

	    	if(id.length < 4) {
	    		$('#idCheckLength').removeClass('d-none') // 경고문구 노출
	    		$('#idCheckDuplicated').addClass('d-none') // 숨김
	    		$('#idCheckOk').addClass('d-none') // 숨김
				return;	    		
	    	}
	    	
	    		// AJAX 통신 - 중복 확인
		    	$.ajax({
		    		// request
		    		url:"/user/is_duplicated_id"
		    		, data:{"loginId:" loginId}
		    		
		    		// response
		    		, success:function() {
						if(data.code == 1) {
							//  성공
							if(data.result) {
								// 중복
								$('#idCheckDuplicated').removeClass('d-none');
							} else {
								// 사용가능
								$('#idCheckOk').removeClass('d-none');
							}
						} else {
							// 실패
							alert(data.errorMessage);
						}
					}
		    		, error:functino(e) {
		    			alert("중복확인에 실패했습니다.");
		    			
		    		}
	    		}
	    	});
	    		
	    	// 회원가입
	    	$('#signUpForm').on('submit', function(e) {
	    		e.preventDefault(); // submit 기능 중단
	    		let deleteBookingId = $(this).data('booking-id');
	    		// validation
	    		let loginId = $('#loginId').val().trim();
	    		let password = $('#password').val().trim();
	    		let confirmPassword} = $('#confirmPassword').val().trim();
	    		let name = $('#name').val().trim();
	    		let email = $('#email').val().trim();
	    		
	    		
	    		if (loginId == "") {
	    			alert("아이디를 입력하세요.");
	    			return false;
	    		}
	    		
	    		if (password == "") {
	    			alert("비밀번호를 입력하세요.");
	    			return false;
	    		}
	    		
	    		if (password != confirmPassword) {
	    			alert("비밀번호가 일치하지 않습니다.");
	    			return false;
	    		}
	    		
	    		if (name == "") {
	    			alert("이름을 입력하세요.");
	    			return false;
	    		}
	    		
	    		if (email == "") {
	    			alert("이메일을 입력하세요.");
	    			return false;
	    		}
	    		
	    		// 아이디 중복확인 완료 됐는지 확인 -> idCheckOk d-none을 가지고 있으면 확인하는 alert 띄워야 한다.
	    		if ($('#idCheckOk').hasClass('d-none')) {
	    			alert("아이디 중복확인을 다시해주세요.")
	    			return false; // 빠져나가지 못하게
	    		}
	    		
	    		// 서버로 1번째 방법
	    		// 1)submit을 동작시킨다.
	    		// $(this)[0].sumbit(); // submit이기 때문에 화면이 넘어간다.
	    		
	    		// 2)ajax를 여기서 호출을 하는 것
	    		let url = $(this).attr('action');
	    		// data:{} 이렇게 할수 있지만
	    		let params = $(this).serialize(); // form 태그에 잇는 name으로 parameter들을 구성
	    		console.log(params);
	    		
	    		// 이름만 post이고 ajax이다. post 방식이면 이렇게 가능하다.
	    		$.post(url, params) // request
	    		.done(funcion(data){
	    			// response
	    			if(data.code == 1) {
	    				// 성공
	    				alert("가입을 환영합니다! 로그인 해주세요.");
	    				location.href="/user/sign_in_view";
	    			} else {
	    				// 실패
	    				alert(data.errorMessage);
	    			}
	    		});
	    	});
	 });
</script>
</body>
</html>