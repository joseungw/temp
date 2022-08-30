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
			}
			#title{
				width:300px;
				text-align:center;
				margin:0 auto;
				padding:20px;
				background-color:rgba(255,255,255,0.7);
			}
			#sign{
				width:300px;
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
			#title a{
				font-size:50px;
			}
			p{
				font-size:25px;
			}
			.class{
				height:30px;
				font-size:15px;
			}
		</style>
	</head>
	<body>
		
		<div id="wrap">
			<div id="title">
				<a href="index.jsp"><b>에러페이지</b></a>
			</div>
			<div id="sign">
				<p><b>로그인하고 입장하기</b></p>
				<form method="post" action="login.do">
					<input type="text" name="id" id="inputid" class="size" placeholder="아이디"><br>
					<input type="password" name="pw" id="inputpw" class="size" placeholder="비밀번호">
					<input type="submit" value="LOGIN" onclick="return logincheak()"><br>
					<a href="sign.jsp">회원가입 하기</a>
				</form>
			</div>
		</div>
		<script>
			function logincheak(){
				if(document.getElementById("inputid").value==""){
					alert("아이디를 입력하셔야 합니다.");
					document.getElementById("inputid").focus();
					return false;
				}else if(document.getElementById("inputpw").value.length==0){
					alert("비밀번호를 입력하셔야 합니다.");
					document.getElementById("inputpw").focus();
					return false;
				}
				return true;
			}
		</script>
		${message }
	</body>
</html>