<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>포트폴리오 랜딩페이지</title>
		<style>
			/* *{
				color:white;
			}
			body{
				background-color:#333333;
			} */
			
			#wrap{
				width:1000px;
				margin:0 auto;
				background-color:white;
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
			/* 여기까지 기본 헤더부분 */
			#loginbox{
				width:200px;
				border:1px solid #0088FF;
				float:right;
				display:inline-block;
			}
			#loginbox input{
				width:120px;
				height:40px;
				margin-top:20px;
				margin-left: 40px;
				font-size:20px;
				background-color:#0088FF;
				color:white;
				font-weight:bold;
			}
			#loginbox input:hover{
				background-color:white;
				color:#0088FF;
				font-weight:bold;
			}
			#loginbox p{
				text-align:center;
			}
			#loginbox a{
				margin:0 auto;
			}
			#radius{
				width:500px;
				height:300px;
				border:1px solid black;
				background:url(test1.jpg);
				display:inline-block;
				border-radius:30px;
				margin-left:150px;
				margin-top:60px;
			}
			#main{
			}
		</style>
	</head>
	<body>
		<%
			String category=(String)session.getAttribute("sports");
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
				<div id="radius">
					
				</div>
					<c:choose>
						<c:when test="${id!=null }">
							<div id="loginbox">
								<input type="button" value="로그아웃" onclick="logout()">
								
								<p>${id } 님<br>환영합니다.</p>
								<a href="mynote.do?id=${id }&sports=${sports }">
									내 게시물 보기
								</a><br>
								<a href="">내 정보 수정</a><br>
								<a href="">정보에 맞는 게시물 보기</a>
							</div>
						</c:when>
						<c:otherwise>
							<div id="loginbox">
								<input type="button" value="로그인" onclick="login()">
								<p>로그인을 이용하시면<br>더욱 더 편리하게<br>이용하실 수 있습니다.</p>
							</div>
						</c:otherwise>
				</c:choose>
			</div>
		</div>
		<script>
			function login(){
				location.href="login.do";
			}
			function logout(){
				location.href="Logout.do";
			}
		</script>
	</body>
</html>