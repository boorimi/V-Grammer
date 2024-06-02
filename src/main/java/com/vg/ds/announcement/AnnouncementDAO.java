package com.vg.ds.announcement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.vg.ds.announcement.DBManager;

public class AnnouncementDAO {

	private ArrayList<AnnouncementDTO> announcements = null;
	private Connection con = null;
	public static final AnnouncementDAO ADAO = new AnnouncementDAO();

	private AnnouncementDAO() {
		try {
			con = DBManager.connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void paging(int page, HttpServletRequest request) {

		int cnt = 20; // 한페이지당 보여줄 개수
		int total = announcements.size(); // 총 데이터 개수

		// 총 페이지 수 , 곧 마지막 페이지
		int pageCount = (int) (Math.ceil((double) total / cnt));
		// 시작, 끝

		int start = total - (cnt * (page - 1));
		int end = (page == pageCount) ? -1 : start - (cnt + 1);

		ArrayList<AnnouncementDTO> items = new ArrayList<AnnouncementDTO>();
		for (int i = start - 1; i > end; i--) {
			items.add(announcements.get(i));
		}
		request.setAttribute("curPageNo", page);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("announcements", items);
	}

	public void selectAllAnnouncement(HttpServletRequest request) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "select * from haco_announcement order by a_date";

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			announcements = new ArrayList<AnnouncementDTO>();

			while (rs.next()) {
				String pk = rs.getString(1);
				String twitterId = rs.getString(2);
				String title = rs.getString(3);
				String txt = rs.getString(4);
				String date = rs.getString(5);

				AnnouncementDTO a = new AnnouncementDTO(pk, twitterId, title, txt, date);
				announcements.add(a);

			}
			request.setAttribute("announcements", announcements);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public void selectAnnouncement(HttpServletRequest request) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "select * from haco_announcement where a_pk = ?";
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));
			rs = pstmt.executeQuery();
			rs.next();

			String pk = rs.getString(1);
			String twitterId = rs.getString(2);
			String title = rs.getString(3);
			String text = rs.getString(4);
			String date = rs.getString(5);

			AnnouncementDTO a = new AnnouncementDTO(pk, twitterId, title, text, date);

			request.setAttribute("announcements", a);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public void insertAnnouncement(HttpServletRequest request) {

		PreparedStatement pstmt = null;

		try {

			String sql = "insert into haco_announcement values (null, 'KOR_JABIRAN', ?, ?, NOW())";
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("title"));
			pstmt.setString(2, request.getParameter("text"));

			if (pstmt.executeUpdate() == 1) {
				System.out.println("입력 성공!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

	public void deleteAnnouncement(HttpServletRequest request) {

		PreparedStatement pstmt = null;

		try {

			String sql = "delete from haco_announcement where a_pk = ?";
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));

			if (pstmt.executeUpdate() == 1) {
				System.out.println("삭제 성공!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

	public void updateAnnouncement(HttpServletRequest request) {

		PreparedStatement pstmt = null;

		try {

			String sql = "update haco_announcement set a_title = ?, a_text = ? where a_pk = ?";
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("title"));
			pstmt.setString(2, request.getParameter("text"));
			pstmt.setString(3, request.getParameter("no"));

			if (pstmt.executeUpdate() == 1) {
				System.out.println("수정 성공!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

}
