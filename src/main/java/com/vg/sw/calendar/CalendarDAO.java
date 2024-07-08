package com.vg.sw.calendar;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.vg.ignore.DBManager;

public class CalendarDAO {
    
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void loadEvent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String yearString = request.getParameter("year");
        int startYear = Integer.parseInt(yearString);
        int endYear = startYear + 10; // 10년 후까지

        List<CalendarInfoDTO> events = new ArrayList<>();

        String sql = "SELECT m.m_pk, m.m_name, m.m_debut, m.m_birth, m.m_mother_name, i.i_icon " +
                     "FROM haco_member m " +
                     "LEFT JOIN haco_image i ON m.m_pk = i.i_m_pk " +
                     "WHERE m.m_name IS NOT NULL AND (m.m_debut IS NOT NULL OR m.m_birth IS NOT NULL)";

        try (Connection conn = DBManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String iconPath = "haco_img/icon/" + rs.getString("i_icon");

                if (rs.getDate("m_debut") != null) {
                    events.add(createEvent(rs, rs.getDate("m_debut"), iconPath));
                }
                if (rs.getDate("m_birth") != null) {
                    events.add(createBirthEvent(rs, rs.getDate("m_birth"), iconPath));
                }
            }

            String json = new Gson().toJson(events);
            response.getWriter().print(json);
            System.out.println(json);

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().print("[]"); // 에러 발생 시 빈 JSON 배열 응답하기.
        }
    }

    private static CalendarInfoDTO createEvent(ResultSet rs, Date date, String iconPath) throws SQLException {
        CalendarInfoDTO event = new CalendarInfoDTO();
        event.setM_pk(rs.getString("m_pk"));
        event.setId(null); // 공휴일 id는 null
        event.setTitle(rs.getString("m_name"));
        event.setImagePath(iconPath);
        event.setStart(dateFormat.format(date));
        return event;
    }

    private static CalendarInfoDTO createBirthEvent(ResultSet rs, Date date, String iconPath) throws SQLException {
        CalendarInfoDTO event = new CalendarInfoDTO();
        event.setM_pk(rs.getString("m_pk"));
        event.setId(null); // 공휴일 id는 null
        event.setTitle(rs.getString("m_name") + "の誕生日");
        event.setImagePath(iconPath);
        event.setStart(dateFormat.format(date));
        return event;
    }
}
