package com.vg.kw.archive;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vg.jw.AccountDAO;

@WebServlet("/ArchiveUpdateC")
public class ArchiveUpdateC extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArchiveDAO.selectOneArchive(request,response);
//		AccountDAO.loginCheck(request);
//		request.setAttribute("content", "archive/archiveupdate.jsp");
//		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
//		AccountDAO.loginCheck(request);
		ArchiveDAO.UpdateArchive(request,response);
//		ArchiveDAO.selectAllArchive(request);
//		AccountDAO.loginCheck(request);
//		request.setAttribute("content", "archive/archiveupdate.jsp");
//		request.getRequestDispatcher("index.jsp").forward(request, response);
//		response.sendRedirect("ArchiveC");
//		ArchiveDAO.getAnotherPage(request, response);

	}

}
