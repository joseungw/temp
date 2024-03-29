<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>내가 올린 게시글</title>
		<link rel="stylesheet" href="CSS/mynote.css">
	</head>
	<body>
		<div id="wrap">
			<div id="logo">
				<a href="index.jsp"><b>Sports</b></a>
			</div>
			<div id="menu">
				<div id="list"><a href="boardmain?category=축구&currentPage=1">축구</a></div>
				<div id="list"><a href="boardmain?category=농구&currentPage=1">농구</a></div>
				<div id="list"><a href="boardmain?category=야구&currentPage=1">야구</a></div>
				<div id="list"><a href="boardmain?category=배드민턴&currentPage=1">배드민턴</a></div>
				<div id="list"><a href="boardmain?category=골프&currentPage=1">골프</a></div>
			</div>
			<div id="main">
				<div id="boardmenu">
					<input id="postit" type="button" value="게시물 작성" onclick="post()">
				</div>
				<table>
					<thead>
						<tr>
							<th>카테고리</th>
							<th>지역</th>
							<th>제목</th>
							<th>닉네임</th>
							<th>날짜</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="list">	
							<tr onclick="href=showpost('${list.getNotenum() }')">
								<td class="post1">${list.getCategory() }</td>
								<td class="post1">${list.getRegion() }</td>
								<td class="post2">${list.getTitle() }</td>
								<td class="post1">${list.getUsernick() }</td>
								<td class="post3">${list.getNotedate() }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="page">
					<ul>
					<c:forEach begin="1" end="${nPage }" var="i">
					<c:choose>
						<c:when test="${currentPage eq i }">
							<li ><a>${i }(현재)</a></li>
						</c:when>
						<c:otherwise>
							<li class="otherPage"><a href="mynote.do?id=${id }&currentPage=${i }"> ${i } </a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</ul>
				</div>
			</div>
		</div>
	</body>
</html>