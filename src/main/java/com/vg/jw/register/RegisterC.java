package com.vg.jw.register;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vg.jw.AccountDAO;

@WebServlet("/RegisterC")
public class RegisterC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Register컨트롤러 get진입");
		request.setAttribute("content", "account/register/register_page.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		System.out.println("Register컨트롤러 post진입");
		request.setCharacterEncoding("utf-8");
		
		
		AccountDAO.registerUser(request);
	}

}
