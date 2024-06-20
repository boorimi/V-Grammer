package com.vg.sw.dday;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.vg.ignore.DBManager;

public class DdayDAO {
    private static String sqlNameDebutAndBirth = "SELECT m_pk, m_name, m_debut, m_birth FROM haco_member";

    protected static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DBManager.connect();
            System.out.println("데이터베이스 연결 성공."); // 데이터베이스 연결 확인용 출력
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static List<DdayDTO> selectAllDdays() {
        List<DdayDTO> ddayList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlNameDebutAndBirth);

            rs = preparedStatement.executeQuery();
            System.out.println("SQL 쿼리 실행 완료."); // 쿼리 실행 확인용 출력

            LocalDate today = LocalDate.now();

            while (rs.next()) {
                int id = rs.getInt("m_pk");
                String name = rs.getString("m_name");
                String debutDateString = rs.getString("m_debut");
                String birthDateString = rs.getString("m_birth");

                // Process debut date
                if (debutDateString != null && !debutDateString.equals("0000-00-00")) {
                    try {
                        LocalDate debutDate = LocalDate.parse(debutDateString);
                        long daysUntilDebutDday = calculateDaysUntilNextOccurrence(today, debutDate);
                        String debutDateFormatted = debutDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        ddayList.add(new DdayDTO(id, name, "데뷔", debutDateFormatted, debutDate, daysUntilDebutDday)); // "데뷔" 이벤트로 DdayDTO 객체 생성
                    } catch (DateTimeParseException e) {
                        System.out.println("멤버 ID " + id + "의 데뷔 날짜 파싱 실패."); // 날짜 파싱 실패 확인용 출력
                    }
                }

                // Process birth date
                if (birthDateString != null && !birthDateString.equals("0000-00-00")) {
                    try {
                        LocalDate birthDate = LocalDate.parse(birthDateString);
                        long daysUntilBirthDday = calculateDaysUntilNextOccurrence(today, birthDate); // D-day 계산 로직 적용
                        String birthDateFormatted = birthDate.format(DateTimeFormatter.ofPattern("MM-dd"));
                        ddayList.add(new DdayDTO(id, name, "생일", birthDateFormatted, birthDate, daysUntilBirthDday)); // "생일" 이벤트로 DdayDTO 객체 생성
                    } catch (DateTimeParseException e) {
                        System.out.println("멤버 ID " + id + "의 생일 날짜 파싱 실패."); // 날짜 파싱 실패 확인용 출력
                    }
                }
            }
            
            System.out.println("selectAllDdays 메서드 내부: 데이터베이스 연결 및 처리 완료.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 자원 닫기
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("데이터베이스 연결 닫힘 확인."); // 데이터베이스 연결 닫힘 확인용 출력
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Sort ddayList by event date in ascending order with adjustment for past dates
        Collections.sort(ddayList, (d1, d2) -> Long.compare(d1.getDaysUntilDday(), d2.getDaysUntilDday()));

        return ddayList;
    }



    /**
     * 오늘 날짜를 기준으로 특정 날짜까지 남은 일수를 계산하는 메소드.
     *
     * @param today      현재 날짜
     * @param targetDate 목표 날짜
     * @return 남은 일수 (음수)
     */
    private static long calculateDaysUntilNextOccurrence(LocalDate today, LocalDate targetDate) {
        long daysBetween = ChronoUnit.DAYS.between(today, targetDate);

        // If the days between is greater than 365, adjust it
        if (daysBetween > 365) {
            daysBetween -= 365;
        }

        // If the days between is negative, add 365 to it
        if (daysBetween < 0) {
            daysBetween += 365;
        }

        return daysBetween; // 결과 값을 양수로 반환하여 처리
    }

}
