package com.vg.ds.announcement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SelectAnnouncement")
public class SelectAnnouncementC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AnnouncementDAO.ADAO.selectAnnouncement(request);
//		request.setAttribute("content", "jsp/review/review_detail.jsp");
		request.getRequestDispatcher("announcement/announcement_detail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}