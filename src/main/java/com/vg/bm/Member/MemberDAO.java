package com.vg.bm.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.vg.ignore.DBManager;

public class MemberDAO {
	private ArrayList<MemberDTO> members;
	private ArrayList<ImageDTO> images;
	private ArrayList<HashTagDTO> hashTags;
	private ArrayList<AddressDTO> address;
	private Connection con = null;
	public static final MemberDAO MDAO = new MemberDAO();

	public MemberDAO() {
		try {
			con = DBManager.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	// 멤버 기본 정보 호출 메서드
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
//			System.out.println(members.size());
			request.setAttribute("members", members);
//			request.setAttribute("member_pk", rs.getString(1));
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
	}
	
	//멤버 이미지 호출 메서드
	public void getMemberImg(HttpServletRequest request) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from haco_image";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
				
			images = new ArrayList<ImageDTO>();
			while (rs.next()) {
				ImageDTO img = new ImageDTO();
				img.setI_m_pk(rs.getString("i_m_pk"));
				img.setI_icon("haco_img/icon/"+rs.getString("i_icon"));
				img.setI_img("haco_img/img/"+rs.getString("i_img"));
				img.setI_3side("haco_img/3sides/"+rs.getString("i_3side"));
				img.setI_background("haco_img/background/"+rs.getString("i_background"));
				images.add(img);
			}
//			System.out.println("----images syso----");
//			System.out.println(images);
			
			request.setAttribute("images", images);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
	}

	// 멤버 해시태그 호출 메서드
	public void getMemberHashTag(HttpServletRequest request) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
//		members = new ArrayList<MemberDTO>();
		MemberDTO pk = members.get(1);
		System.out.println(pk);
		
		String sql = "select * from haco_address a_m_pk=?";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
	}

	// 멤버 주소 호출 메서드
//	public void getAdress(HttpServletRequest request) {
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		String sql = "select * from haco_hashtag where h_m_pk=?";
//		
//		try {
//			con = DBManager.connect();
//			pstmt = con.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//				
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			DBManager.close(con, pstmt, rs);
//		}
//	}
	
	

}
