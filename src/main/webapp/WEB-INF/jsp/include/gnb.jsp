<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<div class="d-flex justify-content-between">
	<div class="logo">
		<h1 class="p-4">Instagram</h1>
	</div>
	<div class="login-info">
		<c:if test="${not empty userName}">
			<div class="mt-5 mr-4 ">
				<a href="/timeline/timeline_view">
				<img src="https://www.iconninja.com/files/603/543/815/home-icon.png" alt="홈으로" width="30" height="30" class="mr-3"></a>
				<a href="/timeline/timeline_view">
				<img src="https://www.iconninja.com/files/26/970/557/profile-icon.png" alt="마이페이지" width="30" height="30" class="mr-3"></a>
				<span class="font-weight-bold">${userName}님</span>
				<a href="/user/sign_out" class="ml-2 text-secondary font-weight-bold"><small>로그아웃</small></a>
			</div>
		</c:if>
	</div>
</div>


