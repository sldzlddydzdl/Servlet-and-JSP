<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateForm.jsp</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/update" method="post">
		<fieldset>
			<legend>방명록 수정하기</legend>
			<input type="text" name="messageId" value="${messageDto.messageId}" readonly="readonly">
			<input type="text" name="writeDate" value="${messageDto.writeDate }" readonly="readonly">
			<input type="text" name="guestName" placeholder="이름" value="${messageDto.guestName}"> 
			<input type="submit" value="메시지 남기기"><br>
			<textarea rows="5" cols="30" name="message" placeholder="내용">${messageDto.message}</textarea>
		</fieldset>	
	</form>
</body>
</html>