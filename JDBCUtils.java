package wordFriend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtils {
	private static String url = "jdbc:mysql://localhost:3306/word?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
	private static String user = "root";
	private static String password = "123456";
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//1.获取连接对象
	public static Connection getConnection() throws SQLException{	
		return DriverManager.getConnection(url, user, password);
	}
	//2.释放资源	
	public static void close(Connection conn,PreparedStatement pst,ResultSet rs){
		try {
			if(rs != null)
				rs.close();
			if (pst != null)
				pst.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
