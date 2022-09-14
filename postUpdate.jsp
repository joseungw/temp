<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>Insert title here</title>
		<style>
			/* *{
				color:white;
			}
			body{
				background-color:#333333;
			} */
			
			#wrap{
				width:1000px;
				margin:0 auto;
				background-color:white;
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
			/* 여기까지 상단 리스트 부분 */
			#post a{
				text-decoration:none;
				background-color:red;
				color:white;
				border:2px solid blue;
			}
			#postTop{
				border-bottom:1px solid #0088FF;
				padding-left:50px;
				
			}
			#postTop h2{
				display:inline-block;
				font-size:25px;
				margin:15px;
			}
			#postTitle{
				border-bottom:1px solid #3399FF;
				padding-left:65px;
			}
			#postTitle h2{
				font-size:22px;
				padding:0;
				margin:0;
				margin-top:12px;
			}
			#postTitle p{
				display:inline-block;
				font-size:15px;
				margin:0;
				margin-bottom:12px;
			}
			#postMain{
				margin-left: 65px;
   				padding-top: 25px;
			}
			#line{
				font-weight:900;
			}
			/* 여기까지 게시판 메인부분 */
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
				<div id="postTop">
					<h2>
						${notice.getCategory() }   <span id="line">│</span>   ${notice.getRegion() }
					</h2>
					<c:choose>
						<c:when test="${notice.getUserid() eq id }">
							<a href="noticeUpdate?notenum=${notice.getNotenum() }">게시물 수정</a>
							<a href="noticeDelete?notenum=${notice.getNotenum() }&category=${notice.getCategory()}">게시물 삭제</a>
						</c:when>
					</c:choose>
				</div>
				<div id="postTitle">
					<h2>${notice.getTitle()}</h2>
					<p>${notice.getUsernick() }   <span id="line">│</span>   ${notice.getNotedate() }</p>
				</div>
				<div id="postMain">
					<p>${notice.getContents() }</p>
				</div>
			</div>
		</div>
	</body>
</html>