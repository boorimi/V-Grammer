package com.vg.jw.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vg.ds.trade.TradeDAO;
import com.vg.jw.AccountDAO;

@WebServlet("/ArticleC")
public class ArticleC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		TradeDAO.TDAO.selectAllTrade(request);
		TradeDAO.TDAO.selectTradeComments(request);
		TradeDAO.TDAO.paging(1, request);
		TradeDAO.TDAO.tradeCheckboxList(request);
		AccountDAO.loginCheck(request);

		// JSP 페이지를 포워딩하여 응답으로 반환
		request.getRequestDispatcher("trade/trade.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
