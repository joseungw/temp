<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>포트폴리오 랜딩페이지</title>
		<link rel="stylesheet" href="CSS/index.css">
		<style>
			@media all and (max-width:960px){
				#wrap{
					width:90%;
				}
				#menu{
					width:100%;
					height:60px;
				}
				#container{
					display:inline-block;
				}
				#list{
					display:none;
				}
				#radius{
					width:75%;
				}
				.slide{
					width:100%;
				}
				#loginbox{
					width:25%;
					border:0;
				}
					input[id=trigger] {
				    display: none;
				}
				#container{
					margin-top:18px;
					margin-left:25px;
				}
			
				/* label 중에 for가 trigger인 항목 */
				label[for=trigger] {
				    /* border: 1px solid red; */
				    width: 30px;
				    height: 20px;
				    display: block;
				    position: relative;
				    cursor: pointer;
				}
				
				/* label 중에 for가 trigger인 항목 안의 span */
				 label[for=trigger] span {
				    display: block;
				    height: 5px;
				    background-color: white;
				    left: 0;
				    border-radius:50px;
				    width: 100%;
				    position: absolute;
				    transition: 0.4s;
				    z-index: 1000;
				}
				
				/* label[for=trigger] > span의 n번째 요소 */
				label[for=trigger] span:nth-child(1) {
				    top:0;
				}
				
				label[for=trigger] span:nth-child(2) {
				    top:50%;
				}
				
				label[for=trigger] span:nth-child(3) {
				    top:100%;
				}
				/* check시 변하는 label[for=trigger] > span의 n번째 요소  */
				input[id=trigger]:checked + label span:nth-child(1) {
				    top: 50%;
				    transform: rotate(45deg);
				}
				
				input[id=trigger]:checked + label span:nth-child(2) {
				    opacity: 0;
				}
				
				input[id=trigger]:checked + label span:nth-child(3) {
				    top: 50%;
				    transform: rotate(-45deg);
				}
				/* 사이드바 부분 */
				.sidebar {
				    width: 250px;
				    height: 100vh;
				    background-color: white;
				    position: fixed;
				    top: 0;
				    left: 0;
				    left: -250px;
				    transition: 0.4s;
				}
				
				/* input[id=trigger]가 체크되었을 때 사이드바가 나오는 형제 인접자 */
				input[id=trigger]:checked ~ .sidebar {
				    left: 0;
				}
				
				/* check시 같이 밀려나가는 CSS 햄버거 메뉴 */
				input[id=trigger]:checked + label[for=trigger] {
				    left: 210px;
				    transition: 0.3s;
				}
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
				<div id="container">
					<input type="checkbox" id="trigger" >
			       	<label for="trigger">
		            	<!-- 햄버거 세줄 표현 -->
			            <span></span>
			            <span></span>
			            <span></span>
		        	</label>
		        	<div class="sidebar">
			        	축구
			        </div>
				</div>
				<div id="list"><a href="boardmain?category=축구&currentPage=1">축구</a></div>
				<div id="list"><a href="boardmain?category=농구&currentPage=1&">농구</a></div>
				<div id="list"><a href="boardmain?category=야구&currentPage=1&">야구</a></div>
				<div id="list"><a href="boardmain?category=배드민턴&currentPage=1">배드민턴</a></div>
				<div id="list"><a href="boardmain?category=골프&currentPage=1">골프</a></div>
			</div>
			<div id="main">
				<div id="radius">
					<img class="slide" src="images/soccer.jpg">
					<img class="slide" src="images/basketball.webp">
					<img class="slide" src="images/baseball.webp">
					<img class="slide" src="images/soccer2.webp">
					<img class="slide" src="images/golf.jpg">
					<img class="slide" src="images/badminton.jpg">
				</div>
				<c:choose>
					<c:when test="${id!=null }">
						<div id="loginbox">
							<input type="button" value="로그아웃" onclick="logout()">
							
							<p>${id } 님<br>환영합니다.</p>
							<br>
							<a href="infoUpdate?id=${id }">정보 수정</a><br>
							
						</div>
					</c:when>
					<c:otherwise>
						<div id="loginbox">
							<input type="button" value="로그인" onclick="login()">
							<p>로그인을 이용하시면<br>더욱 더 편리하게<br>이용하실 수 있습니다.</p>
						</div>
					</c:otherwise>
				</c:choose>
				
				<div id="mynote">
					<a onclick="return loginOk()" href="mynote.do?id=${id }&sports=${sports }&currentPage=1">
						내 게시물 보기
					</a>
				</div>
				<div id="myinfo">
					<a onclick="return loginOk()" href="myimport?id=${id }&password=${password }&sports=${sports }&city=${city }&currentPage=1">
						내 정보에 맞는 게시물 보기
					</a>
				</div>
				
			</div>
		</div>
		<script>
			function login(){
				location.href="login.do";
			}
			function logout(){
				location.href="Logout.do";
			}
			slideIndex=0;
			mySlide();
			function mySlide(){
				var i;
				var x=document.getElementsByClassName("slide");
				for(i=0; i<x.length; i++){
					x[i].style.display="none";
				}
				slideIndex++;
				if(slideIndex > x.length){
					slideIndex=1;
				}
				x[slideIndex-1].style.display="inline-block";
				setTimeout(mySlide,3000);
			}
		function loginOk(){
			if(<%=id ==null%>){
				alert("로그인 후 이용해주세요.");
				return false;
			}
			return true;
		}
		</script>
	</body>
</html>