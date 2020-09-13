package wordFriend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordService {
	public static Vocabulary findByName(String name) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			
			pst = conn.prepareStatement("select * from wordlist where word like ?");
			
			pst.setString(1, name);
			
			rs = pst.executeQuery();
			Vocabulary word = null;
			if(rs.next()){
				word = new Vocabulary(rs.getString(1),rs.getInt(2),rs.getInt(3));
				
				return word;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(conn, pst, rs);
		}
		return null;
	}
    public static boolean addWord(Vocabulary word) {
    	Connection conn = null;
		PreparedStatement pst = null;	
		boolean flag = false;
		try {
			conn = JDBCUtils.getConnection();			
			pst = conn.prepareStatement("insert into wordlist values(?,?,?)");			
			pst.setString(1, word.getWord());
			pst.setInt(2, word.getHistory());			
			pst.setInt(3, word.getT());			
			int row = pst.executeUpdate();
			System.out.println(row);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(conn, pst, null);
			flag= true;
		}
		return flag;
		
	}
    public static  boolean updateWordT(String word , int t) {
    	Connection conn = null;
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			conn = JDBCUtils.getConnection();
			
			pst = conn.prepareStatement("update wordlist set t=? where word like ? ");
			
			pst.setInt(1, t);
			pst.setString(2, word);
			int row = pst.executeUpdate();
			System.out.println(row);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(conn, pst, null);
			flag= true;
		}
		return flag;
	}
    public static  boolean updateWordHistory(Vocabulary word) {
    	Connection conn = null;
		PreparedStatement pst = null;
		boolean flag = false;
		try {
			conn = JDBCUtils.getConnection();
			
			pst = conn.prepareStatement("update wordlist set history=? where word like ? ");
			
			pst.setInt(1, word.getHistory());
			pst.setString(2, word.getWord());
			int row = pst.executeUpdate();
			System.out.println(row);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(conn, pst, null);
			flag= true;
		}
		return flag;
	}
    public static List<Vocabulary> orderByShengshu(int t) {
    	Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			
			pst = conn.prepareStatement("select * from wordlist where t>=?");
			pst.setInt(1, t);
			rs = pst.executeQuery();
			Vocabulary word = null;
			List<Vocabulary> list = new ArrayList<>();
			while(rs.next()){
				word = new Vocabulary(rs.getString(1),rs.getInt(2),rs.getInt(3));
				
				list.add(word);
			}
			if(list.size() > 0){
				
				return list;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(conn, pst, rs);
		}
		return null;
	}
	public List<Vocabulary> findAll() {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			
			pst = conn.prepareStatement("select * from wordlist");
			rs = pst.executeQuery();
			Vocabulary word = null;
			List<Vocabulary> list = new ArrayList<>();
			while(rs.next()){
				word = new Vocabulary(rs.getString(1),rs.getInt(2),rs.getInt(3));
				
				list.add(word);
			}
			if(list.size() > 0){
				
				return list;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(conn, pst, rs);
		}
		return null;
	}
}
