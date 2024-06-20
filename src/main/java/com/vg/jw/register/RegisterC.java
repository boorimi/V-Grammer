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
		//회원등록 페이지 이동과 유효성 검사에 이용되는 부분
		System.out.println("Register컨트롤러 get진입");
		AccountDAO.loginCheck(request);
		
		//파라미터가 있으면 닉네임 유효성 검사
		if (request.getParameterNames().hasMoreElements()) {
			System.out.println("닉네임 유효성 검사 분기로 진입");
			AccountDAO.nickNameCheck(request, response);
			
			
		} else {//들고오는 파라미터 없으면 등록페이지로 이동

			request.setAttribute("content", "account/register/register_page.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//신규유저가 DB에 등록되는 부분
		request.setCharacterEncoding("utf-8");
		System.out.println("Register컨트롤러 post진입");
		System.out.println("이거 전달됨? : "+request.getParameter("inputNickName"));
		
		AccountDAO.loginCheck(request);
		AccountDAO.registerUser(request);
		request.setAttribute("content", "mainpage/main.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
