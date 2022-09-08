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
			#menu a{
				background:
				    linear-gradient(
				      to right,
				      rgba(100, 200, 200, 1),
				      rgba(100, 200, 200, 1)
				    ),
				    linear-gradient(
				      to right,
				      rgba(255, 0, 0, 1),
				      rgba(255, 0, 180, 1),
				      rgba(0, 100, 200, 1)
				  	);
				background-size: 100% 3px, 0 3px;
				background-position: 100% 100%, 0 100%;
				background-repeat: no-repeat;
				transition: background-size 400ms;
			}
			#menu a:hover{
				background-size: 0 3px, 100% 3px;
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
			}
			/*여기까지 header에 대한 CSS부분  */
			#main{
				width:100%;
				border:1px solid black;
			}
			table{
				border:3px solid black;
				width:600px;
			}
			thead th{
				font-size:20px;
			}
			tbody tr{
				border:2px solid black;
				text-align:center;
			}
			tbody tr:hover{
				background-color:black;
				color:white;
			}
			#sel{
				width:400px;
				text-align:center;
				font-size:20px;
			}
		</style>
	</head>
	<body>
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
				<form method="get" action="boardregion.do?region=">
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
					<input type="submit" value="검색">
				</form>
				<br>
				<input type="button" value="게시물 작성하기" onclick="post()">
				<table>
					<thead>
						<tr>
							<th>날짜</th>
							<th>닉네임</th>
							<th>제목</th>
							<th>카테고리</th>
							<th>지역</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="list">	
							<tr  onclick="href=showpost('${list.getNotenum() }')">
								<td class="post1">${list.getNotedate() }</td>
								<td class="post2">${list.getUsernick() }</td>
								<td class="post3">${list.getTitle() }</td>
								<td class="post4">${list.getCategory() }</td>
								<td class="post5">${list.getRegion() }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<script>
			function post(){
				location.href="post.jsp";
			}
			function showpost(num){
				location.href="showpost?notenum="+num;
			}
		</script>
	</body>
</html>