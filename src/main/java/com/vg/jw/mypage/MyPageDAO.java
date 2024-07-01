package com.vg.jw.mypage;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.vg.ds.trade.TradeCommentsDTO;
import com.vg.ds.trade.TradeDTO;
import com.vg.ignore.DBManager;
import com.vg.jw.AccountDTO;

public class MyPageDAO {

	public static void getGoodsInfo(HttpServletRequest request, String goodsCategory) {

		System.out.println("getGoodsInfo메서드 진입");
		String category = goodsCategory;
		System.out.println("현재 카테고리 : " + category);

		// 세션에서 현재 사용자 id정보를 받아옴
		HttpSession twitterLoginSession = request.getSession();

//		long twitterId = (long) twitterLoginSession.getAttribute("twitterId");

		AccountDTO accountInfo = (AccountDTO) twitterLoginSession.getAttribute("accountInfo");
		long twitterId = accountInfo.getU_twitter_id();

		// 일단 멤버들 뿌려줄거 - 이 결과로 나오는건 멤버 테이블의 이름과, pk, 퍼스널컬러 / 이미지 테이블의 icon, 총 4개의 컬럼이 나옴
		String MemberSql = "select hm.m_pk, hm.m_name, hm.m_personalcolor, hi.i_icon from haco_member hm, haco_image hi where hm.m_pk = hi.i_m_pk";

		// icon, 멤버이름, 멤버pk, 유저트위터id, 굿즈수량 등 실제로 필요한 정보들 - DTO에 모아서 정리할 값들임
		String sql = "SELECT hg.*, hi.i_icon, hm.m_name, hm.m_personalcolor from haco_goods hg, haco_member hm, haco_image hi WHERE hi.i_m_pk = hg.g_m_pk and hi.i_m_pk = hm.m_pk and g_u_t_id = ? and g_category = ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<GoodsDTO> haco_members = new ArrayList<GoodsDTO>();
		Map<Integer, GoodsDTO> hacoMemberMap = new HashMap<>();
		GoodsDTO haco_member = null;
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(MemberSql);
			rs = pstmt.executeQuery();
			while (rs.next()) { // MemberSql의 결과 모든 멤버를 데려왔으니 rs.next가 진행하는 만큼 = 25개 전부 생성
				haco_member = new GoodsDTO();
				haco_member.setG_m_pk(rs.getInt("m_pk"));
				haco_member.setM_name(rs.getString("m_name"));
				haco_member.setI_icon(rs.getString("i_icon"));
				haco_member.setU_twitter_id(twitterId);
				haco_member.setM_personalcolor(rs.getString("m_personalcolor"));
				haco_member.setG_category(category);
				haco_members.add(haco_member);
				hacoMemberMap.put(rs.getInt(1), haco_member); // Map에 추가
			}

			System.out.println("[베이스가 되는 멤버 배열]");
			for (GoodsDTO a : haco_members) {
				System.out.println(a);
			}

			pstmt.close();
			rs.close();

			// 해당 id의 굿즈가 몇개 있는지 보기 위해 twitterId로 굿즈 테이블에서 멤버pk와 갯수를 뽑아옴
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, twitterId);
			pstmt.setString(2, category);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int g_m_pk = rs.getInt("g_m_pk");
				int g_count = rs.getInt("g_count");

				// 위에서 전체 돌린 Map에 굿즈 테이블의 멤버pk(실제로 굿즈가 등록된 멤버)가 포함된 부분이 있는지 확인하고 그 부분의 카운트를 굿즈
				// 테이블의 수치로 굿즈 카운트를 업데이트.
				if (hacoMemberMap.containsKey(g_m_pk)) {
					haco_member = hacoMemberMap.get(g_m_pk);
					haco_member.setG_count(g_count); // g_count 업데이트
				}
			}
			System.out.println("[업데이트한 멤버 배열]");
			for (GoodsDTO a : haco_members) {
				System.out.println(a);
			}

			String attributeName = "Infos_" + category;
			request.setAttribute(attributeName, haco_members);
			// 어트리뷰트명 ex) Infos_57mmCanBadge

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public static void updateGoods(HttpServletRequest request, HttpServletResponse response, int pk, int count,
			long u_twitter_id, String g_category, int previousCount) {

		System.out.println("updateGoods 메서드 진입");

		Connection con = null;
		PreparedStatement pstmt = null;

		int member_pk = pk;
		int goods_count = count;
		long userId = u_twitter_id;
		String category = g_category;
		int oldCount = previousCount;

		System.out.println("메서드에 전달된 pk: " + member_pk);
		System.out.println("메서드에 전달된 count: " + goods_count);
		System.out.println("메서드에 전달된 userId: " + userId);
		System.out.println("메서드에 전달된 category: " + category);
		System.out.println("메서드에 전달된 기존값: " + oldCount);

		String sql = "";
		try {

			con = DBManager.connect();

			// 기존값이 0일 경우 insert문
			if (oldCount == 0) {
				sql = "insert into haco_goods values (null, ?, ?, ?, ?);";
				pstmt = con.prepareStatement(sql);
				pstmt.setLong(1, userId);
				pstmt.setInt(2, member_pk);
				pstmt.setString(3, category);
				pstmt.setInt(4, goods_count);

				if (pstmt.executeUpdate() == 1) {
					System.out.println("굿즈데이터 추가 성공");
					//ajax에서 alert를 띄우기 위한 숫자 반환
					response.getWriter().write(String.valueOf(1));
				}

			} else {

				if (goods_count == 0) { // 전달된 굿즈 카운트가0 -> 삭제문
					sql = "DELETE FROM haco_goods WHERE g_u_t_id = ? AND g_category = ? AND g_m_pk = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setLong(1, userId);
					pstmt.setString(2, category);
					pstmt.setInt(3, member_pk);

					if (pstmt.executeUpdate() == 1) {
						System.out.println("굿즈 데이터 삭제 성공");
						response.getWriter().write(String.valueOf(2));
					}

				} else {
					sql = "UPDATE haco_goods SET g_count = ? WHERE g_u_t_id = ? AND g_category = ? AND g_m_pk = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, goods_count);
					pstmt.setLong(2, userId);
					pstmt.setString(3, category);
					pstmt.setInt(4, member_pk);

					if (pstmt.executeUpdate() == 1) {
						System.out.println("굿즈 데이터 업데이트 성공");
						response.getWriter().write(String.valueOf(3));
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

	public static void getMoreArticle(HttpServletRequest request, HttpServletResponse response) {
		// 5개씩 더 가져오기
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; utf-8");

		int limit = Integer.parseInt(request.getParameter("limit"));
		System.out.println(limit);
		AccountDTO accountInfo = (AccountDTO) request.getSession().getAttribute("accountInfo");
		long twitterId = accountInfo.getU_twitter_id();
		System.out.println(twitterId);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String sql = "SELECT ht.t_pk, ht.t_u_t_id, hs.u_screenname, hs.u_nickname, ht.t_text, ht.t_date, ht.t_category" 
                + " FROM haco_tradegoods ht, haco_user hs "
                + "WHERE ht.t_u_t_id = hs.u_twitter_id AND ht.t_u_t_id = ? "
                + "ORDER BY ht.t_date DESC "
                + "LIMIT 5 OFFSET ?";

		String sql2 = "SELECT tc_pk, tc_m_t_id, hu1.u_nickname AS m_nickname, tc_s_t_id, hu2.u_nickname AS s_nickname, tc_text, tc_date, tc_t_pk "
                + "FROM haco_tradegoods_comments "
                + "JOIN haco_user hu1 ON tc_m_t_id = hu1.u_twitter_id "
                + "LEFT JOIN haco_user hu2 ON tc_s_t_id = hu2.u_twitter_id "
                + "WHERE tc_t_pk = ?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, twitterId);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			ArrayList<TradeDTO> trades = new ArrayList<TradeDTO>();
			ArrayList<TradeCommentsDTO> tradeComments = new ArrayList<TradeCommentsDTO>();
			String[] category = null;
			TradeCommentsDTO tc = null;
			System.out.println("--- 여기 진입?");
			while (rs.next()) {
				String t_pk = rs.getString(1);
				String t_id = rs.getString(2);
				String t_screeName = rs.getString(3);
				String t_nickname = rs.getString(4);
				String t_text = rs.getString(5);
				String t_date = rs.getString(6);

				// 배열로 전환
				category = rs.getString(7).split("!");
				TradeDTO t = new TradeDTO(t_pk, t_id, t_screeName, t_nickname, t_text, t_date, category, null);
				System.out.println("카테고리?=================");
				String[] categories = t.getCategory();
				for (String cate : categories) {
				    System.out.println(cate);
				}
				System.out.println("=================");

				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, t_pk);
				rs2 = pstmt.executeQuery();
				while (rs2.next()) {
					String c_pk = rs2.getString(1);
					String c_mTwitterId = rs2.getString(2);
					String c_mNickname = rs2.getString(3);
					String c_sTwitterId = rs2.getString(4);
					String c_sNickname = rs2.getString(5);
					String c_text = rs2.getString(6);
					String c_date = rs2.getString(7);
					String c_t_pk = rs2.getString(8);
					String text2 = c_text.replace("<br>", "\r\n");
					tc = new TradeCommentsDTO(c_pk, c_mTwitterId, c_mNickname, c_sTwitterId, c_sNickname, text2, c_date,
							c_t_pk);
					tradeComments.add(tc);
					System.out.println("~~~~~~~~~~~~~~~~~");
					System.out.println(tc);
					System.out.println();
					System.out.println("~~~~~~~~~~~~~~~~~");
				}
				rs2.close();
				t.setComments(tradeComments);
				trades.add(t);
			}
			Gson g = new Gson();
			System.out.println(g.toJson(trades));
			response.getWriter().print(g.toJson(trades));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			DBManager.close(con, pstmt, rs2);

		}

	}

	public static void changeNickname(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;

		// ajax에서 받아오는 input닉네임 파라미터
		String newNickname = request.getParameter("inputNickname");
		String sql = "UPDATE haco_user SET u_nickname = ? WHERE u_twitter_id = ?";
		AccountDTO accountInfo = (AccountDTO) request.getSession().getAttribute("accountInfo");
		long twitterId = accountInfo.getU_twitter_id();
		System.out.println(twitterId);

		try {

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newNickname);
			pstmt.setLong(2, twitterId);

			if (pstmt.executeUpdate() == 1) {
				System.out.println(newNickname + " 으로 닉네임 업데이트 성공");

				// 로그인 세션의 닉네임정보 업데이트
				accountInfo.setU_nickname(newNickname);
				System.out.println("로그인세션 내 닉네임 정보" + accountInfo.getU_nickname());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

	public static void changeProfile(HttpServletRequest request) {

		System.out.println("changeProfile 메서드 진입");
		Connection con = null;
		PreparedStatement pstmt = null;

		// 기존 계정정보 가져오기
		HttpSession LoginSession = request.getSession();
		AccountDTO accountInfo = (AccountDTO) LoginSession.getAttribute("accountInfo");

		long userId = accountInfo.getU_twitter_id();
		String oldProfileImg = accountInfo.getU_profile_img();

		System.out.println("회원의 기존 정보");
		System.out.println(userId);
		System.out.println("경로 제거 전 기존이미지:" + oldProfileImg);

		if (oldProfileImg != null && oldProfileImg.contains("account/profileImg/")) {
			oldProfileImg = oldProfileImg.replace("account/profileImg/", "");
		}
		System.out.println("경로 제거 후 기존이미지:" + oldProfileImg);
		// 사진받을 준비
		String path = request.getServletContext().getRealPath("account/profileImg");
		System.out.println("프로필사진 경로: " + path);
		try {

			// 파일처리
			MultipartRequest mr = new MultipartRequest(request, path, 1024 * 1024 * 10, "utf-8",
					new DefaultFileRenamePolicy());

			// 변경을 위해 입력한 값 받아오기
			String newProfileImg = mr.getFilesystemName("new-profile-img");
			System.out.println("유저정보 변경에서 새로 입력한 이미지" + newProfileImg);

			// 이미지를 등록하지 않았을 경우 기존 이미지로 설정
			if (newProfileImg == null) {
				System.out.println("이미지 등록 안함");
				newProfileImg = oldProfileImg;
			}

			System.out.println("DB에 새로 들어갈 이미지파일 경로: " + newProfileImg);

			String sql = "UPDATE haco_user SET u_profile_img = ? WHERE u_twitter_id = ?";
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newProfileImg);
			pstmt.setLong(2, userId);

			if (pstmt.executeUpdate() >= 1) {
				System.out.println("사진 업데이트 완료");
				System.out.println("새로운 사진 파일명: " + newProfileImg);

				// 로그인 세션 정보 업데이트
				String subString = newProfileImg.substring(0, 4);
				;

				if (subString.equals("http")) {
					accountInfo.setU_profile_img(newProfileImg);
				} else {
					accountInfo.setU_profile_img("account/profileImg/" + newProfileImg);
				}

				// 예전 이미지 삭제
				if (!newProfileImg.equals(oldProfileImg)) {
					File f = new File(path + "/" + oldProfileImg); // 経路名select
					f.delete();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

}
