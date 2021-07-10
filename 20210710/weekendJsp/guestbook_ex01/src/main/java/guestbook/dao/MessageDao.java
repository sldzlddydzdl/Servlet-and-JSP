package guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import guestbook.db.MysqlConnector;
import guestbook.dto.MessageDto;

public class MessageDao {
	// 디비에 테이타를 CRUD 해주는 메서드를 가진 아이!
	// insert, update, delete .select 등등의 메서드가 있음
	
	// 싱글톤 패턴 // 필드가없기 때문에 하나의 객체로 돌려서 쓴다. 
	private static MessageDao instance = new MessageDao();
	private MessageDao() {}
	public static MessageDao getInstance() {
		return instance;
	}
	
	// 데이타베이스에 메시지를 삽입하는 메소드
	public int insertMessage(MessageDto messageDto) throws ClassNotFoundException, SQLException {
		// 디비와 연결해서 데이타베이스에 삽입하는 쿼리문을 실행
		Connection conn = MysqlConnector.getConn();
		
		String sql = "insert into guestbook(message_id ,guest_name , message) values(?,?,?)";
		
		try(PreparedStatement psmt = conn.prepareStatement(sql)){
			psmt.setInt(1, messageDto.getMessageId());
			psmt.setString(2, messageDto.getGuestName());
			psmt.setString(3, messageDto.getMessage());
			
			// 쿼리문 실행
			return psmt.executeUpdate();
		}
	}
}
