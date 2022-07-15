<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>자료 목록</title>
	</head>
	<body>
		<form action="ReadPage" method="get">
			<input type="hidden" name="currentPage" value="1">
			한 페이지에 보여줄 수를 골라주세요
			<select name="recordsPerPage">
				<option value="5">5</option>
				<option value="10">10</option>
				<option value="15">15</option>
			</select>
			<input type="submit" value="보기">
		</form>
		<!-- P1 : 0~10 (n1=0, n2=10)  -->
		<!-- P2 : 11~20 (n1=10, n2=10) -->
		<!-- P3 : 21~30 (n1=20, n2=10) -->
	</body>
</html>