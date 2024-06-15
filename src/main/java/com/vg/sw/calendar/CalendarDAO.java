package com.vg.sw.calendar;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
        CalendarInfoDTO event = new CalendarInfoDTO();
        ArrayList<CalendarInfoDTO> events = new ArrayList<CalendarInfoDTO>();
        try {
            conn = DBManager.connect();
            String sql = "SELECT m_pk, m_name, m_debut, m_birth, m_mother_name FROM haco_member WHERE m_name IS NOT NULL AND (m_debut IS NOT NULL OR m_birth IS NOT NULL)";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            while (rs.next()) {
                if (rs.getDate("m_debut") != null) {
                    event = new CalendarInfoDTO();
                    event.setM_pk(rs.getString("m_pk"));
                    event.setTitle(rs.getString("m_name"));
                    String startDate = dateFormat.format(rs.getDate("m_debut"));
                    event.setStart(startDate);
                    event.setRrule(generateRrule(startDate, 10)); // 10년 반복 설정
                    events.add(event);
                }
                if (rs.getDate("m_birth") != null) {
                    CalendarInfoDTO birthEvent = new CalendarInfoDTO();
                    birthEvent.setM_pk(rs.getString("m_pk"));
                    birthEvent.setTitle(rs.getString("m_name") + "の誕生日");
                    String birthDate = dateFormat.format(rs.getDate("m_birth"));
                    birthEvent.setStart(birthDate);
                    birthEvent.setRrule(generateRrule(birthDate, 10)); // 10년 반복 설정
                    events.add(birthEvent);
                }
            }
            Gson gson = new Gson();
            String json = gson.toJson(events);
            response.getWriter().print(json);
            System.out.println(json);

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().print("[]"); // 에러 발생 시 빈 JSON 배열 응답
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
    }

    private static String generateRrule(String startDate, int years) {
        Calendar cal = Calendar.getInstance();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            cal.setTime(dateFormat.parse(startDate));
            cal.add(Calendar.YEAR, years);
            String untilDate = dateFormat.format(cal.getTime());
            return "{\"freq\":\"yearly\", \"until\":\"" + untilDate + "\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
