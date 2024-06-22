package com.vg.jw.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vg.jw.AccountDAO;

@WebServlet("/UserInfoC")
public class UserInfoC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserInfoC get진입");
		System.out.println(request.getParameter("userId"));
		
	    
		AccountDAO.deleteUser(request,response);
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("UserInfoC post 진입");
		MyPageDAO.changeNickname(request);
		
		
	}

}
