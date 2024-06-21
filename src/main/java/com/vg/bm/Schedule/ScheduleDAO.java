package com.vg.bm.Schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.vg.ignore.DBManager;
import com.vg.jw.AccountDTO;

public class ScheduleDAO {

	private ArrayList<ScheduleDTO> monSchedule = null;
	private ArrayList<ScheduleDTO> tueSchedule = null;
	private ArrayList<ScheduleDTO> wedSchedule = null;
	private ArrayList<ScheduleDTO> thuSchedule = null;
	private ArrayList<ScheduleDTO> friSchedule = null;
	private ArrayList<ScheduleDTO> satSchedule = null;
	private ArrayList<ScheduleDTO> sunSchedule = null;

	private ArrayList<String> thisWeek;
	private ArrayList<String> thisWeek2;
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
		DateTimeFormatter formatDate;

		// 로컬날짜 = 시작날짜(월요일)로 초기화해서 세팅
		LocalDate date = startWeek;

		thisWeek = new ArrayList<String>(); // M月d日
		thisWeek2 = new ArrayList<String>(); // YYYY-MM-dd

		// thisWeek가 endWeek가 아니면 루프를 계속 돌리기 위한 while문
		while (!date.isAfter(endWeek)) {
			formatDate = DateTimeFormatter.ofPattern("M月d日");
			thisWeek.add(date.format(formatDate));
			formatDate = DateTimeFormatter.ofPattern("YYYY-MM-dd");
			thisWeek2.add(date.toString());
			// 오늘 날짜(월요일)에 1일을 계속 추가
			date = date.plusDays(1);
			// endWeek가 되면 while문 종료
		}

		request.setAttribute("thisWeek", thisWeek);
	}

	@SuppressWarnings("unchecked")
	public void getAllSchedule(HttpServletRequest request) {

		// 스케줄 가져오기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sc.*, m.m_name, hi.i_icon "
				+ "from haco_schedule sc "
				+ "join haco_member m ON sc.s_m_pk = m.m_pk "
				+ "join haco_image hi ON sc.s_m_pk = hi.i_m_pk";

		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			SimpleDateFormat formatDate;

			// 요일
			monSchedule = new ArrayList<ScheduleDTO>();
			tueSchedule = new ArrayList<ScheduleDTO>();
			wedSchedule = new ArrayList<ScheduleDTO>();
			thuSchedule = new ArrayList<ScheduleDTO>();
			friSchedule = new ArrayList<ScheduleDTO>();
			satSchedule = new ArrayList<ScheduleDTO>();
			sunSchedule = new ArrayList<ScheduleDTO>();
			
			List<ScheduleDTO> schedules[] = new List[] { monSchedule, tueSchedule, wedSchedule, thuSchedule,
					friSchedule, satSchedule, sunSchedule };

			while (rs.next()) {

				formatDate = new SimpleDateFormat("HH:mm");
				String time = formatDate.format(rs.getTime(5));

				// 방송 시간 구분
				// String 시간을 int로 바꿔서 비교연산함
				formatDate = new SimpleDateFormat("HHmm");
				String strTime = formatDate.format(rs.getTime(5));
				int intTime = Integer.parseInt(strTime);

//				System.out.println(intTime);

				// 방송 요일 구분
				for (int i = 0; i < 7; i++) {
					if (rs.getString(4).equals(thisWeek2.get(i))) {
						ScheduleDTO schedule = new ScheduleDTO(rs.getString(1), rs.getString(2), rs.getString(3),
								rs.getString(4), time, rs.getString(6), rs.getString(7), intTime, rs.getString(8));
						
						schedules[i].add(schedule);
					}
				}
			}
//			System.out.println("이번주 월요일 날짜 : " + thisWeek2.get(0));
//			System.out.println("월스케줄 : " + monSchedule);
			Gson gson = new Gson();
			String json = gson.toJson(schedules);
//			System.out.println(json);
			
			request.setAttribute("weekSchedules", schedules);
			request.setAttribute("weekJSON", json);

		} catch (

		Exception e) {
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

	public void deleteSchedule(HttpServletRequest request) {
		PreparedStatement pstmt = null;
		String sql = "delete from haco_schedule where s_pk = ?";

		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			System.out.println(request.getParameter("sPk"));
			pstmt.setString(1, request.getParameter("sPk"));
			
			if (pstmt.executeUpdate()==1) {
				System.out.println("삭제 성공!");
			}
	
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public void updateSchedule(HttpServletRequest request) {
		PreparedStatement pstmt = null;
		String sql = "update haco_schedule set s_u_t_id=?, s_date=?, "
				+ "s_time=?, s_title=? where s_pk=?";
		
		HttpSession twitterLoginSession = request.getSession();
		AccountDTO accountInfo = (AccountDTO) twitterLoginSession.getAttribute("accountInfo");
		long id = accountInfo.getU_twitter_id();
		System.out.println(id);
		System.out.println(request.getParameter("s_date"));
		System.out.println(request.getParameter("s_time"));
		System.out.println(request.getParameter("s_title"));
		System.out.println(request.getParameter("sPk"));

		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setLong(1, id);
			pstmt.setString(2, request.getParameter("s_date"));
			pstmt.setString(3, request.getParameter("s_time"));
			pstmt.setString(4, request.getParameter("s_title"));
			pstmt.setString(5, request.getParameter("sPk"));
			
			if (pstmt.executeUpdate()==1) {
				System.out.println("수정 성공!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

}