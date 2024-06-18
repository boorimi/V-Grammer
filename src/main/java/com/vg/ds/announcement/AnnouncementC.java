package com.vg.ds.announcement;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vg.jw.AccountDAO;

@WebServlet("/Announcement")
public class AnnouncementC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AnnouncementDAO.ADAO.selectAllAnnouncement(request);
        AnnouncementDAO.ADAO.paging(1, request);
        AccountDAO.loginCheck(request);

        // 로그 출력
        ArrayList<AnnouncementDTO> announcements = (ArrayList<AnnouncementDTO>) request.getAttribute("announcements");
        if (announcements != null) {
            System.out.println("Announcements size: " + announcements.size());
            for (AnnouncementDTO a : announcements) {
                System.out.println(a.toString());
            }
        } else {
            System.out.println("Announcements is null");
        }

        request.setAttribute("content", "mainpage/main.jsp");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
