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
    // HTTP 요청을 받아 JSON 형식으로 이벤트를 반환하는 메서드
    public static void loadEvent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 응답 헤더 설정 - JSON 형식 및 UTF-8 인코딩으로
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // HTTP 요청에서 연도(year) 파라미터 가져오기
        String yearString = request.getParameter("year");
        int startYear = Integer.parseInt(yearString);
        int endYear = startYear + 10; // 10년 후까지

        // 데이터베이스 연결 관련 변수 초기화
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<CalendarInfoDTO> events = new ArrayList<CalendarInfoDTO>(); // 캘린더 이벤트를 담을 리스트 생성
        try {
            // 데이터베이스 연결
            conn = DBManager.connect();
            
            // 멤버 이벤트 가져오기 쿼리
            String sql1 = "SELECT m.m_pk, m.m_name, m.m_debut, m.m_birth, m.m_mother_name, i.i_icon " +
                          "FROM haco_member m " +
                          "LEFT JOIN haco_image i ON m.m_pk = i.i_m_pk " +
                          "WHERE m.m_name IS NOT NULL AND (m.m_debut IS NOT NULL OR m.m_birth IS NOT NULL)";
            pstmt = conn.prepareStatement(sql1);
            rs = pstmt.executeQuery(); // 쿼리 실행

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // 결과 집합 처리
            while (rs.next()) {
                String iconPath = "haco_img/icon/" + rs.getString("i_icon");

                // 멤버의 데뷔 이벤트 처리
                if (rs.getDate("m_debut") != null) {
                    CalendarInfoDTO event = new CalendarInfoDTO();
                    event.setM_pk(rs.getString("m_pk"));
                    event.setId(null); // 공휴일 ID는 null로 설정
                    event.setTitle(rs.getString("m_name"));
                    event.setImagePath(iconPath);
                    String startDate = dateFormat.format(rs.getDate("m_debut"));
                    event.setStart(startDate);
                    events.add(event); // 이벤트 리스트에 추가
                }

                // 멤버의 생일 이벤트 처리
                if (rs.getDate("m_birth") != null) {
                    CalendarInfoDTO birthEvent = new CalendarInfoDTO();
                    birthEvent.setM_pk(rs.getString("m_pk"));
                    birthEvent.setId(null); // 공휴일 ID는 null로 설정
                    birthEvent.setTitle(rs.getString("m_name") + "の誕生日");
                    birthEvent.setImagePath(iconPath);
                    String birthDate = dateFormat.format(rs.getDate("m_birth"));
                    birthEvent.setStart(birthDate);
                    events.add(birthEvent); // 이벤트 리스트에 추가
                }
            }
            rs.close(); // 결과 집합 닫기
            pstmt.close(); // 준비된 문 닫기

            // 공휴일 이벤트 가져오기 쿼리
            String sql2 = "SELECT id, title, date FROM jp_holidays";
            pstmt = conn.prepareStatement(sql2);
            rs = pstmt.executeQuery(); // 쿼리 실행

            // 결과 집합 처리
            while (rs.next()) {
                CalendarInfoDTO holidayEvent = new CalendarInfoDTO();
                holidayEvent.setM_pk(null); // 멤버 ID는 null로 설정
                holidayEvent.setId(rs.getString("id"));
                holidayEvent.setTitle(rs.getString("title") + " （祝日）");
                holidayEvent.setImagePath(""); // 공휴일에는 이미지 경로가 없음
                String holidayDate = dateFormat.format(rs.getDate("date"));
                holidayEvent.setStart(holidayDate);
                events.add(holidayEvent); // 이벤트 리스트에 추가
            }

            // Gson을 사용하여 events 리스트를 JSON 문자열로 변환
            Gson gson = new Gson();
            String json = gson.toJson(events);

            // JSON 응답을 클라이언트로 전송
            response.getWriter().print(json);
            System.out.println(json); // 서버 콘솔에 JSON 출력 확인

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().print("[]"); // 에러 발생 시 빈 JSON 배열 응답
        } finally {
            // 자원 해제
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
                    System.out.println("데이터베이스 연결 닫힘 확인."); // 데이터베이스 연결 닫힘 확인 메시지 출력
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
