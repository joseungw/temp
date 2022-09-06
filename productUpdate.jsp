<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>상품 관리</title>
		<link rel="stylesheet" href="style.css">
	</head>
	<body>
		<div id="wrap">
			<h1>상품 수정</h1>
			<form method="post" enctype="multipart/form-data" name="frm" action="productUpdate">
				<input type="hidden" name="code" value="${product.getCode() }">
				<input type="hidden" name="nomakeImg" value="${product.getPictureurl() }">
				<table>
					<tr>
						<td>
							<c:choose>
								<c:when test="${product.getPictureurl()=='/file/null' }">
									이미지가 없습니다.
								</c:when>
								<c:otherwise>
									<img src=".${product.getPictureurl() }">
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<table>
								<tr>
									<th>상품명</th>
									<td><input type="text" name="name" value="${product.getName() }"></td>
								</tr>
								<tr>
									<th>가격</th>
									<td><input type="text" name="price" value="${product.getPrice() }"></td>
								</tr>
								<tr>
									<th>사진</th>
									<td>
										<input type="file" name="pictureurl" ><br>
										(이미지를 변경하시려면 파일을 선택해 주세요.)
									</td>
								</tr>
								<tr>
									<th>설명</th>
									<td>
										<textarea rows="10" cols="90" name="description">${product.getDescription() }</textarea>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<input type="submit" value="수정하기" onclick="return check()">
				<input type="submit" value="다시작성">
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
					alert("가격은 숫자로 입력해 주셔야합니다.");
					frm.price.focus();
					return false();
				}
				return true;
			}
		</script>
	</body>
</html>