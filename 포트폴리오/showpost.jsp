<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>게시 글</title>
		<link rel="stylesheet" href="CSS/showpost.css">
	</head>
	<body>
		<%
			String id=null;
			if(session.getAttribute("id")!=null){
				id=(String)session.getAttribute("id");
			}
			String nick=null;
			if(session.getAttribute("nick")!=null){
				nick=(String)session.getAttribute("nick");
			}
		%>
		<div id="wrap">
			<div id="logo">
				<a href="index.jsp"><b>Sports</b></a>
			</div>
			<div id="menu">
				<div id="list"><a href="boardmain?category=축구&currentPage=1">축구</a></div>
				<div id="list"><a href="boardmain?category=농구&currentPage=1">농구</a></div>
				<div id="list"><a href="boardmain?category=야구&currentPage=1">야구</a></div>
				<div id="list"><a href="boardmain?category=배드민턴&currentPage=1">배드민턴</a></div>
				<div id="list"><a href="boardmain?category=골프&currentPage=1">골프</a></div>
			</div>
			<div id="main">
				<div id="postTop">
					<h2>
						${notice.getCategory() }   <span id="line">│</span>   ${notice.getRegion() }
					</h2>
					<c:choose>
						<c:when test="${notice.getUserid() eq id }">
							<div id="burger">
								<h1 class="burBtn">∴</h1>
								<div class="bgbtn">
									<a href="noticeUpdate?notenum=${notice.getNotenum() }">게시물 수정</a>
									<a href="noticeDelete?notenum=${notice.getNotenum() }&category=${notice.getCategory()}">게시물 삭제</a>
								</div>
							</div>
						</c:when>
					</c:choose>
				</div>
				<div id="postTitle">
					<h2>${notice.getTitle()}</h2>
					<p>${notice.getUsernick() }   <span id="line">│</span>   <span id="notedate">${notice.getNotedate() }</span></p>
				</div>
				<div id="postMain">
					<p>${notice.getContents() }</p>
				</div>
				<div id="comment">
					<form method="get" action="comment">
						<div id="comTitle"><p>댓글</p>
							<div id="combtn" onclick="comWrite()" >댓글달기↓</div>
						</div>
						<div id="combox">
							<p id="comnick">${nick }</p>
							<textarea id="comWrite" name="comcon" placeholder="댓글을 입력해주세요."></textarea>
							<input type="submit" id="comok" onclick="return nocomment()" value="등록">
						</div>
<!-- hidden 값가져가기 -->	<input type="hidden" name="postnum" value="${notice.getNotenum() }">
<!-- hidden 값가져가기 -->	<input type="hidden" name="comid" value="${id }">
<!-- hidden 값가져가기 -->	<input type="hidden" name="comnick" value="${nick }">
					</form>
					
					<!-- =====================답급 부분+++++++++++++++++ -->
					
					<c:forEach items="${cmt }" var="cmt">
						<form method="get" action="recomment">
							<ul>
								<li>
									<div id="li1">
										${cmt.getComnick() }
										<p class="oncl">답글달기</p>
									</div>
									<div id="li2">
										${cmt.getComcon() }
									</div>
									<div id="li3">
										${cmt.getComdate() }
										<c:choose>
											<c:when test="${cmt.getComid() eq id}">
												<p class="view">...</p>
											</c:when>
										</c:choose>
									</div>
									<c:choose>
										<c:when test="${cmt.getComid() eq id}">
											<div class="other">
												<div id="commentDelete" onclick="location.href='commentDelete?comnum='+${cmt.getComnum() }+'&postnum='+${notice.getNotenum() }">삭제</div>
											</div>
										</c:when>
									</c:choose>
									<c:forEach items="${rct }" var="rct">
										<c:choose>
											<c:when test="${cmt.getComnum() eq rct.getRecomnum() }">												
												<div id="recomment">
													<div id="li4">
														<p>┗ ${rct.getComnick() }</p>
														${rct.getComid()}
													</div>
													<div id="li5">
														${rct.getComcon() }
													</div>
													<div id="li6">
														${rct.getComdate() }
														<c:choose>
															<c:when test="${rct.getComid() eq id}">
																<p class="view2">...</p>
															</c:when>
														</c:choose>
													</div>
													<c:choose>
														<c:when test="${rct.getComid() eq id}">
															<div class="other2">
																<div id="commentDelete2" onclick="location.href='commentDelete?comnum='+${rct.getComnum() }+'&postnum='+${notice.getNotenum() }">삭제</div>
															</div>
														</c:when>
													</c:choose>
												</div>
											</c:when>
										</c:choose>
									</c:forEach>
									<div class="recombox">
										<p>┗ ${nick }</p>
										<textarea id="recomWrite" name="recomcon" placeholder="답글을 입력해주세요."></textarea>
										<input type="submit" id="recomok" onclick="return norecomment()"value="등록" formaction="recomment">
										<input type="hidden" name="comnum" value="${cmt.getComnum() }">
				<!-- hidden 값가져가기 -->	<input type="hidden" name="postnum" value="${notice.getNotenum() }">
				<!-- hidden 값가져가기 -->	<input type="hidden" name="comid" value="${id }">
				<!-- hidden 값가져가기 -->	<input type="hidden" name="comnick" value="${nick }">
									</div>
									
								</li>
								<li>
									
								</li>
							</ul>
						</form>
					</c:forEach>
				</div>
			</div>
		</div>
		<script>
			function comWrite(){	//댓글 부분 로그인 확인
				if(<%=id==null %>){
					alert("로그인 후 이용해주세요.");		//로그인 확인
				}
				else{				//로그인 상태라면 댓글박스 보이기
					var combox=document.getElementById("combox");
					if(combox.style.display==""){
						combox.style.display="inline-block";
					}
					else{
						combox.style.display="";
					}
				}
			}
			//==============================================================
			//============답글 로그인 확인후 로그인 상태라면 답글박스 보이기
			var oncl=document.getElementsByClassName("oncl");
			for(var i=0; i<oncl.length; i++){
				showrecom(i);
			}
			function showrecom(i){
				oncl[i].addEventListener("click",function(){
					var recombox=document.getElementsByClassName("recombox");
					if(<%=id==null %>){
						alert("로그인 후 이용해주세요.");	
					}
					else{
						if(recombox[i].style.display==""){
							recombox[i].style.display="inline-block";
						}
						else{
							recombox[i].style.display="";
						}
						
					}
				});
			}
			//=================댓글 답글에 아무것도 입력하지 않았을 때====================
			function nocomment(){
				var com=document.getElementById("comWrite");
				if(com.value==""){
					alert("댓글을 입력해주세요.");
					return false;
				}
				
			}
			function norecomment(){
				var recom=document.getElementById("recomWrite");
				if(recom.value==""){
					alert("답글을 입력해주세요.");
					return false;
				}
				
			}
			//============================햄버거 버튼 부분===========================
			/* function showbg(){
				var bgbtn=document.getElementsByClassName("bgbtn");
				if(bgbtn.style.display==""){
					bgbtn.style.display="inline-block";
				}
				else{
					bgbtn.style.display="";
				}
			} */
			var burBtn=document.getElementsByClassName("burBtn");
			burBtn[0].addEventListener("click",function(){
				var bgbtn=document.getElementsByClassName("bgbtn");
				if(bgbtn[0].style.display==""){
					bgbtn[0].style.display="inline-block";
				}
				else{
					bgbtn[0].style.display="";
				}
			});
			
			//==============================================
			//댓글 삭제 버튼 클릭시 삭제 버튼 보이기
			var view=document.getElementsByClassName("view");
			for(var j=0; j<view.length; j++){
				viewDelete(j);
			}
			function viewDelete(j){
				view[j].addEventListener("click",function(){
					var other=document.getElementsByClassName("other");
					
					if(other[j].style.display==""){
						other[j].style.display="inline-block";
					}else{
						other[j].style.display=""
					}
				});
			}
			//==============================================
			//답글 삭제 버튼 클릭시 삭제 버튼 보이기
			var view2=document.getElementsByClassName("view2");
			for(var k=0; k<view2.length; k++){
				viewDelete2(k);
			}
			function viewDelete2(k){
				view2[k].addEventListener("click",function(){
					var other2=document.getElementsByClassName("other2");
					
					if(other2[k].style.display==""){
						other2[k].style.display="inline-block";
					}else{
						other2[k].style.display=""
					}
				});
			}
			
		</script>
	</body>
</html>