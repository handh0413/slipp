package net.slipp.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class UserDAO {
	
	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/slipp_dev?useUnicode=true&characterEncoding=UTF-8";
		String user = "slipp_user";
		String password = "slipp_user";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void addUser(User user) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO USERS(userId, password, name, email) VALUES (?, ?, ?, ?)");
		pstmt.setString(1, user.getUserId());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getEmail());
		pstmt.executeUpdate();
	}

	public User findById(String userId) throws SQLException  {
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userId);
		ResultSet rs = pstmt.executeQuery();
		User user = null;
		
		if (rs.next()) {
			user = new User(
					rs.getString("userId"), 
					rs.getString("password"), 
					rs.getString("name"),
					rs.getString("email"));
		}
		
		return user;
	}
}
