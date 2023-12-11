package com.kh.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.user.dto.UserDTO;

public class UserDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	String drive = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "userID";
	String password = "userPassword";
	
	public UserDAO() {
		try {
			Class.forName(drive);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public UserDTO selectUser(int userNo) {
		UserDTO dto = new UserDTO();
		sql = "SELECT * FROM TABLE_USER WHERE USER_NO = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setUserNo(rs.getInt(userNo));
				dto.setUserId(rs.getString("USER_ID"));
				dto.setUserName(rs.getString("USER_NAME"));
				dto.setUserAge(rs.getInt("USER_AGE"));
			}
			rs.close();
			pstmt.close();
			conn.close();
			return dto;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}
}
