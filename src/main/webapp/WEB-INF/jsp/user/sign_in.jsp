<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<form id="loginForm" action="/user/sign_in" method="post">
		<div class="input-group mb-3 mt-3">
					<div class="input-group-prepend">
						<span class="input-group-text">ID</span>
					</div>
					<input type="text" class="form-control" id="loginId" name="loginId">
				</div>
				
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text">PW</span>
					</div>
					<input type="password" class="form-control" id="password" name="password">
				</div>
				
				<%-- btn-block: 로그인 박스 영역에 버튼을 가득 채운다. --%>
				<input type="submit" id="signInBtn" class="btn btn-block btn-info" value="로그인">
				
				<hr>
				<div class="d-flex justify-content-between">
				<span class="text-secondary">계정이 없으신가요?</span>
				<a class="btn  btn-dark" href="/user/sign_up_view">회원가입</a>
				</div>
	</form>

</div>


<script>
$(document).ready(function(){
	$('#loginForm').on('submit',function(e){
		let loginId = $('#loginId').val().trim();
		
		if( loginId == ''){
			alert("아이디를 입력해주세요.");
			return false;
		}
		
		let password = $('#password').val();
		if(password == ""){
		 	alert("비밀번호를 입력해주세요.");
		 	return false;
		}
		
		//ajax로 하기.
		let url = $(this).attr('action');
		let params = $(this).serialize();
		
		//콘솔 로그
		console.log("url:" + url);
		console.log("params: " + params);
		
		$.post(url, params)
		.done(function(data){
			if(data.result == "success"){
				// 로그인 성공
				location.href = "/timeline/timeline_view"; // 화면으로 가는 것
			}else {
				// 로그인 실패
				alert(data.error_message);
			}
		});
	});
});
</script>