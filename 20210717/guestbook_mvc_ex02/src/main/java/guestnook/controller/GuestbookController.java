package guestnook.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.dto.MessageListViewDto;
import guestbook.service.GuestbookService;

// 이 클래스는 서블릿이 되는 클래스
// 역할은 컨트롤러 역할을 하게됨.
public class GuestbookController extends HttpServlet{
	
	// get 방식으로 요청이 왔을 때 실행하는 메서드
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청에 맞는
		// 데이타를 만들고 
		// 결과로 보여줄 화면을 정해 보내준다. html or jsp 
		
		
		// 화면에서 요청한 페이지 번호를 받는다
		String pageStr = req.getParameter("page");
		
		int page = 1;
		if(pageStr != null ) {
			page = Integer.parseInt(pageStr);
		}
		
		
		// 리스트를 가져오기위한 서비스 객체를 만듬
		GuestbookService service = new GuestbookService();
		
		// 서비스를 이용해서 화면에 보내줄 데이터를 가져온다.
		MessageListViewDto messageListViewDto = service.getMessageListViewDto(page); 
		
		// 화면에 보내주기 위하여 request 객체에 속성(attribute) 에 데이타를 담는다
		req.setAttribute("viewData", messageListViewDto);
		
		// 데이타를 어떤 화면에 보여줄지 정해서 보내준다
		req.getRequestDispatcher("/WEB-INF/view/listview.jsp").forward(req, resp);
		
		
		
		
	}
	
	
	
}
