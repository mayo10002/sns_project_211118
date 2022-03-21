<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<div class="d-flex justify-contents-between">
	<div class="logo">
		<h1 class="p-4">Instagram</h1>
	</div>
	<div class="login-info">
		<c:if test="${not empty userName}">
			<div class="mt-5 mr-4">
				<span class="font-weight-bold">마싯다님 안녕하세요</span>
				<a href="/user/sign_out" class="ml-2 text-white font-weight-bold">로그아웃</a>
			</div>
		</c:if>
	</div>
</div>


