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
   				padding-top: 25px;
   				border-bottom:1px solid #3399FF;
   				height:250px;
			}
			#line{
				font-weight:900;
			}
			/* 여기까지 게시판 메인부분 */
			#comTitle{
				border-bottom:1px solid #3399FF;
				padding:15px 65px;
			}
			ul{
				margin:3px;
				border-bottom:1px solid #99CCFF;
			}
			ul li{
				list-style:none;
				padding:5px 23px;
			}
			ul li div{
				display:inline-block;
				list-style:none;
				font-size:14px;
			}
			#li1{
				width:150px;
			}
			#li2{
				width:600px;
			}
			#li3{
				width:130px;
				color:#999;
			}
			textarea[name=comcon]{
				width:70%;
				height:100px;
				resize:none;
				margin-left:55px;
				margin-top:-15px;
				border:2px solid #3399FF;
			}
			#combox{
				margin-left:65px;
				font-size:14px;
			}
			#commain input{
				width:70px;
				height:25px;
				padding:3px;
			}
			#comok{
				padding: 3px;
			    width: 70px;
			    margin-left: 648px;
			    border:2px solid #3399FF;
			    background-color:white;
			}
			#comok:hover{
				border:2px solid black;
				background-color:#3399FF;
				color:white;
			}
			#recombox{
				/* display:none; */
				margin-left:225px;
			}
			#comnick1{
				margin-left:155px;
				font-size:14px;
			}
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
						<div id="comTitle">댓글</div>
						<c:forEach items="${cmt }" var="cmt">
							<ul>
								<li>
									<div id="li1">
										${cmt.getComnick() }
									</div>
									<div id="li2">
										${cmt.getComcon() }
									</div>
									<div id="li3">
										${cmt.getComdate() }
									</div>
								</li>
							</ul>
							<textarea id="recombox" name="comcon" placeholder="답글을 입력해주세요."></textarea>
						</c:forEach>
						
						<input type="hidden" name="postnum" value="${notice.getNotenum() }">
						<input type="hidden" name="comid" value="${id }">
						<input type="hidden" name="comnick" value="${nick }">
						
						<div id="combox">
							<div id="comnick2">
								${nick }
							</div>
							<div id="commain">
								
								<textarea name="comcon" placeholder="댓글을 입력해주세요."></textarea>
								<input type="hidden" name="notenum" value="${notice.getNotenum() }"> 
							</div>
							<input type="submit" id="comok" value="등록">
						</div>
					</form>
				</div>
			</div>
		</div>
		<script>
			
		</script>
	</body>
</html>