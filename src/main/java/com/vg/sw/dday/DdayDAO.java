package com.vg.sw.dday;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.vg.ignore.DBManager;

public class DdayDAO {
	private String sql = "SELECT m_pk, m_name, m_debut FROM haco_member";

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
	    LocalDate currentDate = LocalDate.now();
	    try (Connection connection = getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            int id = rs.getInt("m_pk");
	            String name = rs.getString("m_name");
	            String debutDateString = rs.getString("m_debut");
	            LocalDate debutDate = null;
	            try {
	                if (debutDateString != null && !debutDateString.equals("0000-00-00")) {
	                    debutDate = LocalDate.parse(debutDateString);
	                    long totalDays = ChronoUnit.DAYS.between(debutDate, currentDate);
	                    long daysUntilDday = totalDays % 365;
	                    DdayDTO dday = new DdayDTO(id, name, debutDateString, daysUntilDday);
	                    ddayList.add(dday);
	                } else {
	                    // Handle invalid date format as needed (e.g., skip or set default value)
	                }
	            } catch (DateTimeParseException e) {
	                // Handle invalid date format as needed (e.g., skip or set default value)
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return ddayList;
	}

}
