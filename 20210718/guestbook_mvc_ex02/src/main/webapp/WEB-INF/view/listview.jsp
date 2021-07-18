<%@page import="guestbook.dto.MessageDto"%>
<%@page import="guestbook.dto.MessageListViewDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
				<!--  태그라이브러리 태그형태로 반복문 같은 로직을 수행할 수 있도록도와주는 기능이 있는아이! -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	window.addEventListener("DOMContentLoaded" , function () {
		// 작성 버튼에 이벤트 추가
		document.querySelector("#write").addEventListener("click" , function () {
				location.href="write";
		})
	})
</script>
</head>
<body>
	<h1>list 화면</h1>
	<button id="write">작성</button>
	<!-- messageListViewDto.getMessageTotalCount(); -->
	<c:if test="${viewData.messageTotalCount == 0 }">
		<b>데이타가 없습니다.</b>
	</c:if>
	<c:if test="${viewData.messageTotalCount > 0 }">
		<c:forEach var="m" items="${viewData.messageList }">
			<article>
				메시지ID: ${m.messageId}
				<a href="update?messageId=${m.messageId}">수정</a> 
				<a href="delete?messageId=${m.messageId}">삭제</a>
				<br>
				작성자: ${m.guestName }<br>
				작성일: ${m.writeDate } , 수정일: ${m.updateDate }<br>
				내용:
				<div>
				 	${m.message }
				 </div>
				 <hr>
			</article>
		</c:forEach>
		
		<%-- 페이징 --%>
		<hr>
		<section>
			<c:forEach var="page" begin="1" end="${viewData.pageTotalCount}">
				<a href="list?page=${page}">[${page}]</a>
			</c:forEach>
		</section>
		
	</c:if>	
	
	
	
</body>
</html>