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
	private ArrayList<GetRecommendVtuberDTO> recommendVtuber = null;
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

	public void getRecommendVtuber(HttpServletRequest request) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

//			String  sql = "SELECT ar.a_pk, ad.*, ar.a_title, ar.a_videoid, im.i_icon, m.m_name ";
//					sql += "FROM haco_address ad ";
//					sql += "JOIN haco_archive ar ON ar.a_m_pk = ad.a_m_pk ";
//					sql += "JOIN haco_member m ON m.m_pk = ar.a_m_pk ";
//					sql += "JOIN haco_image im On im.i_m_pk = m.m_pk ";
//					sql += "JOIN ( SELECT a_pk FROM haco_archive ORDER BY RAND() LIMIT 1 ) ra ON ar.a_pk = ra.a_pk";
//					sql += "where not ad.a_category = 'YchannelID' and not ad.a_category = 'YuploadPLID'";

			String sql = "select * from haco_recommendvtuber";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			recommendVtuber = new ArrayList<GetRecommendVtuberDTO>();

			while (rs.next()) {
				String pk = rs.getString(1);
				String archive_pk = rs.getString(2);
				String address_pk = rs.getString(3);
				String m_pk = rs.getString(4);
				String category = rs.getString(5).replaceAll("twitter", "X");
				String address = rs.getString(6);
				String title = rs.getString(7);
				String videoId = rs.getString(8);
				String icon = rs.getString(9);
				String name = rs.getString(10);
				
				GetRecommendVtuberDTO g = new GetRecommendVtuberDTO(pk, archive_pk, address_pk, m_pk, category, address, title, videoId, icon, name);
				recommendVtuber.add(g);

			}
			request.setAttribute("recommendVtuber", recommendVtuber);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}

}
