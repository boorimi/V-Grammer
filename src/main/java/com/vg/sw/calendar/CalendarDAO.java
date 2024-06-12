package com.vg.sw.calendar;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.vg.ignore.DBManager;

public class CalendarDAO {
    public static void loadEvent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        JSONArray events = new JSONArray();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.connect();
            String sql = "SELECT m_pk, m_name, m_debut FROM haco_member WHERE EXTRACT(MONTH FROM m_debut) IS NOT NULL";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
            		System.out.println(rs.getInt(1));
            		System.out.println(rs.getString(2));
            		System.out.println(rs.getDate(3));
            	
                JSONObject event = new JSONObject();
                event.put("id", rs.getInt("m_pk"));
                event.put("name", rs.getString("m_name"));
                event.put("debut", rs.getString("m_debut"));
                events.add(event);
            }

            System.out.println(events.toString());
            out.write(events.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }

    }
}
