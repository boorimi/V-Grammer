package com.vg.ds.trade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.vg.ds.announcement.AnnouncementDTO;
import com.vg.ignore.DBManager;

public class TradeDAO {

	private ArrayList<TradeDTO> trades = null;
	private ArrayList<TradeCommentsDTO> tradeComments = null;
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

	public void selectTrade(HttpServletRequest request) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "select t_pk, t_u_twitter_id, u_id, u_nickname, t_text, t_date, u_yesno ";
			sql += "from haco_tradegoods, haco_user ";
			sql += "where u_twitter_id = t_u_twitter_id and t_pk = ? ";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));
			rs = pstmt.executeQuery();
			rs.next();

			String pk = rs.getString(1);
			String twitterId = rs.getString(2);
			String id = rs.getString(3);
			String nickname = rs.getString(4);
			String text = rs.getString(5);
			String date = rs.getString(6);
			String yesno = rs.getString(7);

			// 본문내용 확인 할때 DB내용을 br -> 줄바꿈으로 대체하는 코드
			String text2 = text.replace("<br>", "\r\n");

			TradeDTO t = new TradeDTO(pk, twitterId, id, nickname, text2, date, yesno);

			request.setAttribute("text2", text2);
			request.setAttribute("trades", t);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public void insertTrade(HttpServletRequest request) {

		PreparedStatement pstmt = null;

		try {
			// 인서트 할때 DB에 줄바꿈 -> br 로 대체하는 코드
			String text = request.getParameter("text");
			text = text.replaceAll("\r\n", "<br>");

			String sql = "insert into haco_tradegoods values (null, 'KOR_JABIRAN', ?, NOW())";

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, text);

			if (pstmt.executeUpdate() == 1) {
				System.out.println("입력 성공!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

	public void deleteTrade(HttpServletRequest request) {

		PreparedStatement pstmt = null;

		try {

			String sql = "delete from haco_tradegoods where t_pk = ?";
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));

			if (pstmt.executeUpdate() == 1) {
				System.out.println("삭제 성공!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

	public void updateTrade(HttpServletRequest request) {

		PreparedStatement pstmt = null;

		try {
			// 업데이트 할때 DB에 줄바꿈 -> br 로 대체하는 코드
			String text = request.getParameter("text");
			text = text.replace("\r\n", "<br>");

			String sql = "update haco_tradegoods set t_text = ? where t_pk = ?";

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, text);
			pstmt.setString(2, request.getParameter("no"));

			if (pstmt.executeUpdate() == 1) {
				System.out.println("수정 성공!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

	public void selectTradeComments(HttpServletRequest request) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "select tc_pk, tc_m_twitter_id, u_id, u_nickname, tc_s_twitter_id, u_id, u_nickname, tc_text, tc_date, u_yesno, tc_t_pk ";
			sql += "from haco_user, haco_tradegoods_comments ";
			sql += "where tc_m_twitter_id = u_twitter_id ";
			sql += "and tc_s_twitter_id = u_twitter_id and u_yesno = 1 ";
			
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, request.getParameter("no"));
			rs = pstmt.executeQuery();
			
			tradeComments = new ArrayList<TradeCommentsDTO>();
			
			while (rs.next()) {

			String pk = rs.getString(1);
			String mTwitterId = rs.getString(2);
			String mId = rs.getString(3);
			String mNickname = rs.getString(4);
			String sTwitterId = rs.getString(5);
			String sId = rs.getString(6);
			String sNickname = rs.getString(7);
			String text = rs.getString(8);
			String date = rs.getString(9);
			String yesno = rs.getString(10);
			String t_pk = rs.getString(11);

			// 본문내용 확인 할때 DB내용을 br -> 줄바꿈으로 대체하는 코드
			String text2 = text.replace("<br>", "\r\n");

			TradeCommentsDTO tc = new TradeCommentsDTO(pk, mTwitterId, mId, mNickname, sTwitterId, sId, sNickname, text2, date, yesno, t_pk);
			tradeComments.add(tc);
			
			}
			
			request.setAttribute("tradeComments", tradeComments);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}

	public void insertTradeComments(HttpServletRequest request) {

		PreparedStatement pstmt = null;

		try {
			// 인서트 할때 DB에 줄바꿈 -> br 로 대체하는 코드
			String text = request.getParameter("text");
			text = text.replaceAll("\r\n", "<br>");

			String sql = "insert into haco_tradegoods_comments values ";
				  sql += "(null, ?, 'KOR_JABIRAN','KOR_JABIRAN',?,now())";

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));
			pstmt.setString(2, text);

			if (pstmt.executeUpdate() == 1) {
				System.out.println("입력 성공!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}
	
}
