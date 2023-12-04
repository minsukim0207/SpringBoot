package com.kh.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.JDBCTemplate;
import com.kh.model.vo.DTO;

public class DAO {
	// 사용자 조회를 위한 SQL문
	
	// 1. 사용자 존재 확인을 위한 SQL문
	public static List<DTO> selectAllUsers() throws SQLException {
		// 1-1. 커넥션 연결을 위한 getConnection()
		Connection conn = JDBCTemplate.getConnection();
		
		// 1-2. PreparedStatement = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 1-3. List로 조회된 내용을 담을 수 있는 배열 생성
		List<DTO> userList = new ArrayList<>();
		String query = "SELECT * FROM TEST_USER";
		pstmt = conn.prepareStatement(query);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			DTO user = new DTO();
			user.setUser_number(rs.getInt("USER_NUMBER"));
			user.setUser_id(rs.getString("USER_ID"));
			user.setUser_name(rs.getString("USER_NAME"));
			user.setUser_age(rs.getInt("USER_AGE"));
			userList.add(user);
		}
		return userList;
	}
}
