package com.vg.ds.announcement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Announcement")
public class AnnouncementC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AnnouncementDAO.ADAO.selectAllAnnouncement(request);
//		int p = Integer.parseInt(request.getParameter("p"));
		AnnouncementDAO.ADAO.paging(1, request);
//		request.setAttribute("content", "jsp/review/review.jsp");
		request.setAttribute("content", "announcement/announcement.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
