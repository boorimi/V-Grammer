package com.vg.ds.trade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vg.ds.announcement.AnnouncementDTO;
import com.vg.ignore.DBManager;
import com.vg.jw.AccountDTO;
import com.vg.kw.archive.ArchiveDTO;

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

		int cnt = 5; // 한페이지당 보여줄 개수
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
		request.setAttribute("trades", items);
	}

	public void selectAllTrade(HttpServletRequest request) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "select t_pk, u_twitter_id, u_screenname, u_nickname, t_text, t_date, t_category ";
			sql += "from haco_tradegoods, haco_user ";
			sql += "where u_twitter_id = t_u_t_id ";
			////// 검색 진행 시 sql문 추가하는 부분 시작 ////
			if (request.getParameterValues("goodsCategory") != null) {
				String[] category2 = request.getParameterValues("goodsCategory");
				for (int i = 0; i < category2.length; i++) {
					if (i == 0) {
						sql += "and (";
					}
					sql += "t_category like CONCAT('%','" + category2[i] + "','%')";
					if (i == category2.length - 1) {
						sql += ") ";
					} else {
						sql += " or ";
					}
				}
				String category3 = "";
				for (String c : category2) {
					category3 += "&goodsCategory=" + c;
				}
				request.setAttribute("category3", category3);
			}

			if (request.getParameterValues("name") != null) {
				String name = request.getParameter("name");
				sql += "and u_nickname like CONCAT('%','" + name + "','%')";
				String name2 = "&name=" + name;
				request.setAttribute("name", name);
				request.setAttribute("name2", name2);
			}
			////// 검색 진행 시 sql문 추가하는 부분 끝 ////
			sql += "order by t_date asc";

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			trades = new ArrayList<TradeDTO>();
			String[] category = null;

			while (rs.next()) {
				String pk = rs.getString(1);
				String twitterId = rs.getString(2);
				String screenName = rs.getString(3);
				String nickname = rs.getString(4);
				String text = rs.getString(5);
				String date = rs.getString(6);

				// 배열로 전환
				category = rs.getString(7).split("!");
				TradeDTO t = new TradeDTO(pk, twitterId, screenName, nickname, text, date, category, tradeComments);
				trades.add(t);

			}

			request.setAttribute("trades", trades);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	// 마이페이지에서 쓰는 작성글 가져오는 메서드
	public void selectAllTrade2(HttpServletRequest request) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			AccountDTO accountInfo = (AccountDTO) request.getSession().getAttribute("accountInfo");
			long twitterId2 = accountInfo.getU_twitter_id();
		
			con = DBManager.connect();
			int ok = 1;
			int articleCount = 0;
			String checksql = "select count(*) as count "
		            + "from haco_tradegoods, haco_user "
		            + "where u_twitter_id = t_u_t_id and u_twitter_id=? ";
			
			pstmt = con.prepareStatement(checksql);
			pstmt.setLong(1, twitterId2);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1)==0) {
					ok = 0;
					request.setAttribute("checkArticleCount", false);
					return;
				}
				articleCount = rs.getInt(1);
				request.setAttribute("articleCount", articleCount);
			}
								
			String sql = "select t_pk, u_twitter_id, u_screenname, u_nickname, t_text, t_date, t_category "
					+ "from haco_tradegoods, haco_user " + "where u_twitter_id = t_u_t_id and u_twitter_id=? ";
			////// 검색 진행 시 sql문 추가하는 부분 시작 ////
			if (request.getParameterValues("goodsCategory") != null) {
				String[] category2 = request.getParameterValues("goodsCategory");
				for (int i = 0; i < category2.length; i++) {
					if (i == 0) {
						sql += "and (";
					}
					sql += "t_category like CONCAT('%','" + category2[i] + "','%')";
					if (i == category2.length - 1) {
						sql += ") ";
					} else {
						sql += " or ";
					}
				}
				String category3 = "";
				for (String c : category2) {
					category3 += "&goodsCategory=" + c;
				}
				request.setAttribute("category3", category3);
			}

			if (request.getParameterValues("name") != null) {
				String name = request.getParameter("name");
				sql += "and u_nickname like CONCAT('%','" + name + "','%')";
				String name2 = "&name=" + name;
				request.setAttribute("name", name);
				request.setAttribute("name2", name2);
			}
			////// 검색 진행 시 sql문 추가하는 부분 끝 ////
			sql += "order by t_date asc";

			
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, twitterId2);
			rs = pstmt.executeQuery();

			trades = new ArrayList<TradeDTO>();
			String[] category = null;

			while (rs.next()) {
				String pk = rs.getString(1);
				String twitterId = rs.getString(2);
				String screenName = rs.getString(3);
				String nickname = rs.getString(4);
				String text = rs.getString(5);
				String date = rs.getString(6);

				// 배열로 전환
				category = rs.getString(7).split("!");
				TradeDTO t = new TradeDTO(pk, twitterId, screenName, nickname, text, date, category, tradeComments);
				trades.add(t);


			}

			request.setAttribute("trades", trades);
			request.setAttribute("checkArticleCount", true);
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

			String sql = "select t_pk, u_twitter_id, u_nickname, t_text, t_date, t_category ";
			sql += "from haco_tradegoods, haco_user ";
			sql += "where u_twitter_id = t_u_t_id and t_pk = ? ";

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));
			rs = pstmt.executeQuery();
			rs.next();

			String pk = rs.getString(1);
			String twitterId = rs.getString(2);
			String nickname = rs.getString(3);
			String text = rs.getString(4);
			String date = rs.getString(5);

			// 카테고리를 배열로 다시 전환
			String[] category = rs.getString(6).split("!");
			// 본문내용 확인 할때 DB내용을 br -> 줄바꿈으로 대체하는 코드
			String text2 = text.replace("<br>", "\r\n");

			TradeDTO t = new TradeDTO(pk, twitterId, text, nickname, text2, date, category, tradeComments);

//			request.setAttribute("category", category);
			request.setAttribute("text2", text2);
			request.setAttribute("trades", t);
			tradeCheckboxList(request);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public void insertTrade(HttpServletRequest request) {

		PreparedStatement pstmt = null;

		try {

			String[] category = request.getParameterValues("goodsCategory");
			String category2 = "";
			for (String s : category) {
				category2 += s + "!";
			}

			// 인서트 할때 DB에 줄바꿈 -> br 로 대체하는 코드
			String text = request.getParameter("text");
			text = text.replaceAll("\r\n", "<br>");

			String sql = "insert into haco_tradegoods values (null, ?, ?, NOW(), ?)";

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, (long) request.getSession().getAttribute("twitterId"));
			pstmt.setString(2, text);
			pstmt.setString(3, category2);

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

			String sql = "SELECT tc_pk, tc_m_t_id, hu1.u_nickname AS m_nickname, tc_s_t_id, hu2.u_nickname AS s_nickname, tc_text, tc_date, tc_t_pk ";
			sql += "FROM haco_tradegoods_comments ";
			sql += "JOIN haco_user hu1 ON tc_m_t_id = hu1.u_twitter_id ";
			sql += "LEFT JOIN haco_user hu2 ON tc_s_t_id = hu2.u_twitter_id";

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, request.getParameter("no"));
			rs = pstmt.executeQuery();

			tradeComments = new ArrayList<TradeCommentsDTO>();

			while (rs.next()) {

				String pk = rs.getString(1);
				String mTwitterId = rs.getString(2);
				String mNickname = rs.getString(3);
				String sTwitterId = rs.getString(4);
				String sNickname = rs.getString(5);
				String text = rs.getString(6);
				String date = rs.getString(7);
				String t_pk = rs.getString(8);

				// 본문내용 확인 할때 DB내용을 br -> 줄바꿈으로 대체하는 코드
				String text2 = text.replace("<br>", "\r\n");

				TradeCommentsDTO tc = new TradeCommentsDTO(pk, mTwitterId, mNickname, sTwitterId, sNickname, text2,
						date, t_pk);
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
			System.out.println(request.getParameter("no"));
			String text = request.getParameter("text");
			text = text.replaceAll("\r\n", "<br>");

			String sql = "insert into haco_tradegoods_comments values ";
			sql += "(null, ?, ?, ?, ?, now())";

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));
			pstmt.setString(2, request.getParameter("masterTwitterId"));
			pstmt.setLong(3, (long) request.getSession().getAttribute("twitterId"));
			pstmt.setString(4, text);

			if (pstmt.executeUpdate() == 1) {
				System.out.println("입력 성공!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

	public void tradeCheckboxList(HttpServletRequest request) {
		// HashMap 생성 및 데이터 추가
		LinkedHashMap<String, String> checkboxItems = new LinkedHashMap<>();
		checkboxItems.put("bromide", "白賞ブロマイド");
		checkboxItems.put("57mmCanBadge", "57mm缶バッジ");
		checkboxItems.put("76mmCanBadge", "76mm缶バッジ");
		checkboxItems.put("akuki", "SD絵アクキー");
		checkboxItems.put("coaster", "コスタ");
		checkboxItems.put("omoideCyeki", "思い出チェキ風カード");
		checkboxItems.put("dmmMiniShikishi", "DMM：色紙");
		checkboxItems.put("dmm57CanBadge", "DMM：57mm缶バッジ");
		checkboxItems.put("dmmMiniAkusuta", "DMM：ミニアクスタ");
		checkboxItems.put("dmmCyeki", "DMM：チェキ");

		// 요청 객체에 데이터 설정
		request.setAttribute("checkboxItems", checkboxItems);
	}

	public void insertGoodsValue(HttpServletRequest request, HttpServletResponse response) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String category = request.getParameter("category");
		String pk = request.getParameter("pk");
		System.out.println(category);
		System.out.println(pk);

		String sql = "SELECT hg.*, hm.m_name from haco_goods hg, haco_member hm ";
		sql += "where hg.g_m_pk = hm.m_pk and g_category = ? ";
		sql += "and g_u_t_id = ?";
		try {

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, pk);
			rs = pstmt.executeQuery();

			ArrayList<String> goods = new ArrayList<>();

			while (rs.next()) {
				GoodsDTO good = new GoodsDTO();

				good.setPk(rs.getString(1));
				good.setU_t_id(rs.getString(2));
				good.setM_pk(rs.getString(3));
				String valueToFind = rs.getString(4);
				String label = null;
				List<Map<String, String>> checkboxItems2 = new ArrayList<>();
				checkboxItems2.add(Map.of("value", "bromide", "label", "白賞ブロマイド"));
				checkboxItems2.add(Map.of("value", "57mmCanBadge", "label", "57mm缶バッジ"));
				checkboxItems2.add(Map.of("value", "76mmCanBadge", "label", "76mm缶バッジ"));
				checkboxItems2.add(Map.of("value", "akuki", "label", "SD絵アクキー"));
				checkboxItems2.add(Map.of("value", "coaster", "label", "コスタ"));
				checkboxItems2.add(Map.of("value", "omoideCyeki", "label", "思い出チェキ風カード"));
				checkboxItems2.add(Map.of("value", "dmmMiniShikishi", "label", "DMM：色紙"));
				checkboxItems2.add(Map.of("value", "dmm57CanBadge", "label", "DMM：57mm缶バッジ"));
				checkboxItems2.add(Map.of("value", "dmmMiniAkusuta", "label", "DMM：ミニアクスタ"));
				checkboxItems2.add(Map.of("value", "dmmCyeki", "label", "DMM：チェキ"));
				for (Map<String, String> item : checkboxItems2) {
					if (item.get("value").equals(valueToFind)) {
						label = item.get("label");
						break;
					}
				}
				good.setCategory(label);
				good.setCount(rs.getString(5));
				good.setName(rs.getString(6));

				goods.add(good.toJSON());

			}
			System.out.println(goods);
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(goods);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public void deleteCommentsTrade(HttpServletRequest request) {
		PreparedStatement pstmt = null;

		try {

			String sql = "delete from haco_tradegoods_comments where tc_pk = ?";
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

}
