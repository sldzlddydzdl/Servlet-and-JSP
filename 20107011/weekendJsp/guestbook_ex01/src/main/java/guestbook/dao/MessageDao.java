package guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
		
		String sql = "insert into guestbook(message_id ,guest_name , message) values( ?,?,?)";
		
		try(PreparedStatement psmt = conn.prepareStatement(sql)){
			psmt.setInt(1, messageDto.getMessageId());
			psmt.setString(2, messageDto.getGuestName());
			psmt.setString(3, messageDto.getMessage());
			
			// 쿼리문 실행
			return psmt.executeUpdate();
		}
	}
	
	// 전체 메시지 조회
	public List<MessageDto> selectMessageList() throws ClassNotFoundException, SQLException{
		// 커넥터 객체를 가져오고
		Connection conn = MysqlConnector.getConn();
		// 커넥터 statement 객체를 만들고
		List<MessageDto> list = new ArrayList<>();
		
		String sql = "select message_id, guest_name , message , write_date, update_date from guestbook";

		// statement 객체를 이용하여 쿼리를 실행하고
		try(Statement stmt = conn.createStatement();ResultSet rs = stmt.executeQuery(sql)){
			// resultSet에 담긴 결과값을 list 로 옮겨담음 
			while(rs.next()) {
				MessageDto messageDto = new MessageDto();
				// rs에 있는 message_id 컬럼의 값을 dto의 messageId로 넣어줌
				messageDto.setMessageId(rs.getInt("message_id"));
				// rs에 있는 guest_name컬럼의 값을 dto의 guestName 에 넣어줌 
				messageDto.setGuestName(rs.getString("guest_name"));
				// rs에 있는 message 컬럼의 값을 dto 의 message 에 넣어줌
				messageDto.setMessage(rs.getString("message"));
				//rs 에 있는 write_date 컬럼의값을 dto 의 writeDate 에 넣어줌
				// 이때 데이터베이스의 write_Date 는 timestamp 자료형이고
				// dto 의 writeDate 는 LocalDateTime 이기 때문에 형변환을 해줘야함.
				// rs.getTimestamp("write_date") 이게 notnull 이어야한다.
				LocalDateTime writeDate = rs.getTimestamp("write_date").toLocalDateTime();
				messageDto.setWriteDate(writeDate);
				
				// rs에 있는 update_date 컬럼의 값을 dto 의 updateDate 에 넣어줌
				messageDto.setUpdateDate(rs.getTimestamp("update_date").toLocalDateTime());
				
				// 리스트에 messageDto 객체를 담아준다
				list.add(messageDto);
				
			}
		}
		
		
		return list;
	}
	
	// id 로 메시지 데이터 조회
	public MessageDto selectMessageById(int messageId) throws ClassNotFoundException, SQLException {
		
		Connection conn = MysqlConnector.getConn();
		
		String sql = "select message_id , guest_name , message , write_date , update_date "
				+ "from guestbook where message_id = ?";
		
		try(PreparedStatement psmt = conn.prepareStatement(sql)){
			
			psmt.setInt(1, messageId);
			try(ResultSet rs = psmt.executeQuery()){
				if(rs.next()) {
					return new MessageDto(
							rs.getInt("message_id"), 
							rs.getString("guest_name"), 
							rs.getString("message"), 
							rs.getTimestamp("write_date").toLocalDateTime(), 
							rs.getTimestamp("update_date").toLocalDateTime());
							
				}else {
					return null;
				}
			}
		}
	}
	
	public int updateMessage(MessageDto messageDto) throws ClassNotFoundException, SQLException {
		Connection conn = MysqlConnector.getConn();
		String sql = "update guestbook set guest_name = ? , message=? where message_id = ?";
		
		try(PreparedStatement psmt = conn.prepareStatement(sql)){
			psmt.setString(1, messageDto.getGuestName());
			psmt.setString(2, messageDto.getMessage());
			psmt.setInt(3, messageDto.getMessageId());
			
			return psmt.executeUpdate();
		}
	}
	
}
