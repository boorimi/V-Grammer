package com.vg.ds.trade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.vg.ignore.DBManager;
import com.vg.jw.mypage.GoodsDTO;

public class Test {
	public static void main(String[] args) {

		System.out.println("getBromide메서드 진입");

		// 세션에서 현재 사용자 id정보를 받아옴

		long twitterId = 1797433642438029312L;

		String MemberSql = "select hm.m_pk, hm.m_name, hi.i_icon from haco_member hm, haco_image hi where hm.m_pk = hi.i_m_pk";
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
			while (rs.next()) { // 25개 생성
				haco_member = new GoodsDTO();
				haco_member.setU_twitter_id(twitterId);
				haco_member.setG_m_pk(rs.getInt(1));
				haco_member.setM_name(rs.getString(2));
				haco_member.setI_icon(rs.getString(3));
				haco_member.setG_category("bromide");
				haco_members.add(haco_member);
				hacoMemberMap.put(rs.getInt(1), haco_member); // Map에 추가
			}
			pstmt.close();
			rs.close();

			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, twitterId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int g_m_pk = rs.getInt("g_m_pk");
				int g_count = rs.getInt("g_count");

				if (hacoMemberMap.containsKey(g_m_pk)) {
					haco_member = hacoMemberMap.get(g_m_pk);
					haco_member.setG_count(g_count); // g_count 업데이트
				}
			}
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
//			request.setAttribute("bromideInfos", bromideInfos);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}
}
