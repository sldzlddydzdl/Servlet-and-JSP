<%@page import="guestbook.dao.MessageDao"%>
<%@page import="guestbook.dto.MessageDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// update.jsp 에서 보낸 내용을 dao 를 이용해서 update 처리해주자
	request.setCharacterEncoding("utf-8");
	MessageDto messageDto = new MessageDto();
	messageDto.setMessageId(Integer.parseInt(request.getParameter("messageId")));
	messageDto.setGuestName(request.getParameter("guestName"));
	messageDto.setMessage(request.getParameter("message"));
	
	MessageDao messageDao = MessageDao.getInstance();
	messageDao.updateMessage(messageDto);
	int affectedRows = messageDao.updateMessage(messageDto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateProc.jsp</title>
</head>
<body>
	<h1>수정 결과</h1>
	<% if(affectedRows > 0) { %>
		<h3>수정 완료!</h3>
	<% } else{ %> 
		<h3>수정 실패</h3>
	<%	} 	 %>
	<a href="list.jsp">목록으로 돌아가기</a>
</body>
</html>