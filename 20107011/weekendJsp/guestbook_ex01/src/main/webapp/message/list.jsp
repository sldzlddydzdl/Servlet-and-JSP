<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="guestbook.dao.MessageDao"%>
<%@page import="java.sql.SQLException"%>
<%@page import="guestbook.dto.MessageDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 데이터베이스에서 목록을 조회해서 가져와야함 
	MessageDao dao = MessageDao.getInstance(); 
	List<MessageDto> list = null;
	boolean result = false;

	try{
		// dao 를 이용하여 list 를 가져온다.
		list = dao.selectMessageList();	
		if(!list.isEmpty()){
			result = true;
		}
	}catch(ClassNotFoundException e){
		result = false;
	}catch(SQLException e){
		result = false;
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>방명록</h1>
	<%-- 여기에 list 객체를 이용하여 출력 --%>
	
	<% if(result == false){ %>
		<h3>조회된 데이터가 없습니다.</h3>
	<% }else{ %>
		<% for(MessageDto m : list){ %>
			
			<article>
				메시지아이디 <%= m.getMessageId() %><br>
				작성일 : <%= m.getWriteDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) %><br> 
				수정일 : <%= m.getUpdateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) %><br>
				작성자 : <%= m.getGuestName() %><br>
				내용 : <%= m.getMessage()  %><br>
				<a href="update.jsp?messageId=<%= m.getMessageId() %>">수정</a>
				<a href="delete.jsp?messageId=<%= m.getMessageId() %>">삭제</a>
				<hr>
			</article>

		<% } %>
		
	<% } %>

</body>
</html>