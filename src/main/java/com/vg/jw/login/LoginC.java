package com.vg.jw.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vg.jw.AccountDAO;

@WebServlet("/LoginC")
public class LoginC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("content", "account/login/login_page.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("로그인 컨트롤러 post 진입");
		
		AccountDAO.login(request);
		request.setAttribute("content", "mainpage/main.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
	}

}
