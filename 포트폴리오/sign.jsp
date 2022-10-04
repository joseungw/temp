<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>회원가입</title>
		<link rel="stylesheet" href="CSS/sign.css">
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	</head>
	<body>
		<div id="wrap">
			<div id="in">
				<h1>sports</h1>
				<p>모든 칸에 기입해주세요.</p>
				<form method="post" action="register.jsp" name="frm">
					<b>이름</b><br>
					<input type="text" name="name" placeholder="이름을 적어주세요." size="7"><br>
					<b>아이디<span id="idcheck"></span></b> <br>
					<input type="text" name="id" id="id" placeholder="영문,숫자 5 ~ 11자" size="11"><br>
					<input type="button" value="아이디 중복 확인하기" id="idok" onclick="return idCheck()"><br>
					<b>비밀번호</b><br>
					<input type="password" name="pass" placeholder="특수문자,영문,숫자 최소 8자"><br>
					<b>비밀번호 확인</b><br>
					<input type="password" name="passok" placeholder="비밀번호와 똑같이 입력해주세요."><br>
					<b>닉네임<span id="nickcheck"></span></b><br>
					<input type="text" name="nick" id="nick" placeholder="2 ~ 6자" size="8"><br>
					<input type="button" value="닉네임 중복 확인하기" id="nickok" onclick="return nickCheck()"><br>
					<b>전화 번호</b><br>
					<input type="text" name="phone" placeholder="- 빼고 숫자만 적어주세요.">
					<b>활동지역</b><br>
					<select name="city" id="sel1">
						<option value="" selected>--지역--</option>
						<option value="서울">서울</option>
						<option value="남양주">남양주</option>
						<option value="수원">수원</option>
						<option value="부신">부산</option>
						<option value="대구">대구</option>
						<option value="대전">대전</option>
						<option value="의정부">의정부</option>
						<option value="인천">인천</option>
					</select><br>
					<b>스포츠 종목</b><br>
					<select name="sports" id="sel2">
						<option value="" selected>--스포츠 종목--</option>
						<option value="축구">축구</option>
						<option value="농구">농구</option>
						<option value="야구">야구</option>
						<option value="배드민턴">배드민턴</option>
						<option value="골프">골프</option>
					</select><br>
					<input type="submit" value="회원가입 완료하기" id="allOk" onclick="return joinCheck()">
				</form>
			</div>
		</div>
		<script>
			/* 회원가입 아이디 중복체크 기능 구현 */
			var joinUp=false;
			var nickUp=false;
			function idCheck(){
				/* 아이디 서식 조건 확인 후 중복 체크 */
				
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
				$.ajax({
					url:"idCheck",
					type:"get",
					data:{
						"id" : $("#id").val()
					},
					success : function(res){
						if(res=="true"){
							$("#idcheck").html(" * 중복된 ID입니다. ").css("color","red");
							
						}else{
							$("#idcheck").html(" * 사용 가능한 ID입니다. ").css("color","green");
							joinUp=true;
						}
					},
					error : function(){
						alert("요청 실패");
					}
				});
				return joinUp;
			}
			/* 닉네임 중복체크 기능 구현 */
			function nickCheck(){
				if(document.frm.nick.value.length<2){
					alert("닉네임은 2자 이상이어야 합니다.");
					frm.nick.focus();
					return false;
				}
				if(document.frm.nick.value.length>6){
					alert("닉네임은 6자 이하여야 합니다.");
					frm.nick.focus();
					return false;
				}
				$.ajax({
					url:"nickCheck",
					type:"get",
					data:{
						"nick" : $("#nick").val()
					},
					success : function(res){
						if(res=="true"){
							$("#nickcheck").html(" * 중복된 닉네임입니다.").css("color","red");
							
						}
						else{
							$("#nickcheck").html(" * 사용 가능한 닉네임입니다.").css("color","green");
							nickUp=true;
						}
					},
					error:function(){
						alert("요청 실패");
					}
				});
				return nickUp;
			}
		
			/* 빈칸이나 서식에 맞게 작성 하였는지 확인 부분  */
			function joinCheck(){
				if(document.frm.name.value.length==0){
					alert("이름을 입력해주세요.");
					frm.name.focus();
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
				
				if(joinUp==true && nickUp==true){
					alert("회원가입이 되었습니다. 로그인페이지로 돌아갑니다.");
				}
				else if(joinUp==true && nickUp==false){
					alert("닉네임 중복 확인을 해주세요.");
					document.frm.nick.focus();
					joinUp=false;
					return nickUp;
				}
				else if(joinUp==false && nickUp==true){
					alert("아이디 중복 확인을 해주세요.");
					document.frm.id.focus();
					nickUp=false;
					return joinUp;
				}
				else if(joinUp==false && nickUp==false){
					alert("아이디와 닉네임 중복 확인을 해주세요.");
					document.frm.id.focus();
					return false;
				}
				return joinUp;
			}
		</script>
	</body>
</html>