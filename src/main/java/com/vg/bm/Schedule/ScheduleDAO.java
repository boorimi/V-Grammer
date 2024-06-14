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

//	private ArrayList<ScheduleDTO> ScheduleS = null;
	private ArrayList<ScheduleDTO> monSchedule = null;
	private ArrayList<ScheduleDTO> tueSchedule = null;
	private ArrayList<ScheduleDTO> wenSchedule = null;
	private ArrayList<ScheduleDTO> thrSchedule = null;
	private ArrayList<ScheduleDTO> friSchedule = null;
	private ArrayList<ScheduleDTO> satSchedule = null;
	private ArrayList<ScheduleDTO> sunSchedule = null;
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
//		
//		// 스케줄 가져오기
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = "select sc.*,m_name from haco_schedule sc, haco_member where s_m_pk = m_pk";
//		
//		try {
//			con = DBManager.connect();
//			pstmt = con.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			
//			schedules = new ArrayList<ScheduleDTO>();
//
//			SimpleDateFormat formatDate;
//			
//			while (rs.next()) {
//				formatDate = new SimpleDateFormat("M月d日");
//				String date = formatDate.format(rs.getDate(4));
//
//				formatDate = new SimpleDateFormat("HH:mm");
//				String time = formatDate.format(rs.getTime(5));
//
//				ScheduleDTO s = new ScheduleDTO(rs.getString(1), rs.getString(2),
//						rs.getString(3), date, time, rs.getString(6), rs.getString(7));
//				schedules.add(s);
//			}
//			request.setAttribute("schedule", schedules);
//			
////			System.out.println("=====스케줄 전체 시소=====");
////			System.out.println(schedules);
////			System.out.println("=====================");
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DBManager.close(con, pstmt, rs);
//		}
	}
	
	public void getMonSchedule(HttpServletRequest request) {
		getThisWeek(request);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sc.*, m_name from haco_schedule sc "
				+ "join haco_member hm on sc.s_m_pk = hm.m_pk "
				+ "where sc.s_date = " +"'"+ thisWeek2.get(0) +"'";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			monSchedule = new ArrayList<ScheduleDTO>();

			SimpleDateFormat formatDate;
			
			while (rs.next()) {
				formatDate = new SimpleDateFormat("M月d日");
				String date = formatDate.format(rs.getDate(4));

				formatDate = new SimpleDateFormat("HH:mm");
				String time = formatDate.format(rs.getTime(5));

				ScheduleDTO s = new ScheduleDTO(rs.getString(1), rs.getString(2),
						rs.getString(3), date, time, rs.getString(6), rs.getString(7));
				monSchedule.add(s);
			}
			request.setAttribute("monSchedule", monSchedule);
			
			System.out.println("=====월요일 스케줄 시소=====");
			System.out.println(monSchedule);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}
	
	public void getTueSchedule(HttpServletRequest request) {
		getThisWeek(request);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sc.*, m_name from haco_schedule sc "
				+ "join haco_member hm on sc.s_m_pk = hm.m_pk "
				+ "where sc.s_date = " +"'"+ thisWeek2.get(1) +"'";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			tueSchedule = new ArrayList<ScheduleDTO>();
			
			SimpleDateFormat formatDate;
			
			while (rs.next()) {
				formatDate = new SimpleDateFormat("M月d日");
				String date = formatDate.format(rs.getDate(4));
				
				formatDate = new SimpleDateFormat("HH:mm");
				String time = formatDate.format(rs.getTime(5));
				
				ScheduleDTO s = new ScheduleDTO(rs.getString(1), rs.getString(2),
						rs.getString(3), date, time, rs.getString(6), rs.getString(7));
				tueSchedule.add(s);
			}
			request.setAttribute("tueSchedule", tueSchedule);
			
			System.out.println("=====월요일 스케줄 시소=====");
			System.out.println(tueSchedule);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}
	
	public void getWenSchedule(HttpServletRequest request) {
		getThisWeek(request);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sc.*, m_name from haco_schedule sc "
				+ "join haco_member hm on sc.s_m_pk = hm.m_pk "
				+ "where sc.s_date = " +"'"+ thisWeek2.get(2) +"'";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			wenSchedule = new ArrayList<ScheduleDTO>();
			
			SimpleDateFormat formatDate;
			
			while (rs.next()) {
				formatDate = new SimpleDateFormat("M月d日");
				String date = formatDate.format(rs.getDate(4));
				
				formatDate = new SimpleDateFormat("HH:mm");
				String time = formatDate.format(rs.getTime(5));
				
				ScheduleDTO s = new ScheduleDTO(rs.getString(1), rs.getString(2),
						rs.getString(3), date, time, rs.getString(6), rs.getString(7));
				wenSchedule.add(s);
			}
			request.setAttribute("wenSchedule", wenSchedule);
			
			System.out.println("=====wen 스케줄 시소=====");
			System.out.println(wenSchedule);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}
	
	public void getThrSchedule(HttpServletRequest request) {
		getThisWeek(request);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sc.*, m_name from haco_schedule sc "
				+ "join haco_member hm on sc.s_m_pk = hm.m_pk "
				+ "where sc.s_date = " +"'"+ thisWeek2.get(3) +"'";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			thrSchedule = new ArrayList<ScheduleDTO>();
			
			SimpleDateFormat formatDate;
			
			while (rs.next()) {
				formatDate = new SimpleDateFormat("M月d日");
				String date = formatDate.format(rs.getDate(4));
				
				formatDate = new SimpleDateFormat("HH:mm");
				String time = formatDate.format(rs.getTime(5));
				
				ScheduleDTO s = new ScheduleDTO(rs.getString(1), rs.getString(2),
						rs.getString(3), date, time, rs.getString(6), rs.getString(7));
				thrSchedule.add(s);
			}
			request.setAttribute("thrSchedule", thrSchedule);
			
			System.out.println("=====thr 스케줄 시소=====");
			System.out.println(thrSchedule);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}
	
	public void getFriSchedule(HttpServletRequest request) {
		getThisWeek(request);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sc.*, m_name from haco_schedule sc "
				+ "join haco_member hm on sc.s_m_pk = hm.m_pk "
				+ "where sc.s_date = " +"'"+ thisWeek2.get(4) +"'";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			friSchedule = new ArrayList<ScheduleDTO>();
			
			SimpleDateFormat formatDate;
			
			while (rs.next()) {
				formatDate = new SimpleDateFormat("M月d日");
				String date = formatDate.format(rs.getDate(4));
				
				formatDate = new SimpleDateFormat("HH:mm");
				String time = formatDate.format(rs.getTime(5));
				
				ScheduleDTO s = new ScheduleDTO(rs.getString(1), rs.getString(2),
						rs.getString(3), date, time, rs.getString(6), rs.getString(7));
				friSchedule.add(s);
			}
			request.setAttribute("friSchedule", friSchedule);
			
			System.out.println("=====fri 스케줄 시소=====");
			System.out.println(friSchedule);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}
	
	public void getSatSchedule(HttpServletRequest request) {
		getThisWeek(request);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sc.*, m_name from haco_schedule sc "
				+ "join haco_member hm on sc.s_m_pk = hm.m_pk "
				+ "where sc.s_date = " +"'"+ thisWeek2.get(5) +"'";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			satSchedule = new ArrayList<ScheduleDTO>();
			
			SimpleDateFormat formatDate;
			
			while (rs.next()) {
				formatDate = new SimpleDateFormat("M月d日");
				String date = formatDate.format(rs.getDate(4));
				
				formatDate = new SimpleDateFormat("HH:mm");
				String time = formatDate.format(rs.getTime(5));
				
				ScheduleDTO s = new ScheduleDTO(rs.getString(1), rs.getString(2),
						rs.getString(3), date, time, rs.getString(6), rs.getString(7));
				satSchedule.add(s);
			}
			request.setAttribute("msatSchedule", satSchedule);
			
			System.out.println("=====sat 스케줄 시소=====");
			System.out.println(satSchedule);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}
	
	public void getSunSchedule(HttpServletRequest request) {
		getThisWeek(request);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sc.*, m_name from haco_schedule sc "
				+ "join haco_member hm on sc.s_m_pk = hm.m_pk "
				+ "where sc.s_date = " +"'"+ thisWeek2.get(6) +"'";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			sunSchedule = new ArrayList<ScheduleDTO>();
			
			SimpleDateFormat formatDate;
			
			while (rs.next()) {
				formatDate = new SimpleDateFormat("M月d日");
				String date = formatDate.format(rs.getDate(4));
				
				formatDate = new SimpleDateFormat("HH:mm");
				String time = formatDate.format(rs.getTime(5));
				
				ScheduleDTO s = new ScheduleDTO(rs.getString(1), rs.getString(2),
						rs.getString(3), date, time, rs.getString(6), rs.getString(7));
				sunSchedule.add(s);
			}
			request.setAttribute("sunSchedule", sunSchedule);
			
			System.out.println("=====sun 스케줄 시소=====");
			System.out.println(sunSchedule);
			
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
