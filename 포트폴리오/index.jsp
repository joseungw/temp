<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>포트폴리오 랜딩페이지</title>
		<style>
			#wrap{
				width:1000px;
				margin:0 auto;
				border:1px solid black;
			}
			#logo{
				font-size:40px;
				text-align:center;
				background-color:#0088FF;
				padding:20px;
			}
			#logo a{
				text-decoration:none;
				color:white;
			}
			h1{
				text-align:center;
				font-size:40px;
				border:1px solid black;
			}
			#list{
				display:inline-block;
				border:1px solid black;
				width:120px;
				height:50px;
			}
			#menu{
				/* background-color:#0088FF; */
			}
			select{
				margin-left:250px;
				width:300px;
				height:30px;
				text-align:center;
				font-size:20px;
				margin-bottom:30px;
			}
			#loginbox{
				width:200px;
				border:1px solid black;
				float:right;
				height:400px;
			}
			#loginbox input{
				width:150px;
				height:50px;
				margin-top:20px;
				margin-left: 25px;
				font-size:20px;
				background-color:#0088FF;
				color:white;
			}
			#loginbox p{
				text-align:center;
				margin-top:90px;
			}
		</style>
	</head>
	<body>
		<div id="wrap">
			<div id="logo">
				<a href="index.jsp"><b>Sports</b></a>
			</div>
			<form method="get" action="">
				<div id="sel">
					<select name="city" >
						<option value="" selected>==지역 선택==</option>
						<option value="seoul">서울</option>
						<option value="namyang">남양주</option>
						<option value="suwon">수원</option>
						<option value="busan">부산</option>
						<option value="daegu">대구</option>
						<option value="daejun">대전</option>
						<option value="uijeong">의정부</option>
						<option value="incheon">인천</option>
					</select>
				</div>
				<div id="menu">
					<div id="list"><a href="">축구</a></div>
					<div id="list"><a href="">농구</a></div>
					<div id="list"><a href="">야구</a></div>
					<div id="list"><a href="">배드민턴</a></div>
					<div id="list"><a href="">골프</a></div>
				</div>
			</form>
			<div id="loginbox">
				<input type="button" value="로그인" onclick="login()">
				<p>로그인을 이용하시면<br>더욱 더 편리하게<br>이용하실 수 있습니다.</p>
			</div>
		</div>
		<script>
			function login(){
				location.href="login.do";
			}
		</script>
	</body>
</html>