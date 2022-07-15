<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>회원가입</title>
		<style>
			#wrap{
				width:400px;
				margin:0 auto;
				
			}
		</style>
	</head>
	<body>
		<div id="wrap">
			<div id="title">
				<h1>sports</h1>
				<p>모든 칸에 기입해주세요.</p>
			</div>
			<form method="post" action="register.jsp" name="frm">
				이름<br>
				<input type="text" name="name" placeholder="이름을 적어주세요." size="7"><br>
				아이디<br>
				<input type="text" name="id" placeholder="영문,숫자 5-11자" size="11">
				<input type="button" value="중복확인" onclick=""><br>
				비밀번호<br>
				<input type="password" name="pass" placeholder="특수문자,영문,숫자 최소 8자"><br>
				비밀번호 확인<br>
				<input type="password" name="passok" placeholder="비밀번화와 똑같이 입력해주세요."><br>
				닉네임<br>
				<input type="text" name="nick" placeholder="3-8자" size="8"><br>
				활동 지역<br>
				<select name="city">
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
				스포츠 종목<br>
				<select name="sports">
					<option value="" selected>--스포츠 종목--</option>
					<option value="soccer">축구</option>
					<option value="basket">농구</option>
					<option value="base">야구</option>
					<option value="badmin">배드민턴</option>
					<option value="golf">골프</option>
				</select><br>
				<input type="button" value="회원가입 완료" onclick="return joinCheck()">
			</form>
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