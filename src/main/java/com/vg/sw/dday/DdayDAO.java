package com.vg.sw.dday;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlNameDebutAndBirth)) {

            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("SQL 쿼리 실행 완료."); // 쿼리 실행 확인용 출력

            while (rs.next()) {
                int id = rs.getInt("m_pk");
                String name = rs.getString("m_name");
                String debutDateString = rs.getString("m_debut");
                String birthDateString = rs.getString("m_birth");
                LocalDate debutDate = null;
                long daysUntilDday = 0;

                try {
                    LocalDate today = LocalDate.now();
                    String debutDateFormatted = null;
                    if (debutDateString != null && !debutDateString.equals("0000-00-00")) {
                        debutDate = LocalDate.parse(debutDateString);
                        MonthDay debutMonthDay = MonthDay.from(debutDate);
                        daysUntilDday = calculateDaysUntilNextOccurrence(today, debutMonthDay);
                        debutDateFormatted = debutDate.format(DateTimeFormatter.ofPattern("MM-dd"));
                    }

                    String birthDateFormatted = null;
                    if (birthDateString != null && !birthDateString.equals("0000-00-00")) {
                        LocalDate birthDate = LocalDate.parse(birthDateString);
                        birthDateFormatted = birthDate.format(DateTimeFormatter.ofPattern("MM-dd"));
                    }

                    DdayDTO dday = new DdayDTO(id, name, debutDateFormatted, birthDateFormatted, -daysUntilDday);
                    ddayList.add(dday);

                } catch (DateTimeParseException e) {
                    System.out.println("멤버 ID " + id + "의 날짜 파싱 실패."); // 날짜 파싱 실패 확인용 출력
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 

        return ddayList;
    }

    /**
     * 오늘 날짜를 기준으로 특정 날짜까지 남은 일수를 계산하는 메소드.
     *
     * @param today 현재 날짜
     * @param targetMonthDay 목표 월일
     * @return 남은 일수
     */
    private static long calculateDaysUntilNextOccurrence(LocalDate today, MonthDay targetMonthDay) {
        LocalDate targetDateThisYear = targetMonthDay.atYear(today.getYear());
        if (targetDateThisYear.isBefore(today) || targetDateThisYear.isEqual(today)) {
            targetDateThisYear = targetMonthDay.atYear(today.getYear() + 1);
        }
        return ChronoUnit.DAYS.between(today, targetDateThisYear);
    }
}
