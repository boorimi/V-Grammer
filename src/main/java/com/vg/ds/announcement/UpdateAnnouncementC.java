package com.vg.ds.announcement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateAnnouncement")
public class UpdateAnnouncementC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AnnouncementDAO.ADAO.selectAnnouncement(request);
		request.getRequestDispatcher("announcement/announcement_insert.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		AnnouncementDAO.ADAO.updateAnnouncement(request);
		response.sendRedirect("Announcement");
	}

}
