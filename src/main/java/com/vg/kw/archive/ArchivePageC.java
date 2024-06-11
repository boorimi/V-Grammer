package com.vg.kw.archive;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vg.ds.trade.TradeDAO;
import com.vg.jw.AccountDAO;


@WebServlet("/ArchivePageC")
public class ArchivePageC extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArchiveDAO.selectAllArchive(request);
		int p = Integer.parseInt(request.getParameter("p"));
		ArchiveDAO.paging(p, request);
		AccountDAO.loginCheck(request);
		request.setAttribute("content", "archive/archive.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
