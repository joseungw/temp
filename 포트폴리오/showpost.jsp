<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>게시 글</title>
		<style>
			/* *{
				color:white;
			}*/
			body{
			
			} 
			#wrap{
				width:1000px;
				margin:0 auto;
			}
			#logo{
				font-size:50px;
				text-align:center;
				padding:20px;
			}
			#logo a{
				text-decoration:none;
				color:#0088FF;
			}
			#menu{
				/* background-color:#0088FF; */
				text-align:center;
				font-size:0;
				padding:0;
			}
			#menu a:hover{
				border-bottom:3px solid white;
			}
			#list{
				display:inline-block;
				width:198px;
				height:60px;
				line-height:60px;
				border:1px solid #0088FF;
				background-color:#0088FF;
			}
			#list a{
				text-decoration:none;
				font-size:20px;
				color:white;
				font-weight:bold;
			}
			/* 여기까지 상단 리스트 부분 */
			
			#postTop{
				border-bottom:1px solid #0088FF;
				padding-left:50px;
				
			}
			#postTop h2{
				display:inline-block;
				font-size:25px;
				margin:15px;
			}
			#postTitle{
				border-bottom:1px solid #3399FF;
				padding-left:65px;
				padding-right:65px;
			}
			#postTitle h2{
				font-size:22px;
				padding:0;
				margin:0;
				margin-top:12px;
				margin-bottom:5px;
			}
			#notedate{
				color:#999;
			}
			#postTitle p{
				display:inline-block;
				font-size:14px;
				margin:0;
				margin-bottom:12px;
			}
			#postMain{
				padding:25px 65px 25px 65px;
   				border-bottom:1px solid #3399FF;
   				min-height:250px;
			}
			#line{
				font-weight:900;
			}
			/* 여기까지 게시판 메인부분 */
			#comTitle{
				border-bottom:1px solid #3399FF;
				padding-left:65px;
			}
			#comTitle p, #combtn{
				display:inline-block;
			}
			#combtn{
				border:2px solid #3399FF;
				padding:6px;
				margin-left:15px;
			}
			#combtn:hover{
				border:2px solid black;
				color:white;
				background-color:#3399FF;
			}
			#comWrite{
				resize:none;
				border:2px solid #3399FF;
				width: 620px;
    			min-height: 100px;
    			margin-left: 54px;
    			margin-top: -40px;
			}
			#combox{
				margin: 20px 65px;
				padding:0;
				display:none;
			}
			#comok{
				padding: 3px;
			    width: 70px;
			    margin-left: 612px;
			    border:2px solid #3399FF;
			    background-color:white;
			}
			#comok:hover{
				border:2px solid black;
				background-color:#3399FF;
				color:white;
			}
			ul{
				margin:0;
				border-bottom:1px solid #99CCFF;
			}
			ul li{
				list-style:none;
				padding:0px 25px;
			}
			#li1, #li2, #li3{
				display:inline-block;
				font-size:14px;
			}
			#li1{
				width:200px;
			}
			#li1 p{
				display:inline-block;
				margin-left:45px;
				color:#999;
				font-size:12px;
			}
			#li2{
				width: 490px;
			}
			#li3{
				width: 120px;
				color:#999;
				margin-left: 8px;
			}
			#li3 p{
				display:inline-block;
				margin-left:30px;
				color:black;
				padding:5px;
			}
			#recombox{
				display:none;
				margin-left:105px;
				margin-top: -15px;
				font-size:14px;
			}
			#recomWrite{
				resize: none;
			    border: 2px solid #3399FF;
			    width: 620px;
			    min-height: 100px;
			    margin-left: 70px;
   				margin-top: -35px;
			}
			#recomok{
				padding: 3px;
			    width: 70px;
			    margin-left: 628px;
			    border: 2px solid #3399FF;
			    background-color: white;
			}
			#commentUpdate, #commentDelete{
				border:2px solid #3399FF;
				color:#3399FF;
				display:inline-block;
				width:70px;
				height:30px;
			}
			#other{
				width:75px;
				display:inline-block;
			}
			#other p{
				display:inline-block;
				margin-left: 40px;
				
			}
	/* ====================================================== */
			
		</style>
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
				<div id="list"><a href="boardmain?category=축구">축구</a></div>
				<div id="list"><a href="boardmain?category=농구">농구</a></div>
				<div id="list"><a href="boardmain?category=야구">야구</a></div>
				<div id="list"><a href="boardmain?category=배드민턴">배드민턴</a></div>
				<div id="list"><a href="boardmain?category=골프">골프</a></div>
			</div>
			<div id="main">
				<div id="postTop">
					<h2>
						${notice.getCategory() }   <span id="line">│</span>   ${notice.getRegion() }
					</h2>
					<c:choose>
						<c:when test="${notice.getUserid() eq id }">
							<a href="noticeUpdate?notenum=${notice.getNotenum() }">게시물 수정</a>
							<a href="noticeDelete?notenum=${notice.getNotenum() }&category=${notice.getCategory()}">게시물 삭제</a>
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
							<div id="combtn" onclick="comWrite()">댓글달기↓</div>
						</div>
						<div id="combox">
							<p id="comnick">${nick }</p>
							<textarea id="comWrite" name="comcon" placeholder="댓글을 입력해주세요."></textarea>
							<input type="submit" id="comok" value="등록" formaction="comment">
						</div>
<!-- hidden 값가져가기 -->	<input type="hidden" name="postnum" value="${notice.getNotenum() }">
<!-- hidden 값가져가기 -->	<input type="hidden" name="comid" value="${id }">
<!-- hidden 값가져가기 -->	<input type="hidden" name="comnick" value="${nick }">
					</form>
					
					<!-- =====================답급 부분+++++++++++++++++ -->
					
					<form method="get" action="recomment">
						<c:forEach items="${cmt }" var="cmt">
							<ul>
								<li>
									<div id="li1">
										${cmt.getComnick() }
										<p id="oncl" onclick="recomWrite()">답글달기</p>
										
									</div>
									<div id="li2">
										${cmt.getComcon() }
									</div>
									<div id="li3">
										${cmt.getComdate() }
									</div>
										<c:choose>
											<c:when test="${cmt.getComid() eq id}">
												<div id="other">
													<p onclick="">...</p>
													<!-- <div id="commentUpdate">수정</div>
													<div id="commentDelete">삭제</div> -->
												</div>
											</c:when>
										</c:choose>
									<div id="recombox">
										<p>┗ ${nick }</p>
										<textarea id="recomWrite" name="recomcon" placeholder="답글을 입력해주세요."></textarea>
										<input type="submit" id="recomok" value="등록" formaction="recomment">
									</div>
								</li>
							</ul>
						</c:forEach>
<!-- hidden 값가져가기 -->	<input type="hidden" name="postnum" value="${notice.getNotenum() }">
<!-- hidden 값가져가기 -->	<input type="hidden" name="comid" value="${id }">
<!-- hidden 값가져가기 -->	<input type="hidden" name="comnick" value="${nick }">
					</form>
				</div>
			</div>
		</div>
		<script>
			function comWrite(){
				var combox=document.getElementById("combox");
				if(combox.style.display=="none"){
					combox.style.display="inline-block";
				}
				else{
					combox.style.display="none";
				}
			}
			function recomWrite(){
				var recombox=document.getElementById("recombox");
				if(recombox.style.display=="none"){
					recombox.style.display="inline-block";
				}
				else{
					recombox.style.display="none";
				}
			}
		</script>
	</body>
</html>