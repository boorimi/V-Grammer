package com.vg.sw.calendar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.plaf.basic.BasicCheckBoxMenuItemUI;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

// db관련 작업할때 연결코드를 쓴 이후 작업 해옴.
// 다쓰면 그냥 닫음

// 그걸 aop하자
public class DBManager {

	private static BasicDataSource dataSource;
	static {
		dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:mariadb://183.111.242.22:3306/ds6951");
		dataSource.setUsername("ds6951");
		dataSource.setPassword("soldesk802!!");
		dataSource.setMinIdle(10);
		dataSource.setMaxIdle(20);
		dataSource.setMaxOpenPreparedStatements(100);

	}

	public static Connection connect() throws SQLException {
		return dataSource.getConnection();
	}
	// 연결
//	public static Connection connect() throws SQLException {
//		// 연결 코드
//
//		String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		System.out.println("연결 성공!");
//		return DriverManager.getConnection(url, "c##kds", "kds");
//
//	}

	// 닫기

	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
