package com.vg.jw;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.vg.ignore.DBManager;

public class AccountDAO {

	public static void registerUser(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		//트위터 계정정보 가져오기 
		HttpSession twitterSession = request.getSession();
		
		String twitterId = (String) twitterSession.getAttribute("twitterId");
		String twitterScreenName = (String) twitterSession.getAttribute("twitterScreenName");		
		System.out.println("AccountDAO테스트 출력(twitterId):"+ twitterId);
		System.out.println("AccountDAO테스트 출력(twitterScreenName):"+ twitterScreenName);
		
		//등록 페이지 input 값 가져오기
		String inputId = request.getParameter("register-input-id");
		String inputPw = request.getParameter("register-input-pw");
		String inputNickname = request.getParameter("register-input-nickname");
		
		System.out.println(inputId);
		System.out.println(inputPw);
		System.out.println(inputNickname);
		
		String sql = "INSERT INTO haco_user values(?, ?, ?, ?, 1)";
		try {
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputId);
			pstmt.setString(2, inputPw);
			pstmt.setString(3, twitterId);
			pstmt.setString(4, inputNickname);
			
			if (pstmt.executeUpdate() >= 1) {
				System.out.println("유저 등록 성공");
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		
		
		
		
	}

}
