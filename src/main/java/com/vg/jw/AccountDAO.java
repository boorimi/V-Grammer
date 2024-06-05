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
		
		HttpSession twitterSession = request.getSession();
		
		
		
		String twitterId = (String) twitterSession.getAttribute("twitterId");
		String twitterScreenName = (String) twitterSession.getAttribute("twitterScreenName");		
		System.out.println("AccountDAO(twitterId):"+ twitterId);
		System.out.println("AccountDAO(twitterScreenName):"+ twitterScreenName);
		
		try {
			
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		
		
		
		
	}

}
