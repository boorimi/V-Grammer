package com.vg.bm.Schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.vg.ignore.DBManager;
import com.vg.jw.AccountDAO;
import com.vg.jw.AccountDTO;

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
	
	public void getAllSchedule(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}
	
	public void insertSchedule(HttpServletRequest request) {
		PreparedStatement pstmt = null;
		String sql = "insert into haco_schedule values (null, ?, ?, ?, ?, ?)";

		HttpSession twitterLoginSession = request.getSession();
		AccountDTO accountInfo = (AccountDTO) twitterLoginSession.getAttribute("accountInfo");
		long id = accountInfo.getU_twitter_id();

		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("s_member"));
			pstmt.setLong(2, id);
			pstmt.setString(3, request.getParameter("s_date"));
			pstmt.setString(4, request.getParameter("s_time"));
			pstmt.setString(5, request.getParameter("s_title"));

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
