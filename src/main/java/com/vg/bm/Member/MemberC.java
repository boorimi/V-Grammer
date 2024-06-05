package com.vg.bm.Member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MemberC")
public class MemberC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDAO.MDAO.getAllMember(request);
		request.getAttribute("member_pk");
//		MemberDAO.MDAO.getMemberImg(request);
//		MemberDAO.MDAO.getMemberHashTag(request);
//		MemberDAO.MDAO.getAdress(request);
		request.setAttribute("content", "member/member.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response); 
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
