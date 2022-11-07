<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>관리자 페이지</title>
		<link rel="stylesheet" href="CSS/adminPage.css">
	</head>
	<body>
		<div id="wrap">
			<div id="logo">
				<a href="index.jsp"><b>Sports</b></a>
			</div>
			<div id="menu">
				<div id="list"><a href="boardmain?category=축구&currentPage=1">축구</a></div>
				<div id="list"><a href="boardmain?category=농구&currentPage=1&">농구</a></div>
				<div id="list"><a href="boardmain?category=야구&currentPage=1&">야구</a></div>
				<div id="list"><a href="boardmain?category=배드민턴&currentPage=1">배드민턴</a></div>
				<div id="list"><a href="boardmain?category=골프&currentPage=1">골프</a></div>
			</div>
			<div id="main">
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>이름</th>
							<th>아이디</th>
							<th>닉네임</th>
							<th>전화번호</th>
							<th>비밀번호</th>
							<th>지역</th>
							<th>카테고리</th>
							<th>회원 탈퇴</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${UserList }" var="list">	
							<tr>
								<td class="post1">${list.getNum() }</td>
								<td class="post3">${list.getName() }</td>
								<td class="post3">${list.getId() }</td>
								<td class="post3">${list.getNick() }</td>
								<td class="post3">${list.getPhone() }</td>
								<td class="post3">${list.getPassword() }</td>
								<td class="post3">${list.getCity() }</td>
								<td class="post3">${list.getSports() }</td>
								<td class="post2"><a href="userDelete?num=${list.getNum() }">탈퇴</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="page">
					<ul>
						<c:forEach begin="1" end="${nOfPage }" var="i">
							<c:choose>
								<c:when test="${currentPage eq i }">
									<li ><a>${i }(현재)</a></li>
								</c:when>
								<c:otherwise>
									<li class="otherPage"><a href="boardmain?category=${category }&currentPage=${i }&userid=${id }"> ${i } </a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>