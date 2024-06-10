package com.vg.kw.main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vg.jw.AccountDAO;

@WebServlet("/HC")
public class HC extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AccountDAO.loginCheck(request);
		HomeDAO.HDAO.getStreamId(request);
		HomeDAO.HDAO.getRecommendVtuber(request);
//		GetAllStream.getAllLive();
		request.setAttribute("content", "mainpage/main.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
