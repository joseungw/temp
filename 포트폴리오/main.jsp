<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
			body{
				background-color:#99CCFF;
				margin:0;
			}
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
			#menu a{
				background:
				    linear-gradient(
				      to right,
				      rgba(100, 200, 200, 1),
				      rgba(100, 200, 200, 1)
				    ),
				    linear-gradient(
				      to right,
				      rgba(255, 0, 0, 1),
				      rgba(255, 0, 180, 1),
				      rgba(0, 100, 200, 1)
				  	);
				background-size: 100% 3px, 0 3px;
				background-position: 100% 100%, 0 100%;
				background-repeat: no-repeat;
				transition: background-size 400ms;
			}
			#menu a:hover{
				background-size: 0 3px, 100% 3px;
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
			}
			#loginbox{
				width:200px;
				border:1px solid black;
				float:right;
				height:400px;
				display:inline-block;
			}
			#loginbox input{
				width:150px;
				height:50px;
				margin-top:20px;
				margin-left: 25px;
				font-size:20px;
				background-color:#0088FF;
				color:white;
			}
			#loginbox p{
				text-align:center;
				margin-top:90px;
			}
		</style>
	</head>
	<body>
		<%
			String id=(String)session.getAttribute("id");
		%>
		<div id="wrap">
			<div id="logo">
				<a href="index.jsp"><b>Sports</b></a>
			</div>
			<form method="get" action="">
				<div id="menu">
					<div id="list"><a href="soccer.jsp">축구</a></div>
					<div id="list"><a href="">농구</a></div>
					<div id="list"><a href="">야구</a></div>
					<div id="list"><a href="">배드민턴</a></div>
					<div id="list"><a href="">골프</a></div>
				</div>
			</form>
			<div id="loginbox">
				<input type="button" value="로그인" onclick="login()">
				<p><%=id %></p>
			</div>
		</div>
		<script>
			function login(){
				location.href="login.do";
			}
		</script>
	</body>
</html>
