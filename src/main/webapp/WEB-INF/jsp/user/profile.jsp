<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<c:if test="${not empty userName}"> 
			<div id="profile-area" class="d-flex justify-content-between align-items-center m-4">
				<div class="d-flex align-items-center">
					<img src="https://www.iconninja.com/files/26/970/557/profile-icon.png" alt="프로필 이미지"> 
					<div class="ml-4"><h2>${userName}님</h2><h4>${userLoginId}</h4></div>
				</div>
				
			</div>
		</c:if>
	</div>
</div>