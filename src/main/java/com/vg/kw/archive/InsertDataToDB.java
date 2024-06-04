package com.vg.kw.archive;

import com.vg.ignore.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class InsertDataToDB {
    public static void main(String[] args) {
        // 데이터베이스 연결
        try (Connection connection = DBManager.connect()) {
            // 쿼리문 준비
            String sql = "INSERT INTO haco_address (a_date, a_time, a_title, a_thumbnail) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            // 값 설정
            statement.setDate(1, date); // date는 java.sql.Date 객체여야 함
            statement.setTime(2, time); // time은 java.sql.Time 객체여야 함
            statement.setString(3, thumbnail);
            
            // 쿼리 실행
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new row has been inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
