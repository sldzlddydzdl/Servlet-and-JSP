<%@page import="java.sql.SQLException"%>
<%@page import="guestbook.dao.MessageDao"%>
<%@page import="guestbook.dto.MessageDto"%>
<%@page import="jdk.internal.misc.FileSystemOption"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 인코딩작업도 하자
	request.setCharacterEncoding("utf-8");
	
	// 클라이언트에서 보낸 데이터를 서버에서 받아보자.
	// request 라는 객체안에 데이타를 넣어서 보내게된다.
	// form 을 통해서 온 데이타는 parameter 로 온다.
	// getParameter(html 에서 input 의 name 속성 을 넣으면 된다) -> String 으로만 넘어온다.
	String guestName = request.getParameter("guestName");
	String message = request.getParameter("message");
	
	System.out.println("guestName : " + guestName);
	System.out.println("message : " + message);

	// parameter 로 받은 데이터를 dto 객체에 담아준다
	MessageDto messageDto = new MessageDto();
	messageDto.setGuestName(guestName);
	messageDto.setMessage(message);
	
	// dao 를 통해서 디비에 데이터를 넣어주자
	MessageDao messageDao = MessageDao.getInstance();
	String result = "";
	try{
		int affectedRows = messageDao.insertMessage(messageDto);
		if(affectedRows > 0){
			result = "삽입 성공";
		}
	}catch(ClassNotFoundException e ){
		result = "삽입 실패";
		e.printStackTrace();
	}catch(SQLException e){
		result = "삽입 실패";
		e.printStackTrace();
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>write.jsp</title>
</head>
<body>
	<h1>받은데이터</h1>
	작성자 : <%=guestName  %><br> <!-- 자바의 변수를 html 에서 받을 수 있게된다 -->
	메시지 : <%=message %>
	<hr>
	메시지 작성 결과 : <%=result %>
</body>
</html>