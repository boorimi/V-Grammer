package com.vg.bm.Schedule;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vg.jw.AccountDAO;

@WebServlet("/InsertScheduleC")
public class InsertScheduleC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(AccountDAO.loginCheck(request)) {
			ScheduleDAO.SDAO.insertSchedule(request);
			response.sendRedirect("ScheduleC");
		}else {
			request.getRequestDispatcher("index.jsp").forward(request, response); 
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
