<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/write" method="post">
		<fieldset>
			<legend>방명록 작성</legend>
			<input type="text" name="guestName" placeholder="이름"> 
			<input type="submit" value="메시지 남기기"><br>
			<textarea rows="5" cols="30" name="message" placeholder="내용"></textarea>
			 
		</fieldset>	
	</form>
</body>
</html>