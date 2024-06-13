package com.vg.bm.Schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.vg.ignore.DBManager;
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
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from haco_schedule";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			schedules = new ArrayList<ScheduleDTO>();

			// date 형식 변환
			SimpleDateFormat oldDate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatDate = new SimpleDateFormat("M月d日");
			
			// time 형식 변환
			DateTimeFormatter oldTime = DateTimeFormatter.ofPattern("HH:mm:ss");
			DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
			
			while (rs.next()) {
				
				// date 다시 담기
				String date = rs.getString(4);
				Date date2 = null;
				String date3 = "";
				date2 = oldDate.parse(date);
				date3 = formatDate.format(date2);

				// time 다시 담기
				String time = rs.getString(5);
				TemporalAccessor time2 = null;
				String time3 = "";
				time2 = oldTime.parse(time);
				time3 = formatTime.format(time2);
				
				ScheduleDTO s = new ScheduleDTO(rs.getString(1), rs.getString(2),
						rs.getString(3), date3, time3, rs.getString(6));
				schedules.add(s);
			}
			
			request.setAttribute("schedule", schedules);
			
			System.out.println("=====스케줄 전체 시소=====");
			System.out.println(schedules);
			System.out.println("=====================");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
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

			String member[] = request.getParameterValues("s_member");
			String date[] = request.getParameterValues("s_date");
			String time[] = request.getParameterValues("s_time");
			String title[] = request.getParameterValues("s_title");

			for (int i = 0; i < date.length; i++) {
				if (!member.equals("999") && !date[i].isEmpty() && !time[i].isEmpty() && !title[i].isEmpty()) {
					// 포문 한 번 돌고 나면 pstmt 초기화
					pstmt.clearParameters();

					pstmt.setString(1, member[0]);
					pstmt.setLong(2, id);
					pstmt.setString(3, date[i]);
					pstmt.setString(4, time[i]);
					pstmt.setString(5, title[i]);

					if (pstmt.executeUpdate() != 0) {
						System.out.println("등록 성공!");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

}
