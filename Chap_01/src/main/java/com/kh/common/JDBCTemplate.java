package com.kh.common;	// 일반적인 사용을 위한 패키지 명. util을 사용하기도 함.

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCTemplate {
	// Connection 객체를 생성
	public static Connection getConnection() throws SQLException {
		return OracleConnectionProvider.getConnection();
	}
}
