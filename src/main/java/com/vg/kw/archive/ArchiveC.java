package com.vg.kw.archive;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vg.ds.trade.TradeDAO;
import com.vg.jw.AccountDAO;


@WebServlet("/ArchiveC")
public class ArchiveC extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArchiveDAO.selectAllArchive(request);
//		int p = Integer.parseInt(request.getParameter("p"));
//		ArchiveDAO.paging(1, request);
		ArchiveDAO.getCountArchive(request);
		AccountDAO.loginCheck(request);
		request.setAttribute("content", "archive/archive.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ArchiveDAO.getAnotherPage(request, response);
	}

}
