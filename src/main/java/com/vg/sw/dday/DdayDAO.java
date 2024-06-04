package com.vg.sw.dday;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.vg.ignore.DBManager;

public class DdayDAO {
    private String sql = "SELECT m_id, m_name, m_debut FROM haco_member";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DBManager.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<DdayDTO> selectAllDdays() {
        List<DdayDTO> ddayList = new ArrayList<>();
        LocalDate currentDate = LocalDate.now(); // 현재 날짜 가져오기
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("m_id");
                String name = rs.getString("m_name");
                String debutDateString = rs.getString("m_debut");
                LocalDate debutDate = LocalDate.parse(debutDateString); // 문자열을 LocalDate로 변환
                long daysUntilDday = ChronoUnit.DAYS.between(currentDate, debutDate); // 디데이까지 남은 일 수 계산
                DdayDTO dday = new DdayDTO(id, name, debutDateString, daysUntilDday); // DdayDTO 객체에 추가
                ddayList.add(dday);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ddayList;
    }
}
