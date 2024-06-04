package com.vg.ds.trade;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TradePage")
public class TradePageC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TradeDAO.TDAO.selectAllTrade(request);
		int p = Integer.parseInt(request.getParameter("p"));
		TradeDAO.TDAO.paging(p, request);
//		request.setAttribute("content", "jsp/review/review.jsp");
		request.setAttribute("content", "trade/trade.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
