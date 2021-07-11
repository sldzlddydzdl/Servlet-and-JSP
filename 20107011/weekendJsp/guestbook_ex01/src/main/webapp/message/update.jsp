<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.sql.SQLException"%>
<%@page import="guestbook.dao.MessageDao"%>
<%@page import="guestbook.dto.MessageDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 전달받은 파라미터를 받자
	String messageIdStr = request.getParameter("messageId");
	int messageId = -1;
	if(messageIdStr != null && messageIdStr != ""){
		// 문자열 id 를 int 로 변경
		messageId = Integer.parseInt(messageIdStr);
	}
	
	// id 에 해당되는 방명록데이터를 가져온다.
	MessageDao messageDao = MessageDao.getInstance();
	MessageDto messageDto = null;
	try{
		messageDto = messageDao.selectMessageById(messageId);
	}catch(SQLException | ClassNotFoundException e){
		e.printStackTrace();
	}

	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/guestbook_ex01/message/updateCss.css" type="text/css"/> 
</head>
<body>
	<h1>수정 화면!</h1>
		<form action="/guestbook_ex01/message/updateProc.jsp" method="post" >
		<fieldset>
			<legend>방명록</legend>
			<input type="text" name = "messageId" value="<%=messageDto.getMessageId()%>" readonly="readonly">
			작성일 : <%= messageDto.getWriteDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) %>,
			수정일 : <%= messageDto.getUpdateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) %><br>
			
			<input type="text" name="guestName" placeholder="작성자" value="<%=messageDto.getGuestName()%>"><br>
			<textarea rows="5" cols="20" name="message" placeholder="내용"><%=messageDto.getMessage() %></textarea>
			<button>내용 수정하기</button>
			<p>
				수정화면수정화면
			</p>
		</fieldset>
	</form>
</body>
</html>