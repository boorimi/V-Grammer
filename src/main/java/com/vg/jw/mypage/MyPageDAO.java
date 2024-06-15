package com.vg.jw.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.vg.ignore.DBManager;
import com.vg.jw.AccountDTO;

public class MyPageDAO {

	public static void getBromide(HttpServletRequest request) {

		System.out.println("getBromide메서드 진입");

		// 세션에서 현재 사용자 id정보를 받아옴
		HttpSession twitterLoginSession = request.getSession();

		long twitterId = (long) twitterLoginSession.getAttribute("twitterId");

		// 일단 멤버들 뿌려줄거 - 이 결과로 나오는건 멤버 테이블의 이름과, pk / 이미지 테이블의 icon. 총 3개의 컬럼이 나옴
		String MemberSql = "select hm.m_pk, hm.m_name, hi.i_icon from haco_member hm, haco_image hi where hm.m_pk = hi.i_m_pk";

		// icon, 멤버이름, 멤버pk, 유저트위터id, 굿즈수량 등 실제로 필요한 정보들 - DTO에 모아서 정리할 값들임
		String sql = "SELECT hg.*, hi.i_icon, hm.m_name from haco_goods hg, haco_member hm, haco_image hi WHERE hi.i_m_pk = hg.g_m_pk and hi.i_m_pk = hm.m_pk and g_u_t_id = ? and g_category ='bromide'";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

//		ArrayList<GoodsDTO> bromideInfos = new ArrayList<GoodsDTO>();		// 25개인데 멤버  가변
//		GoodsDTO bromideInfo = null;

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
				haco_member.setG_category("bromide");
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
			// pstmt = con.prepareStatement(sql);
//			pstmt.setLong(1, twitterId);
//			rs = pstmt.executeQuery();     // 1 ~ 25 멤버  pk 돌릴 수 있게
//			while (rs.next()) {
//				bromideInfo = new GoodsDTO(rs.getInt("g_m_pk"), rs.getString("g_category"), rs.getInt("g_count"), rs.getString("i_icon"), rs.getString("m_name"));
//				bromideInfos.add(bromideInfo);	// 25개로 만들기
//			}
			request.setAttribute("bromideInfos", haco_members);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public static void get57mmCanBadge(HttpServletRequest request) {
		System.out.println("get57CanBadge메서드 진입");

		// 세션에서 현재 사용자 id정보를 받아옴
		HttpSession twitterLoginSession = request.getSession();

		long twitterId = (long) twitterLoginSession.getAttribute("twitterId");

		// 일단 멤버들 뿌려줄거 - 이 결과로 나오는건 멤버 테이블의 이름과, pk / 이미지 테이블의 icon. 총 3개의 컬럼이 나옴
		String MemberSql = "select hm.m_pk, hm.m_name, hi.i_icon from haco_member hm, haco_image hi where hm.m_pk = hi.i_m_pk";

		// icon, 멤버이름, 멤버pk, 유저트위터id, 굿즈수량 등 실제로 필요한 정보들 - DTO에 모아서 정리할 값들임
		String sql = "SELECT hg.*, hi.i_icon, hm.m_name from haco_goods hg, haco_member hm, haco_image hi WHERE hi.i_m_pk = hg.g_m_pk and hi.i_m_pk = hm.m_pk and g_u_t_id = ? and g_category ='57mmCanBadge'";

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
				haco_member.setG_category("57mmCanBadge");
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
			// pstmt = con.prepareStatement(sql);
//			pstmt.setLong(1, twitterId);
//			rs = pstmt.executeQuery();     // 1 ~ 25 멤버  pk 돌릴 수 있게
//			while (rs.next()) {
//				bromideInfo = new GoodsDTO(rs.getInt("g_m_pk"), rs.getString("g_category"), rs.getInt("g_count"), rs.getString("i_icon"), rs.getString("m_name"));
//				bromideInfos.add(bromideInfo);	// 25개로 만들기
//			}
			request.setAttribute("canBadge57mmInfos", haco_members);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public static void get76mmCanBadge(HttpServletRequest request) {

		System.out.println("get76CanBadge메서드 진입");

		// 세션에서 현재 사용자 id정보를 받아옴
		HttpSession twitterLoginSession = request.getSession();

		long twitterId = (long) twitterLoginSession.getAttribute("twitterId");

		// 일단 멤버들 뿌려줄거 - 이 결과로 나오는건 멤버 테이블의 이름과, pk / 이미지 테이블의 icon. 총 3개의 컬럼이 나옴
		String MemberSql = "select hm.m_pk, hm.m_name, hi.i_icon from haco_member hm, haco_image hi where hm.m_pk = hi.i_m_pk";

		// icon, 멤버이름, 멤버pk, 유저트위터id, 굿즈수량 등 실제로 필요한 정보들 - DTO에 모아서 정리할 값들임
		String sql = "SELECT hg.*, hi.i_icon, hm.m_name from haco_goods hg, haco_member hm, haco_image hi WHERE hi.i_m_pk = hg.g_m_pk and hi.i_m_pk = hm.m_pk and g_u_t_id = ? and g_category ='76mmCanBadge'";

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
				haco_member.setG_category("76mmCanBadge");
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
			request.setAttribute("canBadge76mmInfos", haco_members);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public static void getAkuki(HttpServletRequest request) {

		System.out.println("getAkuki메서드 진입");

		// 세션에서 현재 사용자 id정보를 받아옴
		HttpSession twitterLoginSession = request.getSession();

		long twitterId = (long) twitterLoginSession.getAttribute("twitterId");

		// 일단 멤버들 뿌려줄거 - 이 결과로 나오는건 멤버 테이블의 이름과, pk / 이미지 테이블의 icon. 총 3개의 컬럼이 나옴
		String MemberSql = "select hm.m_pk, hm.m_name, hi.i_icon from haco_member hm, haco_image hi where hm.m_pk = hi.i_m_pk";

		// icon, 멤버이름, 멤버pk, 유저트위터id, 굿즈수량 등 실제로 필요한 정보들 - DTO에 모아서 정리할 값들임
		String sql = "SELECT hg.*, hi.i_icon, hm.m_name from haco_goods hg, haco_member hm, haco_image hi WHERE hi.i_m_pk = hg.g_m_pk and hi.i_m_pk = hm.m_pk and g_u_t_id = ? and g_category ='akuki'";

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
				haco_member.setG_category("akuki");
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
			request.setAttribute("akukiInfos", haco_members);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public static void getCoaster(HttpServletRequest request) {

		System.out.println("getCoaster메서드 진입");

		// 세션에서 현재 사용자 id정보를 받아옴
		HttpSession twitterLoginSession = request.getSession();

		long twitterId = (long) twitterLoginSession.getAttribute("twitterId");

		// 일단 멤버들 뿌려줄거 - 이 결과로 나오는건 멤버 테이블의 이름과, pk / 이미지 테이블의 icon. 총 3개의 컬럼이 나옴
		String MemberSql = "select hm.m_pk, hm.m_name, hi.i_icon from haco_member hm, haco_image hi where hm.m_pk = hi.i_m_pk";

		// icon, 멤버이름, 멤버pk, 유저트위터id, 굿즈수량 등 실제로 필요한 정보들 - DTO에 모아서 정리할 값들임
		String sql = "SELECT hg.*, hi.i_icon, hm.m_name from haco_goods hg, haco_member hm, haco_image hi WHERE hi.i_m_pk = hg.g_m_pk and hi.i_m_pk = hm.m_pk and g_u_t_id = ? and g_category ='coaster'";

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
				haco_member.setG_category("coaster");
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

			request.setAttribute("coasterInfos", haco_members);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public static void getOmoideCyeki(HttpServletRequest request) {

		System.out.println("getOmoideCyeki메서드 진입");

		// 세션에서 현재 사용자 id정보를 받아옴
		HttpSession twitterLoginSession = request.getSession();

		long twitterId = (long) twitterLoginSession.getAttribute("twitterId");

		// 일단 멤버들 뿌려줄거 - 이 결과로 나오는건 멤버 테이블의 이름과, pk / 이미지 테이블의 icon. 총 3개의 컬럼이 나옴
		String MemberSql = "select hm.m_pk, hm.m_name, hi.i_icon from haco_member hm, haco_image hi where hm.m_pk = hi.i_m_pk";

		// icon, 멤버이름, 멤버pk, 유저트위터id, 굿즈수량 등 실제로 필요한 정보들 - DTO에 모아서 정리할 값들임
		String sql = "SELECT hg.*, hi.i_icon, hm.m_name from haco_goods hg, haco_member hm, haco_image hi WHERE hi.i_m_pk = hg.g_m_pk and hi.i_m_pk = hm.m_pk and g_u_t_id = ? and g_category ='omoideCyeki'";

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
				haco_member.setG_category("omoideCyeki");
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

			request.setAttribute("omoideCyekiInfos", haco_members);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public static void getDmmMiniShikishi(HttpServletRequest request) {

		System.out.println("getDmmMiniShikishi메서드 진입");

		// 세션에서 현재 사용자 id정보를 받아옴
		HttpSession twitterLoginSession = request.getSession();

		long twitterId = (long) twitterLoginSession.getAttribute("twitterId");

		// 일단 멤버들 뿌려줄거 - 이 결과로 나오는건 멤버 테이블의 이름과, pk / 이미지 테이블의 icon. 총 3개의 컬럼이 나옴
		String MemberSql = "select hm.m_pk, hm.m_name, hi.i_icon from haco_member hm, haco_image hi where hm.m_pk = hi.i_m_pk";

		// icon, 멤버이름, 멤버pk, 유저트위터id, 굿즈수량 등 실제로 필요한 정보들 - DTO에 모아서 정리할 값들임
		String sql = "SELECT hg.*, hi.i_icon, hm.m_name from haco_goods hg, haco_member hm, haco_image hi WHERE hi.i_m_pk = hg.g_m_pk and hi.i_m_pk = hm.m_pk and g_u_t_id = ? and g_category ='dmmMiniShikishi'";

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
				haco_member.setG_category("dmmMiniShikishi");
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

			request.setAttribute("dmmMiniShikishiInfos", haco_members);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public static void getDmm57CanBadge(HttpServletRequest request) {

		System.out.println("getDmm57CanBadge메서드 진입");

		// 세션에서 현재 사용자 id정보를 받아옴
		HttpSession twitterLoginSession = request.getSession();

		long twitterId = (long) twitterLoginSession.getAttribute("twitterId");

		// 일단 멤버들 뿌려줄거 - 이 결과로 나오는건 멤버 테이블의 이름과, pk / 이미지 테이블의 icon. 총 3개의 컬럼이 나옴
		String MemberSql = "select hm.m_pk, hm.m_name, hi.i_icon from haco_member hm, haco_image hi where hm.m_pk = hi.i_m_pk";

		// icon, 멤버이름, 멤버pk, 유저트위터id, 굿즈수량 등 실제로 필요한 정보들 - DTO에 모아서 정리할 값들임
		String sql = "SELECT hg.*, hi.i_icon, hm.m_name from haco_goods hg, haco_member hm, haco_image hi WHERE hi.i_m_pk = hg.g_m_pk and hi.i_m_pk = hm.m_pk and g_u_t_id = ? and g_category ='dmm57CanBadge'";

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
				haco_member.setG_category("dmm57CanBadge");
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

			request.setAttribute("dmm57CanBadgeInfos", haco_members);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public static void getDmmMiniAkusuta(HttpServletRequest request) {

		System.out.println("getDmmMiniAkusuta메서드 진입");

		// 세션에서 현재 사용자 id정보를 받아옴
		HttpSession twitterLoginSession = request.getSession();

		long twitterId = (long) twitterLoginSession.getAttribute("twitterId");

		// 일단 멤버들 뿌려줄거 - 이 결과로 나오는건 멤버 테이블의 이름과, pk / 이미지 테이블의 icon. 총 3개의 컬럼이 나옴
		String MemberSql = "select hm.m_pk, hm.m_name, hi.i_icon from haco_member hm, haco_image hi where hm.m_pk = hi.i_m_pk";

		// icon, 멤버이름, 멤버pk, 유저트위터id, 굿즈수량 등 실제로 필요한 정보들 - DTO에 모아서 정리할 값들임
		String sql = "SELECT hg.*, hi.i_icon, hm.m_name from haco_goods hg, haco_member hm, haco_image hi WHERE hi.i_m_pk = hg.g_m_pk and hi.i_m_pk = hm.m_pk and g_u_t_id = ? and g_category ='dmmMiniAkusuta'";

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
				haco_member.setG_category("dmmMiniAkusuta");
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

			request.setAttribute("dmmMiniAkusutaInfos", haco_members);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public static void getDmmCyeki(HttpServletRequest request) {

		System.out.println("getDmmCyeki메서드 진입");

		// 세션에서 현재 사용자 id정보를 받아옴
		HttpSession twitterLoginSession = request.getSession();

		long twitterId = (long) twitterLoginSession.getAttribute("twitterId");

		// 일단 멤버들 뿌려줄거 - 이 결과로 나오는건 멤버 테이블의 이름과, pk / 이미지 테이블의 icon. 총 3개의 컬럼이 나옴
		String MemberSql = "select hm.m_pk, hm.m_name, hi.i_icon from haco_member hm, haco_image hi where hm.m_pk = hi.i_m_pk";

		// icon, 멤버이름, 멤버pk, 유저트위터id, 굿즈수량 등 실제로 필요한 정보들 - DTO에 모아서 정리할 값들임
		String sql = "SELECT hg.*, hi.i_icon, hm.m_name from haco_goods hg, haco_member hm, haco_image hi WHERE hi.i_m_pk = hg.g_m_pk and hi.i_m_pk = hm.m_pk and g_u_t_id = ? and g_category ='dmmCyeki'";

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
				haco_member.setG_category("dmmCyeki");
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

			request.setAttribute("dmmCyekiInfos", haco_members);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

}
