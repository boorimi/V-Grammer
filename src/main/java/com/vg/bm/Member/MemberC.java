package com.vg.bm.Member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vg.jw.AccountDAO;

@WebServlet("/MemberC")
public class MemberC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AccountDAO.loginCheck(request);
		MemberDAO.MDAO.getAllMember(request);
		
		request.setAttribute("content", "member/member.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response); 
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
