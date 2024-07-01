package com.vg.sw.calendar;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
            
            // 기존 쿼리: haco_member 테이블에서 이벤트 가져오기
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
                    event.setTitle(rs.getString("m_name"));
                    event.setImagePath(iconPath);
                    String startDate = dateFormat.format(rs.getDate("m_debut"));
                    event.setStart(startDate);
                    events.add(event);
                }
                if (rs.getDate("m_birth") != null) {
                    CalendarInfoDTO birthEvent = new CalendarInfoDTO();
                    birthEvent.setM_pk(rs.getString("m_pk"));
                    birthEvent.setTitle(rs.getString("m_name") + "の誕生日");
                    birthEvent.setImagePath(iconPath);
                    String birthDate = dateFormat.format(rs.getDate("m_birth"));
                    birthEvent.setStart(birthDate);
                    events.add(birthEvent);
                }
            }
            rs.close();
            pstmt.close();

            // 새로운 쿼리: jp_holidays 테이블에서 이벤트 가져오기
            String sql2 = "SELECT id, title, date FROM jp_holidays";
            pstmt = conn.prepareStatement(sql2);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CalendarInfoDTO holidayEvent = new CalendarInfoDTO();
                holidayEvent.setM_pk(rs.getString("id"));
                holidayEvent.setTitle(rs.getString("title"));
                holidayEvent.setImagePath(""); // 공휴일에는 이미지 경로가 없으므로 빈 문자열로 설정
                String holidayDate = dateFormat.format(rs.getDate("date"));
                holidayEvent.setStart(holidayDate);
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
}
