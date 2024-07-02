package com.vg.sw.calendar;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.vg.ignore.DBManager;

public class CalendarDAO {
    public static void loadEvent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String yearString = request.getParameter("year");
        int startYear = Integer.parseInt(yearString);
        int endYear = startYear + 10; // 10년 후까지

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<CalendarInfoDTO> events = new ArrayList<CalendarInfoDTO>();
        try {
            conn = DBManager.connect();
            
            // 멤버 이벤트 가져오기
            String sql1 = "SELECT m.m_pk, m.m_name, m.m_debut, m.m_birth, m.m_mother_name, i.i_icon " +
                          "FROM haco_member m " +
                          "LEFT JOIN haco_image i ON m.m_pk = i.i_m_pk " +
                          "WHERE m.m_name IS NOT NULL AND (m.m_debut IS NOT NULL OR m.m_birth IS NOT NULL)";
            pstmt = conn.prepareStatement(sql1);
            rs = pstmt.executeQuery();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            while (rs.next()) {
                String iconPath = "haco_img/icon/" + rs.getString("i_icon");

                if (rs.getDate("m_debut") != null) {
                    CalendarInfoDTO event = new CalendarInfoDTO();
                    event.setM_pk(rs.getString("m_pk"));
                    event.setId(null); // 공휴일 id는 null
                    event.setTitle(rs.getString("m_name"));
                    event.setImagePath(iconPath);
                    String startDate = dateFormat.format(rs.getDate("m_debut"));
                    event.setStart(startDate);
                    events.add(event);
                }
                if (rs.getDate("m_birth") != null) {
                    CalendarInfoDTO birthEvent = new CalendarInfoDTO();
                    birthEvent.setM_pk(rs.getString("m_pk"));
                    birthEvent.setId(null); // 공휴일 id는 null
                    birthEvent.setTitle(rs.getString("m_name") + "の誕生日");
                    birthEvent.setImagePath(iconPath);
                    String birthDate = dateFormat.format(rs.getDate("m_birth"));
                    birthEvent.setStart(birthDate);
                    events.add(birthEvent);
                }
            }
            rs.close();
            pstmt.close();

            // 공휴일 이벤트 가져오기
            String sql2 = "SELECT id, title, date FROM jp_holidays";
            pstmt = conn.prepareStatement(sql2);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CalendarInfoDTO holidayEvent = new CalendarInfoDTO();
                holidayEvent.setM_pk(null); // 멤버 m_pk는 null
                holidayEvent.setId(rs.getString("id"));
                String holidayTitle = rs.getString("title");
                holidayEvent.setTitle(holidayTitle + "（祝日）");
                holidayEvent.setImagePath(""); // 공휴일에는 이미지 경로가 없으므로 빈 문자열로 설정

                Date holidayDate = rs.getDate("date");
                // 특정 공휴일의 날짜를 특정 월요일로 조정
                if (holidayTitle.equals("成人の日")) {
                    holidayDate = getSpecificMonday(startYear, Calendar.JANUARY, 2);
                } else if (holidayTitle.equals("建国記念の日")) {
                    holidayDate = getFixedDate(startYear, Calendar.FEBRUARY, 11);
                } else if (holidayTitle.equals("みどりの日")) {
                    holidayDate = getFixedDate(startYear, Calendar.MAY, 4);
                } else if (holidayTitle.equals("こどもの日")) {
                    holidayDate = getFixedDate(startYear, Calendar.MAY, 5);
                } else if (holidayTitle.equals("海の日")) {
                    holidayDate = getSpecificMonday(startYear, Calendar.JULY, 3);
                } else if (holidayTitle.equals("山の日")) {
                    holidayDate = getFixedDate(startYear, Calendar.AUGUST, 11);
                } else if (holidayTitle.equals("敬老の日")) {
                    holidayDate = getSpecificMonday(startYear, Calendar.SEPTEMBER, 3);
                } else if (holidayTitle.equals("体育の日")) {
                    holidayDate = getSpecificMonday(startYear, Calendar.OCTOBER, 3);
                } else if (!holidayTitle.equals("憲法記念日")) { //憲法記念日 이외의 주말 공휴일 이동
                    holidayDate = adjustWeekendToWeekday(holidayDate);
                }

                String adjustedDate = dateFormat.format(holidayDate);
                holidayEvent.setStart(adjustedDate);
                
                events.add(holidayEvent);
            }

            Gson gson = new Gson();
            String json = gson.toJson(events);
            response.getWriter().print(json);
            System.out.println(json);

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().print("[]"); // 에러 발생 시 빈 JSON 배열 응답
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("데이터베이스 연결 닫힘 확인.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 특정 월의 특정 번째 월요일을 구하는 메서드
    private static Date getSpecificMonday(int year, int month, int nth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);

        int count = 0;
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY || ++count != nth) {
            calendar.add(Calendar.DATE, 1);
        }
        return calendar.getTime();
    }

    // 특정 월의 특정 일을 구하는 메서드
    private static Date getFixedDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    // 주말을 평일로 조정하는 메서드
    private static Date adjustWeekendToWeekday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        
        if (dayOfWeek == Calendar.SATURDAY) {
            calendar.add(Calendar.DATE, 2); // 토요일인 경우 월요일로 이동
        } else if (dayOfWeek == Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, 1); // 일요일인 경우 월요일로 이동
        }
        
        return calendar.getTime();
    }
}
