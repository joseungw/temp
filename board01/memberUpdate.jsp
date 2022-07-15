<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>회원정보수정 페이지</title>
		<style>
			table{
				border:1px solid black;
			}
			td{
				border:2px solid lightblue;
				padding:5px;
			}
		</style>
	</head>
	<body>
		<h1>회원정보 수정</h1>
		<form method="post" action="memberUpdate.do" name="frm">
			<table>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="${mem.name }" size="20" readonly></td>
				</tr>
				<tr>
					<td>아이디</td>
					<td>
						<input type="text" name="userid" value="${mem.userid }" readonly>
						
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="pwd"size="20"></td>
				</tr>
				<tr>
					<td>비밀번호 재확인</td>
					<td><input type="password" name="pwd_check" size="20"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email" value="${mem.email }" size="20"></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" name="phone" value="${mem.phone }" size="20"></td>
				</tr>
				<tr>
					<td>등급</td>
					<td>
						<c:choose>
							<c:when test="${mem.admin==0 }">
								<input type="radio" name="admin" value="1">관리자<br>
								<input type="radio" name="admin" value="0" checked>일반회원
							</c:when>
							<c:otherwise>
								<input type="radio" name="admin" value="1" checked>관리자<br>
								<input type="radio" name="admin" value="0" >일반회원
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>입력</td>
					<td>
						<input type="submit" value="정보수정" onclick="return joinCheck()">
						<input type="reset" value="초기화">
					</td>
				</tr>
			</table>
		</form>
		<script>
			function idCheck(){
				if(document.frm.userid.value==""){
					document.frm.userid.focus();
					return;
				}
				var url="idCheck.do?userid="+document.frm.userid.value;
				var popupX=(window.screen.width/2)-(450/2);
				var popupY=(window.screen.height/2)-(200/2);
				window.open(url, "_black_1", "toolbar=no, menubar=no, resizable=no, scrollbar=yes, width=450, height=200, left="+popupX+", top="+popupY);
			}
			function joinCheck(){
				if(document.frm.name.value.length==0){
					alert("이름을 입력해주세요.");
					frm.name.focus();
					return false;
				}
				if(document.frm.userid.value.length==0){
					alert("아이디를 입력해주세요.");
					frm.userid.focus();
					return false;
				}
				/* if(document.frm.userid.value.length<4){
					alert("아이디는 4문자 이상이어야 합니다.");
					frm.userid.focus();
					return false;
				} */
				if(document.frm.pwd.value==""){
					alert("비밀번호를 입력하셔야 합니다.");
					frm.pwd.focus();
					return false;
				}
				if(document.frm.pwd.value!=document.frm.pwd_check.value){
					alert("암호가 일치하지 않습니다.");
					frm.pwd_check.focus();
					return false;
				}
				if(document.frm.reid.value.length==0){
					alert("아이디 중복확인을 해주셔야 합니다.");
					frm.userid.focus();
					return false;
				}
				return true;
			}
		</script>
	</body>
</html>