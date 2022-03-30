<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<c:if test="${not empty userName}"> 
			<div id="profile-area" class="d-flex justify-content-between align-items-center m-4">
				<div class="d-flex align-items-center">
					<img src="https://www.iconninja.com/files/26/970/557/profile-icon.png" alt="프로필 이미지" width="100" height="100"> 
					<div class="ml-4"><h2>${userName}님</h2><h4>${userLoginId}</h4></div>
				</div>
				<!--로그인 아이디가 자신일 때는 환경설정 버튼-->
				<c:if test="${commentView.user.id eq userId }"> 
				<!-- 수정사항: profileView 를 만들어서 jstl 바꿔줘야한다 -->
					<a href="#" alt="환경설정 이동">
						<img src="https://www.iconninja.com/files/373/816/921/setting-icon.png" alt="" width="50" height="50">
					</a>
				</c:if>
				<!-- 로그인 아이디가 자신이 아닐 때는 팔로우 관련 버튼-->
				<c:if test="${commentView.user.id ne userId }">
					<a href="#" id="followBtn">
					<!-- 팔로우 안 되어있을 때에는 팔로우 버튼 -->
						<c:if test="${followFilled eq false}">
							<img src="" alt="">
						</c:if>
					<!-- 팔로우 되어있을 때에는 팔로우 취소 버튼 -->
					
					</a>
				</c:if>
			</div>
		</c:if>
	</div>
</div>