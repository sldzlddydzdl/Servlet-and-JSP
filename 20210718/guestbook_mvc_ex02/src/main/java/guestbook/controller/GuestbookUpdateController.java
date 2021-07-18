package guestbook.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.dto.MessageDto;
import guestbook.service.GuestbookService;

public class GuestbookUpdateController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("update doGet...........");
		//messageId 를 받아
		String messageIdStr = req.getParameter("messageId");
		int messageId = Integer.parseInt(messageIdStr);
		
		
		// 서비스를 통해서 
		//해당되는 message 정보를 가져온다.
		GuestbookService service = new GuestbookService();
		MessageDto messageDto = service.getMessageDtoById(messageId);
	
		
		//수정폼으로 보낸다.
		req.setAttribute("messageDto", messageDto);
		req.getRequestDispatcher("/WEB-INF/view/updateForm.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String messageIdStr = req.getParameter("messageId");
		String guestName = req.getParameter("guestName");
		String message = req.getParameter("message");
		String writeDateStr = req.getParameter("writeDate"); // yyyy-MM-dd'T'HH:mm:ss
		
		MessageDto messageDto = new MessageDto();
		messageDto.setMessageId(Integer.parseInt(messageIdStr));
		messageDto.setGuestName(guestName);
		messageDto.setMessage(message);
		messageDto.setWriteDate(LocalDateTime.parse(writeDateStr));
		
		
		
		System.out.println(messageDto);
		
		
		GuestbookService service = new GuestbookService();
		service.update(messageDto);
		
		resp.sendRedirect("list");
	}
}
