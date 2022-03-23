<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="post-create-box w-50">
		<textarea class="post-textarea" placeholder="무슨 일이 일어나고 있나요?"></textarea>
		<div class="d-flex justify-content-between">
			<input type="file" id="file" class="d-none" accept=".gif,.jpg,.jpeg,.png">
			<a href="#" id="fileUploadBtn"><img src="#" alt="이미지 업로드 버튼">
			<img src="https://www.iconninja.com/files/505/794/492/image-icon.png" alt="이미지 삽입"></a>
			<button type="button" class="btn btn-primary">게시</button>
		</div>
	</div>
	<div id="timelinePost">
	
	
	</div>
	
</div>
	
<script>
	$(document).ready(function(){
		// 파일 업로드 이미지 클릭 > 파일 선택 창이 떠야함
		$('#fileUploadBtn').on('click', function(e){
			e.preventDefault(); // 먼저 눌리지 않게 방지
			$('#file').click(); // input file을 클릭한 것과 같은 효과
		});
		
		//사용자가 파일 업로드를 했을 때 유효성 확인 및 업로드된 파일 이름 노출
		$('#file').on('change',function(e){
			let fileName = e.target.files[0].name;
			//alert(fileName);
			let fileArr = fileName.split(".");
			
			//확장자 유효성 체크 - 오류가 나기 쉬운 방법이다. 
			if(fileArr.length < 2 ||
					(fileArr[fileArr.length - 1] != 'gif'
					&& fileArr[fileArr.length - 1] != 'jpg'
					&& fileArr[fileArr.length - 1] != 'jpeg'
					&& fileArr[fileArr.length - 1] != 'png')){
				alert("이미지 파일만 업로드 해주세요.");
				$(this).val(''); //파일이 서버에 업로드되지 않도록 비워둔다.
				$('#fileName').val(''); //파일명도 비워줌
				return;
			}
			
			$('#fileName').text(fileName);
			
		});
	});
</script>