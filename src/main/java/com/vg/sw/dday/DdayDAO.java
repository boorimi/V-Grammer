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
    private static String sqlNameDebutAndBirth = "SELECT m_pk, m_name, m_debut, m_birth FROM haco_member";

    // 데이터베이스 연결을 가져오는 메서드
    protected static Connection getConnection() {
        Connection connection = null;
        try {
            // DBManager를 통해 데이터베이스에 연결
            connection = DBManager.connect();
            System.out.println("데이터베이스 연결 성공."); // 연결 확인용 로그 출력
        } catch (Exception e) {
            e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
        }
        return connection; // 연결된 데이터베이스 커넥션 반환
    }

    // 모든 멤버의 D-day 이벤트를 가져오는 메서드
    public static List<DdayDTO> selectAllDdays() {
        List<DdayDTO> ddayList = new ArrayList<>(); // D-day 이벤트를 담을 리스트 생성
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = getConnection(); // 데이터베이스 연결 가져오기
            preparedStatement = connection.prepareStatement(sqlNameDebutAndBirth); // SQL 쿼리 준비

            rs = preparedStatement.executeQuery(); // 쿼리 실행 후 결과 집합 얻기
            System.out.println("SQL 쿼리 실행 완료."); // 쿼리 실행 확인용 로그 출력

            LocalDate today = LocalDate.now(); // 현재 날짜 가져오기

            // 결과 집합을 반복하며 각 멤버의 데뷔일과 생일을 처리
            while (rs.next()) {
                int id = rs.getInt("m_pk"); // 멤버 ID 가져오기
                String name = rs.getString("m_name"); // 멤버 이름 가져오기
                String debutDateString = rs.getString("m_debut"); // 멤버의 데뷔 날짜 문자열 가져오기
                String birthDateString = rs.getString("m_birth"); // 멤버의 생일 날짜 문자열 가져오기

                // 데뷔 날짜 처리
                if (debutDateString != null && !debutDateString.equals("0000-00-00")) {
                    try {
                        LocalDate debutDate = LocalDate.parse(debutDateString); // 데뷔 날짜 문자열을 LocalDate로 변환
                        long daysUntilDebutDday = calculateDaysUntilNextOccurrence(today, debutDate); // D-day까지 남은 일수 계산
                        String debutDateFormatted = debutDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 포맷팅된 데뷔 날짜 문자열 생성
                        ddayList.add(new DdayDTO(id, name, "데뷔", debutDateFormatted, debutDate, daysUntilDebutDday)); // DdayDTO 객체 생성 및 리스트에 추가
                    } catch (DateTimeParseException e) {
                        System.out.println("멤버 ID " + id + "의 데뷔 날짜 파싱 실패."); // 날짜 파싱 실패 시 로그 출력
                    }
                }

                // 생일 날짜 처리
                if (birthDateString != null && !birthDateString.equals("0000-00-00")) {
                    try {
                        LocalDate birthDate = LocalDate.parse(birthDateString); // 생일 날짜 문자열을 LocalDate로 변환
                        long daysUntilBirthDday = calculateDaysUntilNextOccurrence(today, birthDate); // D-day까지 남은 일수 계산
                        String birthDateFormatted = birthDate.format(DateTimeFormatter.ofPattern("MM-dd")); // 포맷팅된 생일 날짜 문자열 생성
                        ddayList.add(new DdayDTO(id, name, "생일", birthDateFormatted, birthDate, daysUntilBirthDday)); // DdayDTO 객체 생성 및 리스트에 추가
                    } catch (DateTimeParseException e) {
                        System.out.println("멤버 ID " + id + "의 생일 날짜 파싱 실패."); // 날짜 파싱 실패 시 로그 출력
                    }
                }
            }
            
            System.out.println("selectAllDdays 메서드 내부: 데이터베이스 연결 및 처리 완료."); // 메서드 실행 완료 메시지 출력

        } catch (Exception e) {
            e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
        } finally {
            // 자원 닫기
            if (rs != null) {
                try {
                    rs.close(); // 결과 집합 닫기
                } catch (Exception e) {
                    e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close(); // 준비된 문 닫기
                } catch (Exception e) {
                    e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("데이터베이스 연결 닫힘 확인."); // 데이터베이스 연결 닫힘 확인 메시지 출력
                } catch (Exception e) {
                    e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
                }
            }
        }

        // D-day 이벤트 리스트를 이벤트 날짜 기준으로 오름차순 정렬
        Collections.sort(ddayList, (d1, d2) -> Long.compare(d1.getDaysUntilDday(), d2.getDaysUntilDday()));

        return ddayList; // 정렬된 D-day 이벤트 리스트 반환
    }

    /**
     * 오늘 날짜를 기준으로 특정 날짜까지 남은 일수를 계산하는 메소드.
     *
     * @param today      현재 날짜
     * @param targetDate 목표 날짜
     * @return 남은 일수 (음수)
     */
    private static long calculateDaysUntilNextOccurrence(LocalDate today, LocalDate targetDate) {
        long daysBetween = ChronoUnit.DAYS.between(today, targetDate); // 오늘부터 목표 날짜까지의 일 수 계산

        // 일 수가 365보다 크면 조정
        if (daysBetween > 365) {
            daysBetween -= 365;
        }

        // 일 수가 음수면 365를 더함
        if (daysBetween < 0) {
            daysBetween += 365;
        }

        return daysBetween; // 양수로 결과 반환
    }

}
