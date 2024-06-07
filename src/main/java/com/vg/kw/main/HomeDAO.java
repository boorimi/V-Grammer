package com.vg.kw.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.vg.ds.trade.TradeDTO;
import com.vg.ignore.DBManager;

public class HomeDAO {

	private ArrayList<GetStreamIdDTO> streamIds = null;
	private Connection con = null;
	public static final HomeDAO HDAO = new HomeDAO();

	private HomeDAO() {
		try {
			con = DBManager.connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getStreamId(HttpServletRequest request) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			String sql = "select c_m_pk, c_address, i_icon ";
				   sql += "from haco_currentlivestream, haco_image ";
				   sql += "where i_m_pk = c_m_pk";

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			streamIds = new ArrayList<GetStreamIdDTO>();

			while (rs.next()) {
				String pk = rs.getString(1);
				String streamId = rs.getString(2);
				String icon = rs.getString(3);

				GetStreamIdDTO g = new GetStreamIdDTO(pk, streamId, icon);
				streamIds.add(g);

			}
			request.setAttribute("streamIds", streamIds);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}

	

}
