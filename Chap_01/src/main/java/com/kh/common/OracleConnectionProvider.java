package com.kh.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnectionProvider {

	private static final String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUser = "KHCAFE";
	private static final String jdbcPassword = "KHCAFE";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
	}
}
