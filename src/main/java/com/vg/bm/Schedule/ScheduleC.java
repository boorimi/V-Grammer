package com.vg.bm.Schedule;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vg.jw.AccountDAO;

@WebServlet("/ScheduleC")
public class ScheduleC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AccountDAO.loginCheck(request);

		// 이번주 날짜 가져오는 메서드
		ScheduleDAO.SDAO.getThisWeek(request);
		// 이번주 스케줄 가져오는 메서드
		ScheduleDAO.SDAO.getAllSchedule(request, response);
		request.setAttribute("content", "schedule/schedule.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response); 
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
