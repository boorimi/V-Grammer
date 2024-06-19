package com.vg.bm.Schedule;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vg.jw.AccountDAO;

@WebServlet("/ScheduleDeleteC")
public class ScheduleDeleteC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		AccountDAO.loginCheck(request);
		ScheduleDAO.SDAO.DeleteSchedule(request);
		response.sendRedirect("ScheduleC");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
