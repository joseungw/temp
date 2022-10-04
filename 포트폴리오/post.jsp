<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>포트폴리오 랜딩페이지</title>
		<link rel="stylesheet" href="CSS/post.css">
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
				<div id="list"><a href="boardmain?category=축구&currentPage=1">축구</a></div>
				<div id="list"><a href="boardmain?category=농구&currentPage=1">농구</a></div>
				<div id="list"><a href="boardmain?category=야구&currentPage=1">야구</a></div>
				<div id="list"><a href="boardmain?category=배드민턴&currentPage=1">배드민턴</a></div>
				<div id="list"><a href="boardmain?category=골프&currentPage=1">골프</a></div>
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