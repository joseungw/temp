<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>게시 글</title>
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
			#post a{
				text-decoration:none;
				background-color:red;
				color:white;
				border:2px solid blue;
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
				<div id="post">
					<a href="noticeUpdate">게시물 수정</a>
					<a href="noticeDelete">게시물 삭제</a>
					<div id="posttitle">
						<h2>${notice.getTitle()}</h2>
						<p>${notice.getUsernick() }</p>
						<p>${notice.getNotedate() }</p>
						<p>${notice.getContents() }</p>
						<p>${notice.getNotenum() }</p>
					</div>
				</div>
			</div>
		</div>
		<script>
			function noteDel(notenum){
				location.href="noticeDelete?notenum="+notenum;
			}
		</script>
	</body>
</html>