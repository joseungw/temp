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
		<style>
			/* *{
				color:white;
			}*/
			body{
			
			} 
			#wrap{
				width:1000px;
				margin:0 auto;
			}
			#logo{
				font-size:50px;
				text-align:center;
				padding:20px;
			}
			#logo a{
				text-decoration:none;
				color:#0088FF;
			}
			#menu{
				/* background-color:#0088FF; */
				text-align:center;
				font-size:0;
				padding:0;
			}
			
			#menu a:hover{
				border-bottom:3px solid white;
			}
			#list{
				display:inline-block;
				width:198px;
				height:60px;
				line-height:60px;
				border:1px solid #0088FF;
				background-color:#0088FF;
			}
			#list a{
				text-decoration:none;
				font-size:20px;
				color:white;
				font-weight:bold;
			}
			/*여기까지 header에 대한 CSS부분  */
			#main{
				width:100%;
			}
			table{
				border:3px solid #0088FF;
				width:100%;
				margin:0 auto;
				border-collapse:collapse;
			}
			thead th{
				font-size:20px;
				background-color:#0088FF;
				color:white;
			}
			tbody tr{
				text-align:center;
				border-bottom:2px solid white;
			}
			tbody tr:hover{
				border-bottom:2px solid #0088FF;
			}
			#sel{
				text-align:center;
				font-size:20px;
				border: 2px solid #0088FF;
			    width: 300px;
			    margin-left: 357px;
    			margin-top: 25px;
			}
			input[type=submit]{
				border: 2px solid #0088FF;
			    padding: 3px;
			    width: 70px;
			    margin-left: 15px;
			    padding-top: 4px;
    			padding-bottom: 2px;
    			background-color:white;
    			font-weight: bold;
			}
			table td{
				padding:10px;
				margin:5px;
			}
			.post3{
				width:140px;
			}
			.post1{
				width:120px;
			}
			#postit{
				background-color:white;
				border:2px solid #0088FF;
				padding:4px;
				font-weight:bold;
				float:right;
				margin-bottom: 10px;
			}
			#postit:hover{
				background-color:#0088FF;
				color:white;
				border:2px solid black;
			}
			#search:hover{
				border:2px solid black;
				background-color:#0088FF;
				color:white;
			}
		</style>
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
				<div id="list"><a href="boardmain?category=축구">축구</a></div>
				<div id="list"><a href="boardmain?category=농구">농구</a></div>
				<div id="list"><a href="boardmain?category=야구">야구</a></div>
				<div id="list"><a href="boardmain?category=배드민턴">배드민턴</a></div>
				<div id="list"><a href="boardmain?category=골프">골프</a></div>
			</div>
			<div id="main">
				<div id="boardmenu">
					<form method="get" action="boardregion.do?category=축구">
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
						<input type="hidden" name="sports" value="${sports }">
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