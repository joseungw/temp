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
			/* 여기까지 메인메뉴 부분 */
			#main{
				width:100%;
			}
			#postTop{
				border-bottom:1px solid #0088FF;
				padding-left:50px;
				
			}
			#postTitle{
				border-bottom:1px solid #3399FF;
				padding-left:50px;
			}
			#postMain{
				margin-left: 50px;
   				padding-top: 25px;
			}
			#postTop h2{
				display:inline-block;
			}
			select{
				border:2px solid #0088FF;
				font-size:20px;
				padding:10px;
				margin-bottom: 20px;
				margin-top: 20px;
			}
			#sel1{
				width:16%;
			}
			#sel2{
				width:12%;
			}
			#postTitle input{
				border:2px solid #0088FF;
				font-size:20px;
				padding:10px;
				margin-top:20px;
				margin-bottom:20px;
				width:90%;
			}
			textarea{
				width:92%;
				border:2px solid #0088FF;
				height:300px;
				font-size:15px;
				resize:none;
			}
			input[type=submit]{
				border:2px solid #0088FF;
				background-color:white;
				color:#0088FF;
				padding: 8px;
   				width: 90px;
			    margin-left: 453px;
			    margin-top: 35px;
			}
			input[type=submit]:hover{
				background-color:#0088FF;
				color:white;
				border:2px solid black;
			}
			/* 여기까지 메인부분 */
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
				<form method="get" action="notice.do">
					<div id="postTop">
						<select name="category" id="sel1">
							<option value="" selected>--카테고리--</option>
							<option value="축구">축구</option>
							<option value="농구">농구</option>
							<option value="야구">야구</option>
							<option value="배드민턴">배드민턴</option>
							<option value="골프">골프</option>
						</select>
						<h2>│</h2>
						<select name="region" id="sel2">
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
					</div>
					<div id="postTitle">
						<input type="hidden" name="userid" value="<%=id %>">
						<input type="hidden" name="usernick" value="<%=nick %>">
						<input type="text" id="tit" name="title" placeholder="제목을 입력해주세요.">
					</div>
					<div id="postMain">
						<textarea name="contents" id="con" placeholder="내용을 입력해주세요.">${notice.getContents() }</textarea>
					</div>
					<input type="submit" value="게시물 등록" onclick="return postok()">
				</form>
			</div>
		</div>
		<script>
			function postok(){
				if(document.getElementById("sel1").value==""){
					alert("카테고리를 선택해주세요.");
					document.getElementById("sel1").focus();
					return false;
				}
				else if(document.getElementById("sel2").value==""){
					alert("지역을 선택해주세요.");
					document.getElementById("sel2").focus();
					return false;
				}
				if(document.getElementById("tit").value==""){
					alert("제목을 입력해 주세요.");
					document.getElementById("tit").focus();
					return false;
				}
				if(document.getElementById("con").value==""){
					alert("내용을 입력해 주세요.");
					document.getElementById("con").focus();
					return false;
				}
				return true;
			}
		</script>
	</body>
</html>