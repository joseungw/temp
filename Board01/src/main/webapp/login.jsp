<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>로그인 페이지</title>
	</head>
	<body>
		<h1>로그인</h1>
		<form method="post" action="login.do">
			<table>
				<tr>
					<td>아이디</td>
					<td><input id="inputid" type="text" name="userid"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input id="inputpw" type="password" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="로그인" onclick="return check()">
						<input type="reset" value="초기화">
						<input type="button" value="회원가입" onclick="reg()">
					</td>
				</tr>
			</table>
		</form>
		<script>
			function reg(){
				location.href="register.jsp";
			}
			function check(){
				if(document.getElementById("inputid").value==""){
					alert("아이디를 작성하셔야 합니다.");
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
	</body>
</html>