package com.vg.jw.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.vg.ignore.DBManager;
import com.vg.jw.AccountDTO;

public class templogin {

	public static void login(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String inputId = request.getParameter("temploginID");
		long twitterId = 0;
		String sql = "SELECT*FROM haco_user WHERE u_twitter_id = ?";
		String dbId ="";
		AccountDTO accountInfo = null;
		try {

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputId);
			rs = pstmt.executeQuery();

			String loginResult = "";
			if (rs.next()) {
				dbId = rs.getString("u_twitter_id");
				if (inputId.equals(dbId)) {
			
				System.out.println("로그인 성공");
				loginResult = "ログイン成功";

				twitterId = rs.getLong("u_twitter_id");
				// 계정 정보들 저장
				accountInfo = new AccountDTO();
				accountInfo.setU_twitter_id(twitterId);
				accountInfo.setU_nickname(rs.getString("u_nickname"));
				accountInfo.setU_screenName(rs.getString("u_screenname"));

				// 로그인 세션 생성
				HttpSession loginSession = request.getSession();
				loginSession.setAttribute("accountInfo", accountInfo);
				loginSession.setMaxInactiveInterval(60 * 10);
				
				}

			}
			System.out.println("트위터id: " + accountInfo.getU_twitter_id());
			System.out.println("닉네임 : " + accountInfo.getU_nickname());
			System.out.println(accountInfo);

			request.setAttribute("loginResult", loginResult);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

}
