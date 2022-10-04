<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>로그인 페이지</title>
		<link rel="stylesheet" href="CSS/error.css">
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