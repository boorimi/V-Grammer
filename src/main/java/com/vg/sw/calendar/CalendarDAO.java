package com.vg.sw.calendar;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.vg.ignore.DBManager;

public class CalendarDAO {
    public static void loadEvent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

		String monthString = request.getParameter("month");
		String yearString = request.getParameter("year");
		String dateString = yearString + "-" + monthString;
		
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CalenderInfoDTO event = new CalenderInfoDTO();
        ArrayList<CalenderInfoDTO> events = new ArrayList<CalenderInfoDTO>();
        try {
            conn = DBManager.connect();
            String sql = "SELECT m_pk, m_name, m_debut FROM haco_member WHERE m_name IS NOT NULL AND m_debut IS NOT NULL AND m_debut LIKE '"+dateString+"%'";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
            	event =  new CalenderInfoDTO();
                event.setM_pk(rs.getString("m_pk"));
                event.setM_name(rs.getString("m_name"));
                event.setM_debut(rs.getDate("m_debut"));
                events.add(event);
            }
            System.out.println(events);
            Gson gson = new Gson();
            String json =gson.toJson(events);
            System.out.println("here VVVV");
            System.out.println(json);
            response.getWriter().print(json);

        } catch (SQLException e) {
            e.printStackTrace();
            // 에러 발생 시 빈 JSON 배열을 응답으로 보냄
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
    }
}
