package com.vg.jw.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.vg.ds.trade.TradeDAO;
import com.vg.jw.AccountDAO;

@WebServlet("/ArticleC")
public class ArticleC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("ArticleC doGet 진입");
		TradeDAO.TDAO.selectAllTrade2(request);
		TradeDAO.TDAO.selectTradeComments(request);
		//int p = Integer.parseInt(request.getParameter("p"));
		TradeDAO.TDAO.paging(1, request);
		TradeDAO.TDAO.tradeCheckboxList(request);	
		System.out.println("Article로직 처리 완료");
		request.getRequestDispatcher("account/mypage/mypage_article.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
