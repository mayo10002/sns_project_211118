<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-center">
	<div class="w-50">
	<!-- 프로필 부분 -->
		<div id="profile-area" class="d-flex justify-content-between align-items-center m-4">
			<div class="d-flex align-items-center">
				<img src="https://www.iconninja.com/files/26/970/557/profile-icon.png" alt="프로필 이미지" width="100" height="100"> 
				<div class="ml-4"><h2>${profileView.user.name}님</h2><h4>${profileView.user.loginId}</h4></div>
			</div>
			<!--로그인 아이디가 자신일 때는 환경설정 버튼-->
			<c:if test="${profileView.user.id eq userId}">
				<a href="#" alt="환경설정 이동">
					<img src="https://www.iconninja.com/files/373/816/921/setting-icon.png" alt="환경설정 이미지" width="50" height="50">
				</a>
				<!-- 아이디 연락처 이름 정도만 바꾸는 걸로.... 모달로 만들기??? -->
			</c:if>
			<!-- 로그인 아이디가 자신이 아닐 때는 팔로우 버튼-->
			<c:if test="${profileView.user.id ne userId}">
				<a href="#" id="followBtn" data-post-id="${profileView.user.id}">
				<!-- 팔로우 안 되어있을 때에는 팔로우 버튼. data id는 profileView에서 user.id를 가져와야 한다 -->
					<c:if test="${followFilled eq false}">
						<div class="border border-primary rounded pd-4">팔로우하기</div>					
					</c:if>
				<!-- 팔로우 되어있을 때에는 팔로우 취소 버튼 -->
					<c:if test="${followFilled eq true}">
						<div class="bg-secondary rounded pd-4">팔로잉</div>
					</c:if>
				</a>
			</c:if>
			<!-- 게시글 / 마음함 선택 버튼 -->
			<div class="d-flex justify-content-center">
				
			</div>
		</div>
		<hr>
		<!-- 게시글 모음이 보이는 부분. c:forEach 필요함. div 하나마다 걸어줘야 할 듯 -->
		<div id="likeList" class="d-flex flex-row-reverse flex-wrap-reverse justify-content-center">
			
			<!-- 	<a href="#"><img src="#" alt="" width="200" height="200"></a> -->
			<div class="test-use bg-primary">테스트1</div>
			<div class="test-use bg-secondary">테스트2</div>
			<div class="test-use bg">테스트3</div>
			<div class="test-use bg-primary">테스트4</div>
			<!-- 클릭 시 모달로 post카드 하나씩 보이게 하고싶음. 가능할까... -->
		</div>
		<!-- 팔로워 목록이 보이는 부분 : c:forEach -->
				<!-- 클릭시 그 사람 프로필이 보이게 구현(하고싶음) -->
		<div id="followList" class="d-none">
		
			<div class="d-flex flex-column-reverse">
				<div class="follower my-2">
					<img src="https://www.iconninja.com/files/26/970/557/profile-icon.png" alt="팔로워" width="50" height="50" class=""> <span class="ml-4 mr-4">이름</span> <small>아이디</small>
				</div>
			</div>
		</div>
		<!-- 팔로워 목록이 보이는 부분 : c:forEach -->
		<div id="followerList" class="d-none">
			<div class="d-flex flex-column-reverse">
				<div class="follower my-4">
					<img src="https://www.iconninja.com/files/26/970/557/profile-icon.png" alt="팔로워" width="50" height="50" class=""> <span class="ml-4 mr-4">이름</span> <small>아이디</small>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
	$('#followBtn').on('click',function(e){
		e.preventDefault();
		
		let followerId = $(this).data('user-id');
		
		$.ajax({
			url:"/follow/" + followerId
			,success: function(data){
				if(data.result == "success"){
					location.reload();
				}
			}
			,error: function(e){
				alert("동작에 실패했습니다. 관리자에게 문의해주세요.");
			}
		});
	});
	
});
</script>