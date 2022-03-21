<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center container">
	<div class="sign-up-box">
		<h1 class="mb-4 text-secondary pl-2">회원 가입</h1>
		<form id="signUpForm" method="post" action="user/sign_up">
			<div class="sign-up-box">
				<!-- 아이디 -->
				<span>아이디</span>
				<div class="sign-up-input">
					<div class="d-flex">
						<input type="text" id="loginId" name="loginId" class="form-control my-2" placeholder="아이디를 입력하세요">
						<button type="button" id="loginIdCheckBtn" class="btn btn-success ml-3">중복 확인</button>
					</div>
					<!-- 아이디 alert & 중복확인 결과 -->
					<div id="idCheckLength" class="small text-danger  d-none my-2">ID를 4자 이상 입력하세요.</div>
					<div id="idCheckDuplicated" class="small text-danger d-none my-2">이미 사용중인 아이디입니다.</div>
					<div id="idCheckOk" class="small text-success d-none my-2">사용 가능한 아이디입니다.</div>
					
					<!-- 비밀번호 -->
					<span>비밀번호</span>
					<input type="password" id="password" name="password" class="form-control my-2" placeholder="비밀번호를 입력하세요">
					<input type="password" id="confirmPassword" name="confirmPassword" class="form-control mb-2" placeholder="비밀번호를 다시 입력하세요">
					
					<!-- 이름 --> 
					<span>이름</span>		
					<input type="text" id="name" name="name" class="form-control my-2" placeholder="이름을 입력하세요">
					
					<!-- address -->
					<span>전화번호</span>
					<input type="text" id="address" name="address" class="form-control my-2" placeholder="이메일 또는 전화번호를 입력하세요">
  				</div>
			</div>
			<button type="button" id="signUpBtn" class="btn btn-primary float-center">회원가입</button>
		</form>
	
	</div>

</div>

<script>
$(document).ready(function(){
	//아이디 중복확인
	$('#loginIdCheckBtn').on('click',function(e){
		let loginId = $('#loginId').val().trim();
		alert(loginId);
		// 경고 문구 초기화
		$('#idCheckLength').addClass('d-none');
		$('#idCheckDuplicated').addClass('d-none');
		$('#idCheckOk').addClass('d-none');
		
		if(loginId.length < 4){
			$('#idCheckLength').removeClass('d-none');
			return;
		}
		
		
		$.ajax({
			url:"/user/is_duplicated_id"
			, data:{"loginId":loginId}
			, success: function(data){
				if(data.result){
				if(data.result){
					//w 중복인 경우
					$('#idCheckDuplicated').removeClass('d-none');
				}else {
					$('#idCheckOk').removeClass('d-none');
				}
			}
			, error: function(e){
				alert("아이디 중복확인에 실패했습니다. 관리자에게 문의해주세요.");
			}
		});
		
	});
	//회원 가입 btn
	$('#signUpBtn').on('click',function(){
		
		let loginId = $('#loginId').val().trim();
		
		if(loginId == ""){
			alert("아이디를 입력해 주세요.");
			return;
		}
		
		let password = $('#password').val().trim();
		let confirmPassword = $('#confirmPassword').val();
		
		if(password  == ''){
			alert('비밀번호를 입력하세요.');
			return;
		}
		if(password != confirmPassword){
			alert("비밀번호가 일치하지 않습니다. 다시 입력하세요.")
			
			//텍스트 박스 초기화
			$('#password').val('');
			$('#confirmPassword').val('');
			return;
			
		}
		
		let name = $('#name').val().trim();
		if(name == ''){
			alert('이름을 입력하세요.');
			return;
		}
		
		let address = $('#address').val().trim();
		if(address == ''){
			alert('이메일 또는 전화번호를 입력하세요.');
			return;
		}
		
		// 아이디 중복확인이 완료되었는지 확인

		if($('#idCheckOk').hasClass('d-none')){
			alert('id 중복확인을 다시 해주세요.');
			return;
		}
		
		// 회원가입 서버 요청 => ajax
		let url = $('#signUpForm').attr('action'); // form에 있는 action 주소 불러오기	
		let params = $('#signUpForm').serialize();
		console.log(params);
		
		$.post(url, params)   
		.done(function(data){
			if(data.result == "success"){
				alert('가입을 환영합니다! 로그인을 해주세요.');
				location.href = "/user/sign_in_view";
			}else {
				alert('가입에 실패했습니다. 다시 시도해주세요.');
			}
		});
	});
});
</script>