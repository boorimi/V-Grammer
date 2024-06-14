package com.vg.bm.Schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
	private ArrayList<String> thisWeek;
	private ArrayList<String> thisWeek2;
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

	public void getThisWeek(HttpServletRequest request) {
		// 로컬 오늘 날짜
		LocalDate today = LocalDate.now();
		LocalDate startWeek = today.with(DayOfWeek.MONDAY);
		LocalDate endWeek = today.with(DayOfWeek.SUNDAY);

		// 날짜출력 형식세팅
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("M月d日");
		
		// 로컬날짜 = 시작날짜(월요일)로 초기화해서 세팅
		LocalDate date = startWeek;
		
		thisWeek = new ArrayList<String>(); // M月d日
		thisWeek2 = new ArrayList<String>(); // YYYY-MM-dd
		
		// thisWeek가 endWeek가 아니면 루프를 계속 돌리기 위한 while문
		while (!date.isAfter(endWeek)) {
			thisWeek.add(date.format(formatDate));
			thisWeek2.add(date.toString());
			// 오늘 날짜(월요일)에 1일을 계속 추가
			date = date.plusDays(1);
			// endWeek가 되면 while문 종료
		}
//		System.out.println(thisWeek);
		
		request.setAttribute("thisWeek", thisWeek);
	}
	
	public void getAllSchedule(HttpServletRequest request) {
		
		getThisWeek(request);
//		request.getAttribute("thisWeek");
		
		System.out.println("스케줄 메서드 안 위크 시소"+thisWeek2.get(0));
		

		
		// 스케줄 가져오기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sc.*,m_name from haco_schedule sc, haco_member where s_m_pk = m_pk";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			schedules = new ArrayList<ScheduleDTO>();

			SimpleDateFormat formatDate;
			
			while (rs.next()) {
				formatDate = new SimpleDateFormat("M月d日");
				String date = formatDate.format(rs.getDate(4));

				formatDate = new SimpleDateFormat("HH:mm");
				String time = formatDate.format(rs.getTime(5));

				ScheduleDTO s = new ScheduleDTO(rs.getString(1), rs.getString(2),
						rs.getString(3), date, time, rs.getString(6), rs.getString(7));
				schedules.add(s);
			}
			request.setAttribute("schedule", schedules);
			
//			System.out.println("=====스케줄 전체 시소=====");
//			System.out.println(schedules);
//			System.out.println("=====================");
			
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
