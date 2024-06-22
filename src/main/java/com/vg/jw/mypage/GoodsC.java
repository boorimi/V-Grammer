package com.vg.jw.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@WebServlet("/GoodsC")
public class GoodsC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("GoodsC get 진입");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("굿즈 컨트롤러 post 진입");
		int g_m_pk = Integer.parseInt(request.getParameter("g_m_pk"));
		int g_count = Integer.parseInt(request.getParameter("g_count"));
		long u_twitter_id = Long.parseLong(request.getParameter("u_twitter_id"));
		String g_category = request.getParameter("g_category");
		int previousCount = Integer.parseInt(request.getParameter("previousCount"));
		
		
		System.out.println("받아온 멤버 pk:"+g_m_pk);
		System.out.println("받아온 굿즈 수량:" + g_count);
		System.out.println("받아온 유저 id:" + u_twitter_id);
		System.out.println("받아온 카테고리:" + g_category);
		System.out.println("받아온 변경 전 value:" + previousCount);
		
		
		MyPageDAO.updateGoods(request, response, g_m_pk, g_count, u_twitter_id, g_category, previousCount);
	}

}
