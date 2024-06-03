package com.vg.ds.trade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.vg.main.DBManager;

public class TradeDAO {

	private ArrayList<TradeDTO> trades = null;
	private Connection con = null;
	public static final TradeDAO TDAO = new TradeDAO();

	private TradeDAO() {
		try {
			con = DBManager.connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void paging(int page, HttpServletRequest request) {

		int cnt = 10; // 한페이지당 보여줄 개수
		int total = trades.size(); // 총 데이터 개수

		// 총 페이지 수 , 곧 마지막 페이지
		int pageCount = (int) (Math.ceil((double) total / cnt));
		// 시작, 끝

		int start = total - (cnt * (page - 1));
		int end = (page == pageCount) ? -1 : start - (cnt + 1);

		ArrayList<TradeDTO> items = new ArrayList<TradeDTO>();
		for (int i = start - 1; i > end; i--) {
			items.add(trades.get(i));
		}
		request.setAttribute("curPageNo", page);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("announcements", items);
	}

	public void selectAllTrade(HttpServletRequest request) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "select t_pk, t_u_twitter_id, u_id, u_nickname, t_text, t_date, u_yesno ";
			sql += "from haco_tradegoods, haco_user ";
			sql += "where u_twitter_id = t_u_twitter_id ";
			sql += "order by t_date desc";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			trades = new ArrayList<TradeDTO>();

			while (rs.next()) {
				String pk = rs.getString(1);
				String twitterId = rs.getString(2);
				String id = rs.getString(3);
				String nickname = rs.getString(4);
				String text = rs.getString(5);
				String date = rs.getString(6);
				String yesno = rs.getString(7);

				TradeDTO t = new TradeDTO(pk, twitterId, id, nickname, text, date, yesno);
				trades.add(t);

			}
			request.setAttribute("trades", trades);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}
	
}
