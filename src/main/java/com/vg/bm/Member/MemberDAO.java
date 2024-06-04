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
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public void getMember(HttpServletRequest request) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql1 = "select * from haco_member where m_pk=?";
		String sql2 = "select * from haco_hashtag where h_m_pk=?";
		String sql3 = "select * from haco_address a_m_pk=?";
		String sql4 = "select * from haco_image";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql4);
			rs = pstmt.executeQuery();
			
//			String path = request.getServletContext().getRealPath("haco_img/");
//			MultipartRequest mr = new MultipartRequest(request, path, 
//						1024*1024*20, "utf-8", new DefaultFileRenamePolicy());
			
			images = new ArrayList<ImageDTO>();
			while (rs.next()) {
				ImageDTO img = new ImageDTO();
				img.setI_m_pk(rs.getString("i_m_pk"));
				img.setI_icon("haco_img/icon/"+rs.getString("i_icon")+".png");
				img.setI_pic("haco_img/img/"+rs.getString("i_img")+".png");
				img.setI_3side("haco_img/3sides/"+rs.getString("i_3side")+".png");
				img.setI_background("haco_img/background/"+rs.getString("i_background")+".png");
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
	
	
	

}
