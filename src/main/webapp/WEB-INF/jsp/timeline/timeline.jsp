<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<c:if test="${not empty userId}">
		<div class="post-create-box">
			<textarea id="" class="post-textarea" placeholder="무슨 일이 일어나고 있나요?"></textarea>
			<div class="d-flex justify-content-between">
				<div>
					<input type="file" id="file" class="d-none" accept=".gif,.jpg,.jpeg,.png"> 
					<a href="#" id="fileUploadBtn"> 
					<img src="https://www.iconninja.com/files/505/794/492/image-icon.png" alt="이미지 삽입" width="30"></a>
				</div>
				<button type="button" class="btn btn-primary">게시</button>
			</div>
		</div>
		</c:if>
		
		<!-- 타임라인 -->
		<div id="timelinePost" class="border rounded my-3">
			<div class="">
				<div class="d-flex justify-content-between">
					<small class="font-weight-bold">mayo10002</small> <a href="#"><img
						src="https://www.iconninja.com/files/860/824/939/more-icon.png"
						alt="더보기" width="30"></a>
					<!-- 본문 이미지  -->
				</div>
				<div class="post-image">
					<img
						src="https://cdn.pixabay.com/photo/2018/05/10/20/40/basil-3388692_1280.jpg"
						alt="본문 이미지" width="100%">
				</div>
				<!-- 좋아요 -->
				<div class="like">
					<a href="#"><img
						src="https://www.iconninja.com/files/527/809/128/heart-icon.png"
						width="18px" height="18px"></a> <a href="#"><small>좋아요
							11개</small></a>
				</div>
				<!-- 글씨 -->
				<div class="mt-2 d-flex">
					<small class="font-weight-bold">mayo10002</small> <span class="ml-3">내용내용</span>
				</div>
				<!-- 댓글 제목 -->
				<div class="comments mb-2">
					<span class="font-weight-bold ml-3 mb-1">댓글</span>
					<hr>
				</div>
				<!-- 댓글 목록 -->
				<div class="card-comment m-1">
					<span class="font-weight-bold">hagulu : </span> <span> 분류가 잘
						되었군요~</span>
	
					<%-- TODO: 댓글쓴이가 본인이면 삭제버튼 노출 --%>
					<a href="#" class="commentDelBtn"> <img
						src="https://www.iconninja.com/files/603/22/506/x-icon.png"
						width="10px" height="10px">
					</a>
				</div>
	
				<div class="card-comment m-1">
					<span class="font-weight-bold">jun_coffee : </span> <span> 철이
						없었죠 분류를 위해 클러스터를 썼다는게</span>
	
					<%-- TODO: 댓글쓴이가 본인이면 삭제버튼 노출 --%>
					<a href="#" class="commentDelBtn"> <img
						src="https://www.iconninja.com/files/603/22/506/x-icon.png"
						width="10px" height="10px">
					</a>
				</div>
				<!-- 댓글 부분은 꼭 클래스로 지정(타임라인에서는 comment 상자가 여러개 겹친다) postid지정하는 법 :  data-post-id="${post.id}" -->
				<%-- 댓글 쓰기 --%>
				<%-- 로그인 된 상태에서만 쓸 수 있다. --%>
				<c:if test="${not empty userId}">
				<div class="comment-write d-flex border-top mt-2">
					<input type="text" id="comment${post.id}"
						class="form-control border-0 mr-2" placeholder="댓글 달기" />
					<button type="button" class="btn btn-light commentBtn" data-post-id="${post.id}">게시</button>
				</div>
				</c:if>
			</div>
		</div>
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
						$('#commentBtn').on('click',function(){
							let postId = $(this).val();
							alert(postId);
							
							let commentContent = $(this).siblings('input').val().trim();
							alert(commentContent);
							
							// TODO ajax
							//type post url:comment/create / data :  postid, content:
							// error  functino (jqxhr . responseJSON.status;  alert error<s dmfaskdfk)
							
						});
					});
</script>