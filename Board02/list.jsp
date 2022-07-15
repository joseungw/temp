<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>리스트 표현</title>
		<style>
			th, td{
				border:3px solid lightblue;
			}
			ul li{
				display:inline-block;
			}
		</style>
	</head>
	<body>
		<h1>
			currentPage : ${currentPage }<br>
			recordsPerPage : ${recordsPerPage }
		</h1>
		<table>
			<tr>
				<th>num</th>
				<th>title</th>
				<th>content</th>
			</tr>
			<c:forEach items="${data }" var="data">
				<tr>
					<td>${data.getNum() }</td>
					<td>${data.getTitle() }</td>
					<td>${data.getContent() }</td>
				</tr>
			</c:forEach>
		</table>
		<ul>
			<c:forEach begin="1" end="${nOfPage }" var="i">
				<c:choose>
					<c:when test="${currentPage eq i }">
						<li><a>${i }(현재)</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="ReadPage?currentPage=${i }&recordsPerPage=${recordsPerPage}">${i }</a></li>

				</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
	</body>
</html>