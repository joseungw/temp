<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>포트폴리오 랜딩페이지</title>
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
			#main{
				width:100%;
				background-color:#A9E2F3;
			}
		</style>
	</head>
	<body>
	<%
		String id=null;
		if(session.getAttribute("id")!=null){
			id=(String)session.getAttribute("id");
		}
		String nick=(String)session.getAttribute("nick");
	%>
		<div id="wrap">
			<div id="logo">
				<a href="main.jsp"><b>Sports</b></a>
			</div>
			<div id="menu">
				<div id="list"><a href="soccer.jsp">축구</a></div>
				<div id="list"><a href="">농구</a></div>
				<div id="list"><a href="">야구</a></div>
				<div id="list"><a href="">배드민턴</a></div>
				<div id="list"><a href="">골프</a></div>
			</div>
			<div id="main">
				<form method="post" action="notice.do">
					<select name="postSports" id="sel">
						<option value="" selected>--스포츠 종목--</option>
						<option value="측구">축구</option>
						<option value="농구">농구</option>
						<option value="야구">야구</option>
						<option value="배드민턴">배드민턴</option>
						<option value="골프">골프</option>
					</select>
					<select name="region" id="sel">
						<option value="" selected>--지역--</option>
						<option value="서울">서울</option>
						<option value="남양주">남양주</option>
						<option value="수원">수원</option>
						<option value="부신">부산</option>
						<option value="대구">대구</option>
						<option value="대전">대전</option>
						<option value="의정부">의정부</option>
						<option value="인천">인천</option>
					</select>
					<input type="text" name="userid" value="<%=id %>">
					<input type="text" name="usernick" value="<%=nick %>">
					<div id="posttitle">
						<input type="text" name="title" placeholder="제목을 입력해주세요.">
					</div>
					<textarea cols="90" rows="10" name="contents" placeholder="내용을 입력해주세요."></textarea>
					<input type="submit" value="작성 완료">
				</form>
			</div>
		</div>
		
	</body>
</html>