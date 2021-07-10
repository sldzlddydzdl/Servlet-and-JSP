package guestbook.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 디비 커넥트를 해주는 코드
public class MysqlConnector {
	
	// 디비에 연결해서 연결된 커넥션객체를 반환해줌
	public static Connection getConn() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String user = "root";
		String password = "1234";
		String url = "jdbc:mysql://localhost:3306/mega?"
				+ "serverTimezone=Asia/Seoul&characterEncoding=utf-8";
		
		return DriverManager.getConnection(url, user, password);
	}
}
