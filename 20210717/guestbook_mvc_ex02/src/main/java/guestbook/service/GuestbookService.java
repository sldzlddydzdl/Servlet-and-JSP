package guestbook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import guestbook.dao.GuestbookDao;
import guestbook.db.MysqlConnector;
import guestbook.dto.MessageDto;
import guestbook.dto.MessageListViewDto;

public class GuestbookService {
	static final int MESSAGE_COUNT_PER_PAGE=3;
	private GuestbookDao guestbookDao;
	
	public GuestbookService() {
		guestbookDao = GuestbookDao.getInstance();
	}
	
	//한 화면에 보여줄 MessageListViewDto 라는 데이터를 만들자
	public MessageListViewDto getMessageListViewDto(int page) {
		MessageListViewDto messageListViewDto = null;
		
		// dao 를 이용하여 데이터를 가져와 처리하자
		try(Connection conn = MysqlConnector.getConn()){
			
			// 전체 페이지 개수
			int messageTotalCount = guestbookDao.selectTotalCount(conn);
			
			// 메시지 리스트
			List<MessageDto> messageList = null;
			
			int firstRow = 0;
			
			// page에 따라 firstRow 를 셋팅
			if(messageTotalCount > 0) {
				firstRow = (page -1) * MESSAGE_COUNT_PER_PAGE;
			}else {
				page = 0;
			}
			
			// 방명록 리스트를 dao 를 통해 가져오자
			messageList = guestbookDao.selectList(conn, firstRow, MESSAGE_COUNT_PER_PAGE);
			
			// 화면에 보내줄 객체에 담자
			messageListViewDto = new MessageListViewDto(messageTotalCount, page, messageList, MESSAGE_COUNT_PER_PAGE, firstRow);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return messageListViewDto;
	}
	
	
}
