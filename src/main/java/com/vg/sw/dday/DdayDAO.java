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

    // SQL 쿼리 문자열: 멤버의 기본 정보(이름, 데뷔 날짜, 생일)를 가져오는 쿼리
    private static final String SQL_NAME_DEBUT_AND_BIRTH = "SELECT m_pk, m_name, m_debut, m_birth FROM haco_member";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter BIRTH_DATE_FORMATTER = DateTimeFormatter.ofPattern("MM-dd");

    // 모든 멤버의 D-day 이벤트를 가져오는 메서드
    public static List<DdayDTO> selectAllDdays() {
        List<DdayDTO> ddayList = new ArrayList<>();

        try (Connection connection = DBManager.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_NAME_DEBUT_AND_BIRTH);
             ResultSet rs = preparedStatement.executeQuery()) {

            LocalDate today = LocalDate.now(); // 현재 날짜 가져오기

            while (rs.next()) {
                int id = rs.getInt("m_pk"); // 멤버 ID 가져오기
                String name = rs.getString("m_name"); // 멤버 이름 가져오기
                String debutDateString = rs.getString("m_debut"); // 멤버의 데뷔 날짜 문자열 가져오기
                String birthDateString = rs.getString("m_birth"); // 멤버의 생일 날짜 문자열 가져오기

                // 데뷔 날짜 처리
                processDate(ddayList, today, id, name, debutDateString, "데뷔", DATE_FORMATTER);

                // 생일 날짜 처리
                processDate(ddayList, today, id, name, birthDateString, "생일", BIRTH_DATE_FORMATTER, true);
            }

        } catch (Exception e) {
            e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
        }

        // D-day 이벤트 리스트를 이벤트 날짜 기준으로 오름차순 정렬
        Collections.sort(ddayList, (d1, d2) -> Long.compare(d1.getDaysUntilDday(), d2.getDaysUntilDday()));

        return ddayList; // 정렬된 D-day 이벤트 리스트 반환
    }

    private static void processDate(List<DdayDTO> ddayList, LocalDate today, int id, String name, String dateString, String eventType, DateTimeFormatter formatter) {
        processDate(ddayList, today, id, name, dateString, eventType, formatter, false);
    }

    private static void processDate(List<DdayDTO> ddayList, LocalDate today, int id, String name, String dateString, String eventType, DateTimeFormatter formatter, boolean isBirthDate) {
        if (dateString != null && !dateString.equals("0000-00-00")) {
            try {
                LocalDate date = LocalDate.parse(dateString, isBirthDate ? DATE_FORMATTER : formatter); // 날짜 문자열을 LocalDate로 변환
                long daysUntilDday = calculateDaysUntilNextOccurrence(today, date, isBirthDate); // D-day까지 남은 일수 계산
                String formattedDate = date.format(isBirthDate ? BIRTH_DATE_FORMATTER : formatter); // 포맷팅된 날짜 문자열 생성
                ddayList.add(new DdayDTO(id, name, eventType, formattedDate, date, daysUntilDday)); // DdayDTO 객체 생성 및 리스트에 추가
            } catch (DateTimeParseException e) {
                System.out.println("멤버 ID " + id + "의 " + eventType + " 날짜 파싱 실패: " + dateString); // 날짜 파싱 실패 시 로그 출력
            }
        }
    }

    /**
     * 오늘 날짜를 기준으로 특정 날짜까지 남은 일수를 계산하는 메소드.
     *
     * @param today      현재 날짜
     * @param targetDate 목표 날짜
     * @param isBirthDate 생일 여부 (생일일 경우 true, 아닐 경우 false)
     * @return 남은 일수 (음수)
     */
    private static long calculateDaysUntilNextOccurrence(LocalDate today, LocalDate targetDate, boolean isBirthDate) {
        if (isBirthDate) {
            targetDate = targetDate.withYear(today.getYear());
            if (targetDate.isBefore(today) || targetDate.isEqual(today)) {
                targetDate = targetDate.plusYears(1);
            }
        }
        
        long daysBetween = ChronoUnit.DAYS.between(today, targetDate); // 오늘부터 목표 날짜까지의 일 수 계산

        return daysBetween; // 양수로 결과 반환
    }
}
