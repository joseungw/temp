<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="DAO.UserDAO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>포트폴리오 랜딩페이지</title>
		<link rel="stylesheet" href="CSS/boardRegion.css">
	</head>
	<body>
		<%
			String id=null;
			if(session.getAttribute("id")!=null){
				id=(String)session.getAttribute("id");
			}
		%>
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
					<form method="get" action="boardregion.do">
						<select name="region" id="sel">
							<option value="" selected>--지역--</option>
							<option value="서울">서울</option>
							<option value="남양주">남양주</option>
							<option value="수원">수원</option>
							<option value="부산">부산</option>
							<option value="대구">대구</option>
							<option value="대전">대전</option>
							<option value="의정부">의정부</option>
							<option value="인천">인천</option>
						</select>
						<input type="hidden" name="category" value="${category }">
						<input type="hidden" name="currentPage" value="1">
						<input id="search" type="submit" value="검색" onclick="return noValue()">
					</form>
					
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
							<li class="otherPage"><a href="boardregion.do?category=${category }&region=${region }&currentPage=${i }"> ${i } </a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</ul>
				</div>
			</div>
		</div>
		<script>
			function post(){	//로그인 하지 않았을 때 alert
				if(<%=id ==null%>){
					alert("로그인 후 이용해주세요.");
				}
				else{
					location.href="post.jsp";
				}
			}
			function showpost(num){
				location.href="showpost?notenum="+num;
			}
			function noValue(){	//검색할 때 value가 아무것도 없을 때 alert
				if(document.getElementById("sel").value==""){
					alert("지역을 선택 해주세요.");
					return false;
				}
				return true;
			}
		</script>
	</body>
</html>