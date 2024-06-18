package com.vg.kw.archive;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.github.scribejava.core.model.Response;
import com.twitter.joauth.Request;
import com.vg.ds.trade.TradeDTO;
import com.vg.ignore.DBManager;

public class ArchiveDAO {

	static ArrayList<ArchiveDTO> archives = null;

	public static void paging(int page, HttpServletRequest request) {

		int cnt = 20; // 한페이지당 보여줄 개수
		int total = archives.size(); // 총 데이터 개수

		// 총 페이지 수 , 곧 마지막 페이지
		int pageCount = (int) (Math.ceil((double) total / cnt));
		// 시작, 끝

		int start = total - (cnt * (page - 1));
		int end = (page == pageCount) ? -1 : start - (cnt + 1);

		ArrayList<ArchiveDTO> items = new ArrayList<ArchiveDTO>();
		for (int i = start - 1; i > end; i--) {
			items.add(archives.get(i));
		}
		request.setAttribute("curPageNo", page);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("archives", items);
		
	}

	public static void getCountArchive(int page, HttpServletRequest request, HttpServletResponse response) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select count(*) from haco_archive";
		try {

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();

			int total = rs.getInt(1);
			int cnt = 20; // 한페이지당 보여줄 개수
			// 총 페이지 수 , 곧 마지막 페이지
			int pageCount = (int) (Math.ceil((double) total / cnt));
			// 시작, 끝
			int start = total - (cnt * (page - 1));
			int end = (page == pageCount) ? -1 : start - (cnt + 1);

			request.setAttribute("curPageNo", page);
			request.setAttribute("pageCount", pageCount); // 총 페이지 수
			request.setAttribute("start", start);
			request.setAttribute("end", end);
			
			String resData = page+"!"+pageCount;
			response.setContentType("text/plain; charset=utf-8");
			response.getWriter().print(resData);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public static void getAnotherPage(HttpServletRequest request, HttpServletResponse response) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 비동기 검색할때 받는 변수
//		String member = "未分類";
//		String category = "未分類";
//		String title = "";
		String member = null;
		String category = null;
		String title = null;
		if (request.getParameter("member") != null && !request.getParameter("member").equals("未分類")) {
			member = request.getParameter("member");
		}
		if (request.getParameter("category") != null && !request.getParameter("category").equals("未分類")) {
			category = request.getParameter("category");
		}
		if (request.getParameter("title") != null && !request.getParameter("title").equals("")) {
			title = request.getParameter("title");
		}
//		if (member.equals("未分類")) {
//			member = null;
//		}
//		if (category.equals("未分類")) {
//			category = null;
//		}
//		if (title.equals("")) {
//			title = null;
//		}
		System.out.println(member);
		System.out.println(category);
		System.out.println(title);

		// 비동기 페이징 할때 받는 변수
		int page = 1;
		if (request.getParameter("page") != null ) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		String sql = "SELECT ha.*, hm.m_name, hi.i_icon ";
		sql += "from haco_archive ha, haco_member hm, haco_image hi ";
		sql += "where ha.a_m_pk = hm.m_pk and hi.i_m_pk = hm.m_pk ";
		sql += "and (? IS NULL OR hm.m_name = ?) ";
		sql += "and (? IS NULL OR ha.a_category = ?) ";
		sql += "and (? IS NULL OR ha.a_title = ?) ";
		sql += "order by a_date desc, a_time desc ";
		sql += "LIMIT ? OFFSET ?";

		try {

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member);
			pstmt.setString(2, member);
			pstmt.setString(3, category);
			pstmt.setString(4, category);
			pstmt.setString(5, title);
			pstmt.setString(6, title);
			pstmt.setInt(7, 20);
			pstmt.setInt(8, 20 * (page - 1));
			rs = pstmt.executeQuery();
			ArrayList<String> archives = new ArrayList<>();

			while (rs.next()) {
				ArchiveDTO archive = new ArchiveDTO();

				archive.setA_pk(rs.getInt(1));
				archive.setA_m_pk(rs.getInt(2));
				archive.setA_date(rs.getDate(3));
				archive.setA_time(rs.getTime(4));
				archive.setA_collabo(rs.getString(5));
				archive.setA_collabomember(rs.getString(6));
				archive.setA_category(rs.getString(7));
				archive.setA_title(rs.getString(8));
				archive.setA_thumbnail(rs.getString(9));
				archive.setA_videoid(rs.getString(10));
				archive.setM_name(rs.getString(11));
				archive.setI_icon(rs.getString(12));
				archives.add(archive.toJSON());

			}
			System.out.println(archives);
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(archives);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	public static void selectAllArchive(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int p = 0;
		if (req.getParameter("p") == null) {
			p = 1;
		} else {
			p = Integer.parseInt(req.getParameter("p"));
		}

		String sql = "SELECT ha.*, hm.m_name, hi.i_icon from haco_archive ha, haco_member hm, haco_image hi "
				+ "where ha.a_m_pk = hm.m_pk and hi.i_m_pk = hm.m_pk order by a_date desc, a_time desc "
				+ "LIMIT 20 offset ?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (p - 1) * 20);
			rs = pstmt.executeQuery();
			archives = new ArrayList<>();
			while (rs.next()) {
				ArchiveDTO archive = new ArchiveDTO();

				archive.setA_pk(rs.getInt(1));
				archive.setA_m_pk(rs.getInt(2));
				archive.setA_date(rs.getDate(3));
				archive.setA_time(rs.getTime(4));
				archive.setA_collabo(rs.getString(5));
				archive.setA_collabomember(rs.getString(6));
				archive.setA_category(rs.getString(7));
				archive.setA_title(rs.getString(8));
				archive.setA_thumbnail(rs.getString(9));
				archive.setA_videoid(rs.getString(10));
				archive.setM_name(rs.getString(11));
				archive.setI_icon(rs.getString(12));
				archives.add(archive);

			}
			req.setAttribute("archives", archives);
			System.out.println("Archives fetched: " + archives.size()); // 디버깅 메시지
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void selectOneArchive(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String pk = req.getParameter("pk");

		String sql = "SELECT ha.*, hm.m_name, hi.i_icon from haco_archive ha, haco_member hm, haco_image hi "
				+ "where ha.a_m_pk = hm.m_pk and hi.i_m_pk = hm.m_pk and ha.a_pk = ? "
				+ "order by a_date desc, a_time desc ";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pk);
			rs = pstmt.executeQuery();
			rs.next();
			ArchiveDTO archive = new ArchiveDTO();

			archive.setA_pk(rs.getInt(1));
			archive.setA_m_pk(rs.getInt(2));
			archive.setA_date(rs.getDate(3));
			archive.setA_time(rs.getTime(4));
			archive.setA_collabo(rs.getString(5));
			archive.setA_collabomember(rs.getString(6));
			archive.setA_category(rs.getString(7));
			archive.setA_title(rs.getString(8));
			archive.setA_thumbnail(rs.getString(9));
			archive.setA_videoid(rs.getString(10));
			archive.setM_name(rs.getString(11));
			archive.setI_icon(rs.getString(12));

			req.setAttribute("archive", archive);
			System.out.println("Archives fetched: " + archives.size()); // 디버깅 메시지
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void UpdateArchive(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;

		// Get parameters from request
		String collabo = request.getParameter("collabo");
		String collabomember = request.getParameter("collabomember");
		String category = request.getParameter("category");
		String a_pk = request.getParameter("a_pk"); // Unique identifier

		// Validate that necessary parameters are not null or empty
		if (collabo == null || collabomember == null || category == null || a_pk == null) {
			System.out.println("One or more parameters are missing.");
			return;
		}

		// Construct the SQL query
		String sql = "UPDATE haco_archive ha " + "JOIN haco_member hm ON ha.a_m_pk = hm.m_pk "
				+ "JOIN haco_image hi ON hi.i_m_pk = hm.m_pk " + "SET ha.a_collabo = ?, "
				+ "    ha.a_collabomember = ?, " + "    ha.a_category = ? " + "WHERE ha.a_pk = ?"; // Unique condition

		try {
			// Connect to the database
			con = DBManager.connect();
			// Prepare the SQL statement
			pstmt = con.prepareStatement(sql);
			// Set the parameters
			pstmt.setString(1, collabo);
			pstmt.setString(2, collabomember);
			pstmt.setString(3, category);
			pstmt.setString(4, a_pk); // Set the unique title

			// Execute the update
			int rowsUpdated = pstmt.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Update successful. Rows updated: " + rowsUpdated);
			} else {
				System.out.println("No rows updated. Check your condition.");
			}
		} catch (SQLException e) {
			// Handle SQL exception
			e.printStackTrace();
		} finally {
			// Close the database resources
			DBManager.close(con, pstmt, null);
		}
	}

	public static void getSearchResult(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "SELECT ha.*, hm.m_name, hi.i_icon from haco_archive ha, haco_member hm, haco_image hi "
				+ "where ha.a_m_pk = hm.m_pk and hi.i_m_pk = hm.m_pk " + "order by a_date desc, a_time desc "
				+ "LIMIT 20 offset ?";

	}

}
