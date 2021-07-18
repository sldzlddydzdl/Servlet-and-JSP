package guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import guestbook.dto.MessageDto;

public class GuestbookDao {
	private static GuestbookDao instance = new GuestbookDao();
	
	private GuestbookDao() {}
	
	public static GuestbookDao getInstance() {
		return instance;
	}
	
	// 전체 개수를 구하는 메서드
	public int selectTotalCount(Connection conn) throws SQLException {
		String sql = "select count(*) from guestbook";
		try(Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql)){
			
			// 결과가 담긴 rs
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
		}
	}
	
	// 메시지 리스트를 가져오는 메서드
	public List<MessageDto> selectList(Connection conn , int firstRow , int messageCountPerPage) throws SQLException{
		String sql = "select message_id, guest_name, message, write_date, update_date"
				+ " from guestbook order by message_id desc limit ?,?";
		
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, firstRow);
			pst.setInt(2, messageCountPerPage);
			
			try(ResultSet rs = pst.executeQuery()){
				List<MessageDto> messageList = new ArrayList<MessageDto>();
				while(rs.next()) {
					// 각각의 메시지를 리스트에 담아 반환하자
					// db(rs) 에 있는 값을 MessageDto 객체에 담고 MessageDto 객체를 리스트에 추가함
					messageList.add(new MessageDto(
							 rs.getInt(1),
							 rs.getString(2),
							 rs.getString(3),
							 rs.getTimestamp(4).toLocalDateTime(),
							 rs.getTimestamp(5).toLocalDateTime()));
				}
				
				return messageList;
			}
			
			
			
			
		}
	}

	// 메시지를 디비에 넣어주는 메서드
	public int insert(Connection conn , MessageDto messageDto) throws SQLException {
		
		String sql = "insert into guestbook(message_id, guest_name , message, write_date , update_date)"
				+ " values(?,?,?,?,?)";
		
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, messageDto.getMessageId());
			pst.setString(2, messageDto.getGuestName());
			pst.setString(3, messageDto.getMessage());
			pst.setTimestamp(4, Timestamp.valueOf(messageDto.getWriteDate()));
			pst.setTimestamp(5, Timestamp.valueOf(messageDto.getUpdateDate()));
			
			return pst.executeUpdate();
		}
		
	}

	public MessageDto selectMessageDtoById(Connection conn, int messageId) throws SQLException {
		
		String sql = "select message_id , guest_name , message, write_date, update_date "
				+ "from guestbook where message_id=?";
		
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setInt(1, messageId);
			try(ResultSet rs = pst.executeQuery()){
				if(rs.next()) {
					return new MessageDto(
							rs.getInt(1),
							rs.getString(2), 
							rs.getString(3), 
							rs.getTimestamp(4).toLocalDateTime(), 
							rs.getTimestamp(5).toLocalDateTime());
				}else {
					return null;
				}
			}
		}
		
	}

	// update 쿼리를 실행하는 메서드
	public void update(Connection conn, MessageDto messageDto) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update guestbook set guest_name=?, message=?, write_date=?, update_date=?"
				+ " where message_id=?";
		
		try(PreparedStatement pst = conn.prepareStatement(sql)){
			pst.setString(1, messageDto.getGuestName());
			pst.setString(2, messageDto.getMessage());
			pst.setTimestamp(3, Timestamp.valueOf(messageDto.getWriteDate()));
			pst.setTimestamp(4, Timestamp.valueOf(messageDto.getUpdateDate()));
			pst.setInt(5, messageDto.getMessageId());
			
			pst.executeUpdate();
		}
	}
}
