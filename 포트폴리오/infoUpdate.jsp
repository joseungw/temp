<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>회원가입</title>
		<link rel="stylesheet" href="CSS/infoUpdate.css">
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	</head>
	<body>
		<div id="wrap">
			<div id="in">
				<h1>sports</h1>
				<form method="post" action="infoUpdate" name="frm">
					<b>비밀번호</b><br>
					<input type="password" name="pass" placeholder="특수문자,영문,숫자 최소 8자"><br>
					<b>비밀번호 확인</b><br>
					<input type="password" name="phone" placeholder="- 빼고 숫자만 적어주세요.">
					<b>활동지역</b><br>
					<input type="hidden" id="city" value="${u.getCity() }">
					<select name="city" id="sel2">
						<option value="서울">서울</option>
						<option value="남양주">남양주</option>
						<option value="수원">수원</option>
						<option value="부산">부산</option>
						<option value="대구">대구</option>
						<option value="대전">대전</option>
						<option value="의정부">의정부</option>
						<option value="인천">인천</option>
					</select><br>
					<b>스포츠 종목</b><br>
					<input type="hidden" id="sports" value="${u.getSports() }">
					<select name="sports" id="sel1">
						<option value="축구">축구</option>
						<option value="농구">농구</option>
						<option value="야구">야구</option>
						<option value="배드민턴">배드민턴</option>
						<option value="골프">골프</option>
					</select><br>
					<input type="hidden" name="name" value="${name }">
					<input type="hidden" name="nick" value="${nick }">
					<input type="hidden" name="phone" value="${phone }">
					<input type="hidden" name="password" value="${password }">
					<input type="hidden" name="id" value="${id }">
					<input type="hidden" name="num" value="${num }">
					<input type="submit" value="정보수정 완료하기"id="ok" onclick="return joinCheck()">
				</form>
			</div>
		</div>
		<script>
		/* jQery로 select 부분 밸류 정의 */
		$("#sel1 option").each(function(){
			if(this.value==$("#sports").val()){
				this.selected=true;
			}
		});
		$("#sel2 option").each(function(){
			if(this.value==$("#city").val()){
				this.selected=true;
			}
		});
		/* 아래는 회원정보 수정에 대한 스크립트 부분 */
		function joinCheck(){
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
			alert("정보수정이 완료 되었습니다. 메인페이지로 돌아갑니다.");
			return true;
		}
		</script>
	</body>
</html>