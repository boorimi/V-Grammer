package com.vg.bm.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.vg.ignore.DBManager;

public class MemberDAO {
	private ArrayList<MemberDTO> members;
	private Connection con = null;
	public static final MemberDAO MDAO = new MemberDAO();
	
	public MemberDAO() {
		try {
			con = DBManager.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void getAllMember(HttpServletRequest request) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from haco_member";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			members = new ArrayList<MemberDTO>();
			
			while (rs.next()) {
				MemberDTO member = new MemberDTO(rs.getString(1),
						rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
				members.add(member);
			}
//			System.out.println(members);
			request.setAttribute("members", members);
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	
	

}
