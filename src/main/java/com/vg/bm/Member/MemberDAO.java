package com.vg.bm.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

	// 멤버 정보 호출 메서드
	// 해당 메서드로 4개의 테이블 정보 한 번에 가져옴.
	public void getAllMember(HttpServletRequest request) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		// 멤버와 멤버이미지 테이블을 보여주는 sql문 한 번에 작성.
		String sql = "select hm.m_pk, hm.m_name,hm.m_gen,hm.m_birth,hm.m_debut,hm.m_mother_name,\r\n"
				+ "hm.m_mother_twitter,hm.m_introduce,\r\n"
				+ "hi.i_icon,hi.i_img,hi.i_3side,hi.i_background\r\n"
				+ "from haco_member hm, haco_image hi\r\n"
				+ "where hm.m_pk = hi.i_m_pk\r\n"
				+ "order by hm.m_pk;";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			members = new ArrayList<MemberDTO>();
			AddressDTO addressDTO = null;
			ArrayList<AddressDTO> addrs = null;
			HashTagDTO hashTagDTO = null;
			ArrayList<HashTagDTO> hashTag = null;
			
			// string으로 저장된 생일날짜를 date(MM-dd) 형식으로 바꿔주는 코드(하단 첫번째 try catch문 까지)
			SimpleDateFormat oldBirth = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat newBirth = new SimpleDateFormat("MM-dd");

			// 현재 데뷔한 멤버 21명으로, while문이 아닌 for문으로 21번째 멤버까지만 출력되도록.
			// 추후 나머지 4명 모두 데뷔한다면 while로 변경하면 됨.
			// while (rs.next())
			for (int i = 0; i < 21 && rs.next(); i++) {
				String birth = rs.getString(4);
				Date birth2 = null;
				String birth3 = "";
				
				try {
					birth2 = oldBirth.parse(birth);
					birth3 = newBirth.format(birth2);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// 멤버와 멤버이미지 테이블의 정보를 합쳐서 MemberDTO에 생성.
				MemberDTO member = new MemberDTO(rs.getString(1),rs.getString(2),
						rs.getString(3), birth3 ,rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),
						addrs, hashTag);
																	// 멤버 pk
				sql = "select * from haco_address where a_m_pk=" + rs.getString(1);
				pstmt = con.prepareStatement(sql);
				rs2 = pstmt.executeQuery();
				
				// 멤버 address 테이블 정보 추가
				addrs = new ArrayList<AddressDTO>();
				while (rs2.next()) {
					addressDTO = new AddressDTO(rs2.getString(1), rs2.getString(3), rs2.getString(4));
					addrs.add(addressDTO);
				}
//				System.out.println(addrs);
				member.setAddress(addrs);

				// 멤버 hashTag 테이블 정보 추가
				sql = "select * from haco_hashtag where h_m_pk=" + rs.getString(1);
				pstmt = con.prepareStatement(sql);
				rs3 = pstmt.executeQuery();
				
				
				hashTag = new ArrayList<HashTagDTO>();
				while (rs3.next()) {
					// <a>트위터 해시태그 페이지로 넘어가기 위해 #,＃을 %23으로 replace함
					String replaceHashTag = rs3.getString(4);
					if (replaceHashTag.contains("#")) {
						replaceHashTag = replaceHashTag.replace("#", "%23");
					} else if (replaceHashTag.contains("＃")) {
						replaceHashTag = replaceHashTag.replace("＃", "%23");
					}
					System.out.println("해시태그 : "+replaceHashTag);
					
					hashTagDTO = new HashTagDTO(rs3.getString(1),rs3.getString(3),replaceHashTag);
					hashTag.add(hashTagDTO);
				}
				
				member.setHashTag(hashTag);
				members.add(member);
				
			}
//			System.out.println("~~~~~~~~~~~~~~~~~");
//			System.out.println(members);
//			System.out.println("~~~~~~~~~~~~~~~~~");

			request.setAttribute("members", members);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}



	
}
