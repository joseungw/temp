<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>메인 페이지</title>
	</head>
	<body>
		<h1>안녕하세요. ${loginUser.name }, ${loginUser.userid }님</h1>
		<a href="logout.do">로그아웃</a>
		<a href="memberUpdate.do?userid=${loginUser.userid }">회원정보 수정</a>
	</body>
</html>