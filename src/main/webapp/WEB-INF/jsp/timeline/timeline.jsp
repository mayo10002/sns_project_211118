<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<!-- 글 게시하기 -->
		<c:if test="${not empty userId}">
		<div class="post-create-box">
			<textarea id="postTextArea" class="post-textarea" placeholder="무슨 일이 일어나고 있나요?"></textarea>
			<div class="d-flex justify-content-between">
				<div>
					<input type="file" id="file" class="d-none" accept=".gif,.jpg,.jpeg,.png"> 
					<a href="#" id="fileUploadBtn"> 
					<img src="https://www.iconninja.com/files/505/794/492/image-icon.png" alt="이미지 삽입" width="30"></a>
				</div>
				<button type="button" id="writeBtn" class="btn btn-primary">게시</button>
			</div>
		</div>
		</c:if>
		
		<!-- 타임라인 -->
		<c:forEach items="${cardViewList}" var="card">
			<div id="timelinePost" class="border rounded my-3">
				<div class="d-flex justify-content-between align-items-center ml-3 mr-2 p-2">
					<small class="font-weight-bold">${card.user.name}</small> 
					<a href="#"><img src="https://www.iconninja.com/files/860/824/939/more-icon.png"
						alt="더보기" width="30"></a>
				</div>
				<!-- 게시글 이미지  -->
				<div class="post-image">
					<img src="${card.post.imagePath}" alt="본문 이미지" width="100%">
				</div>
				<!-- 좋아요 버튼 -->
				<div class="like ml-3 mt-2">
					<!-- 좋아요 비활성화 상태 -->
					<a href="#" class="like-btn" data-post-id="${card.post.id}">
						<c:if test="${card.filledLike eq false}">
							<img
								src="https://www.iconninja.com/files/214/518/441/heart-icon.png"
								width="18px" height="18px" alt="좋아요 X">
						</c:if>
						<!-- 좋아요 활성화 상태 -->
						<c:if test="${card.filledLike eq true}">
							<img src="https://www.iconninja.com/files/527/809/128/heart-icon.png" width="18px" height="18px" alt="좋아요 O">
							<small>좋아요 ${card.count}개</small>
						</c:if>
					</a>
				</div>
				<!-- 게시글 본문 -->
				<div class="mt-2 d-flex ml-3 pd-2">
					<span class="font-weight-bold">${card.user.name}</span> <span class="ml-3">${card.post.content}</span>
				</div>
				<!-- 댓글 제목 -->
				<c:if test="${not empty card.commentList}">
					<div class="comments mb-2 mt-2">
						<span class="font-weight-bold ml-3 mb-1">댓글</span>
						<hr>
					</div>
					<!-- 댓글 목록 -->
					<div class="card-comment m-1">
						<c:forEach items="${card.commentList}" var="commentView">
							<span class="font-weight-bold ml-3">${commentView.user.name} :</span> 
							<span>${commentView.comment.content}</span>
			
							<!-- 삭제 버튼 (본인인 경우에만) -->
							<c:if test="${commentView.user.id eq userId}">
								<a href="#" class="commentDelBtn" data-comment-id="${commentView.comment.id}"> <img
									src="https://www.iconninja.com/files/603/22/506/x-icon.png"
									width="10px" height="10px">
								</a>
							</c:if>
							<br>
						</c:forEach>
					</div>
				</c:if>
				
				<!-- 댓글 부분은 꼭 클래스로 지정(타임라인에서는 comment 상자가 여러개 겹친다) postid지정하는 법 :  data-post-id="${post.id}" -->
				<!-- 댓글 쓰기 ( 로그인 상태에서만)  -->
				<c:if test="${not empty userId}">
					<div class="comment-write d-flex border-top mt-2">
						<input type="text" id="comment${card.post.id}"
							class="form-control border-0 mr-2" placeholder="댓글 달기" />
						<button type="button" class="btn btn-light commentBtn" data-post-id="${card.post.id}">게시</button>
					</div>
				</c:if>
			</div>
		</c:forEach>
	</div>
</div>

<script>
$(document).ready(function() {
// 파일 업로드 이미지 클릭 > 파일 선택 창이 떠야함
	$('#fileUploadBtn').on('click', function(e) {
		e.preventDefault(); // 먼저 눌리지 않게 방지
			$('#file').click(); // input file을 클릭한 것과 같은 효과
		});

	//사용자가 파일 업로드를 했을 때 유효성 확인 및 업로드된 파일 이름 노출
	$('#file').on('change',function(e) {
		let fileName = e.target.files[0].name;
		//alert(fileName);
		let fileArr = fileName.split(".");

		//확장자 유효성 체크 - 오류가 나기 쉬운 방법이다. 
		if (fileArr.length < 2|| 
		(fileArr[fileArr.length - 1] != 'gif'
		&& fileArr[fileArr.length - 1] != 'jpg'
		&& fileArr[fileArr.length - 1] != 'jpeg' && fileArr[fileArr.length - 1] != 'png')) {
			alert("이미지 파일만 업로드 해주세요.");
			$(this).val(''); //파일이 서버에 업로드되지 않도록 비워둔다.
			$('#fileName').val(''); //파일명도 비워줌
			return;
		}
		$('#fileName').text(fileName);

	});
	
	$('#writeBtn').on('click', function(e){
		// 글 입력 script
		let content = $('#postTextArea').val();
		if ( content.length < 1 ){
			alert("글을 입력해 주세요.");
			return;
		}
		
		// 이미지 업로드 script
		let file = $('#file').val();
		
		if (file != ""){
			console.log(file);
			let ext = file.split('.').pop().toLowerCase(); 
			if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
				alert("gif, png, jpg, jpeg 파일만 업로드 할 수 있습니다.");
				$('#file').val(''); 
				return;
			}
		}
		
		// 자바 스크립트에서 폼 태그를 만든다.
		let formData = new FormData();
		formData.append("content" , content);
		formData.append("file", $('#file')[0].files[0]); // 첫번 째 것을 선택해서 올리는 것.
		
		// TODO ajax
		
		$.ajax({
			type:"post"
			, url:"/post/create"
			, data: formData
			, enctype: "multipart/form-data" 
			, processData: false 
			, contentType: false 
			, success: function(data){
				if(data.result == "success"){
					location.reload();
				}else{
					alert(data.error_message);
				}
			}
			, error: function(e){
				alert("게시에 실패했습니다. 관리자에게 문의해주세요.");
			}
		});
	});
	// 댓글 게시 버튼 
	$('.commentBtn').on('click',function(e){
		let postId = $(this).data('post-id'); 
		// alert(postId);
								
		let commentContent = $(this).siblings('input').val().trim();
		//alert(commentContent);
		
		if (commentContent == "") {
			alert("댓글을 입력해주세요.");
			return;
		}
		
		// TODO ajax
		$.ajax({
			type:"post"
			, url:"/comment/create"
			, data: {"postId":postId, "content":commentContent}
			, success: function(data){
				if(data.result == "success"){
					location.reload();
				}
			}
			, error: function(jqXHR, textStatus, errorThrown){
				let errorMessage = jqXHR.responseJSON.status;
				alert(errorMessage + ":" + textStatus);
			}
		});						
		
		
	});
	
	// 댓글 삭제
	$('.commentDelBtn').on('click',function(){
		e.preventDefault(); 
		
		let commentId = $(this).data('comment-id');
		alert('댓글 삭제');
		
		$.ajax({
			type:"delete"
			,url:"/comment/delete"
			,data:{"commentId":commentId}
			,success : function(data){
				if(data.result == "success"){
					location.reload();
				}
			}
			, error: function(e){
				alert("삭제에 실패했습니다. 관리자에게 문의해주세요.");
			}
		});	
	});
	
	// 좋아요 버튼
	$('.like-btn').on('click', function(){
		e.preventDefault();
		let postId = $(this).data('post-id');
	
		$.ajax({
			type:""
			,url: "/like/" + postId
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