package com.vg.jw;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.vg.ignore.DBManager;

import twitter4j.Twitter;
import twitter4j.User;

public class AccountDAO {

	public static void registerUser(HttpServletRequest request) throws IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		//트위터 계정정보 가져오기 
		HttpSession twitterSession = request.getSession();
		
		long twitterId = (long) twitterSession.getAttribute("twitterId");
		String twitterScreenName = (String) twitterSession.getAttribute("twitterScreenName");		
		System.out.println("AccountDAO테스트 출력(twitterId):"+ twitterId);
		System.out.println("AccountDAO테스트 출력(twitterScreenName):"+ twitterScreenName);
		
		//사진받을 준비
		String path = request.getServletContext().getRealPath("account/profileImg");

		// 파일처리
		MultipartRequest mr = new MultipartRequest(request, path, 1024 * 1024 * 10, "utf-8",
				new DefaultFileRenamePolicy());
		
		String inputId = mr.getParameter("register-input-id");
		String inputPw = mr.getParameter("register-input-pw");
		String inputNickname = mr.getParameter("register-input-nickname");
		
		String inputFile = mr.getFilesystemName("register-input-file");
		
		System.out.println(inputId);
		System.out.println(inputPw);
		System.out.println(inputNickname);
		System.out.println(inputFile);
		
		String sql = "INSERT INTO haco_user values(?, ?, ?, ?, 1, ?)";
		
		try {
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputId);
			pstmt.setString(2, inputPw);
			pstmt.setLong(3, twitterId);
			pstmt.setString(4, inputNickname);
			pstmt.setString(5, inputFile);
			
			if (pstmt.executeUpdate() >= 1) {
				System.out.println("유저 등록 성공");
				
				System.out.println("어디로 갈지 설정");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		
		
	}

}
