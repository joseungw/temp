<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="CSS/postUpdate.css">
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	</head>
	<body>
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
				<form method="post" action="noticeUpdate">
					<div id="postTop">
					<input type="hidden" name="notenum" value="${notice.getNotenum() }">
					<input type="hidden" name="notedate" value="${notice.getNotedate() }">
					<input type="hidden" id="sports" value="${notice.getCategory() }">
						<select name="category" id="sel1">
							<option value="축구">축구</option>
							<option value="농구">농구</option>
							<option value="야구">야구</option>
							<option value="배드민턴">배드민턴</option>
							<option value="골프">골프</option>
						</select>
						<h2>│</h2>
						<input type="hidden" id="city" value="${notice.getRegion() }">
						<select name="region" id="sel2">
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
						<input type="text" name="title" value="${notice.getTitle() }">
					</div>
					<div id="postMain">
						<textarea name="contents" placeholder="내용을 입력해주세요.">${notice.getContents() }</textarea>
					</div>
					<input type="submit" value="수정하기">
				</form>
			</div>
		</div>
		<script>
			$("#sel1 option").each(function(){
				if(this.value==$("#sports").val()){
					this.selected=true;
				}
			});
			$("sel2 option").each(function(){
				if(this.value==$("#city").val()){
					this.selected=true;
				}
			});
		</script>
	</body>
</html>