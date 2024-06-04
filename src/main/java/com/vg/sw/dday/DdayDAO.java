package com.vg.sw.dday;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("m_id");
                String name = rs.getString("m_name");
                String date = rs.getString("m_debut");
                DdayDTO dday = new DdayDTO(id, name, date);
                ddayList.add(dday);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ddayList;
    }
}
