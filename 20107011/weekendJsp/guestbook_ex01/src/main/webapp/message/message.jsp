<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>message.jsp</title>
</head>
<body>
	<!--  
		http://localhost/guestbook_ex01/message/write.jsp 
		/guestbook+ex01/message/write.jsp webapp 이 처음을 기준으로 경로를 써준다. 	
	-->
	<form action="write.jsp" method="post">
		<fieldset>
			<legend>방명록</legend>
			<input type="text" name="guestName" placeholder="작성자"><br>
			<textarea rows="5" cols="20" name="message" placeholder="내용"></textarea>
			<button>내용 남기기</button>
			<button>기기기</button>
		</fieldset>
	</form>
</body>
</html>