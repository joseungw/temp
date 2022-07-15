<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>로그인 페이지</title>
		<style>
			body{
				background:url(test1.jpg);
				background-size:cover;
			} 
			#wrap{
				width:600px;
				margin:0 auto;
				margin-top:130px;
				border:1px solid black;
			}
			#title{
				width:300px;
				text-align:center;
				margin:0 auto;
				background-color:rgba(255,255,255,0.7);
				padding:20px;
			}
			#sign{
				width:200px;
				margin:0 auto;
				margin-top:170px;
				text-align:center;
				background-color:rgba(255,255,255,0.7);
				padding:30px;
			}
			input {
				margin:5px;
			}
			input[value="LOGIN"]{
				padding:5px;
				width:100px;
				color:#0033FF;
			}
			input[value="LOGIN"]:hover{
				background-color:#0088FF;
				color:white;
			}
			a{
				text-decoration:none;
				color:black;
				font-size:12px;
			}
		</style>
	</head>
	<body>
		<div id="wrap">
			<div id="title">
				<h1>SPORTS</h1>
			</div>
			<div id="sign">
				<p><b>로그인하고 입장하기</b></p>
				<form method="post" action="#">
					<input type="text" name="id" placeholder="아이디"><br>
					<input type="password" name="pw" placeholder="비밀번호">
					<input type="submit" value="LOGIN"><br>
					<a href="sign.jsp">회원가입 하기</a>
				</form>
			</div>
		</div>
	</body>
</html>