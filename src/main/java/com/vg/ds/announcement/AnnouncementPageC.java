package com.vg.ds.announcement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AnnouncementPage")
public class AnnouncementPageC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AnnouncementDAO.ADAO.selectAllAnnouncement(request);
		int p = Integer.parseInt(request.getParameter("p"));
		AnnouncementDAO.ADAO.paging(p, request);
//		request.setAttribute("content", "jsp/review/review.jsp");
		request.getRequestDispatcher("announcement/announcement.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
