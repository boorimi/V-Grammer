package com.vg.sw.dday;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Dday")
public class DdayC extends HttpServlet {
    private DdayDAO ddayDAO;

    public void init() throws ServletException {
        ddayDAO = new DdayDAO(); 
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DdayDTO> ddayList = ddayDAO.selectAllDdays();
        request.setAttribute("ddayList", ddayList); 
        request.getRequestDispatcher("dday/dday.jsp").forward(request, response);
    }
}
