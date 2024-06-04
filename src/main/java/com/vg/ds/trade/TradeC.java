package com.vg.ds.trade;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vg.ds.announcement.AnnouncementDAO;

@WebServlet("/Trade")
public class TradeC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TradeDAO.TDAO.selectAllTrade(request);
		TradeDAO.TDAO.selectTradeComments(request);
//		int p = Integer.parseInt(request.getParameter("p"));
		TradeDAO.TDAO.paging(1, request);
		request.setAttribute("content", "trade/trade.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
