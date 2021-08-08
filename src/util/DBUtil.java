package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
//	1获取连接
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/jsd?serverTimezone=UTC","root","123456");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
//	释放连接
	public static void close(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
