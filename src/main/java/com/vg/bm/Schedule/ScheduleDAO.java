package com.vg.bm.Schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.vg.ignore.DBManager;

public class ScheduleDAO {
	
	private ArrayList<ScheduleDTO> schedules = null;
//	private ArrayList<TradeCommentsDTO> tradeComments = null;
	private Connection con = null;
	public static final ScheduleDAO SDAO = new ScheduleDAO();

	private ScheduleDAO() {
		try {
			con = DBManager.connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertSchedule(HttpServletRequest request) {
		
		PreparedStatement pstmt = null;

		try {
			// 인서트 할때 DB에 줄바꿈 -> br 로 대체하는 코드
			String text = request.getParameter("text");
			text = text.replaceAll("\r\n", "<br>");
			
			String sql = "insert into haco_schedule values (null, ?, ?, ?, ?, ?)";

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(5, text);

			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록 성공!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

}
