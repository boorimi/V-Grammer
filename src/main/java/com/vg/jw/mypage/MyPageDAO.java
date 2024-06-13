package com.vg.jw.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.vg.ignore.DBManager;
import com.vg.jw.AccountDTO;

public class MyPageDAO {

	public static void getBromide(HttpServletRequest request) {
		
		System.out.println("getBromide메서드 진입");
		
		//세션에서 현재 사용자 id정보를 받아옴
		HttpSession twitterLoginSession = request.getSession();	
		AccountDTO accountInfo = (AccountDTO) twitterLoginSession.getAttribute("accountInfo");		
		long twitterId = accountInfo.getU_twitter_id();
		System.out.println("getBromide에서 트위터 아이디 : " + twitterId);
		
		String sql = "SELECT hg.*, hi.i_icon, hm.m_name from haco_goods hg, haco_member hm, haco_image hi WHERE hi.i_m_pk = hg.g_m_pk and hi.i_m_pk = hm.m_pk and g_u_t_id = ? and g_category ='bromide'";
				
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<GoodsDTO> bromideInfos = new ArrayList<GoodsDTO>();
		GoodsDTO bromideInfo = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, twitterId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bromideInfo = new GoodsDTO(rs.getInt("g_m_pk"), rs.getString("g_category"), rs.getInt("g_count"), rs.getString("i_icon"), rs.getString("m_name"));
				
				bromideInfos.add(bromideInfo);	
			}

			
			request.setAttribute("bromideInfos", bromideInfos);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		
	}

}
