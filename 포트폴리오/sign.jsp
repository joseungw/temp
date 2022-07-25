<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>회원가입</title>
		<style>
			body{
				background-color:#99CCFF;
				margin:0;
			}
			#wrap{
				width:600px;
				margin:0 auto;
				background-color:white;
			}
			h1{
				font-size:70px;
				text-align:center;
				margin-top:0px;
				padding-top:30px;
			}
			p{
				font-size:17px;
				color:red;
				text-align:center;
				text-decoration:underline;
			}
			#sel{
				width:308px;
				margin-top:10px;
				margin-bottom:10px;
				height:50px;
				font-size:20px;
			}
			#in{
				width:308px;
				margin:0 auto;
			}
			#ok{
				width:308px;
				height:50px;
				background-color:lightgray;
				font-size:15px;
			}
			#idok{
				background-color:lightgray;
				width:150px;
				height:30px;
				margin-left:80px;
				/* 아이디 중복체크는 아직 onclick 메서드 입력 안함 */
			}
			#ok:hover{
				background-color:#0088FF;
				color:white;
			}
			#idok:hover{
				background-color:#0088FF;
				color:white;
			}
			input[type="text"]{
				height:50px;
				width:300px;
				font-size:20px;
			}
			input[type="password"]{
				height:50px;
				width:300px;
				font-size:20px;
			}
			input{
				margin-top:10px;
				margin-bottom:10px;
			}
		</style>
	</head>
	<body>
		<div id="wrap">
			<div id="in">
				<h1>sports</h1>
				<p>모든 칸에 기입해주세요.</p>
				<form method="post" action="register.jsp" name="frm">
					<b>이름</b><br>
					<input type="text" name="name" placeholder="이름을 적어주세요." size="7"><br>
					<b>아이디</b><br>
					<input type="text" name="id" placeholder="영문,숫자 5 ~ 11자" size="11"><br>
					<input type="button" value="아이디 중복 확인하기" id="idok" onclick=""><br>
					<b>비밀번호</b><br>
					<input type="password" name="pass" placeholder="특수문자,영문,숫자 최소 8자"><br>
					<b>비밀번호 확인</b><br>
					<input type="password" name="passok" placeholder="비밀번호와 똑같이 입력해주세요."><br>
					<b>닉네임</b><br>
					<input type="text" name="nick" placeholder="3 ~ 8자" size="8"><br>
					<b>전화 번호</b><br>
					<input type="text" name="phone" placeholder="- 빼고 숫자만 적어주세요.">
					<b>활동지역</b><br>
					<select name="city" id="sel">
						<option value="" selected>--지역--</option>
						<option value="seuol">서울</option>
						<option value="namyang">남양주</option>
						<option value="suwon">수원</option>
						<option value="busan">부산</option>
						<option value="daegu">대구</option>
						<option value="daejun">대전</option>
						<option value="uijeong">의정부</option>
						<option value="incheon">인천</option>
					</select><br>
					<b>스포츠 종목</b><br>
					<select name="sports" id="sel">
						<option value="" selected>--스포츠 종목--</option>
						<option value="soccer">축구</option>
						<option value="basket">농구</option>
						<option value="base">야구</option>
						<option value="badmin">배드민턴</option>
						<option value="golf">골프</option>
					</select><br>
					<input type="button" value="회원가입 완료하기"id="ok" onclick="return joinCheck()">
				</form>
			</div>
		</div>
		<script>
		function joinCheck(){
			if(document.frm.name.value.length==0){
				alert("이름을 입력해주세요.");
				frm.name.focus();
				return false;
			}
			if(document.frm.id.value.length==0){
				alert("아이디를 입력해주세요.");
				frm.id.focus();
				return false;
			}
			if(document.frm.id.value.length<5){
				alert("아이디는 5문자 이상이어야 합니다.");
				frm.id.focus();
				return false;
			}
			if(document.frm.id.value.length>=11){
				alert("아이디는 11문자 이하여야 합니다.");
				frm.id.focus();
				return false;
			}
			if(document.frm.pass.value==""){
				alert("비밀번호를 입력하셔야 합니다.");
				frm.pass.focus();
				return false;
			}
			if(document.frm.pass.value.length<8){
				alert("비밀번호는 8자 이상이어야 합니다.");
				frm.pass.focus();
				return false;
			}
			if(document.frm.pass.value!=document.frm.passok.value){
				alert("암호가 일치하지 않습니다.");
				frm.passok.focus();
				return false;
			}
			if(document.frm.nick.value.length<3){
				alert("닉네임은 3자 이상이어야 합니다.");
				frm.nick.focus();
				return false;
			}
			if(document.frm.nick.value.length>8){
				alert("닉네임은 8자 이하여야 합니다.");
				frm.nick.focus();
				return false;
			}
			if(document.frm.city.value==""){
				alert("활동 지역을 정해주세요.");
				frm.city.focus();
				return false;
			}
			if(document.frm.sports.value==""){
				alert("스포츠 종목을 정해주세요.");
				frm.city.focus();
				return false;
			}
			return true;
		}
		</script>
	</body>
</html>