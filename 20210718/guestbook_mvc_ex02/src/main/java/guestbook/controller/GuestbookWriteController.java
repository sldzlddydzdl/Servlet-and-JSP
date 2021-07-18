package guestbook.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.dto.MessageDto;
import guestbook.service.GuestbookService;

public class GuestbookWriteController extends HttpServlet{
	
	// 작성할 수 있는 화면을 보여주는 메서드
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("write doGet.................");
		req.getRequestDispatcher("/WEB-INF/view/writeForm.jsp").forward(req, resp);
		
	}
	
	
	// 작성된 데이터를 디비로 넣어주는 메서드
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("write doPost.................");
		
		req.setCharacterEncoding("utf-8");
		// request 로 전달받은 파라미터를 이용하여
		String guestName = req.getParameter("guestName");
		String message = req.getParameter("message");
		
		MessageDto messageDto = new MessageDto();
		messageDto.setGuestName(guestName);
		messageDto.setMessage(message);
		
		
		// 데이터 베이스에 데이타를 넣어주고
		// 서비스 객체를 통해서 
		GuestbookService service =  new GuestbookService();
		service.writeMessage(messageDto);
		
		
		// 결과 화면을 보여준다.
		// 리스트 화면으로 보내버리자!
		// 앞에 /가 있으면 루트부터의 url 을 의미
		// 앞에 /가 없으면 현재의 url 에 list 로 
		resp.sendRedirect("list");
		
	}

}
