<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>상품 입력</title>
		<link rel="stylesheet" href="style.css">
	</head>
	<body>
		<div id="wrap">
			<h1>상품 등록 - 관리자</h1>
			<form method="post" enctype="multipart/form-data" name="frm">
				<table>
					<tr>
						<th>상품명</th>
						<td><input type="text" name="name"></td>
					</tr>
					<tr>
						<th>가격</th>
						<td><input type="text" name="price"></td>
					</tr>
					<tr>
						<th>상품사진</th>
						<td><input type="file" name="pictureurl"></td>
					</tr>
					<tr>
						<th>상품설명</th>
						<td>
							<textarea cols="80" rows="10" name="description"></textarea>
						</td>
					</tr>
				</table>
				<br>
				<input type="submit" value="등록" onclick="return check()">
				<input type="reset" value="다시작성">
				<input type="button" value="목록보기" onclick="location.href='productList'">
			</form>
		</div>
		<script>
			function check(){
				if(document.frm.name.value.length==0){
					alert("상품명을 써주세요.");
					frm.name.focus();
					return false;
				}
				if(document.frm.price.value.length==0){
					alert("가격을 입력해 주세요.");
					frm.price.focus();
					return false;
				}
				if(isNaN(document.frm.price.value)){
					alert("가격은 숫자로 입력하셔야 합니다.");
					frm.price.focus();
					return false;
				}
				return true;
			}
		</script>
	</body>
</html>