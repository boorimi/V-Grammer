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
		System.out.println("로그인 컨트롤러 get 진입");
		AccountDAO.loginCheck(request);
		// 등록됐는지 체크해서
		
		System.out.println("회원정보 등록됐나 확인 완료");
		if (AccountDAO.registerCheck(request)) {
			System.out.println("LoginC에서 유저 등록 확인함");
			response.sendRedirect("HC"); // true반환시 메인페이지로
		} else {
			System.out.println("LoginC에서 유저 미등록 확인, 등록페이지로");
			response.sendRedirect("RegisterC"); // false반환시 등록페이지로
		}

		//임시 로그인 처리
		//templogin.login(request);
		//response.sendRedirect("HC");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("로그인 컨트롤러 post진입");
		AccountDAO.loginCheck(request);
		
		System.out.println("로컨 post에서 logincheck완료");
		request.setAttribute("content", "account/login/login_page.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
